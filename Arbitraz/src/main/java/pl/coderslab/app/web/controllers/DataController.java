package pl.coderslab.app.web.controllers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import pl.coderslab.app.dto.DataCoinDTO;
import pl.coderslab.app.model.DataCoin;
import pl.coderslab.app.model.Exchange;
import pl.coderslab.app.services.DataCoinService;
import pl.coderslab.app.services.ExchangeService;

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
    public void getAndSaveData(){
        List<Exchange> exchanges = exchangeService.getExchanges();







//        System.out.println(exchanges.get(0).getName());
//        List<DataCoinDTO> dataCoinsDTO = new ArrayList<>();
//        DataCoinDTO dataCoinDTO = new DataCoinDTO();
//        dataCoinDTO.setBid(40.0);
//        dataCoinDTO.setAsk(30.0);
//        System.out.println(exchangeService.getExchangeById(1L).getExchangeCoins().get(0).getExchange().getId());
//        dataCoinDTO.setExchangeCoin(exchangeService.getExchangeById(1L).getExchangeCoins().get(0));
//        dataCoinsDTO.add(dataCoinDTO);
//        dataCoinService.saveDataCoins(dataCoinsDTO);
        System.out.println("done");
    }
}
