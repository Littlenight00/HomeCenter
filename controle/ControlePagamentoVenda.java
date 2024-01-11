/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Pagamento_venda;

/**
 *
 * @author Evellyn Gomes
 */

public class ControlePagamentoVenda {
    
    public void adicionar(Pagamento_venda pagamento){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.persist(pagamento);      
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void alterar(Pagamento_venda pagamento){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.merge(pagamento);
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void remover(Pagamento_venda pagamento){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       Pagamento_venda pagamentoRemovido = gerente.find(Pagamento_venda.class, pagamento.getIdparcela());
       gerente.remove(pagamentoRemovido);
       gerente.getTransaction().commit();
       gerente.close();
    }

    public List<Pagamento_venda> getAll()
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Pagamento_venda> consulta = gerente.createNamedQuery("Pagamento_venda.Todos", Pagamento_venda.class);
       return consulta.getResultList();   
    }
   
    public List<Pagamento_venda> pesquisarPorNomeCliente(String nome)
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Pagamento_venda> consulta = gerente.createNamedQuery("Pagamento_venda.Cliente", Pagamento_venda.class);
       consulta.setParameter("nomequalquer", "%"+nome+"%");
       return consulta.getResultList();
    }
    
    
}
