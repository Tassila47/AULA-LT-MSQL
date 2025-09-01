package program;

import db.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;



public class Program {
    
    public static void main(String[] args) {
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        
        try {
            conn = DB.getConnection(); // abre conexão
            System.out.println("Conexao realizada com sucesso!\n");
            
            
            // Inserção de dados
            String sqlInsert = "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, "Fulaninho");
            ps.setString(2, "fulaninho@gmail.com");
            ps.setDate(3, Date.valueOf("1990-05-15"));
            ps.setDouble(4, 2500.00);
            ps.setInt(5, 2); 

            int rowsAffected = ps.executeUpdate();
            System.out.println("Inserção realizada! Linhas afetadas: " + rowsAffected);


            
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM seller"); // consulta a tabela
            
            while (rs.next()) {
                System.out.println(
                    rs.getInt("Id") + ", " + 
                    rs.getString("Name") + ", " +
                    rs.getString("Email") + ", " +
                    rs.getDate("BirthDate") + ", " +
                    rs.getDouble("BaseSalary") + ", " +
                    rs.getInt("DepartmentId")
                );
            }
            
            System.out.println("---------UPDATE-----------");
            
            try{
    
    ps = conn.prepareStatement(
            "UPDATE seller "
            + "SET BaseSalary = BaseSalary + ?"
            + "WHERE DepartmentId = ?" );
    
    ps.setDouble(1, 2000.0);
    ps.setInt(2, 4);
    
    int rowsUpdated = ps.executeUpdate();
             System.out.println("Finalizado" + rowsUpdated);

         }catch(SQLException e){
         e.printStackTrace();
         }
            
        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (ps != null) ps.close();
                DB.closeConnection(); //fecha a conexão
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       
        
    }
}  
    

