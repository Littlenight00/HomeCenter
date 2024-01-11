///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package controle;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.ItensVenda;
import modelo.Pagamento_venda;
import modelo.Produto;
import modelo.Venda;

/**
 *
 * @author Evellyn Gomes
 */

public class ControleVendaDAO {
    
    public void adicionar(Venda venda)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        gerente.getTransaction().begin();
        gerente.persist(venda);
        gerente.getTransaction().commit();
        gerente.close();
    }
    
    public void darBaixa(Venda v){
        EntityManager gerente = GerenciadorConexao.getGerente();
        gerente.getTransaction().begin();
        Produto p = null;
        for(ItensVenda iv : v.getItens())
        {
            p = iv.getProduto();
            p.vender(iv.getQtde());
            gerente.merge(p);       
        }
        gerente.getTransaction().commit();
        gerente.close();
    }

    public void remover(Venda venda)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        Venda vendaExcluir = gerente.find(Venda.class,venda.getCodigo());
        gerente.getTransaction().begin();
        gerente.remove(vendaExcluir);
        gerente.getTransaction().commit();
        gerente.close();
    }
    
    public void alterar(Venda venda)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        gerente.getTransaction().begin();
        gerente.merge(venda);
        gerente.getTransaction().commit();
        gerente.close();
    }
    
    /**
     *
     * @return
     */
    public List<Venda> getTodas()
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Venda> consulta = 
                    gerente.createNamedQuery("Venda.todas", Venda.class);
        return consulta.getResultList();
        
    }
    
    public List<Venda> getPorPeriodo(Date inicio, Date fim)
    {

        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Venda> consulta = 
                    gerente.createNamedQuery("Venda.porPeriodo", Venda.class);
        
        consulta.setParameter("inicio", inicio);
        consulta.setParameter("fim", fim);

        return consulta.getResultList();
    }

    public List<Pagamento_venda> getVenda(int venda)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Pagamento_venda> consulta = gerente.createNamedQuery("Pagamento_venda.quero", Pagamento_venda.class);
        consulta.setParameter("venda", venda);
        return consulta.getResultList();
    }

    public List<Venda> getPorCodigo(int codigo)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Venda> consulta = 
                    gerente.createNamedQuery("Venda.porCodigo", Venda.class);
        
        consulta.setParameter("codigo", codigo);
        
        return consulta.getResultList();
    }
    
    public List<ItensVenda> getPorVenda(int venda)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<ItensVenda> consulta = 
                    gerente.createNamedQuery("ItensVenda.porVenda", ItensVenda.class);
        
        consulta.setParameter("venda", venda);
        
        return consulta.getResultList();
    }
    
    public List<ItensVenda> getTodasVendas()
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<ItensVenda> consulta = 
                    gerente.createNamedQuery("ItensVenda.todas", ItensVenda.class);
        
        return consulta.getResultList();
    }
    
    public List<Pagamento_venda> getParcelasNaoPagas()
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Pagamento_venda> consulta = 
                    gerente.createNamedQuery("pagamento_venda.naoPagas", Pagamento_venda.class);
        
        return consulta.getResultList();
    }
}
