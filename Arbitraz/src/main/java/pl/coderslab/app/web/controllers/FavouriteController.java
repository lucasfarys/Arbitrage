package pl.coderslab.app.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        List<DataCoin> dataCoins = dataCoinService.getLastAddedCoins();
        List<Exchange> exchanges = exchangeService.getExchanges();
        List<String> coinNames = new ArrayList<>();
        List<String> exchangeNamesFirst = new ArrayList<>();
        List<String> exchangeNamesSecond = new ArrayList<>();
        List<String> dataFirst = new ArrayList<>();
        List<String> dataSecond = new ArrayList<>();
        List<String> profit = new ArrayList<>();
        for(Exchange exchangeFirst:exchanges){
            for(Exchange exchangeSecond:exchanges){
                if(exchangeFirst.getId()!=exchangeSecond.getId()){
                    for(ExchangeCoin exchangeCoinFirst:exchangeFirst.getExchangeCoins()){
                        for(ExchangeCoin exchangeCoinSecond:exchangeSecond.getExchangeCoins()){
                            if(exchangeCoinFirst.getCoin().getCoinName()==exchangeCoinSecond.getCoin().getCoinName()){
                                coinNames.add(exchangeCoinFirst.getCoin().getCoinName());
                                exchangeNamesFirst.add(exchangeFirst.getName());
                                Double ask = 0.0;
                                Double bid = 0.0;
                                for(DataCoin dataCoin:dataCoins){
                                    if(dataCoin.getExchangeCoin().getId()==exchangeCoinFirst.getId()){
                                        ask = dataCoin.getAsk();
                                        dataFirst.add(String.format("%.8f",ask));
                                    }
                                    if(dataCoin.getExchangeCoin().getId()==exchangeCoinSecond.getId()){
                                        bid = dataCoin.getBid();
                                        dataSecond.add(String.format("%.8f",bid));
                                    }
                                }
                                profit.add(String.format("%.2f",(ask/bid-1)*100));
                                exchangeNamesSecond.add(exchangeSecond.getName());
                            }
                        }
                    }
                }
            }

        }
        model.addAttribute("principal",principal);
        model.addAttribute("name",user.getFirstName());
        model.addAttribute("coinNames",coinNames);
        model.addAttribute("exchangeNamesFirst",exchangeNamesFirst);
        model.addAttribute("exchangeNamesSecond",exchangeNamesSecond);
        model.addAttribute("dataFirst",dataFirst);
        model.addAttribute("dataSecond",dataSecond);
        model.addAttribute("profit",profit);
        return "compareAllCoins";
    }
}
