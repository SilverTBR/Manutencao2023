/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Controller;

import Model.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.TableModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Guilherme Andreotti
 */
public class ClienteDAOTest {
    
    ClienteDAO controle = new ClienteDAO();
    AluguelDAO aluguel = new AluguelDAO();   
    
    public void setCaminhos()
    {
        controle.setCaminhoTeste();
        controle.conectarcomBD();
        aluguel.conectarcomBD();
        aluguel.excluir();
        controle.excluircliente();
    }
    public ClienteDAO criaClienteGenerico(){               
        controle.getCliente().setNome("Teste");
        controle.getCliente().setSobrenome("Teste");
        controle.getCliente().setEstado("SP");
        controle.getCliente().setEndereco("End. Teste");
        controle.getCliente().setCidade("Testalopis");
        controle.getCliente().setCPF("111.111.111-11");
        controle.getCliente().setBairro("Bairro Teste"); 
        controle.getCliente().setContato(Long.parseLong("18981238745"));
        return controle;
    }
    
    public ClienteDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
     @Test
    public void registroDuploTest(){        
       setCaminhos();
        try{ 
            controle.getCliente().setNome("Teste");
            controle.getCliente().setSobrenome("Teste");
            controle.getCliente().setEstado("SP");
            controle.getCliente().setEndereco("End. Teste");
            controle.getCliente().setCidade("Testalopis");
            controle.getCliente().setCPF("111.111.111-11");
            controle.getCliente().setBairro("Bairro Teste");                   
            controle.inserirClientes();
            
            controle.getCliente().setNome("Teste2");
            controle.getCliente().setSobrenome("Teste2");
            controle.getCliente().setEstado("SP");
            controle.getCliente().setEndereco("End. Teste2");
            controle.getCliente().setCidade("Testalopis");
            controle.getCliente().setCPF("111.111.111-11");
            controle.getCliente().setBairro("Bairro Teste");
            controle.inserirClientes();
            
            controle.consultarCountCliente();
                     
            ResultSet rs = controle.getrsdados();
           
            assertEquals(1, rs.getInt(1));
            controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!");
        }          
    }     
    
    @Test
    public void testTelefoneCadastrado(){
        setCaminhos();
        controle = criaClienteGenerico();
        controle.getCliente().setContato(Long.parseLong("18981230749"));
        controle.inserirClientes();
        controle.consultarTodosCliente();
        
        try{
            ResultSet rs = controle.getrsdados();           
            assertEquals(true,rs.next());
            controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!");
        }     
    }   

}
