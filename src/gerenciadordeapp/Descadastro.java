
package gerenciadordeapp;

import DTO.EstoqueDTO;
import dao.EstoqueDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Lucas Daniel
 */
public class Descadastro { // painel para exclusão de peças do estoque
    
    private EstoqueDAO estoqueDao; // Instância da classe EstoqueDAO
    
    public Descadastro(){
    
    super();
            
        try{
            
            estoqueDao = new EstoqueDAO();
            
            estoqueDao.conectarAoBanco();
            
            JFrame descadastro = new JFrame("Exclusão de peças");
            descadastro.setSize(400,400);
            descadastro.getContentPane().setBackground(new Color(247, 249, 250));
            /*descadastro.setResizable(null);*/


                JLabel titulo = new JLabel("Excluir Peças");
                titulo.setBounds(120, 50, 250, 30);
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

                JButton confirmar = new JButton("Confirmar");
                confirmar.setBounds(50, 250, 125, 30);
                confirmar.setFont(new Font("Arial", Font.BOLD, 18));
                confirmar.setForeground(new Color(10, 10, 10));
                confirmar.setBackground(new Color(0, 168, 39));
                
                confirmar.addActionListener(new ActionListener() {//actionListener para adicionar uma função ao clicar no botão
                
                    public void actionPerformed(ActionEvent e) {

                        // Quando o botão "Confirmar" é clicado, o valor do JTextField tipo é salvo na variável pecas
                        String pecas = (String) sel_peca.getSelectedItem();

                        EstoqueDTO estoqueDto = new EstoqueDTO();
                        estoqueDto.setSel_peca(pecas);

                        EstoqueDAO estoqueDao = new EstoqueDAO();
                        
                        try {
                            
                            estoqueDao.ExcluirPeca(estoqueDto); // Exclui do banco de dados
                            sel_peca.removeAllItems(); // Limpa todos os itens do JComboBox                            
                            estoqueDao.SelecionarPeca(sel_peca); // Recarrega os itens do JComboBox
                            
                            JOptionPane.showMessageDialog(null, "Peça Excluída com sucesso!");
                            
                        } catch (SQLException ex) {
                            
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Erro ao excluir peça: " + ex.getMessage());
                            
                        }                        

                    }
                });

                JButton cancelar = new JButton("Cancelar");
                cancelar.setBounds(205, 250, 125, 30);
                cancelar.setFont(new Font("Arial", Font.BOLD, 18));
                cancelar.setForeground(new Color(10, 10, 10));
                cancelar.setBackground(new Color(145, 0, 10));

            descadastro.add(titulo);
            descadastro.add(sel_peca);
            descadastro.add(tipo);
            descadastro.add(confirmar);
            descadastro.add(cancelar);

            descadastro.setLayout(null);
            /*descadastro.setDefaultCloseOperation(EXIT_ON_CLOSE);*/
            descadastro.setVisible(true);
        }catch (SQLException err) {
            
            err.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + err.getMessage());
            
        }
        
    
    }
    
}
