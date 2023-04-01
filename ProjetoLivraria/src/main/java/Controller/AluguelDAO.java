/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Aluguel;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AluguelDAO extends DAO{
    
    ClienteDAO controleCliente = new ClienteDAO();
    LivroDAO controleLivro = new LivroDAO();
    Aluguel Aluguel = new Aluguel();
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    RelatorioEmprestimo controleRelatorio = null;

    
    private static final String inserirAluguel = "INSERT INTO aluguel (id_cliente, id_livro, data_aluguel, data_devolucao, devolucao) VALUES (?, ?, ?, ?, false)";
    private static final String consultarAluguel = "SELECT id_aluguel, aluguel.id_cliente, nome, sobrenome, livro.id_livro, titulo, data_aluguel, data_devolucao FROM aluguel, cliente, livro where aluguel.id_cliente = cliente.id_cliente and aluguel.id_livro = livro.id_livro and devolucao = 'false' group by aluguel.id_aluguel, aluguel.id_cliente, livro.id_livro,cliente.nome, cliente.sobrenome order by aluguel.id_aluguel, aluguel.id_cliente asc";
    private static final String buscarAluguel = "SELECT id_aluguel, aluguel.id_cliente, nome, sobrenome, livro.id_livro, titulo, data_aluguel, data_devolucao FROM aluguel, cliente, livro where aluguel.id_cliente = cliente.id_cliente and aluguel.id_livro = livro.id_livro and devolucao = 'false' and cliente.nome ilike ? group by aluguel.id_aluguel, aluguel.id_cliente, livro.id_livro,cliente.nome, cliente.sobrenome order by aluguel.id_aluguel, aluguel.id_cliente asc";
    private static final String excluirTudo = "delete from aluguel";
    private static final String consultarCount = "SELECT COUNT(id_aluguel) FROM aluguel";
    private static final String devolucaoAluguel = "UPDATE aluguel SET devolucao = 'true' WHERE id_aluguel = ?";

    public Aluguel getAluguel(){
        return Aluguel;
    }
    
    public ResultSet getrsdados(){
        return rsdados;
    }
    
    public void chamarRelatorio(boolean visu){
        controleRelatorio = new RelatorioEmprestimo();
        controleRelatorio.geraRelatorio(visu);
    }
    
    public boolean inserir() {
        if(verificarCampos()){
            try {
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                pstdados = connection.prepareStatement(inserirAluguel, tipo, concorrencia);
                pstdados.setInt(1, Aluguel.getIdCliente());
                pstdados.setInt(2, Aluguel.getIdLivro());
                pstdados.setString(3, Aluguel.getDataAluguel());
                pstdados.setString(4, Aluguel.getDataDev());
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
        }
        return false;
    }
    
      public boolean excluir() {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(excluirTudo, tipo, concorrencia);
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
      
    public boolean devolucaoAluguel(int id) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(devolucaoAluguel, tipo, concorrencia);
            pstdados.setInt(1, id);
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
      
      
      
    public boolean consultarTodosAluguel() {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(consultarAluguel, tipo, concorrencia);
            rsdados = pstdados.executeQuery();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }
        
    public boolean consultarCount() {
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
    
    
    public TableModel AluguelgerarTabela(){
        int linha = 0;
        DefaultTableModel modeloJT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        try {
            
        int qntCol = rsdados.getMetaData().getColumnCount();
        for(int col = 1; col<= qntCol; col++){
            modeloJT.addColumn(rsdados.getMetaData().getColumnLabel(col));

        }

            while(rsdados != null && rsdados.next()){
                modeloJT.addRow(new Object[0]);
                modeloJT.setValueAt(rsdados.getInt("id_aluguel"), linha, 0);
                modeloJT.setValueAt(rsdados.getInt("id_cliente"), linha, 1);
                modeloJT.setValueAt(rsdados.getString("nome"), linha, 2);
                modeloJT.setValueAt(rsdados.getString("sobrenome"), linha, 3);
                modeloJT.setValueAt(rsdados.getInt("id_livro"), linha, 4);
                modeloJT.setValueAt(rsdados.getString("titulo"), linha, 5);
                modeloJT.setValueAt(rsdados.getString("data_aluguel"), linha, 6);
                modeloJT.setValueAt(rsdados.getString("data_devolucao"), linha, 7);
               

                linha++;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao gerar tabela: "+ erro);
        }
        return modeloJT;
    }
        
    public boolean verCliente(int id_cliente){
        controleCliente.conectarcomBD();
        boolean res = controleCliente.verificarIDCliente(id_cliente);
        controleCliente.desconectar();
        return res;
    }
    
        public boolean verLivro(int id_livro){
        controleLivro.conectarcomBD();
        boolean res = controleLivro.verificarIDLivro(id_livro);
        controleLivro.desconectar();
        return res;
    }
                
    public boolean pesquisarAluguel(String busca) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(buscarAluguel, tipo, concorrencia);
            pstdados.setString(1, busca);
            rsdados = pstdados.executeQuery();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar pesquisa: " + erro);
        }
        return false;
    }
    
     public boolean verificarCampos(){
        if(Aluguel.getDataDev().replaceAll("\\s+","").length() != 10){
            JOptionPane.showMessageDialog(null, "Campo da data de devolução está invalido!\n"
            + "Por favor, verifique o campo de data de devolução", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }   
    

    
    public TableModel getPesquisaModel(String busca){
        conectarcomBD();
        pesquisarAluguel(busca);
        controleLivro.desconectar();
        return AluguelgerarTabela();
    }   
    
    public TableModel getLivroModel(String busca){
        controleLivro.conectarcomBD();
        controleLivro.ConsultarSimples(busca);
        controleLivro.desconectar();
        return controleLivro.gerarTabelaSimples();
    }

    public TableModel getClienteModel(String busca){
        controleCliente.conectarcomBD();
        controleCliente.consultarSimplesCliente(busca);
        controleCliente.desconectar();
        return controleCliente.GerarTabelaSimplesCliente();
    }
    

    
}
