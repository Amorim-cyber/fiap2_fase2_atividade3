package br.com.fiap.MoradoresPrestadores.repository;

import br.com.fiap.MoradoresPrestadores.model.Condominio;
import br.com.fiap.MoradoresPrestadores.model.Morada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoradaRepository extends JpaRepository<Morada,Integer> {

    Optional<Morada> findByNumeroAndCondominio(int numero, Condominio condominio);

}
