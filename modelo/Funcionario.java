/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Evellyn Gomes
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "funcionario.Todos", 
                query = "SELECT f FROM Funcionario f "),
    @NamedQuery(name = "funcionario.PorNome",
                query = "SELECT f FROM Funcionario f WHERE f.nome LIKE :nomequalquer"),
    @NamedQuery(name = "Funcionario.login", 
                query = "SELECT f FROM Funcionario f WHERE f.cpf LIKE :login AND f.senha LIKE :senha")
})
@Table(name = "funcionario")
public class Funcionario implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "cod_funcionario")
    private int codigo;
    @Column(name = "nivel_acesso", length = 20, nullable = false)
    private String nivel_de_acesso;
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "cpf", length = 15, nullable = false)
    private String cpf;
    @Column(name = "senha", length = 15, nullable = false)
    private String senha;
    @Column(name = "salario")
    private double salario;
    @Column(name = "data_admissao")
    @Temporal(TemporalType.DATE)
    private Date data_admissao;
    
    @Column(name = "data_demissao")
    @Temporal(TemporalType.DATE)
    private Date data_demissao;

    public Funcionario() 
    { }

    public Funcionario(int codigo, String nivel_de_acesso, String nome, String cpf, String senha, double salario, Date data_admissao, Date data_demissao) {
        this.codigo = codigo;
        this.nivel_de_acesso = nivel_de_acesso;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.salario = salario;
        this.data_admissao = data_admissao;
        this.data_demissao = data_demissao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNivel_de_acesso() {
        return nivel_de_acesso;
    }

    public void setNivel_de_acesso(String nivel_de_acesso) {
        this.nivel_de_acesso = nivel_de_acesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getData_admissao() {
        return data_admissao;
    }

    public void setData_admissao(Date data_admissao) {
        this.data_admissao = data_admissao;
    }

    public Date getData_demissao() {
        return data_demissao;
    }

    public void setData_demissao(Date data_demissao) {
        this.data_demissao = data_demissao;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "codigo=" + codigo + ", nivel_de_acesso=" + nivel_de_acesso + ", nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + ", salario=" + salario + ", data_admissao=" + data_admissao + ", data_demissao=" + data_demissao + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        return this.codigo == other.codigo;
    }

}
