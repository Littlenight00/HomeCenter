/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import modelo.Funcionario;

/**
 *
 * @author Aluno
 */
public class ControleFuncionarios {
    
    private static Funcionario funcionarioLogado = null;

    public static Funcionario login(JFormattedTextField cpf, JPasswordField senha, Object selecteditem) throws FuncionarioOuSenhaIncorretaException {
        List<Funcionario> funcionarios = null;

        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Funcionario> consulta = 
                    gerente.createNamedQuery("Funcionario.login", Funcionario.class);
        
        consulta.setParameter("login", cpf);
        consulta.setParameter("senha", senha);
        consulta.setParameter("acesso", selecteditem);
              
        funcionarios = consulta.getResultList();
        if(funcionarios.isEmpty())
            throw new FuncionarioOuSenhaIncorretaException("Verfique os dados inseridos.");
        else
            ControleFuncionarios.funcionarioLogado = funcionarios.get(0);
        
        return funcionarios.get(0);
    }
    
    public void adicionar(Funcionario funcionario)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        gerente.getTransaction().begin();
        gerente.persist(funcionario);
        gerente.getTransaction().commit();
        gerente.close();
    }

    public void remover(Funcionario funcionario)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        Funcionario funcionarioExcluir = gerente.find(Funcionario.class, 
                                       funcionario.getCodigo());
        gerente.getTransaction().begin();
        gerente.remove(funcionarioExcluir);
        gerente.getTransaction().commit();
        gerente.close();
    }
    
    public void alterar(Funcionario funcionario)
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        gerente.getTransaction().begin();
        gerente.merge(funcionario);
        gerente.getTransaction().commit();
        gerente.close();
    }
    
    public List<Funcionario> getTodos()
    {
        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Funcionario> consulta = 
                    gerente.createNamedQuery("Funcionario.todos", Funcionario.class);
        return consulta.getResultList();
        
    }
    
    public List<Funcionario> getPorNome(String nome)
    {

        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Funcionario> consulta = 
                    gerente.createNamedQuery("Funcionario.porNome", Funcionario.class);
        
        consulta.setParameter("nome", "%"+nome+"%");
        return consulta.getResultList();
        
    }    
    
    public static Funcionario login(String login, String senha) throws FuncionarioOuSenhaIncorretaException
    {
        List<Funcionario> funcionarios = null;

        EntityManager gerente = GerenciadorConexao.getGerente();
        TypedQuery<Funcionario> consulta = 
                    gerente.createNamedQuery("Funcionario.login", Funcionario.class);
        
        consulta.setParameter("login", login);
        consulta.setParameter("senha", senha);
       
        funcionarios = consulta.getResultList();
        if(funcionarios.isEmpty())
            throw new FuncionarioOuSenhaIncorretaException("O funcionario ou senha digitada está incorreta.");
        else
            ControleFuncionarios.funcionarioLogado = funcionarios.get(0);
        
        return funcionarios.get(0);
    }
    
    public static void logout(){
        ControleFuncionarios.funcionarioLogado = null;
    }
    
    public static boolean isFuncionarioLogado()
    {
        return funcionarioLogado != null;
    }

    public static Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }
    
    public static boolean isUsuarioLogado()
    {
        return funcionarioLogado != null;
    }
    
    public static void setFuncionarioLogado(Funcionario f) {
        funcionarioLogado = f;
    }
    
}
