package pl.coderslab.app.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.exchange.Exchange;
import pl.coderslab.app.services.FavouriteService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dashboard/addfavourite")
public class FavouriteController {
    private Exchange bitbay;
    private Exchange bittrex;
    private FavouriteService favouriteService;

    public FavouriteController(Exchange bitbay, Exchange bittrex, FavouriteService favouriteService) {
        this.bitbay = bitbay;
        this.bittrex = bittrex;
        this.favouriteService = favouriteService;
    }

    @GetMapping
    public String prepareFavourite( Model model){
        List<Exchange> exchanges = new ArrayList<>();
        List<String> coinsName = new ArrayList<>();
        exchanges.add(bitbay);
        exchanges.add(bittrex);

        for(String el: bitbay.getCoinsName()){
            coinsName.add(el);
        }
        for(String el: bittrex.getCoinsName()){
            coinsName.add(el);
        }
        model.addAttribute("exchange",exchanges);
        model.addAttribute("coinsName",coinsName);
        return "addFavourite";
    }
}
