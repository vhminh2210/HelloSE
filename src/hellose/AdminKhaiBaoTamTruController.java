/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hellose;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
public class AdminKhaiBaoTamTruController extends SceneController implements Initializable {

    @FXML
    private TextField tbCCCD;
    @FXML
    private TextField tbStart;
    @FXML
    private TextField tbEnd;
    @FXML
    private ChoiceBox<String> cbTrangThai;
    @FXML
    private Button btnKhaiBao;
    @FXML
    private Button btnBack;

    private dbQuery dbquery;
    private dbConnection dbconn;
    @FXML
    private Label txLog;
    
    public void KhaiBao(){
        String qCCCD = tbCCCD.getText();
        String qStart = tbStart.getText();
        String qEnd = tbEnd.getText();
        String qTrangThai = cbTrangThai.getValue();
        String query1 = "SELECT userID, maCanHo FROM nhanKhau WHERE cccd = " + utils.quoteWrap(qCCCD);
        String qMaCanHo = "";
        String qID = "";
        ResultSet rs;
        try {
            rs = dbquery.getSt().executeQuery(query1);
            while(rs.next()){
                qMaCanHo = rs.getString("maCanHo");
                qID = Integer.toString(rs.getInt("userID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminKhaiBaoTamTruController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(qCCCD.length() * qStart.length() * qEnd.length() * qTrangThai.length() == 0){
            txLog.setText("Vui lòng hoàn thiện các thông tin!");
            return;
        }
        if(utils.checkDMY(qStart) == false || utils.checkDMY(qEnd) == false){
            txLog.setText("Ngày cần được điền theo định dạng dd/mm/yyyy");
            return;
        }
        String query2 = "INSERT INTO nhankhau_tamtru (userID, maCanHo, trangThai, ngayBatDau, ngayKetThuc) VALUES ";
        query2 += "(" + qID + ", ";
        query2 += utils.quoteWrap(qMaCanHo) + ", ";
        query2 += utils.quoteWrap(qTrangThai) + ", ";
        query2 += utils.quoteWrap(utils.dmy2ymd(qStart)) + ", ";
        query2 += utils.quoteWrap(utils.dmy2ymd(qEnd)) + ")";
        
        System.out.println(query2);
        
        try {
            dbquery.getSt().executeUpdate(query2);
            txLog.setText("Khai báo thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(AdminKhaiBaoTamTruController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       String[] TrangThai = {"Tạm trú", "Tạm vắng"};
       dbconn = new dbConnection();
       dbquery = new dbQuery(dbconn.getConn());
       cbTrangThai.getItems().addAll(TrangThai);
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        txLog.setText("");
        if(event.getSource() == btnBack){
            try {
                switchScene(event, "AdminNhanKhau.fxml");
            } catch (IOException ex) {
                Logger.getLogger(AdminKhaiBaoTamTruController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(event.getSource() == btnKhaiBao) KhaiBao();
    }
    
}
