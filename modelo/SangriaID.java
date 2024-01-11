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

public class SangriaID implements Serializable {
    
    private int caixa;
    private int cod_sangria;

    public SangriaID() 
    { }

    public SangriaID(int caixa, int cod_sangria) {
        this.caixa = caixa;
        this.cod_sangria = cod_sangria;
    }

    public int getCod_caixa() {
        return caixa;
    }

    public void setCod_caixa(int caixa) {
        this.caixa = caixa;
    }

    public int getCod_sangria() {
        return cod_sangria;
    }

    public void setCod_sangria(int cod_sangria) {
        this.cod_sangria = cod_sangria;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.caixa;
        hash = 59 * hash + this.cod_sangria;
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
        final SangriaID other = (SangriaID) obj;
        if (this.caixa != other.caixa) {
            return false;
        }
        return this.cod_sangria == other.cod_sangria;
    }

}
