package com.example.demo.domain.currency.service;

import com.example.demo.config.CurrencyWebSocket;
import com.example.demo.domain.currency.entity.Currency;
import com.example.demo.domain.currency.entity.CurrencyList;
import com.example.demo.domain.currency.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.domain.common.Constants.BNB_URL;


@Service
@RequiredArgsConstructor
public class CurrencyService {

    private CurrencyRepository repository;
    private CurrencyWebSocket currencyWebSocket;
    private final List<WebSocketSession> sessions = new ArrayList<>();

    public boolean downloadAndSaveCurrencies() {
        try {
            URL url = URI.create(BNB_URL).toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            StringBuilder xmlData = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    xmlData.append(line);
                }
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(Currency.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            CurrencyList newestRates = (CurrencyList) unmarshaller.unmarshal(new StringReader(xmlData.toString()));

            List<Currency> newCurrencies = newestRates.getCurrencies();

            List<Currency> latestRates = repository.findAll();

            if (ratesAreDifferent(newCurrencies, latestRates)) {
                repository.deleteAll();
                repository.saveAll(newCurrencies);
            }
            connection.disconnect();
        } catch (IOException | JAXBException e) {
            e.getMessage();
        }
        return false;
    }

    private boolean ratesAreDifferent(List<Currency> newCurrencies, List<Currency> latestCurrencies) {
        if (newCurrencies.size() != latestCurrencies.size()) {
            return true;
        }

        for (Currency newCurrency : newCurrencies) {
            boolean found = false;
            for (Currency latestCurrency : latestCurrencies) {
                if (newCurrency.getCurrencyCode().equals(latestCurrency.getCurrencyCode()) &&
                        Double.compare(newCurrency.getRate(), latestCurrency.getRate()) == 0) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return true;
            }
        }
        return false;
    }

    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    public void notifyCurrencyUpdates(CurrencyList currencyList) {
        try {
            currencyWebSocket.sendCurrencyUpdates(currencyList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected JpaRepository<Currency, Integer> getRepository() {
        return repository;
    }
}
