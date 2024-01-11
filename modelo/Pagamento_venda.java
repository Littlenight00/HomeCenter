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
@NamedQueries({
    @NamedQuery(name = "Pagamento_venda.Todos", 
                query = "SELECT p FROM Pagamento_venda p"),
    @NamedQuery(name = "Pagamento_venda.Cliente", 
                query = "SELECT p FROM Pagamento_venda p where p.venda.cliente.nome like :nomequalquer"),
    @NamedQuery(name = "pagamento_venda.porCodigo", 
                query = "SELECT p FROM Pagamento_venda p where p.idparcela =:codigo"),
    @NamedQuery(name = "pagamento_venda.naoPagas",
                query = "SELECT p FROM Pagamento_venda p WHERE p.status_pag LIKE 'Não Pago'")
})
@Table(name = "pagamento_venda")
@IdClass(PagamentoVendaID.class)
public class Pagamento_venda implements Serializable {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "cod_venda", referencedColumnName = "cod_venda")
    private Venda venda;
    
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
    @JoinColumn(name = "cod_caixa", referencedColumnName = "cod_caixa")
    private Caixa caixa;

    public Pagamento_venda() { }

    public Pagamento_venda(Venda venda, int idparcela, Date vencimento, double valor, String status_pag, Caixa caixa) {
        this.venda = venda;
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

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
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
        return "Pagamento_venda{" + "venda=" + venda + ", idparcela=" + idparcela + ", vencimento=" + vencimento + ", valor=" + valor + ", caixa=" + caixa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.venda);
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
        final Pagamento_venda other = (Pagamento_venda) obj;
        return Objects.equals(this.venda, other.venda);
    }

    public void setValue(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
