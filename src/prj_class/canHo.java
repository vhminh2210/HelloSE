/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prj_class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class canHo {
    private String maCanHo;
    private float dienTich;
    private int soXeMay;
    private int soXeHoi;
    private int soNhanKhau;
    private int idChuHo;
    
    private dbQuery dbquery;
    private dbConnection dbconn;
    
    public canHo(ResultSet rs){
        try {
            this.maCanHo = rs.getString("maCanHo");
            this.dienTich = rs.getFloat("dienTich");
            this.soXeMay = rs.getInt("soXeMay");
            this.soXeHoi = rs.getInt("soXeHoi");
        } catch (SQLException ex) {
            Logger.getLogger(canHo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dbconn = new dbConnection();
        this.dbquery = new dbQuery(dbconn.getConn());
        
        this.soNhanKhau = 0;
        String query = "SELECT UserId FROM nhankhau WHERE maCanHo = " + utils.quoteWrap(maCanHo);
        ResultSet rss;
        try {
            rss = dbquery.getSt().executeQuery(query);
            while(rss.next()) soNhanKhau += 1;
        } catch (SQLException ex) {
            Logger.getLogger(canHo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "SELECT UserID FROM nhankhau WHERE quanHe = \"Chủ hộ\" OR quanHe = \"ADMIN\" AND maCanHo = " + utils.quoteWrap(maCanHo);
        try {
            rss = dbquery.getSt().executeQuery(query);
            rss.next();
            this.idChuHo = rss.getInt("UserID");
        } catch (SQLException ex) {
            Logger.getLogger(canHo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMaCanHo() {
        return maCanHo;
    }

    public float getDienTich() {
        return dienTich;
    }

    public int getSoXeMay() {
        return soXeMay;
    }

    public int getSoXeHoi() {
        return soXeHoi;
    }

    public int getSoNhanKhau() {
        return soNhanKhau;
    }

    public int getIdChuHo() {
        return idChuHo;
    }

    public dbQuery getDbquery() {
        return dbquery;
    }

    public dbConnection getDbconn() {
        return dbconn;
    }

    public void setMaCanHo(String maCanHo) {
        this.maCanHo = maCanHo;
    }

    public void setDienTich(float dienTich) {
        this.dienTich = dienTich;
    }

    public void setSoXeMay(int soXeMay) {
        this.soXeMay = soXeMay;
    }

    public void setSoXeHoi(int soXeHoi) {
        this.soXeHoi = soXeHoi;
    }
}
