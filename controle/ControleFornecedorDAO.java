/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Fornecedor;

/**
 *
 * @author Aluno
 */
public class ControleFornecedorDAO {
    
    private static final ArrayList<Fornecedor> conjuntoFornecedores = new ArrayList<>();
    
    public void adicionar(Fornecedor fornecedor){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.persist(fornecedor);      
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void alterar(Fornecedor fornecedor){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.merge(fornecedor);
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void remover(Fornecedor fornecedor){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       Fornecedor fornecedorRemovido = gerente.find(Fornecedor.class, fornecedor.getCodigo());
       gerente.remove(fornecedorRemovido);
       gerente.getTransaction().commit();
       gerente.close();
    }

    public List<Fornecedor> getAll()
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Fornecedor> consulta = gerente.createNamedQuery("Fornecedor.todas", Fornecedor.class);
       return consulta.getResultList();   
   }
   
    public List<Fornecedor> pesquisarPorNome(String nome)
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Fornecedor> consulta = gerente.createNamedQuery("Fornecedor.porNome", Fornecedor.class);
       consulta.setParameter("nomequalquer", "%"+nome+"%");
       return consulta.getResultList();
    }
}
