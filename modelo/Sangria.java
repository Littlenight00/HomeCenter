/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Evellyn Gomes
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "Sangria.todas", 
                query = "SELECT s FROM Sangria s"
    ),
    @NamedQuery(name = "Sangria.todasEspecifico", 
                query = "SELECT s FROM Sangria s ORDER BY s.caixa"
    ),
})
@IdClass(SangriaID.class)
public class Sangria implements Serializable {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "cod_caixa", referencedColumnName = "cod_caixa")
    private Caixa caixa;
    
    @Id
    @Column(name = "cod_sangria")
    private int cod_sangria;
    
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "valor")
    private double valor;
    @Column(name = "data_sangria")
    @Temporal(TemporalType.DATE)
    private Date data_sangria;
    @Column(name = "tipo")
    private String tipo;

    public Sangria() 
    { }

    public Sangria(Caixa caixa, int cod_sangria, String motivo, double valor, Date data_sangria, String tipo) {
        this.caixa = caixa;
        this.cod_sangria = cod_sangria;
        this.motivo = motivo;
        this.valor = valor;
        this.data_sangria = data_sangria;
        this.tipo = tipo;
    }

    public int getCod_sangria() {
        return cod_sangria;
    }

    public void setCod_sangria(int cod_sangria) {
        this.cod_sangria = cod_sangria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData_sangria() {
        return data_sangria;
    }

    public void setData_sangria(Date data_sangria) {
        this.data_sangria = data_sangria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.caixa);
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
        final Sangria other = (Sangria) obj;
        if (this.cod_sangria != other.cod_sangria) {
            return false;
        }
        return Objects.equals(this.caixa, other.caixa);
    }

}
