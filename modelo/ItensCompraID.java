/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Evellyn Gomes
 */

public class ItensCompraID implements Serializable {
    
    private int compra;
    private int produto;

    public ItensCompraID() 
    { }

    public ItensCompraID(int compra, int produto) {
        this.compra = compra;
        this.produto = produto;
    }

    public int getCompra() {
        return compra;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.compra;
        hash = 19 * hash + this.produto;
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
        final ItensCompraID other = (ItensCompraID) obj;
        if (this.compra != other.compra) {
            return false;
        }
        return this.produto == other.produto;
    }
    
}
