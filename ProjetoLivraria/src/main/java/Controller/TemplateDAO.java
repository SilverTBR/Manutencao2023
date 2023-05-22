/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import configs.DAO;
import Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author Guilherme Andreotti
 */

public abstract class TemplateDAO extends DAO{
     
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    
    public void executarCadastro() {

    }
    
    // Vai reescrever a setDados para determinada classe, consultar Livro, Cliente e Aluguel para ver o Overwrite.
    protected abstract void setDados(PreparedStatement pstdados) throws SQLException;
    
    protected abstract void zerarCampos();
    
    protected abstract boolean verificarCampos();
    
    protected abstract TableModel GerarTabelaSimples();
    
    public ResultSet getrsdados(){
        return rsdados;
    }
    
    public void setRsDados(ResultSet novo){
        this.rsdados = novo;
    }
              
    protected boolean inserirDados(String inserir){                  
        if(verificarCampos()){
            try {
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                pstdados = connection.prepareStatement(inserir, tipo, concorrencia);

                setDados(pstdados);   //Vai ser sobreescrito a cada tipo, cliente, livro e aluguel.   
                int resposta = pstdados.executeUpdate();
                pstdados.close();
                zerarCampos(); //Esse tmb.

                if (resposta == 1) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                                JOptionPane.showMessageDialog(null,"Limites de alugueis pelo cliente excedido","FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

            } catch (SQLException erro) {
                System.out.println("Erro ao inserir: " + erro);
            }
        }

        return false;    
    }
    
    protected boolean excluirDados(String excluir) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(excluir, tipo, concorrencia);
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
    
    protected boolean consultarTodosDados(String consultar){
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(consultar, tipo, concorrencia);
            rsdados = pstdados.executeQuery();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta: " + erro);
        }
        return false;    
    }
    
    protected boolean consultarCountDados(String consultarCount){
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(consultarCount, tipo, concorrencia);
            rsdados = pstdados.executeQuery();
            rsdados.next();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta: " + erro);
        }
        return false;
    }
    
    public boolean verificarID(int ID, String verificar){
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(verificar, tipo, concorrencia);
            pstdados.setInt(1, ID);
            rsdados = pstdados.executeQuery();
            if (rsdados.next()) {
                return false;
            }
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao verificar cliente por ID: " + erro);
        }
        return true;
    }
    
    public boolean consultarSimples(String busca, String consultarSimples) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(consultarSimples, tipo, concorrencia);
            pstdados.setString(1, busca);
            rsdados = pstdados.executeQuery();
            setRsDados(rsdados);
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar pesquisa simples: " + erro);
        }
        return false;
    }
    
    public boolean verificarCPFT(String verCPF, Cliente cliente){
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(verCPF, tipo, concorrencia);
            pstdados.setString(1, cliente.getCPF());
            rsdados = pstdados.executeQuery();
            if(rsdados.next()) {
                return true;
            }
            return false;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar verificação de CPF = " + erro);
        }
        return false;
    }
    
        public boolean pesquisar(String busca, String pesquisa) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(pesquisa, tipo, concorrencia);
            pstdados.setString(1, busca);
            rsdados = pstdados.executeQuery();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar pesquisa: " + erro);
        }
        return false;
    } 
    
    
}
