package br.com.fiap.MoradoresPrestadores.repository;

import br.com.fiap.MoradoresPrestadores.model.Morador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoradorRepository extends JpaRepository<Morador,Integer> {

    Optional<Morador> findByLoginAndSenha(String login, String senha);

}
