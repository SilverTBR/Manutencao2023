/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
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
public class LivroDAOTest {
    
    LivroDAO controle = new LivroDAO();
    AluguelDAO aluguel = new AluguelDAO();
    
    public LivroDAOTest() {
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
    
    
    public void setCaminhos(){
        controle.setCaminhoTeste();
        aluguel.setCaminhoTeste();
        controle.conectarcomBD();
        aluguel.conectarcomBD();
        aluguel.excluirDados("delete from aluguel");
        controle.excluirlivro();
    }
    /**
     * Test of getLivro method, of class LivroDAO.
     */

       public LivroDAO criaLivroGenerico(){
                
        controle.getLivro().setTitulo("Teste");
        controle.getLivro().setGenero("Teste");
        controle.getLivro().setEditora("Teste");
        controle.getLivro().setNomeAutor("Teste");
        controle.getLivro().setSobrenomeAutor("Teste");
                controle.getLivro().setQntPgns(150);
        
        return controle;
    }
    
    /**
     * Teste com cadastro dando sucesso.
     */
    
    @Test
    public void livroPgsQtdeTest(){
        
        controle.setCaminhoTeste();
        controle.conectarcomBD();
        controle = criaLivroGenerico();

        controle.inserirLivros();
        controle.consultarTodosLivros();
        
        try{
            ResultSet rs = controle.getrsdados();           
            assertEquals(true,rs.next());
            controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!");
        }     
    }  
    
    
}
