package pl.coderslab.app.web.controllers;

import org.json.JSONObject;
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
    public String showChart(Model model){
        List<Bitbay> bitbayList = bitbayRepository.findAll();
        Double[] btcPln = new Double[bitbayList.size()];
        JSONObject jsonObject = new JSONObject();
        for(int i=0;i<bitbayList.size();i++){
            btcPln[i] = bitbayList.get(i).getAskBTCPLN();
        }
        jsonObject.put("btcPln",btcPln);
        model.addAttribute("btcPln", jsonObject);
        System.out.println(jsonObject);
        return jsonObject.toString();
    }
}
