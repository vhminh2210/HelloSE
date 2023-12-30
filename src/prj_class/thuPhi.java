/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prj_class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class thuPhi {
    private String maCanHo;
    private String tenKhoanThu;
    private int Thang;
    private int Nam;
    private int soTien;
    private String trangThai;
    private String ghiChu;
    private String thangNam;
    private int maKhoanThu;
    
    private dbQuery dbquery;
    private dbConnection dbconn;
    
    public thuPhi(ResultSet rs){
        try {
            this.maCanHo = rs.getString("maCanHo");
            this.tenKhoanThu = rs.getString("tenKhoanThu");
            this.Thang = rs.getInt("Thang");
            this.Nam = rs.getInt("Nam");
            this.soTien = rs.getInt("soTien");
            this.trangThai = rs.getString("trangThai");
            this.ghiChu = rs.getString("ghiChu");
            this.thangNam = String.valueOf(Thang) + "/" + String.valueOf(Nam);
            this.maKhoanThu = rs.getInt("maKhoanThu");
        } catch (SQLException ex) {
            Logger.getLogger(thuPhi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dbconn = new dbConnection();
        dbquery = new dbQuery(dbconn.getConn());
    }

    public String getMaCanHo() {
        return maCanHo;
    }

    public String getTenKhoanThu() {
        return tenKhoanThu;
    }

    public int getThang() {
        return Thang;
    }

    public int getNam() {
        return Nam;
    }

    public int getSoTien() {
        return soTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public dbQuery getDbquery() {
        return dbquery;
    }

    public dbConnection getDbconn() {
        return dbconn;
    }

    public String getThangNam() {
        return thangNam;
    }

    public int getMaKhoanThu() {
        return maKhoanThu;
    }

    public void setMaCanHo(String maCanHo) {
        this.maCanHo = maCanHo;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu = tenKhoanThu;
    }

    public void setThang(int Thang) {
        this.Thang = Thang;
    }

    public void setNam(int Nam) {
        this.Nam = Nam;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public void setDbquery(dbQuery dbquery) {
        this.dbquery = dbquery;
    }

    public void setDbconn(dbConnection dbconn) {
        this.dbconn = dbconn;
    }
}
