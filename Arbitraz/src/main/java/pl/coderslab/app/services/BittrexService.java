package pl.coderslab.app.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.dto.bittrex.DataBittrexDTO;
import pl.coderslab.app.exchange.Exchange;
import pl.coderslab.app.model.exchangeModel.Bittrex;
import pl.coderslab.app.repositories.BittrexRepository;

import java.util.Map;

@Service
@Transactional
public class BittrexService {
    private Exchange exchangeBittrex;
    private BittrexRepository bittrexRepository;

    public BittrexService(Exchange bittrex, BittrexRepository bittrexRepository) {
        this.exchangeBittrex = bittrex;
        this.bittrexRepository = bittrexRepository;
    }

    public void saveDataBittrex(Map<String, DataBittrexDTO> bittrexDTOMap){
        Bittrex bittrex = new Bittrex();
        bittrex.setAskETHBTC(bittrexDTOMap.get("BTC-ETH").getResult().getAsk());
        bittrex.setBidETHBTC(bittrexDTOMap.get("BTC-ETH").getResult().getBid());
        bittrexRepository.save(bittrex);

    }
    @Scheduled(fixedRate = 1000000)
    public void getPricesBittrex(){
        Map<String, DataBittrexDTO> valueBittrexCoins;
        valueBittrexCoins = exchangeBittrex.getAllPriceBittrex(exchangeBittrex.getUrl(), exchangeBittrex.getCoins());
        saveDataBittrex(valueBittrexCoins);
    }
}