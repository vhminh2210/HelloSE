/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prj_class;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class User {
    private int UserID;
    private String userName;
    private String sdt;
    private String passWd;
    private String quyenHan;

    public User(int UserID, String userName, String sdt, String passWd, String quyenHan) {
        this.UserID = UserID;
        this.userName = userName;
        this.sdt = sdt;
        this.passWd = passWd;
        this.quyenHan = quyenHan;
    }
    
    public User(ResultSet rs){
        try {
            this.UserID = rs.getInt("userID");
            this.userName = rs.getString("userName");
            this.sdt = rs.getString("sdt");
            this.passWd = rs.getString("passWd");
            this.quyenHan = rs.getString("quyenHan");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getUserID() {
        return UserID;
    }

    public String getUserName() {
        return userName;
    }

    public String getSdt() {
        return sdt;
    }

    public String getPassWd() {
        return passWd;
    }

    public String getQuyenHan() {
        return quyenHan;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public void setQuyenHan(String quyenHan) {
        this.quyenHan = quyenHan;
    }
}
