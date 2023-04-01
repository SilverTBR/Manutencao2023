/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class RenovarDAO extends DAO{
        
        private PreparedStatement pstdados = null;
        private ResultSet rsdados = null;
    
        private static final String renovarAluguel = "UPDATE aluguel SET data_devolucao = ? WHERE id_aluguel = ?";
        
        public ResultSet getrsdados(){
            return rsdados;
        }

        public boolean devolucaoAlu(String data, int id) {
            conectarcomBD();
            if(data.replaceAll("\\s+","").length() != 10){
                JOptionPane.showMessageDialog(null, "Campo da data de devolução está invalido!\n"
                + "Por favor, verifique o campo de data de devolução", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
                return false; 
            }
                try {
                    int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                    int concorrencia = ResultSet.CONCUR_UPDATABLE;
                    pstdados = connection.prepareStatement(renovarAluguel, tipo, concorrencia);
                    pstdados.setString(1, data);
                    pstdados.setInt(2, id);
                    int resposta = pstdados.executeUpdate(); 
                    pstdados.close();
            
                    if (resposta >= 1) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }
                } catch (SQLException erro) {
                    System.out.println("Erro na execução da exclusão: " + erro);
                }
                return false;
            }
            
        }
