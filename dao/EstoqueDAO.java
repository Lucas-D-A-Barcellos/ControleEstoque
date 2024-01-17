package dao;

import DTO.EstoqueDTO;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import org.w3c.dom.Element;

public class EstoqueDAO {
    
    Connection con;
    PreparedStatement pstm;
    PreparedStatement pst;
     
    
    public void conectarAoBanco() throws SQLException, ParserConfigurationException, SAXException, IOException {
        try {
            
            File xmlFile = new File("ConectionString.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Agora, procuramos diretamente o nó <connectionStrings>
            NodeList connectionStringsList = doc.getElementsByTagName("connectionStrings");

            // Verificar se <connectionStrings> foi encontrado
            if (connectionStringsList.getLength() > 0) {
                System.out.println("<connectionStrings> encontrado!");

                // Em seguida, procuramos dentro de <connectionStrings> o nó <add>
                NodeList addList = ((Element) connectionStringsList.item(0)).getElementsByTagName("add");

                for (int temp = 0; temp < addList.getLength(); temp++) {
                    Node connectionNode = addList.item(temp);

                    if (connectionNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element connectionElement = (Element) connectionNode;
                        System.out.println("Elemento encontrado: " + connectionElement.getNodeName());

                        String name = connectionElement.getAttribute("name");

                        if ("MySQLConnection".equals(name)) {
                            String connectionString = connectionElement.getAttribute("connectionString");
                            System.out.println("String de Conexão MySQL: " + connectionString);
                            con = DriverManager.getConnection(connectionString);  
                            break;
                        }
                    }
                }           
                
            } else {
                System.out.println("<connectionStrings> não encontrado!");
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            // Ou lançar uma exceção personalizada, se apropriado para o seu aplicativo.
        }

    }
    
    public void CadastrarPeca(EstoqueDTO estoqueDto) throws SQLException, ParserConfigurationException, SAXException, IOException { //método de cadastro de peças no banco de dados

        String sql = "INSERT INTO estoque (NOME_PECA) values(?)";

        conectarAoBanco();
                
        try {
            
            pstm = con.prepareStatement(sql);
            pstm.setString(1, estoqueDto.getTipo_pecas());
            pstm.execute();
            pstm.close();
            con.close(); // Feche a conexão quando terminar
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null, "EstoqueDAO" + erro);
        }
    }
    
    public void InserirNumPeca(EstoqueDTO estoqueDto) throws SQLException, ParserConfigurationException, SAXException, IOException {//método de inserção de número de peças no banco

        String sql = "UPDATE estoque SET QUANT_PECA = ? WHERE NOME_PECA = ?";
        String selectSql = "SELECT QUANT_PECA FROM estoque WHERE NOME_PECA = ?";
        
        conectarAoBanco();

        try{
             PreparedStatement pstUpdate = con.prepareStatement(sql);
             PreparedStatement pstSelect = con.prepareStatement(selectSql); 

            // selecionando o valor existente de QUANT_PECA
            pstSelect.setString(1, estoqueDto.getSel_peca());
            ResultSet resultSet = pstSelect.executeQuery();

            if (resultSet.next()) {
                int quant_peca = resultSet.getInt("QUANT_PECA");

                // Atualizando o estoque definindo o novo valor
                pstUpdate.setInt(1, estoqueDto.getNum_pecas() + quant_peca);
                pstUpdate.setString(2, estoqueDto.getSel_peca());
                pstUpdate.executeUpdate();
                
            }
            
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "InserirNumPeca" + erro);
        }
    }
    
    public void RetirarNumPeca(EstoqueDTO estoqueDto) throws SQLException, ParserConfigurationException, SAXException, IOException { // Método para retirar número de peças

        String sql = "UPDATE estoque SET QUANT_PECA = ? WHERE NOME_PECA = ?";
        String selectSql = "SELECT QUANT_PECA FROM estoque WHERE NOME_PECA = ?";
        
        conectarAoBanco();

        try{
             PreparedStatement pstUpdate = con.prepareStatement(sql);
             PreparedStatement pstSelect = con.prepareStatement(selectSql);

            // Selecionando o valor existente de QUANT_PECA
            pstSelect.setString(1, estoqueDto.getSel_peca());
            ResultSet resultSet = pstSelect.executeQuery();           
            
            if (resultSet.next()) {
                
                int quant_peca = resultSet.getInt("QUANT_PECA");
                
                if (quant_peca < estoqueDto.getNum_pecas()) { // verificação se o numero de peças retirado é maior que o existente no banco
                    
                    JOptionPane.showMessageDialog(null, "Número de peças no estoque insuficientes!");
                    
                    return;
                    
                } else {
                    
                    // Atualizando o estoque definindo o novo valor
                    pstUpdate.setInt(1, quant_peca - estoqueDto.getNum_pecas());
                    pstUpdate.setString(2, estoqueDto.getSel_peca());
                    pstUpdate.executeUpdate();
                    
                }
                
            }   
            
            JOptionPane.showMessageDialog(null, "Peça retirada com sucesso!");
                   
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "RetirarNumPeca erro aqui" + erro);
        }
    }
    
    public void ExcluirPeca(EstoqueDTO estoqueDto) throws SQLException, ParserConfigurationException, SAXException, IOException { // método para excluir peça do estoque

        String sql = "DELETE FROM estoque WHERE NOME_PECA = (?)";
        
        // método de conexão com o banco
        conectarAoBanco();

        try {            
            
            pstm = con.prepareStatement(sql);
            pstm.setString(1, estoqueDto.getSel_peca());
            pstm.execute();
            pstm.close();
            con.close(); // Feche a conexão quando terminar
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null, "EstoqueDAO" + erro);
        }
    }
    
    public void SelecionarPeca(JComboBox<Object> sel_peca) throws SQLException, ParserConfigurationException, SAXException, IOException { //método auxiliar para colocar todas as opções de peças no banco dentro de um JcomboBox
        
        conectarAoBanco();

        String query = "SELECT NOME_PECA FROM estoque";
       
        try {
            
            pstm = con.prepareStatement(query);
            pstm.execute();
            

            ResultSet resultSet = pstm.executeQuery(query);

            sel_peca.removeAllItems();

            while (resultSet.next()) {
                String option = resultSet.getString("NOME_PECA");
                sel_peca.addItem(option);
            }

            pstm.close();
            con.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public void PreencherTabela(JTable tabela) throws SQLException, ParserConfigurationException, SAXException, IOException { //método para preencher a tabela de estoque com os dados do banco
        
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();

        // Limpa qualquer dado existente na tabela
        model.setRowCount(0);

        String sql = "SELECT * FROM estoque"; 
        
        conectarAoBanco();

        try {
            
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                // Para cada linha no resultado, crie um array de objetos
                Object[] row = {
                    resultSet.getString("NOME_PECA"), 
                    resultSet.getInt("QUANT_PECA"), 
                    // Adicione mais colunas conforme necessário
                };
                // Adicione a linha ao modelo da tabela
                model.addRow(row);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela: " + erro);
        }
    }
}
