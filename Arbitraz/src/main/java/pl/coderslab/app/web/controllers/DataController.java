package pl.coderslab.app.web.controllers;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import pl.coderslab.app.dto.DataCoinDTO;
import pl.coderslab.app.model.Exchange;
import pl.coderslab.app.model.ExchangeCoin;
import pl.coderslab.app.services.DataCoinService;
import pl.coderslab.app.services.ExchangeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DataController {
    private ExchangeService exchangeService;
    private DataCoinService dataCoinService;

    public DataController(ExchangeService exchangeService, DataCoinService dataCoinService) {
        this.exchangeService = exchangeService;
        this.dataCoinService = dataCoinService;
    }


    @Scheduled(fixedRate = 3600000)
    public void getAndSaveData() {
        List<Exchange> exchanges = exchangeService.getExchanges();
        exchanges.forEach(e -> dataCoinService.saveDataCoins(getDataCoins(e)));

    }

    private List<DataCoinDTO> getDataCoins(Exchange exchange) {
        List<DataCoinDTO> dataCoinsDTO = new ArrayList<>();
        List<ExchangeCoin> exchangeCoins = exchange.getExchangeCoins();
        System.out.println("zrobione");

        for (ExchangeCoin ex : exchangeCoins) {
            dataCoinsDTO.add(getData(ex, exchange));
        }
        return dataCoinsDTO;
    }

    private DataCoinDTO getData(ExchangeCoin ex, Exchange exchange) {
        DataCoinDTO dataCoinDTO = new DataCoinDTO();
        JSONObject jsonObject = getJson(ex,exchange);
        dataCoinDTO.setExchangeCoin(ex);
        if(jsonObject.isNull("ask")) {
            dataCoinDTO.setAsk(jsonObject.getJSONObject("result").getDouble("Ask"));
            dataCoinDTO.setBid(jsonObject.getJSONObject("result").getDouble("Bid"));
        }else{
            dataCoinDTO.setAsk(jsonObject.getDouble("ask"));
            dataCoinDTO.setBid(jsonObject.getDouble("bid"));
        }
        return dataCoinDTO;
    }
    public JSONObject getJson (ExchangeCoin ex, Exchange exchange){
        BufferedReader reader = null;
        JSONObject jsonObject = new JSONObject();
        try {
            URL url = new URL(exchange.getAddressUrlPrefix() + ex.getUniqueName() +
                    exchange.getAddressUrlSuffix());
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);
            jsonObject = new JSONObject(buffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }
}