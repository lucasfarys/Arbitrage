package pl.coderslab.app.services;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.app.dto.DataBitbayDTO;
import pl.coderslab.app.dto.bittrex.DataBittrexDTO;
import pl.coderslab.app.exchange.Exchange;
import pl.coderslab.app.model.Favourite;
import pl.coderslab.app.repositories.BitbayRepository;
import pl.coderslab.app.repositories.FavouriteRepository;

import java.util.List;
import java.util.Map;

@Service
public class MainService {
    private FavouriteRepository favouriteRepository;
    private Exchange bitbayExchange;
    private Exchange bittrexExchange;
    private BitbayRepository bitbayRepository;
    private BitbayService bitbayService;
    private BittrexService bittrexService;


    public MainService(FavouriteRepository favouriteRepository, Exchange bitbay, Exchange bittrex,
                       BitbayRepository bitbayRepository, BitbayService bitbayService, BittrexService bittrexService) {
        this.favouriteRepository = favouriteRepository;
        this.bitbayExchange = bitbay;
        this.bittrexExchange = bittrex;
        this.bitbayRepository = bitbayRepository;
        this.bitbayService = bitbayService;
        this.bittrexService = bittrexService;
    }

    @Scheduled(fixedRate = 3600000)
    public void getPrices(){
        Map<String, DataBitbayDTO> valueBitbayCoins;

        valueBitbayCoins = bitbayExchange.getAllPriceBitbay(bitbayExchange.getUrl(), bitbayExchange.getCoins());
        bitbayService.saveDataBitbay(valueBitbayCoins);

        Map<String, DataBittrexDTO> valueBittrexCoins;
        valueBittrexCoins = bittrexExchange.getAllPriceBittrex(bittrexExchange.getUrl(), bittrexExchange.getCoins());
        bittrexService.saveDataBittrex(valueBittrexCoins);

        calculateDifferenceBetweenCoins(valueBitbayCoins,valueBittrexCoins);
    }

    private void calculateDifferenceBetweenCoins(Map<String, DataBitbayDTO> valueBitbayCoins,
                                                 Map<String,DataBittrexDTO> valueBittrexCoins) {
        List<Favourite> favourites = favouriteRepository.findAll();
        Double firstValue = 0.0;
        Double secondValue = 0.0;
        for(Favourite el: favourites){
            if("Bitbay".equals(el.getExchange_first())){
                firstValue = valueBitbayCoins.get(bitbayExchange.getCoins().get(el.getCoin())).getAsk();
            } else if("Bittrex".equals(el.getExchange_first())){
                firstValue = valueBittrexCoins.get(bittrexExchange.getCoins().get(el.getCoin())).getResult().getAsk();
            }

            if("Bitbay".equals(el.getExchange_second())){
                secondValue = valueBitbayCoins.get(bitbayExchange.getCoins().get(el.getCoin())).getBid();
            } else if("Bittrex".equals(el.getExchange_second())){
                secondValue = valueBittrexCoins.get(bittrexExchange.getCoins().get(el.getCoin())).getResult().getBid();
            }
        }
        Double difference = firstValue-secondValue;
        Double differenceProcent = (firstValue/secondValue)-1;
        // if difference >= 2%
        if(differenceProcent >= 2){
            sendEmail();
        }
    }

    private void sendEmail() {
        System.out.println("send Email");
    }
}
