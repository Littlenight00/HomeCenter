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
    @NamedQuery(name = "Caixa.todas",
            query="SELECT c FROM Caixa c"),
    @NamedQuery(name = "Caixa.aberto", 
            query="SELECT c FROM Caixa c WHERE c.status LIKE 'Aberto'")
})
@Table(name = "caixa")
public class Caixa implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "cod_caixa")
    private int codigo;
    
    @Column(name = "data_caixa")
    @Temporal(TemporalType.DATE)
    private Date data_caixa;
    
    @Column(name = "horarioAbertura")
    @Temporal(TemporalType.TIME)
    private Date horarioAbertura;
    
    @Column(name = "horarioFechamento")
    @Temporal(TemporalType.TIME)
    private Date horarioFechamento;
    
    @Column(name = "valorAbertura")
    private double valorAbertura;
    
    @Column(name = "valorEntrada")
    private double valorEntrada;
    
    @Column(name = "valorSaida")
    private double valorSaida;
    
    @Column(name = "saldo")
    private double saldo;
    
    @Column(name = "status_caixa")
    private String status;
    
    @OneToMany(cascade = CascadeType.ALL,
               orphanRemoval = true,
               mappedBy = "caixa")
    private List<Sangria> conjuntoMovimentacao = new ArrayList<>();

    public Caixa() 
    { }

    public Caixa(int codigo, Date data_caixa, Date horarioAbertura, Date horarioFechamento, double valorAbertura, double valorEntrada, double valorSaida, double saldo, String status) {
        this.codigo = codigo;
        this.data_caixa = data_caixa;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.valorAbertura = valorAbertura;
        this.valorEntrada = valorEntrada;
        this.valorSaida = valorSaida;
        this.saldo = saldo;
        this.status = status;
    }
    
    public double retorneSaidas(){
        double saida = 0;
        for(Sangria s : conjuntoMovimentacao){
            if(!s.getMotivo().equals("Fechamento de Caixa")){
                saida += s.getValor();
            }
        }
        
        return saida;
    }
    
     public double retorneEntradas(){
        double entrada = 0;
        for(Sangria s : conjuntoMovimentacao){
            entrada += s.getValor(); 
        }
        
        return entrada;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getData_caixa() {
        return data_caixa;
    }

    public void setData_caixa(Date data_caixa) {
        this.data_caixa = data_caixa;
    }

    public Date getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(Date horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public Date getHorarioFechamento() {
        return horarioFechamento;
    }

    public void setHorarioFechamento(Date horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

    public List<Sangria> getConjuntoMovimentacao() {
        return conjuntoMovimentacao;
    }

    public void setConjuntoMovimentacao(List<Sangria> conjuntoMovimentacao) {
        this.conjuntoMovimentacao = conjuntoMovimentacao;
    }

    public void adicionarMovimentacao(Sangria s)
    {
        s.setCaixa(this);
        s.setCod_sangria(this.conjuntoMovimentacao.size()+1);     
        this.conjuntoMovimentacao.add(s);
    }
    
    public void removerMovimentacao(Sangria s){
        this.conjuntoMovimentacao.remove(s);
    }
    
    public double getValorAbertura() {
        return valorAbertura;
    }

    public void setValorAbertura(double valorAbertura) {
        this.valorAbertura = valorAbertura;
    }

    public double getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public double getValorSaida() {
        return valorSaida;
    }

    public void setValorSaida(double valorSaida) {
        this.valorSaida = valorSaida;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.codigo;
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
        final Caixa other = (Caixa) obj;
        return this.codigo == other.codigo;
    }

}
