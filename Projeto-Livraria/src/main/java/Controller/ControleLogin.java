/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import configs.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

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
    private static final String inserirConta = "INSERT INTO administradores (login, senha) VALUES (?, ?)";
    
        public boolean verificarConta(String loginPassado, String senhaPassada){
            conectarcomBD();
            try {
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                pstdados = connection.prepareStatement(recuperarConta, tipo, concorrencia);
                rsdados = pstdados.executeQuery();
                
                while (rsdados.next()){
                     login = rsdados.getString("login");
                     senha = rsdados.getString("senha");
                    if(login.equals(loginPassado) && senha.equals(senhaPassada)){
                        return true;
                    }
                }
                return false;
            } catch (SQLException erro) {
                System.out.println(erro);
            }
            return false;
        }   
        
    public boolean inserirADMs(String login, String senha){  
            conectarcomBD();
            try {
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                pstdados = connection.prepareStatement(inserirConta, tipo, concorrencia);

                pstdados.setString(1, login);
                pstdados.setString(2, senha);
                int resposta = pstdados.executeUpdate();
                pstdados.close();
    
                if (resposta == 1) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }

            } catch (SQLException erro) {
                System.out.println("Erro ao inserir: " + erro);
            }
            return false;
    }
        
}
