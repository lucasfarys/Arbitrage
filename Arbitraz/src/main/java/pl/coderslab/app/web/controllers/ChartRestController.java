package pl.coderslab.app.web.controllers;

import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.app.model.Coin;
import pl.coderslab.app.model.Favourite;
import pl.coderslab.app.services.*;

@RestController
@RequestMapping("/restchart")
public class ChartRestController {

    private DataCoinService dataCoinService;
    private ExchangeService exchangeService;
    private FavouriteService favouriteService;
    private CoinService coinService;
    private DataService dataService;

    public ChartRestController(DataCoinService dataCoinService, ExchangeService exchangeService,
                               FavouriteService favouriteService, CoinService coinService,
                               DataService dataService) {
        this.dataCoinService = dataCoinService;
        this.exchangeService = exchangeService;
        this.favouriteService = favouriteService;
        this.coinService = coinService;
        this.dataService = dataService;
    }

    @GetMapping
    public String showChart(){
        Favourite lastAddedFavourite = favouriteService.findLastAddedFavourite(
                SecurityContextHolder.getContext().getAuthentication().getName());

        JSONObject json = dataService.createJson(lastAddedFavourite.getExchangeFirst().getName(),
                lastAddedFavourite.getExchangeSecond().getName(),lastAddedFavourite.getCoin().getId());
        return json.toString();
    }


    @GetMapping("/{exchangeSelected}")
    public String showChart(@PathVariable String exchangeSelected){
        String[] exchangeSel= exchangeSelected.replace(" ", "").split("\\|");
        String exchangeFirst = exchangeSel[0];
        String exchangeSecond = exchangeSel[1];
        String coinName = exchangeSel[2];
        Coin coin = coinService.getCoinByName(coinName);
        return dataService.createJson(exchangeFirst,exchangeSecond,coin.getId()).toString();
    }
}
