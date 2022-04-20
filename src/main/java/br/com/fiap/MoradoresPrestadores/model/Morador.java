package br.com.fiap.MoradoresPrestadores.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_morador")
public class Morador extends Usuario{

    @Id
    @SequenceGenerator(name="morador",sequenceName="sq_tb_morador",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="morador")
    @Column(name="id_morador")
    private int id;

    @Column(name="nm_morador",nullable=false,length=100)
    private String nome;

    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(joinColumns = @JoinColumn(name="id_morador"),
            inverseJoinColumns = @JoinColumn(name="id_morada"), name = "tb_registro_morada")
    private List<Morada> moradas = new ArrayList<Morada>();

    public Morador() {
    }

    public Morador(int id, String nome, String login, String senha, List<Morada> moradas) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.login = login;
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

    public List<Morada> getMoradas() {
        return moradas;
    }

    public void setMoradas(List<Morada> moradas) {
        this.moradas = moradas;
    }


    @Override
    public String toString() {
        return "Morador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", moradas=" + moradas +
                '}';
    }
}
