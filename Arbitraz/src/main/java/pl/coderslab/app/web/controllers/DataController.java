package pl.coderslab.app.web.controllers;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import pl.coderslab.app.dto.DataCoinDTO;
import pl.coderslab.app.model.*;
import pl.coderslab.app.services.DataCoinService;
import pl.coderslab.app.services.ExchangeService;
import pl.coderslab.app.services.FavouriteService;

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
    FavouriteService favouriteService;

    public DataController(ExchangeService exchangeService, DataCoinService dataCoinService,
                          FavouriteService favouriteService) {
        this.exchangeService = exchangeService;
        this.dataCoinService = dataCoinService;
        this.favouriteService = favouriteService;
    }


    @Scheduled(fixedRate = 3600000)
    public void getAndSaveData() {
        List<Exchange> exchanges = exchangeService.getExchanges();
        exchanges.forEach(e -> dataCoinService.saveDataCoins(getDataCoins(e)));
        List<Favourite> favourites = favouriteService.finadAll();
        compareCoins(favourites,exchanges);
    }

    private List<DataCoinDTO> getDataCoins(Exchange exchange) {
        List<DataCoinDTO> dataCoinsDTO = new ArrayList<>();
        List<ExchangeCoin> exchangeCoins = exchange.getExchangeCoins();

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

    private void compareCoins(List<Favourite> favourites, List<Exchange> exchanges) {
        DataCoin dataCoinFirst = dataCoinService.getFirstDataCoinByExchangeIdAndCoinId(
                favourites.get(0).getExchangeFirst().getId(),favourites.get(0).getCoin().getId());
        favourites.forEach(f->{
            Double coinValueFirst = dataCoinService.getFirstDataCoinByExchangeIdAndCoinId(f.getExchangeFirst().getId(),
                    f.getCoin().getId()).getAsk();
            Double coinValueSecond = dataCoinService.getFirstDataCoinByExchangeIdAndCoinId(f.getExchangeSecond().getId(),
                    f.getCoin().getId()).getBid();
            if((coinValueFirst/coinValueSecond-1)>2){
                sendEmail();
            }
        });
        System.out.println(dataCoinFirst.getAsk());
    }

    private void sendEmail() {
    }
}