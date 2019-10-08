package pl.coderslab.app.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.dto.LoginFormDTO;

import java.security.Principal;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String prepareLoginPage(Model model) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("dataLogin", new LoginFormDTO());
        model.addAttribute("principal", principal);
        return "login";
    }


}