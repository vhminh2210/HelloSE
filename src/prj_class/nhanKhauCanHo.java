/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prj_class;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author admin
 */
public class nhanKhauCanHo {
    private int userID;
    private String maCanHo;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    
    public nhanKhauCanHo(ResultSet rs){
        try {
            userID = rs.getInt("userID");
            maCanHo = rs.getString("maCanHo");
            ngayBatDau = rs.getDate("ngayBatDau");
            ngayKetThuc = rs.getDate("ngayKetThuc");
        } catch (SQLException ex) {
            Logger.getLogger(nhanKhauCanHo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public int getUserID() {
        return userID;
    }

    public String getMaCanHo() {
        return maCanHo;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }
    
    
}
