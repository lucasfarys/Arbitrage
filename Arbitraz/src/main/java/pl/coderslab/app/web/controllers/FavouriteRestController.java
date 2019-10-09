package pl.coderslab.app.web.controllers;


import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dto.FavouriteFormDTO;
import pl.coderslab.app.services.CoinService;
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

    public FavouriteRestController(FavouriteService favouriteService,
                                   ExchangeService exchangeService, CoinService coinService) {
        this.favouriteService = favouriteService;
        this.exchangeService = exchangeService;
        this.coinService = coinService;
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
            System.out.println(coin);
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
}