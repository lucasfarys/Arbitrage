package pl.coderslab.app.web.controllers;

import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.app.model.DataCoin;
import pl.coderslab.app.model.Exchange;
import pl.coderslab.app.model.Favourite;
import pl.coderslab.app.model.exchangeModel.Bitbay;
import pl.coderslab.app.model.exchangeModel.Bittrex;
import pl.coderslab.app.repositories.BitbayRepository;
import pl.coderslab.app.repositories.BittrexRepository;
import pl.coderslab.app.services.DataCoinService;
import pl.coderslab.app.services.ExchangeService;
import pl.coderslab.app.services.FavouriteService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/restchart")
public class ChartRestController <T>{
    private BitbayRepository bitbayRepository;
    private BittrexRepository bittrexRepository;

    private DataCoinService dataCoinService;
    private ExchangeService exchangeService;
    private FavouriteService favouriteService;

    public ChartRestController(BitbayRepository bitbayRepository, BittrexRepository bittrexRepository,
                               DataCoinService dataCoinService, ExchangeService exchangeService,
                               FavouriteService favouriteService) {
        this.bitbayRepository = bitbayRepository;
        this.bittrexRepository = bittrexRepository;
        this.dataCoinService = dataCoinService;
        this.exchangeService = exchangeService;
        this.favouriteService = favouriteService;
    }

    @GetMapping
    public String showChart(){
        JSONObject json = new JSONObject();

        Favourite lastAddedFavourite = favouriteService.findLastAddedFavourite(
                SecurityContextHolder.getContext().getAuthentication().getName());

        List<DataCoin> dataCoinsFirst = dataCoinService.getFirst24DataCoinByExchangeIdAndCoinId(
                lastAddedFavourite.getExchangeFirst().getId(),lastAddedFavourite.getCoin().getId());
        List<DataCoin> dataCoinsSecond = dataCoinService.getFirst24DataCoinByExchangeIdAndCoinId(
                lastAddedFavourite.getExchangeSecond().getId(),lastAddedFavourite.getCoin().getId());

        Double[] dataFirst = new Double[dataCoinsFirst.size()];
        Double[] dataSecond = new Double[dataCoinsSecond.size()];
        Double[] dataDifference = new Double[dataCoinsFirst.size()];
        Integer[] date = new Integer[dataCoinsFirst.size()];

        for (int i = 0; i < dataCoinsFirst.size(); i++) {
            dataFirst[i] = dataCoinsFirst.get(i).getAsk();
            date[i] = dataCoinsFirst.get(i).getCreated().getHour();
        }
        for (int i = 0; i < dataCoinsSecond.size(); i++) {
            dataSecond[i] = dataCoinsSecond.get(i).getAsk();
        }
        for (int i = 0; i < dataDifference.length; i++) {
            dataDifference[i] = dataFirst[i] - dataSecond[i];
        }
        json.put("chartFirst",dataFirst);
        json.put("chartSecond",dataSecond);
        json.put("date",date);
        json.put("nameFirst", lastAddedFavourite.getExchangeFirst().getName());
        json.put("nameSecond", lastAddedFavourite.getExchangeSecond().getName());
        json.put("chartDifference",dataDifference);
        System.out.println(dataCoinsFirst.get(0).getAsk());
        //Exchange exchangeFirst = exchangeService.getExchangeByName("Bitbay");


        return json.toString();
//        return getJson("Bitbay","Bittrex","BTCETH");
    }
    @GetMapping("/{exchangeSelected}")
    public String showChart(@PathVariable String exchangeSelected){
        String[] exchangeSel= exchangeSelected.replace(" ", "").split("\\|");
        String exchangeFirst = exchangeSel[0];
        String exchangeSecond = exchangeSel[1];
        String coin = exchangeSel[2];
        return getJson(exchangeFirst,exchangeSecond,coin);
    }
    public String getJson(String exchangeFirst,String exchangeSecond, String coin){
        Double[] chartFirstData = getAskExchangeModelFromDB(exchangeFirst, coin);
        Double[] chartSecondData = getBidExchangeModelFromDB(exchangeSecond, coin);
        Double[] chartDifferenceData = differenceData(chartFirstData,chartSecondData);

        JSONObject jsonObjectValue = new JSONObject();
        jsonObjectValue.put("chartFirst",chartFirstData);
        jsonObjectValue.put("chartSecond",chartSecondData);
        jsonObjectValue.put("chartDifference",chartDifferenceData);
        jsonObjectValue.put("date",getDate(exchangeFirst));
        jsonObjectValue.put("nameFirst", exchangeFirst);
        jsonObjectValue.put("nameSecond", exchangeSecond);
        return jsonObjectValue.toString();
    }

