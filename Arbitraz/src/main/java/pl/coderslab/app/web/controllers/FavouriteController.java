package pl.coderslab.app.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.model.Favourite;
import pl.coderslab.app.services.CoinService;
import pl.coderslab.app.services.ExchangeService;
import pl.coderslab.app.services.FavouriteService;

import java.util.List;

@Controller
@RequestMapping("/dashboard/addfavourite")
public class FavouriteController {
    private FavouriteService favouriteService;
    private ExchangeService exchangeService;
    private CoinService coinService;

    public FavouriteController( FavouriteService favouriteService, ExchangeService exchangeService,
                                CoinService coinService) {
        this.favouriteService = favouriteService;
        this.coinService = coinService;
        this.exchangeService = exchangeService;
    }

    @GetMapping
    public String prepareFavourite( Model model){
        List<Favourite> favourites = favouriteService.fiandAllFavouritesByLogin(
                SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("exchange", exchangeService.getExchanges());
        model.addAttribute("coins",coinService.findAllCoins());
        return "addFavourite";
    }
}
