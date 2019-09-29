package pl.coderslab.app.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.model.exchangeModel.Bitbay;
import pl.coderslab.app.model.exchangeModel.Bittrex;
import pl.coderslab.app.repositories.BitbayRepository;
import pl.coderslab.app.repositories.BittrexRepository;

import java.util.List;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private BitbayRepository bitbayRepository;
    private BittrexRepository bittrexRepository;

    public DashboardController(BitbayRepository bitbayRepository, BittrexRepository bittrexRepository) {
        this.bitbayRepository = bitbayRepository;
        this.bittrexRepository = bittrexRepository;
    }

    @GetMapping
    public String prepareDashboard(Model model){
        List<Bitbay> bitbayValue = bitbayRepository.findAll();
        List<Bittrex> bittrexValue = bittrexRepository.findAll();
        model.addAttribute("bitbayValue",bitbayValue);
        model.addAttribute("bittrexValue",bittrexValue);
        return "dashboard";
    }
}