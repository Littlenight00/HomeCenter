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

public class ItensVendaID implements Serializable {
    
    private int venda;
    private int produto;

    public ItensVendaID() 
    { }

    public ItensVendaID(int venda, int produto) {
        this.venda = venda;
        this.produto = produto;
    }

    public int getVenda() {
        return venda;
    }

    public void setVenda(int venda) {
        this.venda = venda;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "ItensVendaID{" + "venda=" + venda + ", produto=" + produto + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.venda;
        hash = 89 * hash + this.produto;
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
        final ItensVendaID other = (ItensVendaID) obj;
        if (this.venda != other.venda) {
            return false;
        }
        return this.produto == other.produto;
    }
    
}
