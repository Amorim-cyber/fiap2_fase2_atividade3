package br.com.fiap.MoradoresPrestadores.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tb_servico")
public class Servico {

    @Id
    @SequenceGenerator(name="servico",sequenceName="sq_tb_servico",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="servico")
    @Column(name="id_servico")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name="nm_servico")
    private Ocupacao ocupacao;

    public Servico() {
    }

    public Servico(int id, Ocupacao ocupacao) {
        super();
        this.id = id;
        this.ocupacao = ocupacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ocupacao getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(Ocupacao ocupacao) {
        this.ocupacao = ocupacao;
    }

}
