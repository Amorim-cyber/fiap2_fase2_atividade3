package br.com.fiap.MoradoresPrestadores.controller;

import br.com.fiap.MoradoresPrestadores.controller.resource.CondominioResource;
import br.com.fiap.MoradoresPrestadores.controller.resource.MoradaResource;
import br.com.fiap.MoradoresPrestadores.controller.resource.MoradorResource;
import br.com.fiap.MoradoresPrestadores.controller.resource.PrestadorResource;
import br.com.fiap.MoradoresPrestadores.model.Condominio;
import br.com.fiap.MoradoresPrestadores.model.Morada;
import br.com.fiap.MoradoresPrestadores.model.Morador;
import br.com.fiap.MoradoresPrestadores.model.Prestador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    private PrestadorResource prestadorResource;

    @Autowired
    private MoradorResource moradorResource;

    @Autowired
    private MoradaResource moradaResource;

    @Autowired
    private CondominioResource condominioResource;

    @PostMapping("prestador")
    public String create(@Valid Prestador prestador,Model model,
                         BindingResult result){
        if(result.hasErrors()) {return "produto/form";}
        prestadorResource.save(prestador);
        model.addAttribute("prestador",prestador);

        model.addAttribute("msg", "Cadastro realizado com sucesso!");
        return "home/home";

    }

    @PostMapping("morador")
    public String create(@Valid Morador morador,
                         @Valid Morada morada,
                         @Valid Condominio condominio,
                         Model model,
                         BindingResult result){
        if(result.hasErrors()) {return "produto/form";}

        System.out.println(condominio);

        int num = condominio.getNumeroCondominio();
        String cep = condominio.getCep();
        if(condominioResource.findByNumeroAndCep(num,cep) == null)
            condominioResource.save(condominio);

        morada.setCondominio(condominio);
        moradaResource.save(morada);

        morador.getMoradas().add(morada);
        moradorResource.save(morador);
        model.addAttribute("morador",morador);


        model.addAttribute("msg", "Cadastro realizado com sucesso!");
        return "home/home";

    }

}
