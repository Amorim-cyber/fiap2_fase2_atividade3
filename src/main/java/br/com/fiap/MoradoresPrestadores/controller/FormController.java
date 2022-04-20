package br.com.fiap.MoradoresPrestadores.controller;

import br.com.fiap.MoradoresPrestadores.controller.resource.PrestadorResource;
import br.com.fiap.MoradoresPrestadores.controller.resource.ServicoResource;
import br.com.fiap.MoradoresPrestadores.model.*;
import br.com.fiap.MoradoresPrestadores.repository.PrestadorRepository;
import br.com.fiap.MoradoresPrestadores.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("formulario")
public class FormController {

    @Autowired
    private ServicoResource servicoResource;


    @GetMapping()
    public String open(Model model){
        return "formulario/form";
    }

    @GetMapping("usuario")
    public String reOpen(Model model,
                         Prestador prestador,
                         Morador morador,
                         Morada morada,
                         Condominio condominio,
                         HttpServletRequest request){
        model.addAttribute("servicos",servicoResource.findAll());
        model.addAttribute("usuario",request.getParameter("tipo"));
        return "formulario/form";
    }


}
