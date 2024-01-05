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

/**
 *
 * @author admin
 */
public class homthu {
    private String mahomthu;
    private String ngayGopY;
    private String userID;
    private String noidung;

    public homthu( String mahomthu, String ngayGopY,String userID, String noidung) {
        this.mahomthu = mahomthu;
        this.ngayGopY = ngayGopY;
        this.userID=userID;
        this.noidung = noidung;
    }

    public homthu(ResultSet rs){
        try {
            this.mahomthu = rs.getString("mahomthu");
            this.ngayGopY = rs.getString("ngayGopY");
            this.userID = rs.getString("userID");
            this.noidung = rs.getString("noidung");

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMahomthu() {
        return mahomthu;
    }

    public String getNgayGopY() {
        return ngayGopY;
    }

    public String getUserID() {
        return userID;
    }
    public String getNoidung() {
        return noidung;
    }

    public void setMahomthu(String mahomthu) {
        this.mahomthu = mahomthu;
    }

    public void setNgayGopY(String ngayGopY) {
        this.ngayGopY = ngayGopY;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }


}