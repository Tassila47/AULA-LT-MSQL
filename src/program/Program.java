package program;

import db.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Program {
    
    public static void main(String[] args) {
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
            conn = DB.getConnection(); // abre conexão
            System.out.println("Conexao realizada com sucesso!\n");
            
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
        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                DB.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}  
    

