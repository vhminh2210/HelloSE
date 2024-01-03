/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hellose;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import prj_class.dbConnection;
import prj_class.dbQuery;
import prj_class.utils;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AdminChinhSuaNhanKhauController extends SceneController implements Initializable {

    @FXML
    private TextField tbID;
    @FXML
    private TextArea tbINFO;
    @FXML
    private Button btnTim;
    @FXML
    private TextField tbTen;
    @FXML
    private TextField tbCCCD;
    @FXML
    private TextField tbMaCanHo;
    @FXML
    private TextField tbNgaySinh;
    @FXML
    private TextField tbQuanHe;
    @FXML
    private TextField tbNgheNghiep;
    @FXML
    private TextField tbCoQuan;
    @FXML
    private TextField tbGioiTinh;
    @FXML
    private Button btnChinhSua;
    @FXML
    private Button btnBack;
    @FXML
    private Label txLog;

    private dbQuery dbquery;
    private dbConnection dbconn;
    
    public boolean TimNhanKhau() {
        String qID = tbID.getText();
        
        String query = "SELECT * FROM nhankhau WHERE userID = " + qID;
        int cnt = 0;
        ResultSet rs;
        try {
            rs = dbquery.getSt().executeQuery(query);
            while(rs.next()){
                cnt += 1;
                String info = "";
                info += "Họ và tên: " + rs.getString("hoTen") + "\n";
                info += "CCCD: " + rs.getString("cccd") + "\n";
                info += "Mã căn hộ: " + rs.getString("maCanHo") + "\n";
                info += "Ngày sinh: " + utils.dobFormat(rs.getInt("ngaySinh"), rs.getInt("thangSinh"), rs.getInt("namSinh")) + "\n";
                info += "Quan hệ với chủ hộ: " + rs.getString("quanHe") + "\n";
                info += "Nghề nghiệp: " + rs.getString("ngheNghiep") + "\n";
                info += "Cơ quan: " + rs.getString("coQuan") + "\n";
                info += "Giới tính: " + rs.getString("gioiTinh") + "\n";
                tbINFO.setText(info);
                return true;
            }
            if(cnt == 0){
                txLog.setText("Không tồn tại mã nhân khẩu này!");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminChinhSuaNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void ChinhSuaNhanKhau() {
        String qID = tbID.getText();
        String qTen = tbTen.getText();
        String qCCCD = tbCCCD.getText();
        String qMaCanHo = tbMaCanHo.getText();
        String qDOB = tbNgaySinh.getText();
        String qQuanHe = tbQuanHe.getText();
        String qNgheNghiep = tbNgheNghiep.getText();
        String qCoQuan = tbCoQuan.getText();
        String qGioiTinh = tbGioiTinh.getText();
        
        boolean ckc = TimNhanKhau();
        if(ckc == false) return;
        
        String query = "UPDATE nhankhau SET userID=" + qID;
        String info = "";
        if(qTen.length() > 0) {
            query += ",hoTen=" + utils.quoteWrap(qTen);
            info += "Họ và tên: " + qTen + "\n";
        }
        if(qCCCD.length() > 0) {
            query += ",cccd=" + utils.quoteWrap(qCCCD);
            info += "CCCD: " + qCCCD + "\n";
        }
        if(qMaCanHo.length() > 0) {
            query += ",maCanHo=" + utils.quoteWrap(qMaCanHo);
            info += "Mã căn hộ: " + qMaCanHo + "\n";
        }
        if(qDOB.length() > 0) {
            if(utils.checkDMY(qDOB)){
                txLog.setText("Sai định dạng ngày!");
                return;
            }
            String[] words = qDOB.split("/");
            query += ",ngaySinh=" + words[0];
            query += ",thangSinh" + words[1];
            query += ",namSinh" + words[2];
            info += "Ngày sinh: " + qDOB + "\n";
        }
        if(qQuanHe.length() > 0){
            query += ",quanHe=" + utils.quoteWrap(qQuanHe);
            info += "Quan hệ với chủ hộ: " + qQuanHe + "\n";
        }
        if(qNgheNghiep.length() > 0) {
            query += ",ngheNghiep=" + utils.quoteWrap(qNgheNghiep);
            info += "Nghề nghiệp: " + qNgheNghiep + "\n";
        }
        if(qCoQuan.length() > 0){
            query += ",coQuan=" + utils.quoteWrap(qCoQuan);
            info += "Cơ quan: " + qCoQuan + "\n";
        }
        if(qGioiTinh.length() > 0) {
            query += ",gioiTinh=" + utils.quoteWrap(qGioiTinh);
            info += "Giới tính: " + qGioiTinh + "\n";
        }
        query += " WHERE userID=" + qID;
        
        System.out.println(query);
        
        /*Tham khảo: https://openplanning.net/11529/javafx-alert-dialog#1101511*/
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cập nhật nhân khẩu");
        alert.setHeaderText("Bạn chắc chắn muốn cập nhật nhân khẩu này với các thiết lập mới sau đây chứ?");
        alert.setContentText(info);
        
        Optional<ButtonType> option = alert.showAndWait();
        
	if (option.get() == null) {
	txLog.setText("Chưa đưa ra lựa chọn!");
	} else if (option.get() == ButtonType.OK) {
            try {
                dbquery.getSt().executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(AdminChinhSuaNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            }
		txLog.setText("Đã chỉnh sửa nhân khẩu được chọn!");
	}
        System.out.println(query);  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbconn = new dbConnection();
        dbquery = new dbQuery(dbconn.getConn());
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnTim) TimNhanKhau();
        if(event.getSource() == btnChinhSua) ChinhSuaNhanKhau();
        if(event.getSource() == btnBack){
            try {
                switchScene(event, "AdminNhanKhau.fxml");
            } catch (IOException ex) {
                Logger.getLogger(AdminChinhSuaNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
