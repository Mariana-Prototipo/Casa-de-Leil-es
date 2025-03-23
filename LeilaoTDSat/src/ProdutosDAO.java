

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
            prep.setInt(3, produto.getValor());
            prep.setString(4,produto.getStatus());
            status = prep.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }  
    }
    
    public void vender (int id){
                
                String sql = "UPDATE status FROM produtos WHERE id = ?";
                try {
                    //esse trecho é igual ao método editar e inserir
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    stmt.setInt(1, id);
                    
                    //Executando a query
                    stmt.execute();
                    //tratando o erro, caso ele ocorra
                } catch (Exception e) {
                    System.out.println("Erro ao excluir produto: " + e.getMessage());
                }
                
            }
    
   public List<ProdutosDTO> getProdutoPorId(int id) {
                String sql = "SELECT * FROM produtos WHERE id = ? "; 
                
                try {
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                                
                    stmt.setInt(1,id);
                    ResultSet rs = stmt.executeQuery();            
                    
                    List<ProdutosDTO> lista = new ArrayList<>();
                    
                     while (rs.next()) {
                        ProdutosDTO p = new ProdutosDTO();
                                 
                        p.setId(rs.getInt("id"));
                        p.setNome(rs.getString("nome"));
                        p.setValor(rs.getInt("valor"));
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

