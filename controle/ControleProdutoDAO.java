/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Produto;

/**
 *
 * @author Aluno
 */
public class ControleProdutoDAO {
    
    private static final ArrayList<Produto> conjuntoProdutos = new ArrayList<>();
    
    public void adicionar(Produto produto){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.persist(produto);      
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void alterar(Produto produto){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.merge(produto);
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void remover(Produto produto){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       Produto produtoRemovido = gerente.find(Produto.class, produto.getCodigo());
       gerente.remove(produtoRemovido);
       gerente.getTransaction().commit();
       gerente.close();
    }

    public List<Produto> getAll()
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Produto> consulta = gerente.createNamedQuery("produto.Todos", Produto.class);
       return consulta.getResultList();   
   }
   
    public List<Produto> pesquisarPorNome(String nome)
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Produto> consulta = gerente.createNamedQuery("produto.PorNome", Produto.class);
       consulta.setParameter("nomequalquer", "%"+nome+"%");
       return consulta.getResultList();
    }
    
    public List<Produto> getPorCodigo(int codigo)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Produto> consulta = 
                    gerente.createNamedQuery("produto.Comparar", Produto.class);
        consulta.setParameter("codigo", + codigo);
        return consulta.getResultList();
    }
    
    public List<Produto> getPorQtde()
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Produto> consulta = gerente.createNamedQuery("produto.porQtde", Produto.class);
        return consulta.getResultList();
    }
}
