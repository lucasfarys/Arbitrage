package pl.coderslab.app.web.controllers;


import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.app.exchange.Exchange;

import java.util.ArrayList;
import java.util.List;

@RestController("/addfavourite")
public class FavouriteRestController {
    private Exchange bitbay;
    private Exchange bittrex;

    public FavouriteRestController(Exchange bitbay, Exchange bittrex) {
        this.bitbay = bitbay;
        this.bittrex = bittrex;
    }

    @GetMapping("/{exchangeFirst}/{exchangeSecond}")
    public String prepareFavouriteCourse(@PathVariable String exchangeFirst,@PathVariable String exchangeSecond){
        JSONObject jsonObject = new JSONObject();
        List<String> favouriteCoinsName = new ArrayList<>();
        System.out.println("test");
        System.out.println(exchangeFirst);
        System.out.println(exchangeSecond);
        if(bitbay.getName().equals(exchangeFirst)){
            if(bittrex.getName().equals(exchangeSecond)){
                favouriteCoinsName = getFavouriteCoinsName(bitbay,bittrex);
            }
        } else if(bittrex.getName().equals(exchangeFirst)){
                    if(bitbay.getName().equals(exchangeSecond)){
                        System.out.println("test");

                        favouriteCoinsName = getFavouriteCoinsName(bittrex,bitbay);

                    }
                }
        System.out.println(favouriteCoinsName);
        System.out.println("test");
        jsonObject.put("favouriteCoinsName", favouriteCoinsName);
        return jsonObject.toString();
    }
    public List<String> getFavouriteCoinsName(Exchange exchangeFirst, Exchange exchangeSecond){
        List<String> result = new ArrayList<>();

        return result;
    }
}
