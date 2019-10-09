package pl.coderslab.app.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.exchange.Exchange;
import pl.coderslab.app.model.Favourite;
import pl.coderslab.app.model.User;
import pl.coderslab.app.repositories.BitbayRepository;
import pl.coderslab.app.repositories.BittrexRepository;
import pl.coderslab.app.repositories.ExchangeRepository;
import pl.coderslab.app.repositories.FavouriteRepository;
import pl.coderslab.app.services.DataCoinService;
import pl.coderslab.app.services.FavouriteService;
import pl.coderslab.app.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {
//    private ExchangeRepository exchangeRepository;
//    private DataCoinService dataCoinService;
//    private BitbayRepository bitbayRepository;
//    private BittrexRepository bittrexRepository;
    private FavouriteService favouriteService;
    private UserService userService;
//    private FavouriteRepository favouriteRepository;
//    private Exchange bitbay;
//    private Exchange bittrex;

    public DashboardController(ExchangeRepository exchangeRepository, DataCoinService dataCoinService, BitbayRepository bitbayRepository, BittrexRepository bittrexRepository,
                               Exchange bitbay, Exchange bittrex, FavouriteService favouriteService,
                               FavouriteRepository favouriteRepository, UserService userService) {
//        this.exchangeRepository = exchangeRepository;
//        this.dataCoinService = dataCoinService;
//        this.bitbayRepository = bitbayRepository;
//        this.bittrexRepository = bittrexRepository;
        this.favouriteService = favouriteService;
        this.userService = userService;
//        this.favouriteRepository = favouriteRepository;
//        this.bitbay = bitbay;
//        this.bittrex = bittrex;
    }

    @GetMapping
    public String prepareDashboard(Model model) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(principal.getName());
        List<Favourite> favouritesFromDB = favouriteService.fiandAllFavouritesByLogin(principal.getName());
        List<String> favourites = new ArrayList<>();
        for(Favourite el: favouritesFromDB){
            favourites.add(el.getExchangeFirst().getName() + " | " + el.getExchangeSecond().getName() + " | " +
                    el.getCoin().getCoinName());
        }
        model.addAttribute("favourites", favourites);
        model.addAttribute("principal", principal);
        model.addAttribute("name", user.getFirstName());
        return "dashboard";
    }





}