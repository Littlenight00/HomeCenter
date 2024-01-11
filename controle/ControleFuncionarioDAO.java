/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Funcionario;

/**
 *
 * @author Aluno
 */
public class ControleFuncionarioDAO {
    
    private static final ArrayList<Funcionario> conjuntoFuncionarios = new ArrayList<>();
    
    public void adicionar(Funcionario funcionario){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.persist(funcionario);      
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void alterar(Funcionario funcionario){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.merge(funcionario);
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void remover(Funcionario funcionario){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       Funcionario funcionarioRemovido = gerente.find(Funcionario.class, funcionario.getCodigo());
       gerente.remove(funcionarioRemovido);
       gerente.getTransaction().commit();
       gerente.close();
    }

    public List<Funcionario> getAll()
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Funcionario> consulta = gerente.createNamedQuery("funcionario.Todos", Funcionario.class);
       return consulta.getResultList();    
   }
   
    public List<Funcionario> pesquisarPorNome(String nome)
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Funcionario> consulta = gerente.createNamedQuery("funcionario.PorNome", Funcionario.class);
       consulta.setParameter("nomequalquer", "%"+nome+"%");
       return consulta.getResultList();
    }
    
}
