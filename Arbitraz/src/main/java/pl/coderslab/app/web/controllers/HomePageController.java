package pl.coderslab.app.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.model.User;
import pl.coderslab.app.services.DataCoinService;
import pl.coderslab.app.services.ExchangeService;
import pl.coderslab.app.services.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/")
public class HomePageController {
    private ExchangeService exchangeService;
    private DataCoinService dataCoinService;
    private UserService userService;
    public HomePageController(ExchangeService exchangeService, DataCoinService dataCoinService,
                              UserService userService) {
        this.exchangeService = exchangeService;
        this.dataCoinService = dataCoinService;
        this.userService = userService;
    }

    @GetMapping
    public String prepareHomePage(Model model) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(principal.getName());
        if(user!=null) {
            model.addAttribute("principal", principal);
            model.addAttribute("name", user.getFirstName());
        }
        return "index";
    }
}