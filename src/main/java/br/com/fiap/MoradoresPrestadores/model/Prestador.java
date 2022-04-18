package br.com.fiap.MoradoresPrestadores.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tb_prestador")
public class Prestador extends Usuario{

    @Id
    @SequenceGenerator(name="prestador",sequenceName="sq_tb_prestador",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="prestador")
    @Column(name="id_prestador")
    private int id;

    @Column(name="nm_prestador",nullable=false,length=100)
    private String nome;

    @Column(name="nr_prestador",nullable=false)
    private int telefone;


    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(joinColumns = @JoinColumn(name="id_prestador"),
            inverseJoinColumns = @JoinColumn(name="id_servico"), name = "tb_ocupacao")
    private List<Servico> servicos;

    public Prestador() {
    }

    public Prestador(int id, String nome, String login, String senha, int telefone, List<Servico> servicos) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.telefone = telefone;
        this.servicos = servicos;
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

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    @Override
    public String toString() {
        return "Prestador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone=" + telefone +
                ", servicos=" + servicos +
                '}';
    }
}
