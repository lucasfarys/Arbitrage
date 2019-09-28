package pl.coderslab.app.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.dto.DataBitbayDTO;
import pl.coderslab.app.exchange.Exchange;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/dashboard")
public class PanelController {


    @GetMapping
    public String prepareDashboard(){
        return "dashboard";
    }
}