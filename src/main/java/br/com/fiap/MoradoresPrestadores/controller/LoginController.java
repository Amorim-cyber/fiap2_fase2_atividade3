package br.com.fiap.MoradoresPrestadores.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("login")
public class LoginController {

    @GetMapping()
    public String open(Model model){
        return "login";
    }

}
