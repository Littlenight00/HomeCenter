/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
    @NamedQuery(name = "ItensVenda.todas", 
                query = "SELECT v FROM Venda v, ItensVenda iv WHERE iv.codigo = v.codigo"),
    @NamedQuery(name = "ItensVenda.porVenda", 
                query = "SELECT iv FROM ItensVenda iv WHERE iv.venda.codigo =:venda"
    ),
})
@Table(name="itensVenda")
@IdClass(ItensVendaID.class)
public class ItensVenda implements Serializable {
    
    @Column(name="cod_item")
    private int codigo;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "cod_venda", referencedColumnName = "cod_venda")
    private Venda venda;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "cod_produto", referencedColumnName = "cod_produto")
    private Produto produto;
    
    @Column(name = "quantidade")
    private int qtde;
    
    @Column(name = "preco")
    private double preco;

    public ItensVenda() { }

    public ItensVenda(int codigo, Venda venda, Produto produto, int qtde, double preco) {
        this.codigo = codigo;
        this.venda = venda;
        this.produto = produto;
        this.qtde = qtde;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "ItensVenda{" + "venda=" + venda + ", produto=" + produto.getNome() + ", qtde=" + qtde + ", preco=" + preco + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.venda);
        hash = 83 * hash + Objects.hashCode(this.produto);
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
        final ItensVenda other = (ItensVenda) obj;
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        return Objects.equals(this.produto, other.produto);
    }
    
}
