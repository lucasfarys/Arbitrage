package pl.coderslab.app.web.controllers;

import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.app.model.Coin;
import pl.coderslab.app.model.DataCoin;
import pl.coderslab.app.model.Exchange;
import pl.coderslab.app.model.Favourite;
import pl.coderslab.app.services.CoinService;
import pl.coderslab.app.services.DataCoinService;
import pl.coderslab.app.services.ExchangeService;
import pl.coderslab.app.services.FavouriteService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/restchart")
public class ChartRestController {

    private DataCoinService dataCoinService;
    private ExchangeService exchangeService;
    private FavouriteService favouriteService;
    private CoinService coinService;

    public ChartRestController(DataCoinService dataCoinService, ExchangeService exchangeService,
                               FavouriteService favouriteService, CoinService coinService) {
        this.dataCoinService = dataCoinService;
        this.exchangeService = exchangeService;
        this.favouriteService = favouriteService;
        this.coinService = coinService;
    }

    @GetMapping
    public String showChart(){
        Favourite lastAddedFavourite = favouriteService.findLastAddedFavourite(
                SecurityContextHolder.getContext().getAuthentication().getName());

        JSONObject json = getJson(lastAddedFavourite.getExchangeFirst().getName(),
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
        return getJson(exchangeFirst,exchangeSecond,coin.getId()).toString();
    }

    private JSONObject getJson(String exchangeFirstName, String exchangeSecondName, Long coinId) {
        Exchange exchangeFirst = exchangeService.getExchangeByName(exchangeFirstName);
        Exchange exchangeSecond = exchangeService.getExchangeByName(exchangeSecondName);

        JSONObject json = new JSONObject();
        List<DataCoin> dataCoinsFirst = dataCoinService.getFirst24DataCoinByExchangeIdAndCoinId(
                exchangeFirst.getId(),coinId);
        List<DataCoin> dataCoinsSecond = dataCoinService.getFirst24DataCoinByExchangeIdAndCoinId(
                exchangeSecond.getId(),coinId);


        List<Double> dataFirst = new ArrayList<>();
        List<Double> dataSecond = new ArrayList<>();
        List<Double> dataDifference = new ArrayList<>();
        List<Integer> date = new ArrayList<>();

        dataCoinsFirst.forEach(d->{
            dataFirst.add(d.getAsk());
            date.add(d.getCreated().getHour());
        });
        dataCoinsSecond.forEach(d->dataSecond.add(d.getBid()));

        for (int i = 0; i < dataFirst.size(); i++) {
            dataDifference.add(dataFirst.get(i)/dataSecond.get(i)-1);
        }

        Collections.reverse(dataFirst);
        Collections.reverse(dataSecond);
        Collections.reverse(date);
        Collections.reverse(dataDifference);

        json.put("chartFirst",dataFirst);
        json.put("chartSecond",dataSecond);
        json.put("date",date);
        json.put("nameFirst", exchangeFirst.getName());
        json.put("nameSecond", exchangeSecond.getName());
        json.put("chartDifference",dataDifference);
        return json;
    }

}
