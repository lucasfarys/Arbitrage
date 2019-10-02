package pl.coderslab.app.web.controllers;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.model.exchangeModel.Bitbay;
import pl.coderslab.app.repositories.BitbayRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chart")
public class ChartController {
    private BitbayRepository bitbayRepository;

    public ChartController(BitbayRepository bitbayRepository) {
        this.bitbayRepository = bitbayRepository;
    }

    @GetMapping
    public String showChart(Model model){return "chart";}
}
