package br.com.fiap.MoradoresPrestadores.repository;

import br.com.fiap.MoradoresPrestadores.model.Ocupacao;
import br.com.fiap.MoradoresPrestadores.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicoRepository extends JpaRepository<Servico,Integer> {

    Optional<Servico> findByOcupacao(Ocupacao ocupacao);

}
