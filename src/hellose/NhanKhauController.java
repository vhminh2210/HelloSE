/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hellose;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import java.sql.ResultSet;
import java.sql.SQLException;

import prj_class.NhanKhauVBoxController;
import prj_class.User;
import prj_class.nhanKhau;
import prj_class.dbConnection;
import prj_class.dbQuery;
import prj_class.utils;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class NhanKhauController extends SceneController implements Initializable {

    @FXML
    private Label lbTitle;
    @FXML
    private Label lbTTChung;
    @FXML
    private Label lbSoCanHo;
    @FXML
    private Label lbtxSoCanHo;
    @FXML
    private Label lbChuHo;
    @FXML
    private Label lbTTChuHo;
    @FXML
    private Label lbHoTen;
    @FXML
    private Label lbDOB;
    @FXML
    private Label lbsdt;
    @FXML
    private Label lbcccd;
    @FXML
    private ListView<VBox> lvInfo;
    @FXML
    private Label lbTTThanhVien;
    @FXML
    private Button btnHome;
    @FXML
    private Label lbHoTenChuHo;
    @FXML
    private Label lbNgaySinh;
    @FXML
    private Label lbSDT;
    @FXML
    private Label lbCCCD;
    
    private User current_user;
    private nhanKhau current_nk;
    private dbQuery dbquery;
    private dbConnection conn;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnHome) {
            try {
                switchScene(event, "HomePage.fxml");
            } catch (IOException ex) {
                Logger.getLogger(NhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void addMember(nhanKhau nk){
//        nhanKhau nk = new nhanKhau("Vương Hoàng Minh", "cccd", "12345", 1, 2, 3, 4, "Con", "sinhvien", "HUST", "stay", 'M');
        NhanKhauVBoxController nkvb = new NhanKhauVBoxController();
        nkvb.setNk(nk);
        VBox vbox = nkvb.getVbox();
        lvInfo.getItems().add(vbox);
        System.out.println(vbox.getChildren());
    }
    
    public void loadChuHo(nhanKhau chuHo){
        lbChuHo.setText(chuHo.getHoTen());
        lbHoTenChuHo.setText(chuHo.getHoTen());
        lbNgaySinh.setText(utils.dobFormat(chuHo.getNgaySinh(), chuHo.getThangSinh(), chuHo.getNamSinh()));
        lbSDT.setText(chuHo.getSDT());
        lbCCCD.setText(chuHo.getCccd());
        lbtxSoCanHo.setText(chuHo.getMaCanHo());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        current_user = hellose.HelloSE.getCurrent_user();
        conn = new dbConnection();
        dbquery = new dbQuery(conn.getConn());
        
        String query = "SELECT * FROM nhankhau WHERE userID = " + String.valueOf(current_user.getUserID());
        ResultSet rs;
        try {
            rs = dbquery.getSt().executeQuery(query);
            rs.next();
            current_nk = new nhanKhau(rs);
        } catch (SQLException ex) {
            Logger.getLogger(NhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ResultSet dstv = dbquery.dsThanhVien(current_nk.getMaCanHo());
        nhanKhau chuHo;
        try {
            while(dstv.next()){
                nhanKhau tmp_nk = new nhanKhau(dstv);
                addMember(tmp_nk);
                if(tmp_nk.getQuanHe().equals("Chủ hộ") || tmp_nk.getQuanHe().equals("ADMIN")){
                    chuHo = tmp_nk;
                    loadChuHo(chuHo);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
