package pl.coderslab.app.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.services.DataCoinService;
import pl.coderslab.app.services.ExchangeService;

import java.security.Principal;


@Controller
@RequestMapping("/")
public class HomePageController {
    private ExchangeService exchangeService;
    private DataCoinService dataCoinService;
    public HomePageController(ExchangeService exchangeService, DataCoinService dataCoinService) {
        this.exchangeService = exchangeService;
        this.dataCoinService = dataCoinService;
    }

    @GetMapping
    public String prepareHomePage(Model model) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("principal", principal);
        return "index";
    }
}