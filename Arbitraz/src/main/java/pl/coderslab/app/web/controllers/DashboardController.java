package pl.coderslab.app.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dto.FavouriteFormDTO;
import pl.coderslab.app.exchange.Exchange;
import pl.coderslab.app.model.exchangeModel.Bitbay;
import pl.coderslab.app.model.exchangeModel.Bittrex;
import pl.coderslab.app.repositories.BitbayRepository;
import pl.coderslab.app.repositories.BittrexRepository;
import pl.coderslab.app.services.FavouriteService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private BitbayRepository bitbayRepository;
    private BittrexRepository bittrexRepository;
    private FavouriteService favouriteService;
    private Exchange bitbay;
    private Exchange bittrex;

    public DashboardController(BitbayRepository bitbayRepository, BittrexRepository bittrexRepository,
                                Exchange bitbay, Exchange bittrex, FavouriteService favouriteService) {
        this.bitbayRepository = bitbayRepository;
        this.bittrexRepository = bittrexRepository;
        this.favouriteService = favouriteService;
        this.bitbay = bitbay;
        this.bittrex = bittrex;
    }

    @GetMapping
    public String prepareDashboard(Model model){
        List<Bitbay> bitbayValue = bitbayRepository.findAll();
        List<Bittrex> bittrexValue = bittrexRepository.findAll();

        return "dashboard";
    }

    @GetMapping("/addfavourite")
    public String prepareFavourite( Model model){
        List<Exchange> exchanges = new ArrayList<>();
        List<String> coinsName = new ArrayList<>();
        exchanges.add(bitbay);
        exchanges.add(bittrex);
//        for(int i=1; i<bitbay.getCoinsName().size();i++){
//            if(bitbay.getCoinsName().get(i).equalsIgnoreCase(bittrex.getCoinsName().get(i)));{
//                coinsName.add(bitbay.getCoinsName().get(i));
//            }
//        }
        coinsName.add("BTCPLN");
        coinsName.add("BTCETH");
        coinsName.add("BTCUSD");
        model.addAttribute("exchange",exchanges);
        model.addAttribute("coinsName",coinsName);
        return "addFavourite";
    }
    @PostMapping("/addfavourite")
    public String addFavourite(@ModelAttribute("exchange01") String exchange01, @ModelAttribute("exchange02") String exchange02,
                               @ModelAttribute("coin") String coin, BindingResult bindingResult){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        FavouriteFormDTO favouriteFormDTO = new FavouriteFormDTO();
        favouriteFormDTO.setCoin(coin);
        favouriteFormDTO.setExchange_first(exchange01);
        favouriteFormDTO.setExchange_second(exchange02);
        favouriteFormDTO.setLogin(authentication.getName());
        favouriteService.saveFavourite(favouriteFormDTO);
        return "redirect:/chart";
    }

}