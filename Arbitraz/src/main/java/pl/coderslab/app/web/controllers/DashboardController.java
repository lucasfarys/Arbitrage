package pl.coderslab.app.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.model.Favourite;
import pl.coderslab.app.model.User;
import pl.coderslab.app.services.FavouriteService;
import pl.coderslab.app.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private FavouriteService favouriteService;
    private UserService userService;

    public DashboardController(FavouriteService favouriteService, UserService userService) {
        this.favouriteService = favouriteService;
        this.userService = userService;
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