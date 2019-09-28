package pl.coderslab.app.exchange;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import pl.coderslab.app.dto.DataBitbayDTO;
import pl.coderslab.app.dto.bittrex.DataBittrexDTO;
import pl.coderslab.app.dto.bittrex.JsonBittrexResult;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Exchange {
    Map<String,String> url;
    Map<String,String> coins;

    public Map<String, String> getUrl() {
        return url;
    }

    public void setUrl(Map<String, String> url) {
        this.url = url;
    }

    public Map<String, String> getCoins() {
        return coins;
    }

    public void setCoins(Map<String, String> coins) {
        this.coins = coins;
    }


    public Map<String,DataBitbayDTO> getAllPriceBitbay(Map<String,String> url, Map<String,String> coins){
        Map<String,DataBitbayDTO> valueCoins = new HashMap<>();
        for (Map.Entry<String, String> coin : coins.entrySet()) {
            final String nameCoin = coin.getValue();
            valueCoins.put(nameCoin, getJsonPriceBitbay(nameCoin,url));

        }
        return valueCoins;
    }
    public Map<String,DataBittrexDTO> getAllPriceBittrex(Map<String,String> url, Map<String,String> coins){
        Map<String,DataBittrexDTO> valueCoins = new HashMap<>();
        for (Map.Entry<String, String> coin : coins.entrySet()) {
            final String nameCoin = coin.getValue();
            valueCoins.put(nameCoin, getJsonPriceBittrex(nameCoin,url));

        }
        return valueCoins;
    }

    private DataBitbayDTO getJsonPriceBitbay(String nameCoin, Map<String,String> mapUrl) {


        ObjectMapper mapper = new ObjectMapper();
        DataBitbayDTO obj = new DataBitbayDTO();
        String completeUrl = mapUrl.get("prefix") + nameCoin + mapUrl.get("suffix");
        try {
            obj = mapper.readValue(new URL(completeUrl), DataBitbayDTO.class);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return obj;
    }

    private DataBittrexDTO getJsonPriceBittrex(String nameCoin, Map<String,String> mapUrl) {


        DataBittrexDTO obj = new DataBittrexDTO();
        String completeUrl = mapUrl.get("prefix") + nameCoin + mapUrl.get("suffix");
        SimpleModule module;
        module = new SimpleModule("BittrexDeserializer", new Version(3, 1, 8, null, null, null));
        module.addDeserializer(JsonBittrexResult.class, new BittrexDeserializer(JsonBittrexResult.class));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        try {
            obj = mapper.readValue(new URL(completeUrl), DataBittrexDTO.class);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return obj;
    }


}