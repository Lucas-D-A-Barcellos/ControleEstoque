package dao;

import DTO.EstoqueDTO;
import gerenciadordeapp.Entrada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import javax.swing.JComboBox;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EstoqueDAO {
    
    Connection con;
    PreparedStatement pstm;
    PreparedStatement pst;
     
    
    public Connection conectarAoBanco() throws SQLException { // método para conexão com o banco de dados
        try {
            
            //informações de conexão
            String url = "jdbc:mysql://localhost:3306/controle_estoque?useSSL=false";
            String user = "root";
            String password = "l123";

            
            // Estabelecendo a conexão
            con = DriverManager.getConnection(url, user, password);
            
            return con;
            
        } catch (SQLException e) {
                
            e.printStackTrace();
            throw e;
            
        }
    }

    public void CadastrarPeca(EstoqueDTO estoqueDto) throws SQLException { //método de cadastro de peças no banco de dados

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
    
    public void InserirNumPeca(EstoqueDTO estoqueDto) throws SQLException {//método de inserção de número de peças no banco

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
    
    public void RetirarNumPeca(EstoqueDTO estoqueDto) throws SQLException { // Método para retirar número de peças

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
    
    public void ExcluirPeca(EstoqueDTO estoqueDto) throws SQLException { // método para excluir peça do estoque

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
    
    public void SelecionarPeca(JComboBox<Object> sel_peca) throws SQLException { //método auxiliar para colocar todas as opções de peças no banco dentro de um JcomboBox
        
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
    
    public void PreencherTabela(JTable tabela) throws SQLException { //método para preencher a tabela de estoque com os dados do banco
        
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
