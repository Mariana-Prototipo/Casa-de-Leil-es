

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        int status;
        
        try {
            prep = conn.prepareStatement("INSERT INTO jogos VALUES(?,?,?,?)");
            prep.setInt(1, produto.getId());
            prep.setString(2,produto.getNome());
            prep.setInt(3, produto.getValor());
            prep.setString(4,produto.getStatus());
            status = prep.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }  
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(int id){
      String sql = "SELECT * FROM produtos Where id Like ?";
      try {
                  
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          stmt.setInt(1,id);
          ResultSet rs = stmt.executeQuery();
         
       
          while(rs.next()){ 
          ProdutosDTO d = new ProdutosDTO();
          
          d.setId(rs.getInt("id"));
          d.setNome(rs.getString("nome"));
          d.setValor(rs.getInt("valor"));
          d.setStatus(rs.getString("status"));
          
          listagem.add(d);
           
          }
            return listagem;
      } catch (Exception e) {
          System.out.println("erro: " + e.getMessage());
          return null;
      }
    }
    
    
    
        
}

