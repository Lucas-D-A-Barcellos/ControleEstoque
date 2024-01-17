
package gerenciadordeapp;


import DTO.EstoqueDTO;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import dao.EstoqueDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Lucas Daniel
 */
public class Entrada { //painel de entrada de novas peças no estoque
    
    private EstoqueDAO estoqueDao; // Instância da classe EstoqueDAO
    
    public Entrada() throws SQLException, ParserConfigurationException, SAXException, IOException{
         
        super();
        
        try{
            
            estoqueDao = new EstoqueDAO();
            
            estoqueDao.conectarAoBanco();

            JFrame addentrada = new JFrame("Entrada de peças");
            addentrada.setSize(400,550);
            addentrada.getContentPane().setBackground(new Color(247, 249, 250));
            addentrada.setResizable(false);


                JLabel titulo = new JLabel("Entrada de Peças");
                titulo.setBounds(80, 50, 250, 30);
                titulo.setFont(new Font("Arial", Font.BOLD, 24));
                titulo.setForeground(new Color(10, 10, 10));

                JLabel tipo = new JLabel("Nome da peça:");
                tipo.setBounds(60, 130, 250, 25);
                tipo.setFont(new Font("Arial", Font.BOLD, 18));
                tipo.setForeground(new Color(10, 10, 10));

                JComboBox<Object> sel_peca = new JComboBox<>();
                sel_peca.setBounds(50, 160, 280, 25);
                sel_peca.setFont(new Font("Arial", Font.BOLD, 18)); 
                
                estoqueDao.SelecionarPeca(sel_peca);

                JLabel pecas = new JLabel("Quantidade de peças:");
                pecas.setBounds(60, 220, 250, 25);
                pecas.setFont(new Font("Arial", Font.BOLD, 18));
                pecas.setForeground(new Color(10, 10, 10));

                JTextField num_pecas = new JTextField();
                num_pecas.setBounds(50, 250, 280, 25);
                num_pecas.setFont(new Font("Arial", Font.BOLD, 18));  

                JLabel entrada = new JLabel("Data de entrada:");
                entrada.setBounds(60, 310, 250, 25);
                entrada.setFont(new Font("Arial", Font.BOLD, 18));
                entrada.setForeground(new Color(10, 10, 10));

                JDateChooser data_entrada = new JDateChooser();
                data_entrada.setBounds(50, 340, 280, 25);
                data_entrada.setFont(new Font("Arial", Font.BOLD, 18)); //parametro de data ainda não foi definido no banco

                JButton confirmar = new JButton("Confirmar");
                confirmar.setBounds(50, 420, 125, 30);
                confirmar.setFont(new Font("Arial", Font.BOLD, 18));
                confirmar.setForeground(new Color(10, 10, 10));
                confirmar.setBackground(new Color(0, 168, 39));
                
                confirmar.addActionListener(new ActionListener() { //actionListener para adicionar uma função ao clicar no botão
                
                public void actionPerformed(ActionEvent e) {
                    
                        String pecas = (String) sel_peca.getSelectedItem();
                        String quantidade = num_pecas.getText();
                        
                        int quant_peca = Integer.parseInt(quantidade);

                        EstoqueDTO estoqueDto = new EstoqueDTO();
                        estoqueDto.setSel_peca(pecas);
                        estoqueDto.setNum_pecas(quant_peca);

                        EstoqueDAO estoqueDao = new EstoqueDAO();
                        
                        try {
                            
                            estoqueDao.InserirNumPeca(estoqueDto); // Exclui do banco de dados
                            sel_peca.removeAllItems(); // Limpa todos os itens do JComboBox                            
                            estoqueDao.SelecionarPeca(sel_peca); // Recarrega os itens do JComboBox
                            num_pecas.setText("");
                            
                            JOptionPane.showMessageDialog(null, "Peça inserida no estoque com sucesso!");
                            
                        } catch (SQLException ex) {
                            
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Erro ao inserir no estoque: " + ex.getMessage());
                            
                        } catch (ParserConfigurationException ex) {
                            
                            Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
                            
                        } catch (SAXException ex) {
                            
                            Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
                            
                        } catch (IOException ex) {
                            
                            Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
                            
                        }
                    }
                });

                JButton cancelar = new JButton("Cancelar");
                cancelar.setBounds(205, 420, 125, 30);
                cancelar.setFont(new Font("Arial", Font.BOLD, 18));
                cancelar.setForeground(new Color(10, 10, 10));
                cancelar.setBackground(new Color(145, 0, 10));

            addentrada.add(titulo);
            addentrada.add(sel_peca);
            addentrada.add(tipo);
            addentrada.add(pecas);
            addentrada.add(num_pecas);
            addentrada.add(entrada);
            addentrada.add(data_entrada);
            addentrada.add(confirmar);
            addentrada.add(cancelar);

            addentrada.setLayout(null);
            /*addentrada.setDefaultCloseOperation(EXIT_ON_CLOSE);*/
            addentrada.setVisible(true); 

        }catch (SQLException ex) {
            
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + ex.getMessage());
        }
    }

    
}
