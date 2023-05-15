/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import static Controller.DAO.pasta_relatorios;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author EDUARDO
 */
public class RelatorioEmprestimo extends DAO{
        public static final File relatorio_alugueis = new File(pasta_relatorios, "ReportAlugueis.jrxml");
        public static final File PDF_Alugueis = new File(pasta_relatorios, "ReportAlugueis.pdf");

        public RelatorioEmprestimo(){
            conectarcomBD();
        }    
         
        public void geraRelatorio() {
        JasperPrint preenchido;
        try {
            FileInputStream arquivo = new FileInputStream(relatorio_alugueis);
            JasperReport relatorio = JasperCompileManager.compileReport(arquivo);
            preenchido = JasperFillManager.fillReport(relatorio,null,connection);
            if(!preenchido.getPages().isEmpty()){
//                if (visu) {
                    JasperViewer.viewReport(preenchido, false);
//                } else {;
//                    JasperExportManager.exportReportToPdfFile(preenchido, PDF_Emprestimo.getAbsolutePath());
//                JOptionPane.showMessageDialog(null, "Arquivo criado na pasta: "+pasta_relatorios,"RESULTADO DO RELATORIO",JOptionPane.INFORMATION_MESSAGE);
//                }
            }else{
                JOptionPane.showMessageDialog(null, "Não há emprestimos cadastrados!","FALHA NO RELATORIO",JOptionPane.ERROR_MESSAGE);
            }

        } catch (JRException | FileNotFoundException erro) {
            System.err.println("ERRO na geração do relaorio livro: " + erro);
        }
        desconectar();
    }        
}
