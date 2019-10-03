package pl.coderslab.app.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.model.Favourite;
import pl.coderslab.app.repositories.BitbayRepository;
import pl.coderslab.app.repositories.FavouriteRepository;

import java.util.List;


@Controller
@RequestMapping("/chart")
public class ChartController {
    private BitbayRepository bitbayRepository;
    private FavouriteRepository favouriteRepository;

    public ChartController(BitbayRepository bitbayRepository, FavouriteRepository favouriteRepository) {
        this.bitbayRepository = bitbayRepository;
        this.favouriteRepository = favouriteRepository;
    }

    @GetMapping
    public String showChart(Model model){
        List<Favourite> favourites = favouriteRepository.findAllByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("favourites", favourites);
        return "chart";}
}
