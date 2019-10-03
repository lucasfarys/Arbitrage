package pl.coderslab.app.web.controllers;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.app.model.exchangeModel.Bitbay;
import pl.coderslab.app.model.exchangeModel.Bittrex;
import pl.coderslab.app.repositories.BitbayRepository;
import pl.coderslab.app.repositories.BittrexRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/restchart")
public class ChartRestController <T>{
    private BitbayRepository bitbayRepository;
    private BittrexRepository bittrexRepository;

    public ChartRestController(BitbayRepository bitbayRepository, BittrexRepository bittrexRepository) {
        this.bitbayRepository = bitbayRepository;
        this.bittrexRepository = bittrexRepository;
    }

    @GetMapping
    public String showChart(){
        Double[] chartFirst = getAskExchangeModelFromDB("Bitbay", "BTCETH");
        Double[] chartSecond = getBidExchangeModelFromDB("Bittrex", "BTCETH");
        List<String> names = new ArrayList<>();
        names.add("Bitbay");
        names.add("Bittrex");
//        List<Bitbay> bitbayList = bitbayRepository.findFirst24ByOrderByDate();
//        chartFirst = chartFirst.subList(chartFirst.size()-24,chartFirst.size());
//        Double[] btcPln = new Double[chartFirst.size()];
//        String[] btcPlnDate = new String[chartFirst.size()];
        JSONObject jsonObjectValue = new JSONObject();
//        for(int i=0;i<exchangeFirst.size();i++){
//            btcPln[i] = exchangeFirst.get(i).getAskBTCPLN();
//            btcPlnDate[i] = bitbayList.get(i).getDate().split("T")[1].substring(0,2);
//        }
        jsonObjectValue.put("chartFirst",chartFirst);
        jsonObjectValue.put("chartSecond",chartSecond);
        jsonObjectValue.put("date",getDate("Bitbay"));
        jsonObjectValue.put("nameFirst", "Bitbay");
        jsonObjectValue.put("nameSecond", "Bittrex");
        return jsonObjectValue.toString();
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
