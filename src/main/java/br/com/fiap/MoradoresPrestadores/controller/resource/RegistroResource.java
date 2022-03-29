package br.com.fiap.MoradoresPrestadores.controller.resource;

import br.com.fiap.MoradoresPrestadores.exceptions.BusinessException;
import br.com.fiap.MoradoresPrestadores.exceptions.NotFoundException;
import br.com.fiap.MoradoresPrestadores.model.Registro;
import br.com.fiap.MoradoresPrestadores.repository.RegistroRepository;
import br.com.fiap.MoradoresPrestadores.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("registro")
public class RegistroResource {

    @Autowired
    private RegistroRepository registroRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Registro> findAll(){
        return registroRepository.findAll();
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Registro findById(@PathVariable int id){
        return registroRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Registro save(@RequestBody Registro registro){
        Optional<Registro> registroOptional =
                registroRepository.findMoradorAndPrestador(registro.getMorador(),registro.getPrestador());

        if(registroOptional.isPresent())
            throw new BusinessException(MessageUtils.REGISTRO_ALREADY_EXISTS);

        return registroRepository.save(registro);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Registro update(@RequestBody Registro registro){

        findById(registro.getId());

        return registroRepository.save(registro);

    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Registro delete(int id){
        Registro registro = findById(id);

        registroRepository.deleteById(registro.getId());

        return registro;
    }

}
