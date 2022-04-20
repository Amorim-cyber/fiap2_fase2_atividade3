package br.com.fiap.MoradoresPrestadores.controller;

import br.com.fiap.MoradoresPrestadores.controller.resource.*;
import br.com.fiap.MoradoresPrestadores.model.Condominio;
import br.com.fiap.MoradoresPrestadores.model.Morada;
import br.com.fiap.MoradoresPrestadores.model.Morador;
import br.com.fiap.MoradoresPrestadores.model.Prestador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    private ServicoResource servicoResource;

    @Autowired
    private CondominioResource condominioResource;

    @PostMapping()
    public String entry(Model model,
                         HttpServletRequest request){
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String tipo = request.getParameter("tipo");
        Prestador prestador = null;
        Morador morador = null;

        switch(tipo){
            case "prestador":{
                prestador = prestadorResource.findByLoginAndSenha(login,senha);
                break;
            }
            case "morador":{
                morador = moradorResource.findByLoginAndSenha(login,senha);
                break;
            }
        }

        if(prestador==null && morador==null){
            model.addAttribute("error", "Login ou senha incorretos!");
            return "login";
        }else if(prestador!=null){
            model.addAttribute("prestador",prestador);
        }else if(morador!=null){
            model.addAttribute("morador",morador);
        }

        return "home/home";

    }

    private List<String> checarListaDeErros(Prestador prestador){
        List<String> listErrors = new ArrayList<String>();

        if(prestador.getNome().isBlank())
            listErrors.add("Nome do prestador é obrigatório!");
        if(prestador.getLogin().isBlank())
            listErrors.add("Login do prestador é obrigatório!");
        if(prestador.getSenha().isBlank())
            listErrors.add("Senha do prestador é obrigatória!");
        if(prestador.getTelefone().isBlank())
            listErrors.add("Celular do prestador é obrigatório!");
        if(prestador.getTelefone().length()<14)
            listErrors.add("Celular do prestador não foi preenchido corretamente!");
        if(prestador.getServicos().size()==0)
            listErrors.add("Especialidade do prestador não foi preenchida!");

        return listErrors;
    }

    private List<String> checarListaDeErros(Morador morador){
        List<String> listErrors = new ArrayList<String>();

        if(morador.getNome().isBlank())
            listErrors.add("Nome do morador é obrigatório!");
        if(morador.getLogin().isBlank())
            listErrors.add("Login do morador é obrigatório!");
        if(morador.getSenha().isBlank())
            listErrors.add("Senha do morador é obrigatória!");

        return listErrors;
    }

    private List<String> checarListaDeErros(Condominio condominio){
        List<String> listErrors = new ArrayList<String>();

        if(condominio.getCep().isBlank())
            listErrors.add("CEP do condominio é obrigatório!");
        if(condominio.getCep().length()<9)
            listErrors.add("CEP do condominio não foi preenchido corretamente!");
        if(condominio.getNomeCondominio().isBlank())
            listErrors.add("Nome do condominio é obrigatório!");
        if(condominio.getEndereco().isBlank())
            listErrors.add("Endereço do condominio é obrigatório!");

        return listErrors;
    }

    private List<String> checarListaDeErros(Morada morada){
        List<String> listErrors = new ArrayList<String>();

        if(morada.getEstrutura()==null)
            listErrors.add("Tipo de morada é obrigatório!");

        return listErrors;
    }

    @PostMapping("novoPrestador")
    public String create(Prestador prestador,
                         Model model){
        List<String> listErrors = checarListaDeErros(prestador);

        if(listErrors.size()>0) {
            model.addAttribute("errors", listErrors);
            model.addAttribute("servicos",servicoResource.findAll());
            model.addAttribute("usuario","prestador");
            return "formulario/form";
        }

        model.addAttribute("msg", "Cadastro realizado com sucesso!");
        prestadorResource.save(prestador);
        model.addAttribute("prestador",prestador);

        return "home/home";

    }

    @PostMapping("novoMorador")
    public String create(Morador morador,
                         Morada morada,
                         Condominio condominio,
                         Model model){

        List<String> listErrorsMorador = checarListaDeErros(morador);
        List<String> listErrorsMorada = checarListaDeErros(morada);
        List<String> listErrorsCondominio = checarListaDeErros(condominio);

        if(listErrorsMorador.size()>0 ||
                listErrorsMorada.size()>0 ||
                listErrorsCondominio.size()>0) {

            if(listErrorsMorador.size()>0)
                model.addAttribute("errorsMorador", listErrorsMorador);
            if(listErrorsMorada.size()>0)
                model.addAttribute("errorsMorada", listErrorsMorada);
            if(listErrorsCondominio.size()>0)
                model.addAttribute("errorsCondominio", listErrorsCondominio);

            model.addAttribute("usuario","morador");
            return "formulario/form";
        }

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
