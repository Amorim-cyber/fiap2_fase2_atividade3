package br.com.fiap.MoradoresPrestadores.controller.resource;

import br.com.fiap.MoradoresPrestadores.exceptions.BusinessException;
import br.com.fiap.MoradoresPrestadores.exceptions.NotFoundException;
import br.com.fiap.MoradoresPrestadores.model.Morada;
import br.com.fiap.MoradoresPrestadores.repository.MoradaRepository;
import br.com.fiap.MoradoresPrestadores.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("morada")
public class MoradaResource {

    @Autowired
    private MoradaRepository moradaRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Morada> findAll(){return moradaRepository.findAll();}

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Morada findById(@PathVariable int id){
        return moradaRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Morada save(@RequestBody Morada morada) {

        Optional<Morada> moradaOptional =
                moradaRepository.findByNumeroMoradaAndCondominio(morada.getNumeroMorada(), morada.getCondominio());

        if(moradaOptional.isPresent())
            throw new BusinessException(MessageUtils.MORADA_ALREADY_EXISTS);

        return moradaRepository.save(morada);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Morada update(@RequestBody Morada morada){

        findById(morada.getId());

        return moradaRepository.save(morada);

    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Morada delete(@PathVariable int id){
        Morada morada = findById(id);

        moradaRepository.deleteById(morada.getId());

        return morada;
    }
}
