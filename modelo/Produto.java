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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Evellyn Gomes
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "produto.Todos", 
                query = "SELECT p FROM Produto p"
    ),
    @NamedQuery(name = "produto.PorNome",
                query = "SELECT p FROM Produto p WHERE p.nome LIKE :nomequalquer"
    ),
    @NamedQuery(name = "produto.Comparar",
            query = "SELECT p FROM Produto p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "produto.porQtde",
            query = "SELECT p FROM Produto p WHERE p.qtdeEstoque < p.qtdeMinimo")
    }
)

@Table(name = "produto")
public class Produto implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_produto")
    private int codigo;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "qtdeEstoque")
    private int qtdeEstoque;
    @Column(name = "precoVenda")
    private double precoVenda;
    @Column(name = "qtdeMinimo")
    private int qtdeMinimo;
    
    @ManyToOne
    @JoinColumn(name="departamento", referencedColumnName = "cod_departamento")
    private Departamento departamento;
    
    public Produto() 
    { }

    public Produto(int codigo, String nome, String descricao, int qtdeEstoque, double precoVenda, int qtdeMinimo, Departamento departamento) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.qtdeEstoque = qtdeEstoque;
        this.precoVenda = precoVenda;
        this.qtdeMinimo = qtdeMinimo;
        this.departamento = departamento;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(int qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQtdeMinimo() {
        return qtdeMinimo;
    }

    public void setQtdeMinimo(int qtdeMinimo) {
        this.qtdeMinimo = qtdeMinimo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    public void vender(int quantidade)
    {
        this.qtdeEstoque -= quantidade;
    }
    
    public void comprar(int quantidade)
    {
        this.qtdeEstoque += quantidade;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.codigo;
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
        final Produto other = (Produto) obj;
        return this.codigo == other.codigo;
    }
    
    
}
