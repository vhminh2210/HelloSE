/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hellose;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import prj_class.dbConnection;
import prj_class.dbQuery;
import prj_class.utils;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AdminNhanKhauMoiController extends SceneController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private TextField tbTen;
    @FXML
    private TextField tbCCCD;
    @FXML
    private TextField tbMaCanHo;
    @FXML
    private TextField tbDOB;
    @FXML
    private TextField tbNgheNghiep;
    @FXML
    private TextField tbCoQuan;
    @FXML
    private TextField tbStart;
    @FXML
    private TextField tbQuanHe;
    @FXML
    private Button btnThemMoi;
    @FXML
    private Label tbLog;
    
    private dbQuery dbquery;
    private dbConnection dbconn; 
    @FXML
    private TextField tbGioiTinh;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnBack) {
            try {
                switchScene(event, "AdminNhanKhau.fxml");
            } catch (IOException ex) {
                Logger.getLogger(AdminNhanKhauMoiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getSource() == btnThemMoi) queryThemNhanKhau();
    }

    public void queryThemNhanKhau(){
        String qTen = tbTen.getText();
        String qCCCD = tbCCCD.getText();
        String qMaCanHo = tbMaCanHo.getText();
        String qDOB = tbDOB.getText();
        String qQuanHe = tbQuanHe.getText();
        String qNgheNghiep = tbNgheNghiep.getText();
        String qCoQuan = tbCoQuan.getText();
        String qNgayBatDau = tbStart.getText();
        String qGioiTinh = tbGioiTinh.getText();
        String qNgaySinh, qThangSinh, qNamSinh;
        
        if(qTen.length() == 0) {
            tbLog.setText("Hãy bổ sung thêm tên nhân khẩu!");
            return;
        }
        
        if(qCCCD.length() == 0) {
            tbLog.setText("Hãy bổ sung thêm số CCCD!");
            return;
        }
        
        if(qMaCanHo.length() == 0) {
            tbLog.setText("Hãy bổ sung thêm mã căn hộ!");
            return;
        }
        
        if(qNgayBatDau.length() == 0) {
            tbLog.setText("Hãy bổ sung thêm ngày bắt đâu!");
            return;
        }
        else if(utils.checkDMY(qNgayBatDau) == false) {
            tbLog.setText("Ghi sai định dạng ngày!");
            return;
        }
        
        if(qDOB.length() == 0) {
            qNgaySinh = qThangSinh = qNamSinh = "NA";
        }
        else {
            if(utils.checkDMY(qDOB) == false) {
                tbLog.setText("Sai định dạng ngày!");
                return;
            }
            String[] words = qDOB.split("/");
            qNgaySinh = words[0];
            if(qNgaySinh.equals("00")) qNgaySinh = "NA";
            qThangSinh = words[1];
            if(qThangSinh.equals("00")) qThangSinh = "NA";
            qNamSinh = words[2];
            if(qNamSinh.equals("00")) qNamSinh = "NA";
        }
        
        String query = "INSERT INTO nhankhau (hoTen, cccd, maCanHo, ngaySinh, thangSinh, namSinh, quanHe, ngheNghiep, coQuan, trangThai, gioiTinh) VALUES";
        String value = utils.quoteWrap(qTen) + ", ";
        value += (utils.quoteWrap(qCCCD) + ", ");
        value += (utils.quoteWrap(qMaCanHo) + ", ");
        value += qNgaySinh + ", " + qThangSinh + ", " + qNamSinh + ", ";
        value += (utils.quoteWrap(qQuanHe) + ", ");
        value += (utils.quoteWrap(qNgheNghiep) + ", ");
        value += (utils.quoteWrap(qCoQuan) + ", ");
        value += (utils.quoteWrap("Có mặt") + ", ");
        value += (utils.quoteWrap(qGioiTinh));
        query = query + "(" + value + ")";
        
        try {
            System.out.println(query);
            dbquery.getSt().executeUpdate(query);
            System.out.println("Tạo mới nhân khẩu thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(AdminNhanKhauMoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "SELECT userID FROM nhankhau ORDER BY userID DESC LIMIT 1";
        System.out.println(query);
        try {
            ResultSet rs = dbquery.getSt().executeQuery(query);
            int max_userid = 0;
            while(rs.next()){
                max_userid = rs.getInt("userID");
            }
            System.out.println(max_userid);
            query = "INSERT INTO nhankhau_canho (userID, maCanHo, ngayBatDau) VALUES";
            value = Integer.toString(max_userid) + ", ";
            value += (utils.quoteWrap(qMaCanHo) + ", ");
            value += (utils.quoteWrap(utils.dmy2ymd(qNgayBatDau)));
            value = "(" + value + ")";
            query += value;
            System.out.println(query);
            dbquery.getSt().executeUpdate(query);
            System.out.println("Cập nhật lịch sử thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(AdminNhanKhauMoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tbLog.setText("Tạo nhân khẩu mới thành công!");
        return;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbconn = new dbConnection();
        dbquery = new dbQuery(dbconn.getConn());
    }    
    
}
