/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Evellyn Gomes
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "Cliente.todos", 
                query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "cliente.PorNome",
                query = "SELECT c FROM Cliente c WHERE c.nome LIKE :nomequalquer"),
    @NamedQuery(name = "cliente.porEmail", 
                query = "SELECT c FROM Cliente c WHERE c.email LIKE :email"),
    @NamedQuery(name = "cliente.porCPF", 
                query = "SELECT c FROM Cliente c WHERE c.cpf LIKE :cpf"),
    @NamedQuery(name = "cliente.porTelefone", 
                query = "SELECT c FROM Cliente c WHERE c.telefone LIKE :telefone"),
})
@Table(name = "cliente")
public class Cliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "cod_cliente")
    private int codigo;
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @Column(name = "cpf", length = 15, nullable = false)
    private String cpf;
    @Column(name = "telefone", length = 20, nullable = false)
    private String telefone;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "cep")
    private String cep;
    @Column(name = "uf")
    private String uf; 

    public Cliente() 
    { }

    public Cliente(int codigo, String nome, String email, String cpf, String telefone, String endereco, String cidade, String cep, String uf) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cidade = cidade;
        this.cep = cep;
        this.uf = uf;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Cliente{" + "codigo=" + codigo + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", telefone=" + telefone + ", endereco=" + endereco + ", cidade=" + cidade + ", cep=" + cep + ", uf=" + uf + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.codigo;
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
        final Cliente other = (Cliente) obj;
        return this.codigo == other.codigo;
    }

}
