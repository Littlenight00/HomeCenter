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

public class PagamentoVendaID implements Serializable {
    
    private int venda;
    private int idparcela;

    public PagamentoVendaID() 
    { }

    public PagamentoVendaID(int venda, int idparcela) {
        this.venda = venda;
        this.idparcela = idparcela;
    }

    public int getCod_venda() {
        return venda;
    }

    public void setCod_venda(int venda) {
        this.venda = venda;
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
        hash = 41 * hash + this.venda;
        hash = 41 * hash + this.idparcela;
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
        final PagamentoVendaID other = (PagamentoVendaID) obj;
        if (this.venda != other.venda) {
            return false;
        }
        return this.idparcela == other.idparcela;
    }

    
}
