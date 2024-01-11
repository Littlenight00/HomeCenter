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
    @NamedQuery(name = "ItensCompra.porCompra", 
                query = "SELECT ic FROM ItensCompra ic WHERE ic.compra.codigo =:compra"
    ),
})
@Table(name="itensCompra")
@IdClass(ItensCompraID.class)
public class ItensCompra implements Serializable {
    
    @Column(name="cod_item")
    private int codigo;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "cod_compra", referencedColumnName = "cod_compra")
    private Compra compra;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "cod_produto", referencedColumnName = "cod_produto")
    private Produto produto;
    
    @Column(name = "qtde")
    private int qtde;
    @Column(name = "preco")
    private double preco;

    public ItensCompra() 
    { }

    public ItensCompra(Compra compra, Produto produto, int qtde, double preco) {
        this.compra = compra;
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
    
    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
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
        return "ItensCompra{" + "compra=" + compra + ", produto=" + produto + ", qtde=" + qtde + ", preco=" + preco + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.compra);
        hash = 13 * hash + Objects.hashCode(this.produto);
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
        final ItensCompra other = (ItensCompra) obj;
        if (!Objects.equals(this.compra, other.compra)) {
            return false;
        }
        return Objects.equals(this.produto, other.produto);
    }
    
    
}
