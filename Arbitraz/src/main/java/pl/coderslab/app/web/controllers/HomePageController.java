package pl.coderslab.app.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.services.ExchangeService;

import java.security.Principal;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/")
public class HomePageController {
    private ExchangeService exchangeService;

    public HomePageController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping
    public String prepareHomePage(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("username", username);
        String time = LocalDateTime.now().toString();
        System.out.println(exchangeService.getExchangeById(1L).getAddressUrlPrefix());

        return "index";
    }
}