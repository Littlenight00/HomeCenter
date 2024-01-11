/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Compra;
import modelo.ItensCompra;
import modelo.Pagamento_compra;
import modelo.Produto;

/**
 *
 * @author Evellyn Gomes
 */

public class ControleCompraDAO {
    
    public void adicionar(Compra compra)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        gerente.getTransaction().begin();
        gerente.persist(compra);
        gerente.getTransaction().commit();
        gerente.close();
    }
    
    public void darBaixa(Compra c){
        EntityManager gerente = GerenciadorConexao.getGerente();
        gerente.getTransaction().begin(); 
        Produto produto = null;
        for(ItensCompra ic : c.getItens())
        {
            produto = ic.getProduto();
            produto.comprar(ic.getQtde());
            gerente.merge(produto);       
        }
        gerente.getTransaction().commit();
        gerente.close();
    }

    public void remover(Compra compra)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        Compra compraExcluir = gerente.find(Compra.class,compra.getCodigo());
        gerente.getTransaction().begin();
        gerente.remove(compraExcluir);
        gerente.getTransaction().commit();
        gerente.close();
    }
    
    public void alterar(Compra compra)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        gerente.getTransaction().begin();
        gerente.merge(compra);
        gerente.getTransaction().commit();
        gerente.close();
    }
    
    public List<Compra> getTodas()
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Compra> consulta = gerente.createNamedQuery("Compra.todas", Compra.class);
        return consulta.getResultList();
    }
    
    public List<Compra> getPorPeriodo(Date inicio, Date fim)
    {

        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Compra> consulta = 
                    gerente.createNamedQuery("Compra.porPeriodo", Compra.class);
        
        consulta.setParameter("inicio", inicio);
        consulta.setParameter("fim", fim);

        return consulta.getResultList();
        
    }    
    
    public List<Compra> getPorCodigo(int codigo)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Compra> consulta = 
                    gerente.createNamedQuery("Compra.porCodigo", Compra.class);
        
        consulta.setParameter("codigo", codigo);
        
        return consulta.getResultList();
    }
    
    public List<ItensCompra> getPorCompra(int compra)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<ItensCompra> consulta = 
                    gerente.createNamedQuery("ItensCompra.porCompra", ItensCompra.class);
        
        consulta.setParameter("compra", compra);
        
        return consulta.getResultList();
    }
    
    public List<Pagamento_compra> getParcelasNaoPagas()
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Pagamento_compra> consulta = 
                    gerente.createNamedQuery("pagamento_compra.naoPagas", Pagamento_compra.class);
        
        return consulta.getResultList();
    }
}
