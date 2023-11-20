/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prj_class;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbQuery {
    private Connection conn;
    private Statement st;

    public dbQuery(Connection conn) {
        this.conn = conn;
        try {
            this.st = (Statement) conn.createStatement();
            
        } catch (SQLException ex) {
            Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getSt() {
        return st;
    }
    
    
    
    public ResultSet findUser(String userName){
        String query = "SELECT * FROM user WHERE userName = \"" + userName + "\"";
        try {
            ResultSet rs = st.executeQuery(query);
            return rs;
            
        } catch (SQLException ex) {
            Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ResultSet findSDT_fromID(int userID){
        String query = "SELECT sdt FROM user WHERE userID = " + String.valueOf(userID);
        try {
            ResultSet rs = st.executeQuery(query);
            return rs;
            
        } catch (SQLException ex) {
            Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ResultSet dsThanhVien(String maCanHo){
        String query = "SELECT nhankhau.* FROM user, nhankhau WHERE user.UserID = nhankhau.userID AND nhankhau.maCanHo = \"" + maCanHo + "\"";
        try {
            ResultSet rs = st.executeQuery(query);
            return rs;
            
        } catch (SQLException ex) {
            Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
