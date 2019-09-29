package pl.coderslab.app.web.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.app.model.exchangeModel.Bitbay;
import pl.coderslab.app.repositories.BitbayRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/restchart")
public class ChartRestController {
    private BitbayRepository bitbayRepository;

    public ChartRestController(BitbayRepository bitbayRepository) {
        this.bitbayRepository = bitbayRepository;
    }

    @GetMapping
    public List<Double> showChart(Model model){
        List<Bitbay> bitbayList = bitbayRepository.findAll();
//        JSONPObject jsonpObject = new JSONPObject();
        List<Double> btcPln = new ArrayList<>();
        for(Bitbay el: bitbayList){
            btcPln.add(el.getAskBTCPLN());
        }
        model.addAttribute("btcPln", btcPln);

        return btcPln;
    }
}
