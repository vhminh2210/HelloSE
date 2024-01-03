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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import prj_class.dbConnection;
import prj_class.dbQuery;
import prj_class.utils;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AdminChinhSuaKhoanThuController extends SceneController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private TextField tbID;
    @FXML
    private TextField tbTen;
    @FXML
    private TextField tbTenMoi;
    @FXML
    private TextField tbThangMoi;
    @FXML
    private TextField tbTrangThaiMoi;
    @FXML
    private TextField tbNamMoi;
    @FXML
    private TextField tbSoTienMoi;
    @FXML
    private TextField tbGhiChuMoi;
    @FXML
    private TextField tbMaCanHoMoi;
    @FXML
    private Button btnCapNhat;
    @FXML
    private TextField tbThangCu;
    @FXML
    private TextField tbNamCu;
    @FXML
    private Label txLog;
   
    private dbQuery dbquery;
    private dbConnection dbconn;

    public void ChinhSuaKhoanThu() {
        String qID = tbID.getText();
        String qTenCu = tbTen.getText();
        String qThangCu = tbThangCu.getText();
        String qNamCu = tbNamCu.getText();
        String qTenMoi = tbTenMoi.getText();
        String qThangMoi = tbThangMoi.getText();
        String qNamMoi = tbNamMoi.getText();
        String qTrangThaiMoi = tbTrangThaiMoi.getText();
        String qSoTienMoi = tbSoTienMoi.getText();
        String qGhiChuMoi = tbGhiChuMoi.getText();
        String qMaCanHoMoi = tbMaCanHoMoi.getText();
        
        if(qID.length() > 0 && utils.isNumeric(qID) == false){
            txLog.setText("Sai định dạng mã khoản thu");
            System.out.println("Sai định dạng mã khoản thu");
            return;
        }
        
        if(qID.length() == 0 && qMaCanHoMoi.length() > 0) {
            txLog.setText("Thiết đặt mã căn hộ chỉ dùng cho các khoản thu lẻ!");
            System.out.println("Thiết đặt mã căn hộ chỉ dùng cho các khoản thu lẻ!");
            return;
        }       
        
        System.out.println("Begin");
        
        String query = "UPDATE thuphi SET";
        String info = "";
        
        if(qTenMoi.length() > 0) {
            query += " tenKhoanThu=" + utils.quoteWrap(qTenMoi) + ",";
            info += "Tên khoản thu mới: " + qTenMoi + "\n";
        }
        System.out.println(info);
        if(qThangMoi.length() > 0) {
            query += " Thang=" + qThangMoi + ",";
            info += "Tháng mới: " + qThangMoi + "\n";
        }
        if(qNamMoi.length() > 0) {
            query += " Nam=" + qNamMoi + ",";
            info += "Năm mới: " + qNamMoi + "\n";
        }
        if(qTrangThaiMoi.length() > 0) {
            query += " trangThai=" + utils.quoteWrap(qTrangThaiMoi) + ",";
            info += "Trạng thái mới: " + qTrangThaiMoi + "\n";
        }
        if(qSoTienMoi.length() > 0) {
            query += " soTien=" + qSoTienMoi + ",";
            info += "Số tiền mới: " + qSoTienMoi + "\n";
        }
        if(qGhiChuMoi.length() > 0) {
            query += " ghiChuMoi=" + utils.quoteWrap(qGhiChuMoi) + ",";
            info += "Ghi chú mới: " + qGhiChuMoi + "\n";
        }
        
        if(query.charAt(query.length() - 1) == ',') query = query.substring(0, query.length() - 1);
        query += "WHERE maKhoanThu > 0";
        
        if(qID.length() > 0) query += " AND maKhoanThu = " + qID;
        if(qTenCu.length() > 0) query += " AND tenKhoanThu LIKE " + utils.quoteWrap("%" + qTenCu + "%");
        if(qThangCu.length() > 0) query += " AND Thang = " + qThangCu;
        if(qNamCu.length() > 0) query += " AND Nam = " + qNamCu;
        
        /*Tham khảo: https://openplanning.net/11529/javafx-alert-dialog#1101511*/
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cập nhật khoản thu");
        alert.setHeaderText("Bạn chắc chắn muốn cập nhật các khoản thu này với các thiết lập mới sau đây chứ?");
        alert.setContentText(info);
        
        Optional<ButtonType> option = alert.showAndWait();
        
	if (option.get() == null) {
	txLog.setText("Chưa đưa ra lựa chọn!");
	} else if (option.get() == ButtonType.OK) {
                try {
                    dbquery.getSt().executeUpdate(query);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminChinhSuaKhoanThuController.class.getName()).log(Level.SEVERE, null, ex);
                }
		txLog.setText("Đã chỉnh sửa các khoản thu được chọn!");
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
        if(event.getSource() == btnBack){
            try {
                switchScene(event, "AdminThuPhi.fxml");
            } catch (IOException ex) {
                Logger.getLogger(AdminChinhSuaKhoanThuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(event.getSource() == btnCapNhat)  {
            System.out.println("Chỉnh sửa khoản thu");
            ChinhSuaKhoanThu();
        }
    }
    
}
