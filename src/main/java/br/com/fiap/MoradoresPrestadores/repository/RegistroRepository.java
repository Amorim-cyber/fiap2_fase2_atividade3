package br.com.fiap.MoradoresPrestadores.repository;

import br.com.fiap.MoradoresPrestadores.model.Morador;
import br.com.fiap.MoradoresPrestadores.model.Prestador;
import br.com.fiap.MoradoresPrestadores.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistroRepository extends JpaRepository<Registro,Integer> {

    Optional<Registro> findMoradorAndPrestador(Morador morador, Prestador prestador);

}
