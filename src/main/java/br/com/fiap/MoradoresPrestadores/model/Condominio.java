package br.com.fiap.MoradoresPrestadores.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tb_condominio")
public class Condominio {

    @Id
    @SequenceGenerator(name="condominio",sequenceName="sq_tb_condominio",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="condominio")
    @Column(name="id_condominio")
    private int id;


    @Column(name="nm_condominio",nullable=false,length=100)
    private String nome;


    @Column(name="endereco",nullable=false,length=200)
    private String endereco;

    @OneToMany(mappedBy = "condominio")
    private List<Morada> moradas;

    public Condominio() {
    }

    public Condominio(int id, String nome, String endereco, List<Morada> moradas) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.moradas = moradas;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Morada> getMoradas() {
        return moradas;
    }

    public void setMoradas(List<Morada> moradas) {
        this.moradas = moradas;
    }



}
