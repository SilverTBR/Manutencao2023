/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Livro;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class LivroDAO extends TemplateDAO{
    
    private PreparedStatement pstdados = null;
    Livro livro = new Livro();
    
    private static final String inserirLivros = "INSERT INTO livro (titulo, nome_autor, sobrenome_autor, paginas, genero, editora) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String excluirTudo = "delete from livro";  
    private static final String consultarLivro = "SELECT * FROM livro ORDER BY id_livro";
    private static final String consultarCount = "SELECT COUNT(id_livro) FROM livro";
    private static final String verLivro = "SELECT id_livro FROM livro WHERE id_livro = ?";
    private static final String consultarLivrosSimples = "select id_livro, titulo from livro where titulo ILIKE ? and id_livro not in (select id_livro from aluguel where devolucao = 'false')";

    public Livro getLivro(){
        return livro;
    }
    
    
    @Override
    protected void setDados(PreparedStatement pstdados) throws SQLException{
        pstdados.setString(1, livro.getTitulo());
        pstdados.setString(2, livro.getNomeAutor());
        pstdados.setString(3, livro.getSobrenomeAutor());
        pstdados.setInt(4, livro.getQntPgns());
        pstdados.setString(5, livro.getGenero());
        pstdados.setString(6, livro.getEditora());
    }
    
    public boolean inserirLivros() {
        return inserirDados(inserirLivros);
    }
    
    public boolean excluirlivro() {
        return inserirDados(excluirTudo);
    }
    
    public boolean consultarTodosLivros() {
        return consultarTodosDados(consultarLivro);
    }
    
    public boolean consultarCountLivro() {
        return consultarCountDados(consultarLivro);
    }
    
    public boolean verificarIDLivro(int ID){
        return verificarID(ID, verLivro);
    }
    
    public boolean ConsultarSimplesLivro(String busca) {
        return consultarSimples(busca, consultarLivrosSimples);
    }
       
    @Override
    public boolean verificarCampos(){
        if(getLivro().getEditora().isBlank() || getLivro().getEditora().length() > 50){
            JOptionPane.showMessageDialog(null, "Campo do editor está invalido!\nPor favor, preencha o campo editor", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(getLivro().getGenero().isBlank() || getLivro().getGenero().length() > 45){
            JOptionPane.showMessageDialog(null, "Campo do genero está inválido!\nPor favor, verifique o campo genero", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(getLivro().getNomeAutor().isBlank() || getLivro().getNomeAutor().length() > 45){
            JOptionPane.showMessageDialog(null, "Campo do nome do autor está inválido!\nPor favor, verifique o campo nome do autor", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(getLivro().getQntPgns() == 0){
            JOptionPane.showMessageDialog(null, "Campo de paginas está invalido!\nPor favor, verifique o campo pagina corretamente", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(getLivro().getSobrenomeAutor().isBlank() || getLivro().getSobrenomeAutor().length() > 60){
            JOptionPane.showMessageDialog(null, "Campo do sobrenome do autor está inválido!\nPor favor, verifique o campo sobrenome do autor", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(getLivro().getTitulo().isBlank() || getLivro().getTitulo().length() > 100){
            JOptionPane.showMessageDialog(null, "Campo do titulo está inválido!\nPor favor, verifique o campo titulo", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
  
        return true;
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
                modeloJT.setValueAt(getrsdados().getInt("id_livro"), linha, 0);
                modeloJT.setValueAt(getrsdados().getString("titulo"), linha, 1);
                linha++;
            }
        } catch (SQLException erro) {
            System.out.println("ERRO: "+ erro);
        }
        return modeloJT;
    }
    
    @Override
    public void zerarCampos(){
        getLivro().setEditora("");
        getLivro().setGenero("");
        getLivro().setIdLivro(0);
        getLivro().setNomeAutor("");
        getLivro().setQntPgns(0);
        getLivro().setSobrenomeAutor("");
        getLivro().setTitulo("");
    }

}