    private Double[] differenceData(Double[] chartFirstData, Double[] chartSecondData) {
        Double[] result = new Double[chartFirstData.length];
        for (int i = 0; i < chartFirstData.length; i++) {
            result[i] = (chartFirstData[i]/chartSecondData[i])-1;
        }
        return result;
    }

    public Double[] getAskExchangeModelFromDB(String exchangeName, String coin){
        if("Bitbay".equalsIgnoreCase(exchangeName)){
            List<Bitbay> exchangeList = bitbayRepository.findFirst24ByOrderByIdDesc();
            Collections.reverse(exchangeList);
            Double[] coinValue = new Double[exchangeList.size()];
            for(int i=0;i<exchangeList.size();i++){
                if("BTCPLN".equals(coin)){
                    coinValue[i] = exchangeList.get(i).getAskBTCPLN();
                } else if ("BTCETH".equals(coin)){
                    coinValue[i] = exchangeList.get(i).getAskETHBTC();
                } else if("ETHPLN".equals(coin)){
                    coinValue[i] = exchangeList.get(i).getAskETHPLN();
                }
            }
            return coinValue;
        }
        if("Bittrex".equalsIgnoreCase(exchangeName)){
            List<Bittrex> exchangeList = bittrexRepository.findFirst24ByOrderByDate();
            Collections.reverse(exchangeList);
            Double[] coinValue = new Double[exchangeList.size()];
            for(int i=0; i<exchangeList.size(); i++){
                if ("BTCETH".equals(coin)){
                    coinValue[i] = exchangeList.get(i).getAskETHBTC();
                }
            }
            return coinValue;
        }
         return null;
    }
    public Double[] getBidExchangeModelFromDB(String exchangeName, String coin){
        if("Bitbay".equalsIgnoreCase(exchangeName)){
            List<Bitbay> exchangeList = bitbayRepository.findFirst24ByOrderByIdDesc();
            Collections.reverse(exchangeList);
            Double[] coinValue = new Double[exchangeList.size()];
            for(int i=0; i<exchangeList.size();i++){
                if("BTCPLN".equals(coin)){
                    coinValue[i] = exchangeList.get(i).getBidBTCPLN();
                } else if ("BTCETH".equals(coin)){
                    coinValue[i] = exchangeList.get(i).getBidETHBTC();
                } else if("ETHPLN".equals(coin)){
                    coinValue[i] = exchangeList.get(i).getBidETHPLN();
                }
            }
            return coinValue;
        }
        if("Bittrex".equalsIgnoreCase(exchangeName)){
            List<Bittrex> exchangeList = bittrexRepository.findFirst24ByOrderByDate();
            Collections.reverse(exchangeList);
            Double[] coinValue = new Double[exchangeList.size()];
            for(int i = 0; i<exchangeList.size();i++){
                if ("BTCETH".equals(coin)){
                    coinValue[i] = exchangeList.get(i).getBidETHBTC();
                }
            }
            return coinValue;
        }
        return null;
    }
    public String[] getDate(String exchangeName){
        if("Bitbay".equalsIgnoreCase(exchangeName)){
            List<Bitbay> exchangeList = bitbayRepository.findFirst24ByOrderByIdDesc();
            Collections.reverse(exchangeList);
            String[] date = new String[exchangeList.size()];
            for(int i=0; i<exchangeList.size(); i++){
                date[i] = exchangeList.get(i).getDate().split("T")[1].substring(0,2);
            }
            return date;
        }
        if("Bittrex".equalsIgnoreCase(exchangeName)){
            List<Bittrex> exchangeList = bittrexRepository.findFirst24ByOrderByDate();
            Collections.reverse(exchangeList);
            String[] date = new String[exchangeList.size()];
            for(int i=0; i<exchangeList.size(); i++){
                date[i] = exchangeList.get(i).getDate().split("T")[1].substring(0,2);
                return date;
            }
        }
        return null;
    }
}
