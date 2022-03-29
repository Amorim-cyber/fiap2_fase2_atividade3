package br.com.fiap.MoradoresPrestadores.controller.resource;

import br.com.fiap.MoradoresPrestadores.exceptions.BusinessException;
import br.com.fiap.MoradoresPrestadores.exceptions.InvalidComandException;
import br.com.fiap.MoradoresPrestadores.exceptions.NotFoundException;
import br.com.fiap.MoradoresPrestadores.model.Condominio;
import br.com.fiap.MoradoresPrestadores.repository.CondominioRepository;
import br.com.fiap.MoradoresPrestadores.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("condominio")
public class CondominioResource {

    @Autowired
    private CondominioRepository condominioRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Condominio> findAll(){
        return condominioRepository.findAll();
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Condominio findById(@PathVariable int id){
        return condominioRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Condominio save(@RequestBody Condominio condominio){

        Optional<Condominio> condominioOptional =
                condominioRepository.findByIdAndNome(condominio.getId(),condominio.getNome());

        if(condominioOptional.isPresent())
            throw new BusinessException(MessageUtils.CONDOMINIO_ALREADY_EXISTS);

        return condominioRepository.save(condominio);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Condominio update(@RequestBody Condominio condominio){

        Optional<Condominio> condominioOptional = Optional.ofNullable(findById(condominio.getId()));

        if(condominioOptional.isEmpty())
            throw new InvalidComandException(MessageUtils.INVALID_COMMAND);

        return condominioRepository.save(condominio);
    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Condominio delete(int id){
        Condominio condominio = findById(id);

        condominioRepository.deleteById(condominio.getId());

        return condominio;
    }

}
