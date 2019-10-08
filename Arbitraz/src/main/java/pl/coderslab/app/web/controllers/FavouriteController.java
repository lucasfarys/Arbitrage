package pl.coderslab.app.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.model.Favourite;
import pl.coderslab.app.services.CoinService;
import pl.coderslab.app.services.ExchangeService;
import pl.coderslab.app.services.FavouriteService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
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

    @GetMapping("/addfavourite")
    public String prepareFavourite( Model model){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        List<Favourite> favourites = favouriteService.fiandAllFavouritesByLogin(principal.getName());
//        List<String> favouriteCoinList = new ArrayList<>();
//        favourites.forEach(f->{
//            favouriteCoinList.add(f.getExchangeFirst().getName() + " | " + f.getExchangeSecond().getName() +
//                                    " | " + f.getCoin().getCoinName());
//        });

        model.addAttribute("exchange", exchangeService.getExchanges());
        model.addAttribute("coins",coinService.findAllCoins());
        model.addAttribute("principal",principal);
        model.addAttribute("favouriteCoinList",favourites);
        return "addFavourite";
    }
    @GetMapping("/delfavourite/{id}")
    public String deleteFavourite(@PathVariable Long id){
        System.out.println(id);
        favouriteService.deleteFavouriteById(id);
        return "redirect:/dashboard/addfavourite";
    }
}
