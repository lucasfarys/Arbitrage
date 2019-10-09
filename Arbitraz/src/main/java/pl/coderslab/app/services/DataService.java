package pl.coderslab.app.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.dto.DataCoinDTO;
import pl.coderslab.app.model.Exchange;
import pl.coderslab.app.model.ExchangeCoin;
import pl.coderslab.app.model.Favourite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {
    private ExchangeService exchangeService;
    private DataCoinService dataCoinService;
    private FavouriteService favouriteService;

    public DataService(ExchangeService exchangeService, DataCoinService dataCoinService, FavouriteService favouriteService) {
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
        // for Bittrex
        if(jsonObject.isNull("ask")) {
            dataCoinDTO.setAsk(jsonObject.getJSONObject("result").getDouble("Ask"));
            dataCoinDTO.setBid(jsonObject.getJSONObject("result").getDouble("Bid"));
        }
        // for HitBTC
        else if("HitBTC".equals(exchange.getName())){
            dataCoinDTO.setAsk(jsonObject.getDouble("ask"));
            dataCoinDTO.setBid(jsonObject.getDouble("bid"));
        }
        else{
            dataCoinDTO.setAsk(jsonObject.getDouble("ask"));
            dataCoinDTO.setBid(jsonObject.getDouble("bid"));
        }
        return dataCoinDTO;
    }
    public JSONObject getJson (ExchangeCoin ex, Exchange exchange){
        String coinName = ex.getUniqueName();
        BufferedReader reader = null;
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        if("HitBTC".equals(exchange.getName())){
            coinName ="";
        }
        try {
            URL url = new URL(exchange.getAddressUrlPrefix() + coinName +
                    exchange.getAddressUrlSuffix());
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);


            // for HitBTC
            if(exchange.getName().equals("HitBTC")){
                jsonArray = new JSONArray(buffer.toString());
                for (int i = 0; i < jsonArray.length() - 1; i++) {
                    if(jsonArray.getJSONObject(i).getString("symbol").equals(ex.getUniqueName())){
                        jsonObject = jsonArray.getJSONObject(i);
                    }
                }
            }else{
                jsonObject = new JSONObject(buffer.toString());
            }
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
        favourites.forEach(f->{
            Double coinValueFirst = dataCoinService.getFirstDataCoinByExchangeIdAndCoinId(f.getExchangeFirst().getId(),
                    f.getCoin().getId()).getAsk();
            Double coinValueSecond = dataCoinService.getFirstDataCoinByExchangeIdAndCoinId(f.getExchangeSecond().getId(),
                    f.getCoin().getId()).getBid();
            if((coinValueFirst/coinValueSecond-1)>2){
                sendEmail();
            }
        });
    }


    private void sendEmail() {
        System.out.println("send email");
    }
}
