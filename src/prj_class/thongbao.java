/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prj_class;

// import com.mysql.jdbc.Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Date;
import java.lang.String;

/**
 *
 * @author admin
 */
public class thongbao {
    private String mathongbao;
    private Date ngaythongbao;
    private String noidung;

    public thongbao( String mathongbao, Date ngaythongbao, String noidung) {
        this.mathongbao = mathongbao;
        this.ngaythongbao = ngaythongbao;
        this.noidung = noidung;
    }

    public thongbao(ResultSet rs){
        try {
            this.mathongbao = rs.getString("mathongbao");
            this.ngaythongbao = rs.getDate("ngaythongbao");
            this.noidung = rs.getString("noidung");
        } catch (SQLException ex) {
            Logger.getLogger(thongbao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMathongbao() {
        return mathongbao;
    }

    public Date getNgaythongbao() {
        return ngaythongbao;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setMathongbao(String mathongbao) {
        this.mathongbao = mathongbao;
    }

    public void setNgaythongbao(Date ngaythongbao) {
        this.ngaythongbao = ngaythongbao;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

}