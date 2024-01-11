/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

/**
 *
 * @author Aluno
 */

public class FuncionarioOuSenhaIncorretaException extends Exception {
    
    /**
     * Creates a new instance of <code>FuncionarioOuSenhaIncorreta</code> without
     * detail message.
     */
    public FuncionarioOuSenhaIncorretaException() {
    }

    /**
     * Constructs an instance of <code>FuncionarioOuSenhaIncorreta</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FuncionarioOuSenhaIncorretaException(String msg) {
        super(msg);
    }
    
}
