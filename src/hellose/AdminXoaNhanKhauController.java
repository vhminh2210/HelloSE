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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import prj_class.User;
import prj_class.dbConnection;
import prj_class.dbQuery;
import prj_class.nhanKhau;
import prj_class.utils;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AdminXoaNhanKhauController extends SceneController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private TextField tbTen;
    @FXML
    private TextField tbMaCanHo;
    @FXML
    private TextField tbCCCD;
    @FXML
    private TextField tbID;
    @FXML
    private TableView<nhanKhau> tbQuanLy;
    @FXML
    private TableColumn<nhanKhau, Integer> colID;
    @FXML
    private TableColumn<nhanKhau, String> colTen;
    @FXML
    private TableColumn<nhanKhau, String> colMaCanHo;
    @FXML
    private TableColumn<nhanKhau, String> colCCCD;
    @FXML
    private Button btnTimKiem;
    @FXML
    private Button btnXoaNhanKhau;
    @FXML
    private TextField tbNgayKetThuc;
    @FXML
    private Label txLog;

    private dbQuery dbquery;
    private dbConnection dbconn;
    private User current_user;
    private String query;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnBack) {
            try {
                switchScene(event, "AdminNhanKhau.fxml");
            } catch (IOException ex) {
                Logger.getLogger(AdminXoaNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getSource() == btnTimKiem){
            nhanKhauQuery();
        }
        if(event.getSource() == btnXoaNhanKhau){
            XoaNhanKhau();
        }
    }
    
    public void showList(ObservableList <nhanKhau> list){
        if(list.size() == 0){
            txLog.setText("Không tìm thấy kết quả phù hợp!");
            return;
        }
        colID.setCellValueFactory(new PropertyValueFactory<nhanKhau, Integer>("userID"));
        colMaCanHo.setCellValueFactory(new PropertyValueFactory<nhanKhau, String>("maCanHo"));
        colTen.setCellValueFactory(new PropertyValueFactory<nhanKhau, String>("hoTen"));
        colCCCD.setCellValueFactory(new PropertyValueFactory<nhanKhau, String>("cccd"));
        
        tbQuanLy.setItems(list);
    }
    
    public void nhanKhauQuery(){
        String qTen = tbTen.getText();
        String qCCCD = tbCCCD.getText();
        String qMaCanHo = tbMaCanHo.getText();
        String qID = tbID.getText();
        
        query = "SELECT * FROM nhankhau WHERE userID > 0";
        if(qTen.length() > 0) query += " AND hoTen LIKE " + utils.quoteWrap("%" + qTen + "%");
        if(qCCCD.length() > 0) query += " AND cccd LIKE " + utils.quoteWrap("%" + qCCCD + "%");
        if(qMaCanHo.length() > 0) query += " AND maCanHo LIKE " + utils.quoteWrap("%" + qMaCanHo + "%");
        if(qID.length() > 0) query += "AND userID = " + utils.quoteWrap(qID);
        
        String select_query = "SELECT * FROM nhankhau WHERE userID > 0" + query;
        System.out.println(select_query);
        
        ResultSet rs;
        try {
            rs = dbquery.getSt().executeQuery(select_query);
            ObservableList <nhanKhau> list = FXCollections.observableArrayList();
            while(rs.next()){
                nhanKhau tmp = new nhanKhau(rs);
                list.add(tmp);
            }
            if(list != null) showList(list);
            else txLog.setText("Không tìm thấy kết quả phù hợp!");
        } catch (SQLException ex) {
            Logger.getLogger(ThuPhiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void XoaNhanKhau() {
        String ngayKetThuc = tbNgayKetThuc.getText();
        if(ngayKetThuc.length() == 0){
            txLog.setText("Cần bổ sung thêm ngày kết thúc!");
            return;
        }
        String info = "Ngày kết thúc: " + ngayKetThuc + "\n**********\n";
        String select_query = "SELECT * FROM nhankhau WHERE userID > 0" + query;
        try {
            ResultSet rs = dbquery.getSt().executeQuery(select_query);
            while(rs.next()) {
                info += "UserID: " + Integer.toString(rs.getInt("UserID")) + "\n";
                info += "Họ và tên: "  + rs.getString("hoTen") + "\n";
                info += "Mã căn hộ: " + rs.getString("maCanHo") + "\n";
                info += "Số CCCD: " + rs.getString("cccd") + "\n\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminXoaNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*Tham khảo: https://openplanning.net/11529/javafx-alert-dialog#1101511*/
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xóa nhân khẩu");
        alert.setHeaderText("Bạn chắc chắn muốn xóa các nhân khẩu này chứ?");
        alert.setContentText(info);
        
        Optional<ButtonType> option = alert.showAndWait();
        
	if (option.get() == null) {
		txLog.setText("Chưa đưa ra lựa chọn!");
	} else if (option.get() == ButtonType.OK) {
                try {
                    String delquery = "UPDATE nhankhau_canho SET ngayKetThuc=" + utils.dmy2ymd(ngayKetThuc);
                    delquery += " WHERE userID > 0" + query;
                    System.out.println(delquery);
                    dbquery.getSt().executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(AdminThuPhiController.class.getName()).log(Level.SEVERE, null, ex);
                }
		txLog.setText("Đã xóa các khoản thu được chọn!");
	}
        System.out.println(query);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        current_user = hellose.HelloSE.getCurrent_user();
        dbconn = new dbConnection();
        dbquery = new dbQuery(dbconn.getConn());
        
    }    
    
}
