/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Sangria;

/**
 *
 * @author Evellyn Gomes
 */
public class ControleSangria {
    
    public void adicionar(Sangria sangria){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.persist(sangria);      
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void alterar(Sangria sangria){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       gerente.merge(sangria);
       gerente.getTransaction().commit();
       gerente.close();
    }
   
    public void remover(Sangria sangria){
       EntityManager gerente = GerenciadorConexao.getGerente();
       gerente.getTransaction().begin();
       Sangria sangriaRemovida = gerente.find(Sangria.class, sangria.getCod_sangria());
       gerente.remove(sangriaRemovida);
       gerente.getTransaction().commit();
       gerente.close();
    }

    public List<Sangria> getAll()
    {
       EntityManager gerente = GerenciadorConexao.getGerente();
       TypedQuery<Sangria> consulta = gerente.createNamedQuery("Sangria.todas", Sangria.class);
       return consulta.getResultList();   
   }
   
//    public List<Sangria> pesquisarPorNome(String nome)
//    {
//       EntityManager gerente = GerenciadorConexao.getGerente();
//       TypedQuery<Sangria> consulta = gerente.createNamedQuery("Sangria.porNome", Sangria.class);
//       consulta.setParameter("nomequalquer", "%"+nome+"%");
//       return consulta.getResultList();
//    }
    
}
