/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prj_class;

// import com.mysql.jdbc.Connection;
import java.sql.Connection;
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
    private static Connection default_conn = null;
    
    public dbConnection(){
        conn = getDefaultConn();
    }

    public dbConnection(String dbURL, String username, String password){
        try {
            conn = null;
            //Tạo kết nối
            conn = (Connection) DriverManager.getConnection(dbURL, username, password);
            if(conn != null) System.out.println("Kết nối thành công!!!");
            
        } catch (SQLException ex) {
            Logger.getLogger(HelloSEController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setConn(Connection conn){
        this.conn = conn;
    }
    
    public Connection getConn(){
        return conn;
    }

    public static Connection getDefaultConn(){
        if(default_conn == null){
            try {
                default_conn = null;
//                 String dbURL = "jdbc:mysql://localhost:3306/hellose_db";
//                 String username = "root";
//                 String password = "";

                // String dbURL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12664450";
                // String username = "sql12664450";
                // String password = "uj7HSxrHlz";

                String dbURL = "jdbc:mysql://hellose1234.mysql.database.azure.com:3306/hellose_db?useUnicode=true&characterEncoding=UTF-8";
                String username = "gigachad";
                String password = "Nhat2003!";

                //Tạo kết nối
                default_conn = (Connection) DriverManager.getConnection(dbURL, username, password);
                if(default_conn != null) System.out.println("Kết nối thành công!!!");
                
            } catch (SQLException ex) {
                Logger.getLogger(HelloSEController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return default_conn;
    }

    public static void setDefaultConn(Connection conn){
        default_conn = conn;
    }
}