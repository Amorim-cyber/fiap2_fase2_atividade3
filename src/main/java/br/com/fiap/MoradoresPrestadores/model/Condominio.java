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
    private String nomeCondominio;

    @Column(name="cep",nullable=false,length=20)
    private String cep;

    @Column(name="nr_condominio",nullable=false)
    private int numeroCondominio;

    @Column(name="endereco",nullable=false,length=200)
    private String endereco;

    public Condominio() {
    }

    public Condominio(int id, String nomeCondominio, String cep, int numeroCondominio, String endereco) {
        this.id = id;
        this.nomeCondominio = nomeCondominio;
        this.cep = cep;
        this.numeroCondominio = numeroCondominio;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCondominio() {
        return nomeCondominio;
    }

    public void setNomeCondominio(String nomeCondominio) {
        this.nomeCondominio = nomeCondominio;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumeroCondominio() {
        return numeroCondominio;
    }

    public void setNumeroCondominio(int numeroCondominio) {
        this.numeroCondominio = numeroCondominio;
    }

    @Override
    public String toString() {
        return "Condominio{" +
                "id=" + id +
                ", nomeCondominio='" + nomeCondominio + '\'' +
                ", cep='" + cep + '\'' +
                ", numeroCondominio=" + numeroCondominio +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
