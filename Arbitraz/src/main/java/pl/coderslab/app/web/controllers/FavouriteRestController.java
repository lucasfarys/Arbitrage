package pl.coderslab.app.web.controllers;


import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dto.FavouriteFormDTO;
import pl.coderslab.app.model.Exchange;
import pl.coderslab.app.model.ExchangeCoin;
import pl.coderslab.app.repositories.ExchangeCoinRepository;
import pl.coderslab.app.services.CoinService;
import pl.coderslab.app.services.DataService;
import pl.coderslab.app.services.ExchangeService;
import pl.coderslab.app.services.FavouriteService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/restfavourite")
public class FavouriteRestController {
    private FavouriteService favouriteService;
    private ExchangeService exchangeService;
    private CoinService coinService;
    private DataService dataService;
    private ExchangeCoinRepository exchangeCoinRepository;

    public FavouriteRestController(FavouriteService favouriteService,ExchangeService exchangeService,
                                   CoinService coinService, DataService dataService,
                                   ExchangeCoinRepository exchangeCoinRepository) {
        this.favouriteService = favouriteService;
        this.exchangeService = exchangeService;
        this.coinService = coinService;
        this.dataService = dataService;
        this.exchangeCoinRepository = exchangeCoinRepository;
    }

    @PostMapping("/add")
    public Long saveFavourite(@RequestParam String exchangeFirst, @RequestParam String exchangeSecond,
                              @RequestParam String coin) {
        Long id = 0L;
        if(!("Giełda 1".equals(exchangeFirst) | "Giełda 2".equals(exchangeSecond)| "Kurs".equals(coin))) {
            FavouriteFormDTO favouriteFormDTO = new FavouriteFormDTO();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            favouriteFormDTO.setLogin(authentication.getName());
            favouriteFormDTO.setExchangeFirst(exchangeService.getExchangeByName(exchangeFirst));
            favouriteFormDTO.setExchangeSecond(exchangeService.getExchangeByName(exchangeSecond));
            favouriteFormDTO.setCoin(coinService.getCoinByName(coin));
            favouriteService.saveFavourite(favouriteFormDTO);
            id = favouriteService.findLastAddedFavourite(authentication.getName()).getId();
        }
        return id;
    }

    @GetMapping("/select/{exchangeFirst}/{exchangeSecond}")
    public String getCoinsName(@PathVariable String exchangeFirst, @PathVariable String exchangeSecond) {
        JSONObject jsonObject = new JSONObject();
        List<String> coinNames = new ArrayList<>();
        if (!("Giełda 1".equals(exchangeFirst) | "Giełda 2".equals(exchangeSecond))) {
            List<String> coinNamesFirst = new ArrayList<>();
            List<String> coinNamesSecond = new ArrayList<>();
            exchangeService.getExchangeByName(exchangeFirst).getExchangeCoins().
                    forEach(e ->coinNamesFirst.add(e.getCoin().getCoinName()));
            exchangeService.getExchangeByName(exchangeSecond).getExchangeCoins().
                    forEach(e ->coinNamesSecond.add(e.getCoin().getCoinName()));

            for(String coinNameFirst:coinNamesFirst){
                for(String coinNameSecond:coinNamesSecond){
                    if(coinNameFirst.equals(coinNameSecond)){
                        coinNames.add(coinNameFirst);
                    }
                }
            }
            jsonObject.put("coinNames", coinNames);
        }
        return jsonObject.toString();
    }
    @PostMapping("/addNewUniqueCoin")
    public String addNewUniqueCoin(@RequestParam String coinName, @RequestParam Long exchangeId,
                             @RequestParam Long coinId){
        JSONObject jsonObject = dataService.getJson(coinName.toUpperCase(),exchangeService.getExchangeById(exchangeId));
            if((1==exchangeId & jsonObject.isNull("message")) |
                (2==exchangeId & !jsonObject.isNull("result")) |
                (3==exchangeId & !jsonObject.isNull("timestamp")) |
                (4==exchangeId & !jsonObject.isNull("symbol"))) {
                System.out.println(jsonObject);
                ExchangeCoin exchangeCoin = new ExchangeCoin();
                exchangeCoin.setUniqueName(coinName.toUpperCase());
                exchangeCoin.setCoin(coinService.getCoinById(coinId));
                exchangeCoin.setExchange(exchangeService.getExchangeById(exchangeId));
                exchangeCoinRepository.save(exchangeCoin);
                return coinName.toUpperCase();
            }
            JSONObject json = new JSONObject();
            json.put("coinName",coinName.toUpperCase());
            return "false";
    }
    @GetMapping("/getCoins")
    public String getExchangeCoinsByExchange(@RequestParam Long id){
        JSONObject jsonObject = new JSONObject();
        Exchange exchange = exchangeService.getExchangeById(id);
        List<String> coinsName = new ArrayList<>();
        exchange.getExchangeCoins().forEach(e->coinsName.add(e.getCoin().getCoinName()));
        jsonObject.put("coinsName",coinsName);
        return jsonObject.toString();
    }
    @PostMapping("/addNewCoin")
    public void addNewCoin(@RequestParam String name){
        if(name!=null){
            coinService.addNewCoin(name);
        }
    }
}