package pl.coderslab.app.web.controllers;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.app.model.exchangeModel.Bitbay;
import pl.coderslab.app.repositories.BitbayRepository;

import java.util.List;

@RestController
@RequestMapping("/restchart")
public class ChartRestController {
    private BitbayRepository bitbayRepository;

    public ChartRestController(BitbayRepository bitbayRepository) {
        this.bitbayRepository = bitbayRepository;
    }

    @GetMapping
    public String showChart(){
        List<Bitbay> bitbayList = bitbayRepository.findAll();
        bitbayList = bitbayList.subList(bitbayList.size()-24,bitbayList.size());
        Double[] btcPln = new Double[bitbayList.size()];
        String[] btcPlnDate = new String[bitbayList.size()];
        JSONObject jsonObjectValue = new JSONObject();
        for(int i=0;i<bitbayList.size();i++){
            btcPln[i] = bitbayList.get(i).getAskBTCPLN();
            btcPlnDate[i] = bitbayList.get(i).getDate().split("T")[1].substring(0,2);
        }
        jsonObjectValue.put("btcPln",btcPln);
        jsonObjectValue.put("date",btcPlnDate);
        return jsonObjectValue.toString();
    }
}
