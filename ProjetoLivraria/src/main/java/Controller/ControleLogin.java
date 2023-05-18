/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import configs.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Guilherme Andreotti
 */
public class ControleLogin extends DAO{
    
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    String login = null;
    String senha = null;
    
    private static final String recuperarConta = "SELECT login, senha FROM administradores";
    
        public boolean verificarConta(String loginPassado, String senhaPassada){
            conectarcomBD();
            try {
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                pstdados = connection.prepareStatement(recuperarConta, tipo, concorrencia);
                rsdados = pstdados.executeQuery();
                
                while (rsdados.next()) {
                    login = rsdados.getString("login");
                    senha = rsdados.getString("senha");
                }
                
                if(login.equals(loginPassado) && senha.equals(senhaPassada)){
                    return true;  
                }
                return false;
            } catch (SQLException erro) {
                System.out.println(erro);
            }
            return false;
        }   
}
