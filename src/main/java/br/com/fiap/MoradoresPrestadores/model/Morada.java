package br.com.fiap.MoradoresPrestadores.model;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tb_morada")
public class Morada {

    @Id
    @SequenceGenerator(name="morada",sequenceName="sq_tb_morada",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="morada")
    @Column(name="id_morada")
    private int id;

    @Column(name="nr_morada",nullable=false)
    private int numero;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_morada")
    private Estrutura estrutura;

    @JoinColumn(name = "id_condominio")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Condominio condominio;

    public Morada() {
    }

    public Morada(int id, int numero, Estrutura estrutura, Condominio condominio) {
        this.id = id;
        this.numero = numero;
        this.estrutura = estrutura;
        this.condominio = condominio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Estrutura getEstrutura() {
        return estrutura;
    }

    public void setEstrutura(Estrutura estrutura) {
        this.estrutura = estrutura;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

}
