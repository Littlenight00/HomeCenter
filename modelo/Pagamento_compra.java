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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Evellyn Gomes
 */

@Entity
@Table(name = "pagamento_compra")
@NamedQueries({
    @NamedQuery(name = "Pagamento_compra.porPeriodo", query="SELECT p FROM Pagamento_compra p WHERE p.vencimento BETWEEN :inicio AND :fim"),
    @NamedQuery(name = "Pagamento_compra.Todos", query = "SELECT p FROM Pagamento_compra p"),
    @NamedQuery(name = "Pagamento_compra.Fornecedor", 
                query = "SELECT p FROM Pagamento_compra p where p.compra.fornecedor.nome like :nomequalquer"),
    @NamedQuery(name = "pagamento_compra.porCodigo", 
                query = "SELECT p FROM Pagamento_compra p where p.idparcela =:codigo"),
    @NamedQuery(name = "pagamento_compra.naoPagas",
                query = "SELECT p FROM Pagamento_compra p WHERE p.status_pag LIKE 'Não Pago'")
})
@IdClass(PagamentoCompraID.class)
public class Pagamento_compra implements Serializable {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "cod_compra", referencedColumnName = "cod_compra")
    private Compra compra;
    
    @Id
    @Column(name = "idparcela")
    private int idparcela;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "vencimento")
    private Date vencimento;
    @Column(name = "valor")
    private double valor;
    @Column(name = "status_pag")
    private String status_pag;
    
    @ManyToOne
    @JoinColumn(name = "caixa", referencedColumnName = "cod_caixa")
    private Caixa caixa;

    public Pagamento_compra() 
    { }

    public Pagamento_compra(Compra compra, int idparcela, Date vencimento, double valor, String status_pag, Caixa caixa) {
        this.compra = compra;
        this.idparcela = idparcela;
        this.vencimento = vencimento;
        this.valor = valor;
        this.status_pag = status_pag;
        this.caixa = caixa;
    }

    public String getStatus_pag() {
        return status_pag;
    }

    public void setStatus_pag(String status_pag) {
        this.status_pag = status_pag;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public int getIdparcela() {
        return idparcela;
    }

    public void setIdparcela(int idparcela) {
        this.idparcela = idparcela;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    @Override
    public String toString() {
        return "Pagamento_compra{" + "compra=" + compra + ", idparcela=" + idparcela + ", vencimento=" + vencimento + ", valor=" + valor + ", status_pag=" + status_pag + ", caixa=" + caixa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.compra);
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
        final Pagamento_compra other = (Pagamento_compra) obj;
        return Objects.equals(this.compra, other.compra);
    }
    
}
