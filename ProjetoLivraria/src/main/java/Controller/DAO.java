/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.File;
import java.io.IOException;
import java.sql.*;


public abstract class DAO {
    protected Connection connection = null;
    private static final String origem = System.getProperty("user.dir");
    private static File caminho = new File(origem + "/src/main/java/Controller/configuracaobd.properties");
    public static final String pasta_relatorios = System.getProperty("user.dir") + "/src/relatorios/";
   
   
    public boolean conectarcomBD() {
            try {
                JDBCUtil.init(caminho);
                connection = JDBCUtil.getConnection();
                connection.setAutoCommit(false);
                return true;
            } catch (ClassNotFoundException erro) {
                System.out.println("Falha ao achar arquivo: " + erro);
            } catch (IOException erro) {
                System.out.println("Falha ao carregar arquivo: " + erro);
            } catch (SQLException erro) {
                System.out.println("Falha na conexao, comando sql = " + erro);
            }
            return false;
        }
        
        public boolean desconectar() {
            if (connection != null) {
                try {
                    connection.close();
                    return true;
                } catch (SQLException erro) {
                    System.err.println("Erro ao fechar a conexão: " + erro);
                    return false;
                }
            } else {
                return false;
            }
        }
       
        

}
