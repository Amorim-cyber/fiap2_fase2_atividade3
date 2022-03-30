package br.com.fiap.MoradoresPrestadores.repository;

import br.com.fiap.MoradoresPrestadores.model.Morador;
import br.com.fiap.MoradoresPrestadores.model.Prestador;
import br.com.fiap.MoradoresPrestadores.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistroRepository extends JpaRepository<Registro,Integer> {

    @Query("SELECT registro "+
            "FROM Registro registro "+
            "WHERE registro.morador = :morador AND registro.prestador = :prestador")
    Optional<Registro> findMoradorAndPrestador(Morador morador, Prestador prestador);

}
