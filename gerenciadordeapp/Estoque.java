
package gerenciadordeapp;

import dao.EstoqueDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Lucas Daniel
 */
public class Estoque { // painel de estoque contendo todos os dados

    public Estoque() throws SQLException, ParserConfigurationException, SAXException, IOException {
        
        JFrame estoque = new JFrame("Estoque");
        /*estoque.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        estoque.getContentPane().setBackground(new Color(247, 249, 250));
        estoque.setSize(1080, 600);

        JPanel contentPanel = new JPanel(new GridBagLayout()); // Usando GridBagLayout para centralizar
        contentPanel.setBackground(new Color(247, 249, 250));

        JLabel titulo_estoque = new JLabel("ESTOQUE:");
        titulo_estoque.setFont(new Font("Arial", Font.BOLD, 40));
        titulo_estoque.setForeground(new Color(10, 10, 10));

        DefaultTableModel model = new DefaultTableModel(new String[]{"PEÇA", "QUANTIDADE"}, 0);
        JTable tabela = new JTable(model);
        tabela.setFont(new Font("Arial", Font.BOLD, 14));
        tabela.setRowHeight(30);
        tabela.setRowMargin(10);

        JScrollPane scrollPane = new JScrollPane(tabela);// adicionando barra de rolagem

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(20, 0, 20, 0); // Espaçamento superior e inferior

        contentPanel.add(titulo_estoque, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        contentPanel.add(scrollPane, gridBagConstraints);

        estoque.add(contentPanel);
        estoque.pack();
        estoque.setVisible(true);

        EstoqueDAO estoqueDao = new EstoqueDAO();

        estoqueDao.PreencherTabela(tabela);// chamada da função para preencher a tabela com os dados do banco
    }
}
