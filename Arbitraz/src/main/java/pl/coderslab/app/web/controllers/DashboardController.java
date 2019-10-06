package pl.coderslab.app.web.controllers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dto.DataCoinDTO;
import pl.coderslab.app.exchange.Exchange;
import pl.coderslab.app.model.DataCoin;
import pl.coderslab.app.model.Favourite;
import pl.coderslab.app.repositories.BitbayRepository;
import pl.coderslab.app.repositories.BittrexRepository;
import pl.coderslab.app.repositories.ExchangeRepository;
import pl.coderslab.app.repositories.FavouriteRepository;
import pl.coderslab.app.services.DataCoinService;
import pl.coderslab.app.services.FavouriteService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private ExchangeRepository exchangeRepository;
    private DataCoinService dataCoinService;
    private BitbayRepository bitbayRepository;
    private BittrexRepository bittrexRepository;
    private FavouriteService favouriteService;
    private FavouriteRepository favouriteRepository;
    private Exchange bitbay;
    private Exchange bittrex;

    public DashboardController(ExchangeRepository exchangeRepository, DataCoinService dataCoinService, BitbayRepository bitbayRepository, BittrexRepository bittrexRepository,
                               Exchange bitbay, Exchange bittrex, FavouriteService favouriteService,
                               FavouriteRepository favouriteRepository) {
        this.exchangeRepository = exchangeRepository;
        this.dataCoinService = dataCoinService;
        this.bitbayRepository = bitbayRepository;
        this.bittrexRepository = bittrexRepository;
        this.favouriteService = favouriteService;
        this.favouriteRepository = favouriteRepository;
        this.bitbay = bitbay;
        this.bittrex = bittrex;
    }

    @GetMapping
    public String prepareDashboard(Model model) {
        List<Favourite> favouritesFromDB = favouriteRepository.findAllByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        List<String> favourites = new ArrayList<>();
        for(Favourite el: favouritesFromDB){
            favourites.add(el.getExchange_first() + " | " + el.getExchange_second() + " | " + el.getCoin());
        }
        model.addAttribute("favourites", favourites);
        return "dashboard";
    }





}