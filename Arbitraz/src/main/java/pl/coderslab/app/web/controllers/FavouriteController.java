package pl.coderslab.app.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dto.CoinsListDTO;
import pl.coderslab.app.model.*;
import pl.coderslab.app.services.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()
public class FavouriteController {
    private FavouriteService favouriteService;
    private ExchangeService exchangeService;
    private CoinService coinService;
    private UserService userService;
    private DataCoinService dataCoinService;

    public FavouriteController( FavouriteService favouriteService, ExchangeService exchangeService,
                                CoinService coinService, UserService userService,
                                DataCoinService dataCoinService) {
        this.favouriteService = favouriteService;
        this.coinService = coinService;
        this.exchangeService = exchangeService;
        this.userService = userService;
        this.dataCoinService = dataCoinService;
    }

    @GetMapping("/addfavourite")
    public String prepareFavourite( Model model){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(principal.getName());
        List<Favourite> favourites = favouriteService.fiandAllFavouritesByLogin(principal.getName());
        model.addAttribute("exchange", exchangeService.getExchanges());
        model.addAttribute("coins",coinService.findAllCoins());
        model.addAttribute("principal",principal);
        model.addAttribute("name",user.getFirstName());
        model.addAttribute("favouriteCoinList",favourites);
        return "addFavourite";
    }
    @GetMapping("/delfavourite/{id}")
    public String deleteFavourite(@PathVariable Long id){
        favouriteService.deleteFavouriteById(id);
        return "redirect:/addfavourite";
    }
    @GetMapping("/addNewCoin")
    public String prepareAddNewCoin(Model model){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(principal.getName());
        List<Exchange> exchanges = exchangeService.getExchanges();
        List<Coin> coins = coinService.findAllCoins();

        model.addAttribute("principal",principal);
        model.addAttribute("name",user.getFirstName());
        model.addAttribute("exchanges",exchanges);
        model.addAttribute("coins",coins);
        return "addNewCoin";
    }
    @GetMapping("/compareAllCoins")
    public String prepareCompareAllCoins(Model model){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(principal.getName());
        List<Exchange> exchanges = exchangeService.getExchanges();
        List<CoinsListDTO> coinList = dataCoinService.getCoins(exchanges);
        model.addAttribute("principal",principal);
        model.addAttribute("name",user.getFirstName());
        model.addAttribute("coinList",coinList);
        return "compareAllCoins";
    }
}
