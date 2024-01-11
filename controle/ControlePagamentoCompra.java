/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Cliente;
import modelo.Pagamento_compra;

/**
 *
 * @author Evellyn Gomes
 */

public class ControlePagamentoCompra {
    
    public void adicionar(Pagamento_compra pagamento){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.persist(pagamento);      
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void alterar(Pagamento_compra pagamento){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.merge(pagamento);
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void remover(Pagamento_compra pagamento){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       Pagamento_compra pagamentoRemovido = gerente.find(Pagamento_compra.class, pagamento.getIdparcela());
       gerente.remove(pagamentoRemovido);
       gerente.getTransaction().commit();
       gerente.close();
    }

    public List<Pagamento_compra> getAll()
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Pagamento_compra> consulta = gerente.createNamedQuery("Pagamento_compra.Todos", Pagamento_compra.class);
       return consulta.getResultList();   
   }
   
    public List<Pagamento_compra> getPorPeriodo(Date inicio, Date fim)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Pagamento_compra> consulta = 
                    gerente.createNamedQuery("Pagamento_compra.porPeriodo", Pagamento_compra.class);
        
        consulta.setParameter("inicio", inicio);
        consulta.setParameter("fim", fim);
        return consulta.getResultList();
    }
    
    public List<Pagamento_compra> pesquisarPorNomeFornecedor(String nome)
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Pagamento_compra> consulta = gerente.createNamedQuery("Pagamento_compra.Fornecedor", Pagamento_compra.class);
       consulta.setParameter("nomequalquer", "%"+nome+"%");
       return consulta.getResultList();
    }
    
    public List<Pagamento_compra> getPorCodigo(int codigo)
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Pagamento_compra> consulta = gerente.createNamedQuery("pagamento_compra.porCodigo", Pagamento_compra.class);
       consulta.setParameter("codigo", codigo);
       return consulta.getResultList();
    }
}
