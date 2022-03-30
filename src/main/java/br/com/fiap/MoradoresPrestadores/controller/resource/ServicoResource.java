package br.com.fiap.MoradoresPrestadores.controller.resource;


import br.com.fiap.MoradoresPrestadores.exceptions.BusinessException;
import br.com.fiap.MoradoresPrestadores.exceptions.NotFoundException;
import br.com.fiap.MoradoresPrestadores.model.Servico;
import br.com.fiap.MoradoresPrestadores.repository.ServicoRepository;
import br.com.fiap.MoradoresPrestadores.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("servico")
public class ServicoResource {

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Servico> findAll(){return servicoRepository.findAll();}

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Servico findById(@PathVariable int id){
        return servicoRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Servico save(@RequestBody Servico servico){
        Optional<Servico> servicoOptional =
                servicoRepository.findByOcupacao(servico.getOcupacao());

        if(servicoOptional.isPresent())
            throw new BusinessException(MessageUtils.SERVICO_ALREADY_EXISTS);

        return servicoRepository.save(servico);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Servico update(@RequestBody Servico servico){
        findById(servico.getId());

        return servicoRepository.save(servico);
    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Servico delete(int id){
        Servico servico = findById(id);

        servicoRepository.deleteById(servico.getId());

        return servico;
    }


}
