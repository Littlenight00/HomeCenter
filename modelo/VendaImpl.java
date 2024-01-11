/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;


public class VendaImpl extends Venda {

    public VendaImpl() {
    }

    public VendaImpl(int codigo, String formaPagamento, double valorTotal, Date dataVenda, Date dataPagamento, Funcionario funcionario, Cliente cliente, Caixa caixa) {
        super(codigo, formaPagamento, valorTotal, dataVenda, dataPagamento, funcionario, cliente, caixa);
    }
    
}
