package br.com.fiap.MoradoresPrestadores.repository;

import br.com.fiap.MoradoresPrestadores.model.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondominioRepository extends JpaRepository<Condominio,Integer> {

    Optional<Condominio> findByNumeroCondominioAndCep(int numeroCondominio,String cep);

}
