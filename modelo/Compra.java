/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Evellyn Gomes
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "Compra.todas",
                query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.porPeriodo",
                query = "SELECT c FROM Compra c WHERE c.dataCompra BETWEEN :inicio AND :fim"),
    @NamedQuery(name = "Compra.porCodigo",
                query = "SELECT c FROM Compra c WHERE c.codigo =:codigo")
})
@Table(name = "compra")
public class Compra implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_compra")
    private int codigo;
    @Column(name = "formaPagamento")
    private String formaPagamento;
    @Column(name = "valorTotal")
    private double valorTotal;
    @Column(name = "dataCompra")
    @Temporal(TemporalType.DATE)
    private Date dataCompra;
    @Column(name = "dataPagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    
    @ManyToOne
    @JoinColumn(name="fornecedor", referencedColumnName = "cod_fornecedor")
    private Fornecedor fornecedor;

    public Compra() 
    { }

    public Compra(int codigo, String formaPagamento, double valorTotal, Date dataCompra, Date dataPagamento, Fornecedor fornecedor) {
        this.codigo = codigo;
        this.formaPagamento = formaPagamento;
        this.valorTotal = valorTotal;
        this.dataCompra = dataCompra;
        this.dataPagamento = dataPagamento;
        this.fornecedor = fornecedor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    public String getFormaPagamento() {
        return formaPagamento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @OneToMany(cascade = CascadeType.ALL,
               orphanRemoval = true,
               mappedBy = "compra") 
    private List<ItensCompra> itens = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL,
               orphanRemoval = true,
               mappedBy = "compra")
    private List<Pagamento_compra> parcelas = new ArrayList<>();

    public void adicionarItemCompra(ItensCompra i)
    {
        i.setCompra(this);
        i.setCodigo(this.itens.size()+1);  
        this.itens.add(i);
    }
    
    public void setItemCompra(List<ItensCompra> itens) {
        this.itens = itens;
    }
    
    public void removerProducao(ItensCompra i){
        this.itens.remove(i);
    }
    
    public void adicionarParcela(Pagamento_compra p)
    {
        p.setCompra(this);
        p.setIdparcela(this.parcelas.size()+1);
        if (p.getIdparcela() == 1) 
        {
            p.setStatus_pag("Pago");
        } else {
            p.setStatus_pag("Não Pago");
        }
        this.parcelas.add(p);
    }

    public List<ItensCompra> getItens() {
        return itens;
    }

    public void setItens(List<ItensCompra> itens) {
        this.itens = itens;
    }

    public List<Pagamento_compra> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Pagamento_compra> parcelas) {
        this.parcelas = parcelas;
    }
    
    @Override
    public String toString() {
        return "Compra{" + "codigo=" + codigo + ", valorTotal=" + valorTotal + ", dataCompra=" + dataCompra + ", fornecedor=" + fornecedor + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.codigo;
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
        final Compra other = (Compra) obj;
        return this.codigo == other.codigo;
    }
    
}
