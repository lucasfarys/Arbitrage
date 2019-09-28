package pl.coderslab.app.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.dto.DataBitbayDTO;
import pl.coderslab.app.exchange.Exchange;
import pl.coderslab.app.model.exchangeModel.Bitbay;
import pl.coderslab.app.repositories.BitbayRepository;

import java.util.Map;

@Service
@Transactional
public class BitbayService {
    private BitbayRepository bitbayRepository;
    private Exchange bitbayExchange;
    public BitbayService(Exchange bitbay, BitbayRepository bitbayRepository) {
        this.bitbayExchange = bitbay;
        this.bitbayRepository = bitbayRepository;
    }

    public void saveDataBitbay(Map<String,DataBitbayDTO> bitbayDTOMap){
        Bitbay bitbay = new Bitbay();
        bitbay.setAskBTCPLN(bitbayDTOMap.get("BTCPLN").getAsk());
        bitbay.setBidBTCPLN(bitbayDTOMap.get("BTCPLN").getBid());

        bitbay.setAskBTCETH(bitbayDTOMap.get("ETHBTC").getAsk());
        bitbay.setBidBTCETH(bitbayDTOMap.get("ETHBTC").getBid());

        bitbay.setAskETHPLN(bitbayDTOMap.get("ETHPLN").getAsk());
        bitbay.setBidETHPLN(bitbayDTOMap.get("ETHPLN").getBid());
        bitbayRepository.save(bitbay);
    }
    @Scheduled(fixedRate = 1000000)
    public void getPrices(){
        Map<String, DataBitbayDTO> valueBitbayCoins;
        valueBitbayCoins = bitbayExchange.getAllPriceBitbay(bitbayExchange.getUrl(), bitbayExchange.getCoins());
        saveDataBitbay(valueBitbayCoins);
        System.out.println("Test2");
    }

}