/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Cliente;

/**
 *
 * @author Aluno
 */
public class ControleClienteDAO {
    
    public void adicionar(Cliente cliente){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.persist(cliente);      
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void alterar(Cliente cliente){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.merge(cliente);
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void remover(Cliente cliente){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       Cliente clienteRemovido = gerente.find(Cliente.class, cliente.getCodigo());
       gerente.remove(clienteRemovido);
       gerente.getTransaction().commit();
       gerente.close();
    }

    public List<Cliente> getAll()
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
        
        List<Cliente> listaClientes;
    
        TypedQuery<Cliente> consultaCliente = 
                gerente.createNamedQuery("Cliente.todos", Cliente.class);
        
        listaClientes = consultaCliente.getResultList();
        return listaClientes;
   }
   
    public List<Cliente> pesquisarPorNome(String nome)
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Cliente> consulta = gerente.createNamedQuery("cliente.PorNome", Cliente.class);
       consulta.setParameter("nomequalquer", "%"+nome+"%");
       return consulta.getResultList();
    }
    
    public List<Cliente> getPorEmail(String email)
    {

        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Cliente> consulta = 
                    gerente.createNamedQuery("cliente.porEmail", Cliente.class);
        
        consulta.setParameter("email", "%"+email+"%");
        return consulta.getResultList();   
    }
    
    public List<Cliente> getPorCPF(String cpf)
    {

        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Cliente> consulta = 
                    gerente.createNamedQuery("cliente.porCPF", Cliente.class);
        
        consulta.setParameter("cpf", "%"+cpf+"%");
        return consulta.getResultList();   
    }
    
    public List<Cliente> getPorTelefone(String telefone)
    {

        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Cliente> consulta = 
                    gerente.createNamedQuery("cliente.porTelefone", Cliente.class);
        
        consulta.setParameter("telefone", "%"+telefone+"%");
        return consulta.getResultList();   
    }
    
}
