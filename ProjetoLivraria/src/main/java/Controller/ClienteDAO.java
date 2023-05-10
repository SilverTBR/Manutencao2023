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


public class ClienteDAO extends DAO{

    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    private Cliente cliente = new Cliente();
    
    
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
    
    public ResultSet getrsdados(){
        return rsdados;
    }

    public boolean inserirClientes() {
        if (VerificarcamposCliente()) {             
            try {
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                pstdados = connection.prepareStatement(inserirClientes, tipo, concorrencia);

                pstdados.setString(1, cliente.getNome());
                pstdados.setString(2, cliente.getSobrenome());
                pstdados.setString(3, cliente.getCPF());
                pstdados.setString(4, cliente.getEstado());
                pstdados.setString(5, cliente.getCidade());
                pstdados.setString(6, cliente.getBairro());
                pstdados.setString(7, cliente.getEndereco());
                pstdados.setLong(8, cliente.getContato());
                
                int resposta = pstdados.executeUpdate();
                pstdados.close();
                zerarCamposCliente();
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

    public boolean excluircliente() {
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

    public boolean consultarTodosCliente() {
            try {
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                pstdados = connection.prepareStatement(consultarClientes, tipo, concorrencia);
                rsdados = pstdados.executeQuery();
                return true;
            } catch (SQLException erro) {
                System.out.println("Erro ao executar consulta: " + erro);
            }
            return false;
    }

    public boolean consultarCountCliente() {
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


    public boolean verificarIDCliente(int ID){
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(verCliente, tipo, concorrencia);
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

    public boolean consultarSimplesCliente(String busca) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(consultarClienteSimples, tipo, concorrencia);
            pstdados.setString(1, busca);
            rsdados = pstdados.executeQuery();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar pesquisa simples: " + erro);
        }
        return false;
    }
 
    public boolean verificarLetrasCPF(){
        String temp = getCliente().getCPF();
        temp = temp.replace(".", "");
        temp = temp.replace("-", "");       
        String valor = temp;
        return NumberExecao.verNum(valor);
    }
    
    public TableModel GerarTabelaSimplesCliente(){
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
                    modeloJT.setValueAt(rsdados.getInt("id_cliente"), linha, 0);
                    modeloJT.setValueAt(rsdados.getString("nome"), linha, 1);
                    linha++;
                }
        } catch (SQLException erro) {
            System.out.println("Erro ao gerar tabela simples: "+ erro);
        }
        return modeloJT;
    }
    
        
        public boolean verificarCPF(){
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

    public boolean VerificarcamposCliente(){
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

    public void zerarCamposCliente(){
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