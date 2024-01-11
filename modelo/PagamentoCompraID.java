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

public class PagamentoCompraID implements Serializable {
    
    private int compra;
    private int idparcela;

    public PagamentoCompraID() 
    { }

    public PagamentoCompraID(int compra, int idparcela) {
        this.compra = compra;
        this.idparcela = idparcela;
    }

    public int getCod_compra() {
        return compra;
    }

    public void setCod_compra(int compra) {
        this.compra = compra;
    }

    public int getIdparcela() {
        return idparcela;
    }

    public void setIdparcela(int idparcela) {
        this.idparcela = idparcela;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.compra;
        hash = 23 * hash + this.idparcela;
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
        final PagamentoCompraID other = (PagamentoCompraID) obj;
        if (this.compra != other.compra) {
            return false;
        }
        return this.idparcela == other.idparcela;
    }
    
}
