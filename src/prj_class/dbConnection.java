/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prj_class;

<<<<<<< Updated upstream
import java.sql.*;
=======
// import com.mysql.jdbc.Connection;
import java.sql.Connection;
>>>>>>> Stashed changes
import hellose.HelloSEController;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class dbConnection {
    private Connection conn;
    
    public dbConnection(){
        try {
            conn = null;
            String dbURL = "jdbc:mysql://localhost:3306/hellose_db";
            String username = "root";
            String password = "";
            
            //Tạo kết nối
            conn = (Connection) DriverManager.getConnection(dbURL, username, password);
            if(conn != null) System.out.println("Kết nối thành công!!!");
            
        } catch (SQLException ex) {
            Logger.getLogger(HelloSEController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConn(){
        return conn;
    }
}
