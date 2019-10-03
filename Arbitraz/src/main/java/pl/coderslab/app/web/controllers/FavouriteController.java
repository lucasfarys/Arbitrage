package pl.coderslab.app.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.dto.FavouriteFormDTO;
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
    @PostMapping
    public String addFavourite(@ModelAttribute("exchange01") String exchange01, @ModelAttribute("exchange02") String exchange02,
                               @ModelAttribute("coin") String coin, BindingResult bindingResult){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        FavouriteFormDTO favouriteFormDTO = new FavouriteFormDTO();
        favouriteFormDTO.setCoin(coin);
        favouriteFormDTO.setExchange_first(exchange01);
        favouriteFormDTO.setExchange_second(exchange02);
        favouriteFormDTO.setLogin(authentication.getName());
        favouriteService.saveFavourite(favouriteFormDTO);
        return "redirect:/dashboard";
    }
}
