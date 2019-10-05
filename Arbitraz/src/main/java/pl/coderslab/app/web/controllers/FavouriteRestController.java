package pl.coderslab.app.web.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dto.FavouriteFormDTO;
import pl.coderslab.app.exchange.Exchange;
import pl.coderslab.app.repositories.FavouriteRepository;
import pl.coderslab.app.services.FavouriteService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/restfavourite")
public class FavouriteRestController {
    private Exchange bitbay;
    private Exchange bittrex;
    private FavouriteService favouriteService;

    public FavouriteRestController(Exchange bitbay, Exchange bittrex, FavouriteService favouriteService) {
        this.bitbay = bitbay;
        this.bittrex = bittrex;
        this.favouriteService = favouriteService;
    }
    @PostMapping("/add")
    public void saveFavourite(@RequestParam String exchangeFirst, @RequestParam String exchangeSecond,
                              @RequestParam String coin){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        FavouriteFormDTO favouriteFormDTO = new FavouriteFormDTO();
        favouriteFormDTO.setCoin(coin);
        favouriteFormDTO.setExchange_first(exchangeFirst);
        favouriteFormDTO.setExchange_second(exchangeSecond);
        favouriteFormDTO.setLogin(authentication.getName());
        favouriteService.saveFavourite(favouriteFormDTO);
    }
    @GetMapping("/select/{exchangeFirst}/{exchangeSecond}")
    public String getCoinsName(@PathVariable String exchangeFirst, @PathVariable String exchangeSecond){
        JSONObject jsonObject = new JSONObject();
        List<String> coinsName = new ArrayList<>();
        if(!("Giełda 1".equals(exchangeFirst) | "Giełda 2".equals(exchangeSecond))){
            if("Bitbay".equals(exchangeFirst)){
                if("Bittrex".equals(exchangeSecond)){
                    coinsName = searchCoinsName(bitbay,bittrex);
                }
            }
            if("Bittrex".equals(exchangeFirst)){
                if("Bitbay".equals(exchangeSecond)){
                    coinsName = searchCoinsName(bitbay,bittrex);
                }
            }
        }
        jsonObject.put("coinsName", coinsName);
        return jsonObject.toString();
    }

    private List<String> searchCoinsName(Exchange exchangeFirst, Exchange exchangeSecond) {
        List<String> result = new ArrayList<>();
        for (String exFirst: exchangeFirst.getCoinsName()){
            for (String exSecond: exchangeSecond.getCoinsName()){
                if(exFirst.equals(exSecond)){
                    result.add(exFirst);
                }
            }
        }
        return result;
    }
}
