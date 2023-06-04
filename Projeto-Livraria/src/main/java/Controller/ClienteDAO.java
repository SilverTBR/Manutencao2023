/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package Controller;

import Model.Cliente;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class ClienteDAO extends TemplateDAO{

    private Cliente cliente = new Cliente();
    private PreparedStatement pstdados = null;
    
    private static final String verCPF = "SELECT cpf FROM cliente WHERE cpf = ?";
    private static final String inserirClientes = "INSERT INTO cliente (nome, sobrenome, cpf, estado, cidade, bairro, endereco, contato) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String excluirTudo = "delete from cliente";
    private static final String consultarClientes = "SELECT * FROM cliente ORDER BY id_cliente";
    private static final String consultarCount = "SELECT COUNT(id_cliente) FROM cliente";
    private static final String verCliente = "SELECT id_cliente FROM cliente WHERE id_cliente = ?";
    private static final String consultarClienteSimples = "select id_cliente, CONCAT(nome,' ',sobrenome) \"Nome\" from cliente where concat(nome,' ',sobrenome) ILIKE ?";

    public Cliente getCliente(){
        return cliente;
    }
        
    @Override
    protected void setDados(PreparedStatement pstdados) throws SQLException{
        pstdados.setString(1, cliente.getNome());
        pstdados.setString(2, cliente.getSobrenome());
        pstdados.setString(3, cliente.getCPF());
        pstdados.setString(4, cliente.getEstado());
        pstdados.setString(5, cliente.getCidade());
        pstdados.setString(6, cliente.getBairro());
        pstdados.setString(7, cliente.getEndereco());
        pstdados.setLong(8, cliente.getContato());
    }
    
    public boolean inserirClientes() {
        return inserirDados(inserirClientes);
    } 
    
    public boolean excluircliente() {
        return excluirDados(excluirTudo);
    }

    public boolean consultarTodosCliente() {
        return consultarTodosDados(consultarClientes);
    }

    public boolean consultarCountCliente() {
        return consultarCountDados(consultarCount);
    }
    
    public boolean verificarIDCliente(int ID){
        return verificarID(ID, verCPF);
    }
    
    public boolean consultarSimplesCliente(String busca) {
        return consultarSimples(busca, consultarClienteSimples);
    }
        
    public boolean verificarCPF(){
        return verificarCPFT(verCPF, cliente);
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
                    modeloJT.setValueAt(getrsdados().getInt("id_cliente"), linha, 0);
                    modeloJT.setValueAt(getrsdados().getString("nome"), linha, 1);
                    linha++;
                }
        } catch (SQLException erro) {
            System.out.println("Erro ao gerar tabela simples: "+ erro);
        }
        return modeloJT;
    }
    
    public boolean verificarLetrasCPF(){
        String temp = getCliente().getCPF();
        temp = temp.replace(".", "");
        temp = temp.replace("-", "");       
        String valor = temp;
        return NumberExecao.verNum(valor);
    }
    
    @Override
    public boolean verificarCampos()
    {
        if(getCliente().getBairro().isBlank() || getCliente().getBairro().length() > 50){
            JOptionPane.showMessageDialog(null, "Campo do bairro está inválido!\nPor favor, verifique o campo bairro", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(getCliente().getCPF().isBlank() || getCliente().getCPF().length() != 14 || getCliente().getCPF().charAt(3) != '.' || getCliente().getCPF().charAt(7) != '.' || getCliente().getCPF().charAt(11) != '-' ||  verificarLetrasCPF() == true){
            JOptionPane.showMessageDialog(null, "Campo do CPF está inválido!\nPor favor, verifique o campo CPF", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(getCliente().getCidade().isBlank() || getCliente().getCidade().length() > 50){
            JOptionPane.showMessageDialog(null, "Campo da cidade está inválido!\nPor favor, verifique o campo cidade", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(getCliente().getEndereco().isBlank() || getCliente().getEndereco().length() > 75){
            JOptionPane.showMessageDialog(null, "Campo do endereço está inválido!\nPor favor, verifique o campo endereço", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(getCliente().getEstado().isBlank() || getCliente().getEstado().contains(" ") || getCliente().getEstado().length() != 2){
            JOptionPane.showMessageDialog(null, "Campo do estado está inválido!\nPor favor, verifique o campo estado", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(getCliente().getNome().isBlank() || getCliente().getNome().length() > 45){
            JOptionPane.showMessageDialog(null, "Campo do nome está inválido!\nPor favor, verifique o campo nome", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(getCliente().getSobrenome().isBlank() || getCliente().getSobrenome().length() > 60){
            JOptionPane.showMessageDialog(null, "Campo do sobrenome está inválido!\nPor favor, verifique o campo sobrenome", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }if(verificarCPF()){
            JOptionPane.showMessageDialog(null, "CPF digitado já está cadastrado!\nPor favor, preencha o campo com um CPF valido", "FALHA AO SALVAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    @Override
    public void zerarCampos(){
        getCliente().setBairro(null);
        getCliente().setCPF(null);
        getCliente().setCidade(null);
        getCliente().setEndereco(null);
        getCliente().setEstado(null);
        getCliente().setIdCliente(0);
        getCliente().setNome(null);
        getCliente().setSobrenome(null);
    }

}