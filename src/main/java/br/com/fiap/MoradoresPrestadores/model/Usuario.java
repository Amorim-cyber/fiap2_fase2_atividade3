package br.com.fiap.MoradoresPrestadores.model;
import javax.persistence.*;

@Entity
@Table(name="tb_usuario")
@Inheritance(strategy=InheritanceType.JOINED)
public class Usuario {

    @Id
    @SequenceGenerator(name="usuario",sequenceName="sq_tb_usuario",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario")
    @Column(name="id_usuario")
    private int id;

    @Column(name="login",nullable=false,length=20)
    protected String login;

    @Column(name="senha",nullable=false,length=20)
    protected String senha;

}
