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
import modelo.Departamento;

/**
 *
 * @author Aluno
 */
public class ControleDepartamentoDAO {
    
    private static final ArrayList<Departamento> conjuntoDepartamentos = new ArrayList<>();
    
    public void adicionar(Departamento departamento){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.persist(departamento);      
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void alterar(Departamento departamento){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.merge(departamento);
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void remover(Departamento departamento){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       Departamento departamentoRemovido = gerente.find(Departamento.class, departamento.getCodigo());
       gerente.remove(departamentoRemovido);
       gerente.getTransaction().commit();
       gerente.close();
    }

    public List<Departamento> getAll()
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Departamento> consulta = gerente.createNamedQuery("Departamento.todas", Departamento.class);
       return consulta.getResultList();   
   }
   
    public List<Departamento> pesquisarPorNome(String nome)
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Departamento> consulta = gerente.createNamedQuery("Departamento.porNome", Departamento.class);
       consulta.setParameter("nomequalquer", "%"+nome+"%");
       return consulta.getResultList();
    }
    
}
