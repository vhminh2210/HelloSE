/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prj_class;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class nhanKhau {
    private String hoTen;
    private String cccd;
    private String maCanHo;
    private int UserID;
    private int ngaySinh;
    private int thangSinh;
    private int namSinh;
    private String quanHe;
    private String ngheNghiep;
    private String coQuan;
    private String trangThai;
    private char gioiTinh;
    private dbQuery query;

    public nhanKhau(String hoTen, String cccd, String maCanHo, int UserID, int ngaySinh, int thangSinh, int namSinh, String quanHe, String ngheNghiep, String coQuan, String trangThai, char gioiTinh) {
        this.hoTen = hoTen;
        this.cccd = cccd;
        this.maCanHo = maCanHo;
        this.UserID = UserID;
        this.ngaySinh = ngaySinh;
        this.thangSinh = thangSinh;
        this.namSinh = namSinh;
        this.quanHe = quanHe;
        this.ngheNghiep = ngheNghiep;
        this.coQuan = coQuan;
        this.trangThai = trangThai;
        this.gioiTinh = gioiTinh;
        
        dbConnection dbconn = new dbConnection();
        this.query = new dbQuery(dbconn.getConn());
    }
    
    public nhanKhau(ResultSet rs){
        try {
            this.hoTen = rs.getString("hoTen");
            this.cccd = rs.getString("cccd");
            this.maCanHo = rs.getString("maCanHo");
            this.UserID = rs.getInt("UserID");
            this.namSinh = rs.getInt("namSinh");
            this.thangSinh = rs.getInt("thangSinh");
            this.ngaySinh = rs.getInt("ngaySinh");
            this.quanHe = rs.getString("quanHe");
            this.ngheNghiep = rs.getString("ngheNghiep");
            this.coQuan = rs.getString("coQuan");
            this.trangThai = rs.getString("trangThai");
            this.gioiTinh = rs.getString("gioiTinh").charAt(0);
        } catch (SQLException ex) {
            Logger.getLogger(nhanKhau.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dbConnection dbconn = new dbConnection();
        this.query = new dbQuery(dbconn.getConn());
    }

    public char getGioiTinh() {
        return gioiTinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getCccd() {
        return cccd;
    }

    public String getMaCanHo() {
        return maCanHo;
    }

    public int getUserID() {
        return UserID;
    }

    public int getNgaySinh() {
        return ngaySinh;
    }

    public int getThangSinh() {
        return thangSinh;
    }

    public int getNamSinh() {
        return namSinh;
    }
    
    public String getSDT(){
        ResultSet rs = query.findSDT_fromID(UserID);
        try {
            if(rs.isBeforeFirst() == false){
                System.out.println("UserID không hợp lệ.");
                return "";
            }
            else {
                rs.next();
                return rs.getString("sdt");
            }
        } catch (SQLException ex) {
            Logger.getLogger(nhanKhau.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public boolean isChuHo() {
        return (quanHe == "Chủ hộ");
    }

    public String getQuanHe() {
        return quanHe;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public String getCoQuan() {
        return coQuan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setGioiTinh(char gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setMaCanHo(String maCanHo) {
        this.maCanHo = maCanHo;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public void setNgaySinh(int ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setThangSinh(int thangSinh) {
        this.thangSinh = thangSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public void setQuanHe(String quanHe) {
        this.quanHe = quanHe;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public void setCoQuan(String coQuan) {
        this.coQuan = coQuan;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
}
