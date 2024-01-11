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
    @NamedQuery(name = "Venda.todas",
                query = "SELECT v FROM Venda v"),
    @NamedQuery(name = "Venda.porPeriodo",
                query = "SELECT v FROM Venda v WHERE v.dataVenda BETWEEN :inicio AND :fim"),
    @NamedQuery(name = "Venda.porCodigo",
                query = "SELECT v FROM Venda v WHERE v.codigo =:codigo"),
    
    
})
@Table(name = "venda")
public class Venda implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_venda")
    private int codigo;
    
    @Column(name = "formaPagamento")
    private String formaPagamento;
    @Column(name = "valorTotal")
    private double valorTotal;
    @Temporal(TemporalType.DATE)
    @Column(name = "dataVenda")
    private Date dataVenda;
    @Column(name = "dataPagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    
    @ManyToOne
    @JoinColumn(name = "funcionario", referencedColumnName = "cod_funcionario")
    private Funcionario funcionario;
    
    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "cod_cliente")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "caixa", referencedColumnName = "cod_caixa")
    private Caixa caixa;
    
    @OneToMany(cascade = CascadeType.ALL,
               orphanRemoval = true,
               mappedBy = "venda") // atributo que possui o JoinColumn
    private List<ItensVenda> itens = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL,
               orphanRemoval = true,
               mappedBy = "venda")
    private List<Pagamento_venda> parcelas = new ArrayList<>();

    public Venda() 
    { }

    public Venda(int codigo, String formaPagamento, double valorTotal, Date dataVenda, Date dataPagamento, Funcionario funcionario, Cliente cliente, Caixa caixa) {
        this.codigo = codigo;
        this.formaPagamento = formaPagamento;
        this.valorTotal = valorTotal;
        this.dataVenda = dataVenda;
        this.dataPagamento = dataPagamento;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.caixa = caixa;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
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

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void adicionarItemVenda(ItensVenda i)
    {
        i.setVenda(this);
        i.setCodigo(this.itens.size()+1);  
        this.itens.add(i);
    }
    
    public void setItemVenda(List<ItensVenda> itens) {
        this.itens = itens;
    }
    
    public void removerProducao(ItensVenda i){
        this.itens.remove(i);
    }
    
    public void adicionarParcela(Pagamento_venda p)
    {
        p.setVenda(this);
        p.setIdparcela(this.parcelas.size()+1);
        if (p.getIdparcela() == 1) 
        {
            p.setStatus_pag("Pago");
        } else {
            p.setStatus_pag("Não Pago");
        }
        this.parcelas.add(p);
    }

    public List<ItensVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItensVenda> itens) {
        this.itens = itens;
    }

    public List<Pagamento_venda> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Pagamento_venda> parcelas) {
        this.parcelas = parcelas;
    }

    @Override
    public String toString() {
        return "Venda{" + "codigo=" + codigo + ", valorTotal=" + valorTotal + ", dataVenda=" + dataVenda + ", funcionario=" + funcionario + ", cliente=" + cliente + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.codigo;
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
        final Venda other = (Venda) obj;
        return this.codigo == other.codigo;
    }
    
}
