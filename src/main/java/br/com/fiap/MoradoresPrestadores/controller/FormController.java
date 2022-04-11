package br.com.fiap.MoradoresPrestadores.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("formulario")
public class FormController {

    @GetMapping()
    public String open(Model model){
        return "formulario/form";
    }

}
