package br.com.fiap.MoradoresPrestadores.controller.resource;

import br.com.fiap.MoradoresPrestadores.exceptions.BusinessException;
import br.com.fiap.MoradoresPrestadores.exceptions.NotFoundException;
import br.com.fiap.MoradoresPrestadores.model.Morador;
import br.com.fiap.MoradoresPrestadores.repository.MoradorRepository;
import br.com.fiap.MoradoresPrestadores.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("morador")
public class MoradorResource {

    @Autowired
    private MoradorRepository moradorRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Morador> findAll(){return moradorRepository.findAll();}

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Morador findById(@PathVariable int id){
        return moradorRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value="/{login}/{senha}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Morador findByLoginAndSenha(@PathVariable String login, @PathVariable String senha){
        return moradorRepository.findByLoginAndSenha(login,senha).orElse(null);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Morador save(@RequestBody Morador morador){

        Optional<Morador> moradorOptional =
                moradorRepository.findByLoginAndSenha(morador.getLogin(), morador.getSenha());

        if(moradorOptional.isPresent())
            throw new BusinessException(MessageUtils.MORADOR_ALREADY_EXISTS);

        return moradorRepository.save(morador);

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Morador update(@RequestBody Morador morador){

        findById(morador.getId());

        return moradorRepository.save(morador);

    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Morador delete(@PathVariable int id){
        Morador morador = findById(id);

        moradorRepository.deleteById(morador.getId());

        return morador;
    }

}
