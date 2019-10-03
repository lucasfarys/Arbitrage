package pl.coderslab.app.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.app.dto.DataBitbayDTO;
import pl.coderslab.app.exchange.Exchange;
import pl.coderslab.app.model.exchangeModel.Bitbay;
import pl.coderslab.app.repositories.BitbayRepository;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class BitbayService {
    private BitbayRepository bitbayRepository;
    private Exchange bitbayExchange;
    public BitbayService(Exchange bitbay, BitbayRepository bitbayRepository) {
        this.bitbayRepository = bitbayRepository;
        this.bitbayExchange = bitbay;
    }

    public void saveDataBitbay(Map<String,DataBitbayDTO> bitbayDTOMap){
        Bitbay bitbay = new Bitbay();

        bitbay.setAskBTCPLN(bitbayDTOMap.get("BTCPLN").getAsk());
        bitbay.setBidBTCPLN(bitbayDTOMap.get("BTCPLN").getBid());

        bitbay.setAskETHBTC(bitbayDTOMap.get("ETHBTC").getAsk());
        bitbay.setBidBTCETH(bitbayDTOMap.get("ETHBTC").getBid());

        bitbay.setAskETHPLN(bitbayDTOMap.get("ETHPLN").getAsk());
        bitbay.setBidETHPLN(bitbayDTOMap.get("ETHPLN").getBid());

        bitbay.setDate(LocalDateTime.now().toString());

        bitbayRepository.save(bitbay);
    }


}