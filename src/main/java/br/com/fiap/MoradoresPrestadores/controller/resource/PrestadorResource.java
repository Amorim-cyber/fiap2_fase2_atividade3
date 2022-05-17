package br.com.fiap.MoradoresPrestadores.controller.resource;

import br.com.fiap.MoradoresPrestadores.exceptions.BusinessException;
import br.com.fiap.MoradoresPrestadores.exceptions.NotFoundException;
import br.com.fiap.MoradoresPrestadores.model.Prestador;
import br.com.fiap.MoradoresPrestadores.repository.PrestadorRepository;
import br.com.fiap.MoradoresPrestadores.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("prestador")
public class PrestadorResource {

    @Autowired
    private PrestadorRepository prestadorRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Prestador> findAll(){
        return prestadorRepository.findAll();
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Prestador findById(@PathVariable int id){
        return prestadorRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value="/{login}/{senha}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Prestador findByLoginAndSenha(@PathVariable String login,@PathVariable String senha){
        return prestadorRepository.findByLoginAndSenha(login,senha).orElse(null);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Prestador save(@RequestBody Prestador prestador){

        Optional<Prestador> prestadorOptional =
                prestadorRepository.findByLoginAndSenha(prestador.getLogin(), prestador.getSenha());

        if(prestadorOptional.isPresent())
            throw new BusinessException(MessageUtils.PRESTADOR_ALREADY_EXISTS);

        return prestadorRepository.save(prestador);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Prestador update(@RequestBody Prestador prestador){

        findById(prestador.getId());

        return prestadorRepository.save(prestador);

    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Prestador delete(@PathVariable int id){
        Prestador prestador = findById(id);

        prestadorRepository.deleteById(prestador.getId());

        return prestador;
    }

}
