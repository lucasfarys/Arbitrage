package pl.coderslab.app.web.controllers;


import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dto.FavouriteFormDTO;
import pl.coderslab.app.exchange.Exchange;
import pl.coderslab.app.services.CoinService;
import pl.coderslab.app.services.ExchangeService;
import pl.coderslab.app.services.FavouriteService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/restfavourite")
public class FavouriteRestController {
    private Exchange bitbay;
    private Exchange bittrex;
    private FavouriteService favouriteService;
    private ExchangeService exchangeService;
    private CoinService coinService;

    public FavouriteRestController(Exchange bitbay, Exchange bittrex, FavouriteService favouriteService,
                                   ExchangeService exchangeService, CoinService coinService) {
        this.bitbay = bitbay;
        this.bittrex = bittrex;
        this.favouriteService = favouriteService;
        this.exchangeService = exchangeService;
        this.coinService = coinService;
    }

    @PostMapping("/add")
    public void saveFavourite(@RequestParam String exchangeFirst, @RequestParam String exchangeSecond,
                              @RequestParam String coin) {
        FavouriteFormDTO favouriteFormDTO = new FavouriteFormDTO();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        favouriteFormDTO.setLogin(authentication.getName());
        favouriteFormDTO.setExchange_first(exchangeService.getExchangeByName(exchangeFirst).getId());
        favouriteFormDTO.setExchange_second(exchangeService.getExchangeByName(exchangeSecond).getId());
        favouriteFormDTO.setCoin(coinService.getCoinByName(coin));
        favouriteService.saveFavourite(favouriteFormDTO);
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
//        List<String> coinsName = new ArrayList<>();
//        if(!("Giełda 1".equals(exchangeFirst) | "Giełda 2".equals(exchangeSecond))){
//            if("Bitbay".equals(exchangeFirst)){
//                if("Bittrex".equals(exchangeSecond)){
//                    coinsName = searchCoinsName(bitbay,bittrex);
//                }
//            }
//            if("Bittrex".equals(exchangeFirst)){
//                if("Bitbay".equals(exchangeSecond)){
//                    coinsName = searchCoinsName(bitbay,bittrex);
//                }
//            }
//        }
            System.out.println(coinNames);
            jsonObject.put("coinNames", coinNames);
        }
        return jsonObject.toString();

    }
}
//    private List<String> searchCoinsName(Exchange exchangeFirst, Exchange exchangeSecond) {
//        List<String> result = new ArrayList<>();
//        for (String exFirst: exchangeFirst.getCoinsName()){
//            for (String exSecond: exchangeSecond.getCoinsName()){
//                if(exFirst.equals(exSecond)){
//                    result.add(exFirst);
//                }
//            }
//        }
//        return result;
//    }
//}
