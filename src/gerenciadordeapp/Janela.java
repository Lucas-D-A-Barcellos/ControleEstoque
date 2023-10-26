package gerenciadordeapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Lucas Daniel
 */
public class Janela extends JFrame implements ActionListener{
    
    JButton cadastrar = new JButton("Cadastrar peça");
    JButton excluir = new JButton("Excluir peça");
    JButton adicionar = new JButton("Adicionar material");
    JButton retirar = new JButton("Retirar material"); 
    JButton verificar = new JButton("Verificar Estoque");
    
    public void actionPerformed(ActionEvent e){ //action listener para acessar as outras abas do sistema atravez dos botões
        if(e.getSource()==cadastrar){
            new Cadastro();            
        }
        if(e.getSource()==excluir){
            new Descadastro();            
        }
        if(e.getSource()==adicionar){
            try {            
                new Entrada();
                
            } catch (SQLException ex) {
                
                Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource()==retirar){
            try {            
                new Saida();
                
            } catch (SQLException ex) {
                
                Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource()==verificar){
            new Estoque();
        }        
                
    }
    
        
    
    public Janela(){
        
        super(); 
        
        cadastrar.addActionListener(this);
        excluir.addActionListener(this);
        adicionar.addActionListener(this);
        retirar.addActionListener(this);
        verificar.addActionListener(this);
                             
        /*criação do objeto Janela*/
        JFrame GUI = new JFrame("Controle de Estoque");
        GUI.setSize(910, 600);              
        GUI.setLocationRelativeTo(null);
        GUI.getContentPane().setBackground(new Color(255, 255, 255));
              
            /*criação do JLabel titulo*/
            JLabel titulo = new JLabel("Controle de Estoque");
            titulo.setBounds(270, 50, 400, 40);
            titulo.setFont(new Font("Arial", Font.BOLD, 40));
            titulo.setForeground(new Color(10, 10, 10));
            
            ImageIcon imagem = new ImageIcon(getClass().getResource("logo.png"));
            JLabel logo = new JLabel(imagem);
            logo.setBounds(335, 100, 230, 230);
        
            /*criação do botão cadastro de peças*/              
            cadastrar.setBounds(160,450,210,50);
            cadastrar.setBackground(new Color(6, 117, 11));
            cadastrar.setFont(new Font("Arial", Font.BOLD, 20));
            cadastrar.setForeground(new Color(10, 10, 10));
            
            /*criação do botão para exclusão de peças*/              
            excluir.setBounds(530,450,210,50);
            excluir.setFont(new Font("Arial", Font.BOLD, 20));
            excluir.setBackground(new Color(173, 35, 58));
            excluir.setForeground(new Color(10, 10, 10));

            /*criação botão adicionar item*/  
            adicionar.setBounds(60,350,220,50);
            adicionar.setFont(new Font("Arial", Font.BOLD, 20));
            adicionar.setForeground(new Color(10, 10, 10));
            adicionar.setBackground(new Color(194,194,194));

            /*criação botão retirar item*/
            retirar.setBounds(340,350,220,50);
            retirar.setFont(new Font("Arial", Font.BOLD, 20));
            retirar.setForeground(new Color(10, 10, 10));
            retirar.setBackground(new Color(194,194,194));

            /*criação botão verificar estoque*/            
            verificar.setBounds(620,350,220,50);
            verificar.setFont(new Font("Arial", Font.BOLD, 20));
            verificar.setForeground(new Color(10, 10, 10));
            verificar.setBackground(new Color(194,194,194));

            // Crie um objeto ImageIcon com o ícone
            ImageIcon icon = new ImageIcon(getClass().getResource("logo.png"));

            // Configure o ícone no JFrame
            GUI.setIconImage(icon.getImage());
        
        /*Inserindo objetos ao JFrame criado*/
        GUI.add(titulo);
        GUI.add(logo);
        GUI.add(cadastrar);
        GUI.add(excluir);
        GUI.add(adicionar);
        GUI.add(retirar);
        GUI.add(verificar); 
        
        GUI.setResizable(false);
        GUI.setLayout(null);
        GUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
        GUI.setVisible(true);
        pack(); // Ajusta o tamanho do JFrame para acomodar os componentes
    }
    
}
