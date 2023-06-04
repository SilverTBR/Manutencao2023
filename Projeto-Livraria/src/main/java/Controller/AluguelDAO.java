/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Aluguel;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Date; 

public class AluguelDAO extends TemplateDAO{
    
    ClienteDAO controleCliente = new ClienteDAO();
    LivroDAO controleLivro = new LivroDAO();
    Aluguel Aluguel = new Aluguel();
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    RelatorioEmprestimo controleRelatorio = null;
    DateFormat formaData = new SimpleDateFormat("dd/MM/yyyy");

    
    private static final String inserirAluguel = "INSERT INTO aluguel (id_cliente, id_livro, data_aluguel, data_devolucao, devolucao) SELECT ?, ?, ?, ?, false WHERE (SELECT COUNT(id_cliente) FROM aluguel WHERE id_cliente = ? and devolucao = false) < 3;";
    private static final String consultarAluguel = "SELECT id_aluguel, aluguel.id_cliente, nome, sobrenome, contato, livro.id_livro, titulo, data_aluguel, data_devolucao FROM aluguel, cliente, livro where aluguel.id_cliente = cliente.id_cliente and aluguel.id_livro = livro.id_livro and devolucao = 'false' group by aluguel.id_aluguel, aluguel.id_cliente, livro.id_livro,cliente.nome, cliente.sobrenome, cliente.contato order by aluguel.id_aluguel, aluguel.id_cliente asc";
    private static final String buscarAluguel = "SELECT id_aluguel, aluguel.id_cliente, nome, sobrenome, contato, livro.id_livro, titulo, data_aluguel, data_devolucao FROM aluguel, cliente, livro where aluguel.id_cliente = cliente.id_cliente and aluguel.id_livro = livro.id_livro and devolucao = 'false' and cliente.nome ilike ? group by aluguel.id_aluguel, aluguel.id_cliente, livro.id_livro,cliente.nome, cliente.sobrenome, cliente.contato order by aluguel.id_aluguel, aluguel.id_cliente asc";
    private static final String excluirTudo = "delete from aluguel";
    private static final String consultarCount = "SELECT COUNT(id_aluguel) FROM aluguel";
    private static final String devolucaoAluguel = "UPDATE aluguel SET devolucao = 'true' WHERE id_aluguel = ?";
    private static final String consultarData = "select data_devolucao from aluguel where id_cliente = ? and devolucao = false";

    public Aluguel getAluguel(){
        return Aluguel;
    }
    
    public void chamarRelatorio(boolean visu){
        controleRelatorio = new RelatorioEmprestimo();
        controleRelatorio.geraRelatorio();
    }

    
    @Override
    protected void setDados(PreparedStatement pstdados) throws SQLException{
        pstdados.setInt(1, Aluguel.getIdCliente());
        pstdados.setInt(2, Aluguel.getIdLivro());
        pstdados.setString(3, Aluguel.getDataAluguel());
        pstdados.setString(4, Aluguel.getDataDev());
        pstdados.setInt(5, Aluguel.getIdCliente());
    }
    
    public boolean inserirAluguel() {
        return inserirDados(inserirAluguel);
    }
    
    public boolean excluiraluguel() {
        return excluirDados(excluirTudo);
    }

    public boolean consultarTodosAlugueis() {
        return consultarTodosDados(consultarAluguel);
    }
        
    public boolean consultarCountAluguel() {
        return consultarCountDados(consultarCount);
    }
    
    public boolean pesquisarAluguel(String busca) {
            return pesquisar(busca, buscarAluguel);
    } 
    
    
    public boolean buscarData(int idCliente) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(consultarData, tipo, concorrencia);
            pstdados.setInt(1, idCliente);
            rsdados = pstdados.executeQuery();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar pesquisa: " + erro);
        }
        return false;
    }
    
        public boolean verificarData(int idCliente) {
        buscarData(idCliente);
        try {
            Date dataAtual = new Date();
            while(rsdados != null && rsdados.next()){
            Date dataDev = formaData.parse(rsdados.getString("data_devolucao"));
                if (dataDev.before(dataAtual)) {
                    JOptionPane.showMessageDialog(null, "Devolução de aluguel atrasado!\nPor favor realize a devolução do aluguel atrasado antes de prosseguir!", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            return true;
        } catch (ParseException e) {
            System.out.println("Formato de data inválido: " + e.getMessage());
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao obter dados do banco de dados: " + erro.getMessage());
            return true;
        }
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
    
    @Override
    public TableModel GerarTabelaSimples(){
        int linha = 0;
        DefaultTableModel modeloJT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        try {
            
        int qntCol = getrsdados().getMetaData().getColumnCount();
        for(int col = 1; col<= qntCol; col++){
            modeloJT.addColumn(getrsdados().getMetaData().getColumnLabel(col));
        }

            while(getrsdados() != null && getrsdados().next()){
                modeloJT.addRow(new Object[0]);
                modeloJT.setValueAt(getrsdados().getInt("id_aluguel"), linha, 0);
                modeloJT.setValueAt(getrsdados().getInt("id_cliente"), linha, 1);
                modeloJT.setValueAt(getrsdados().getString("nome"), linha, 2);
                modeloJT.setValueAt(getrsdados().getString("sobrenome"), linha, 3);
                modeloJT.setValueAt(getrsdados().getString("contato"), linha, 4);
                modeloJT.setValueAt(getrsdados().getInt("id_livro"), linha, 5);
                modeloJT.setValueAt(getrsdados().getString("titulo"), linha, 6);
                modeloJT.setValueAt(getrsdados().getString("data_aluguel"), linha, 7);
                modeloJT.setValueAt(getrsdados().getString("data_devolucao"), linha, 8);
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
                    
     @Override
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
        return GerarTabelaSimples();
    }   
    
    public TableModel getLivroModel(String busca){
        controleLivro.conectarcomBD();
        controleLivro.ConsultarSimplesLivro(busca);
        controleLivro.desconectar();
        return controleLivro.GerarTabelaSimples();
    }

    public TableModel getClienteModel(String busca){
        controleCliente.conectarcomBD();
        controleCliente.consultarSimplesCliente(busca);
        controleCliente.desconectar();
        return controleCliente.GerarTabelaSimples();
    }

    @Override
    protected void zerarCampos(){}


    

    
}
