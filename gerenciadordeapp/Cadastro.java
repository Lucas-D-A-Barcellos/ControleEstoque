
package gerenciadordeapp;

import DTO.EstoqueDTO;
import dao.EstoqueDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Lucas Daniel
 */
public class Cadastro extends JFrame{
    
    public Cadastro(){
        
        super();

        JFrame cadastro = new JFrame("Cadastro de peças");
        cadastro.setSize(400,400);
        cadastro.getContentPane().setBackground(new Color(247, 249, 250));
        /*cadastro.setResizable(null);*/
        
       
            JLabel titulo = new JLabel("Cadastrar Peças");
            titulo.setBounds(100, 50, 250, 30);
            titulo.setFont(new Font("Arial", Font.BOLD, 24));
            titulo.setForeground(new Color(10, 10, 10));
            
            JLabel tipo = new JLabel("Nome da peça:");
            tipo.setBounds(60, 130, 250, 25);
            tipo.setFont(new Font("Arial", Font.BOLD, 18));
            tipo.setForeground(new Color(10, 10, 10));

            JTextField tipo_pecas = new JTextField();
            tipo_pecas.setBounds(50, 160, 280, 25);
            tipo_pecas.setFont(new Font("Arial", Font.BOLD, 18)); 
            
            JButton confirmar = new JButton("Confirmar");
            confirmar.setBounds(50, 250, 125, 30);
            confirmar.setFont(new Font("Arial", Font.BOLD, 18));
            confirmar.setForeground(new Color(10, 10, 10));
            confirmar.setBackground(new Color(0, 168, 39));
            
           
            confirmar.addActionListener(new ActionListener() {//actionListener para adicionar uma função ao clicar no botão
                
                public void actionPerformed(ActionEvent e) {
                    
                    // Quando o botão "Confirmar" é clicado, o valor do JTextField tipo é salvo na variável pecas
                    String pecas = tipo_pecas.getText();
                    
                    EstoqueDTO estoqueDto = new EstoqueDTO();
                    estoqueDto.setTipo_pecas(pecas);
                    
                    EstoqueDAO estoqueDao = new EstoqueDAO();
                    try {
                        estoqueDao.CadastrarPeca(estoqueDto);
                    } catch (SQLException ex) {
                        Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParserConfigurationException ex) {
                        Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SAXException ex) {
                        Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    tipo_pecas.setText("");
                    
                    JOptionPane.showMessageDialog(null, "Peça cadastrada com sucesso!");
                }
            });
            
            JButton cancelar = new JButton("Cancelar");
            cancelar.setBounds(205, 250, 125, 30);
            cancelar.setFont(new Font("Arial", Font.BOLD, 18));
            cancelar.setForeground(new Color(10, 10, 10));
            cancelar.setBackground(new Color(145, 0, 10));
            
        cadastro.add(titulo);
        cadastro.add(tipo_pecas);
        cadastro.add(tipo);
        cadastro.add(confirmar);
        cadastro.add(cancelar);
        
        cadastro.setLayout(null);
        /*cadastro.setDefaultCloseOperation(EXIT_ON_CLOSE);*/
        cadastro.setVisible(true);
          
       
    }
    

}
