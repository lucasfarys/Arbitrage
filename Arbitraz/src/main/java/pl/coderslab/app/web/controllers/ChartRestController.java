package pl.coderslab.app.web.controllers;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.app.model.exchangeModel.Bitbay;
import pl.coderslab.app.model.exchangeModel.Bittrex;
import pl.coderslab.app.repositories.BitbayRepository;
import pl.coderslab.app.repositories.BittrexRepository;

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
        return getJson("Bitbay","Bittrex","BTCETH");
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

        JSONObject jsonObjectValue = new JSONObject();
        jsonObjectValue.put("chartFirst",chartFirstData);
        jsonObjectValue.put("chartSecond",chartSecondData);
        jsonObjectValue.put("date",getDate(exchangeFirst));
        jsonObjectValue.put("nameFirst", exchangeFirst);
        jsonObjectValue.put("nameSecond", exchangeSecond);
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
