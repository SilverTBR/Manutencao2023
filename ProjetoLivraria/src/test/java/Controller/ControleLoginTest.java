/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 *
 * @author Guilherme Andreotti
 */

public class ControleLoginTest {
    
    ControleLogin control = new ControleLogin();
    
    public ControleLoginTest() {
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
    public void testVerificarContaFalse() {   
        assertEquals(false,control.verificarConta("qualquercoisa", "qualquersenha"));
    }
    @Test
    public void testVerificarContaTrue() {   
        assertEquals(true,control.verificarConta("admin", "admin"));
    }
    
}
