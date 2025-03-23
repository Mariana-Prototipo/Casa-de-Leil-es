

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
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11","root", "1408");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }
    
    public int cadastrarProduto (ProdutosDTO produto){
        int status;
        
        try {
            prep = conn.prepareStatement("INSERT INTO produtos VALUES(?,?,?,?)");
            prep.setInt(1, produto.getId());
            prep.setString(2,produto.getNome());
            prep.setString(3, produto.getValor());
            prep.setString(4,produto.getStatus());
            status = prep.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }  
    }
    
    public int vender (int id){
            int status;
                try {
                     ProdutosDTO p = new ProdutosDTO();
                    //esse trecho é igual ao método editar e inserir
                    prep = conn.prepareStatement("UPDATE produtos set status WHERE id = ?");
                    prep.setInt(1, id);
                    prep.setString(2, p.getStatus());
                    
                    status = prep.executeUpdate();
                    return status;
                    
                } catch (SQLException ex) {
                    System.out.println("Erro ao conectar: " + ex.getMessage());
                     return ex.getErrorCode();
                }
                
            }
    
   public List<ProdutosDTO> getProdutoPorId() {
                String sql = "SELECT * FROM produtos WHERE id = ? "; 
                    ProdutosDTO p = new ProdutosDTO();
                try {
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                   
                    ResultSet rs = stmt.executeQuery();            
                    
                    List<ProdutosDTO> lista = new ArrayList<>();
                    
                     while (rs.next()) {
                                 
                        p.setId(rs.getInt("id"));
                        p.setNome(rs.getString("nome"));
                        p.setValor(rs.getString("valor"));
                        p.setStatus(rs.getString("status"));
                       
                        
                        lista.add(p);
                        
                    }
                      return lista;       
                } catch (Exception e) {
                    return null;
                }
            }
    

    
      public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
            
        }  
     }
    
    
    
        
}

