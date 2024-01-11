/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Caixa;
import modelo.Sangria;

/**
 *
 * @author Evellyn Gomes
 */

public class ControleCaixaDAO {
    
    public static Caixa caixaAberto = null;
        
        public void abrir(Caixa caixa)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        gerente.getTransaction().begin();
        gerente.persist(caixa);
        gerente.getTransaction().commit();
        gerente.close();
        caixaAberto = caixa;
    }
    
    public void fechar(Caixa caixa)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        gerente.getTransaction().begin();
        gerente.merge(caixa);
        gerente.getTransaction().commit();
        gerente.close();
        caixaAberto = null;
    }
    
    public void alterar(Caixa caixa){
        EntityManager gerente = GerenciadorConexao.getGerente();
        gerente.getTransaction().begin();
        gerente.merge(caixa);
        gerente.getTransaction().commit();
        gerente.close();
    }
    
    public List<Caixa> getTodos()
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Caixa> consulta = gerente.createNamedQuery("Caixa.todas", Caixa.class);
        return consulta.getResultList();
    }
    
    public List<Sangria> listarSangrias()
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Sangria> consulta = gerente.createNamedQuery("Sangria.todasEspecifico", Sangria.class);
        return consulta.getResultList();
    }
    
    public List<Caixa> getPorPeriodo(Date inicio, Date fim)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Caixa> consulta = 
                    gerente.createNamedQuery("Caixa.porPeriodo", Caixa.class);
        
        consulta.setParameter("inicio", inicio);
        consulta.setParameter("fim", fim);
        return consulta.getResultList();
        
    }
    
    public void setCaixa(Caixa caixa){
        caixaAberto = caixa;
    }

    public static Caixa verificarCaixaAbertoBanco()
    {
        List<Caixa> caixas;

        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Caixa> consulta = 
                    gerente.createNamedQuery("Caixa.aberto", Caixa.class);
             
        caixas = consulta.getResultList();
        if(caixas.isEmpty())
            return null;
        else
            return caixas.get(0);
    }
    
    public static boolean isCaixaAberto()
    {
        if (caixaAberto == null)
            caixaAberto = verificarCaixaAbertoBanco();
        return caixaAberto != null;
    }

    public static Caixa getCaixaAberto() {
        if (caixaAberto == null)
        {
            caixaAberto = verificarCaixaAbertoBanco();
        }
        return caixaAberto;
    }
}
