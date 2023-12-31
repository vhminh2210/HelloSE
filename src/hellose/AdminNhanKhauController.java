/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hellose;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import prj_class.User;
import prj_class.dbConnection;
import prj_class.dbQuery;
import prj_class.nhanKhau;
import prj_class.nhanKhauCanHo;
import prj_class.thuPhi;
import prj_class.utils;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AdminNhanKhauController extends SceneController implements Initializable {

    @FXML
    private TableView<nhanKhau> tbQuanLy;
    @FXML
    private TableColumn<nhanKhau, Integer> colID;
    @FXML
    private TableColumn<nhanKhau, String> colMaCanHo;
    @FXML
    private TableColumn<nhanKhau, String> colTen;
    @FXML
    private TableColumn<nhanKhau, String> colNgaySinh;
    @FXML
    private TableColumn<nhanKhau, String> colCCCD;
    @FXML
    private TableColumn<nhanKhau, String> colNgayBatDau;
    @FXML
    private TableColumn<nhanKhau, String> colNgayKetThuc;
    @FXML
    private TextField tbTen;
    @FXML
    private TextField tbCCCD;
    @FXML
    private Button btnTimKiem;
    @FXML
    private Button btnHome;
    @FXML
    private TextField tbDOB;
    @FXML
    private Text txLog;
    @FXML
    private TextField tbMaCanHo;
    @FXML
    private Button btnNhanKhauMoi;
    @FXML
    private Label lbID;
    @FXML
    private TextField tbID;
    @FXML
    private Button btnXoaNhanKhau;
    @FXML
    private Button btnXuat;
    
    private ObservableList <nhanKhau> nhanKhau_list;
    private dbQuery dbquery;
    private dbConnection dbconn;
    private User current_user;
    @FXML
    private Button btnChinhSua;
    @FXML
    private Button btnKhaiBao;

    ResultSet rs;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource()== btnXuat)try{
            if(rs != null) utils.xuatFile(rs, "nhankhau");
        } catch (Exception e){
            e.printStackTrace();
        }
        if(event.getSource() == btnNhanKhauMoi) {
            try {
                switchScene(event, "AdminNhanKhauMoi.fxml");
            } catch (IOException ex) {
                Logger.getLogger(AdminNhanKhauMoiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getSource() == btnHome){
            try {
                switchScene(event, "HomePage.fxml");
            } catch (IOException ex) {
                Logger.getLogger(AdminNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getSource() == btnTimKiem) nhanKhauQuery();
        if(event.getSource() == btnXoaNhanKhau) {
            try {
                switchScene(event, "AdminXoaNhanKhau.fxml");
            } catch (IOException ex) {
                Logger.getLogger(AdminNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getSource() == btnChinhSua){
            try {
                switchScene(event, "AdminChinhSuaNhanKhau.fxml");
            } catch (IOException ex) {
                Logger.getLogger(AdminNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getSource() == btnKhaiBao){
            try {
                switchScene(event, "AdminKhaiBaoTamTru.fxml");
            } catch (IOException ex) {
                Logger.getLogger(AdminNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        colNgaySinh.setCellValueFactory(new PropertyValueFactory<nhanKhau, String>("dob"));
        colCCCD.setCellValueFactory(new PropertyValueFactory<nhanKhau, String>("cccd"));
        colNgayBatDau.setCellValueFactory(new PropertyValueFactory<nhanKhau, String>("ngayBatDau"));
        colNgayKetThuc.setCellValueFactory(new PropertyValueFactory<nhanKhau, String>("ngayKetThuc"));
        
        tbQuanLy.setItems(list);
    }
    
    public void nhanKhauQuery(){
        String qTen = tbTen.getText();
        String qCCCD = tbCCCD.getText();
        String qDOB = tbDOB.getText();
        String qMaCanHo = tbMaCanHo.getText();
        String qID = tbID.getText();
        
        String query = "SELECT * FROM nhankhau WHERE userID > 0";
        if(qTen.length() > 0) query += " AND hoTen LIKE " + utils.quoteWrap("%" + qTen + "%");
        if(qCCCD.length() > 0) query += " AND cccd LIKE " + utils.quoteWrap("%" + qCCCD + "%");
        if(qDOB.length() > 0) {
            String[] words = qDOB.split("/");
            query += " AND ngaySinh = " + words[0];
            query += " AND thangSinh = " + words[1];
            query += " AND namSinh = " + words[2];
        }
        if(qMaCanHo.length() > 0) query += " AND maCanHo LIKE " + utils.quoteWrap("%" + qMaCanHo + "%");
        if(qID.length() > 0) query += "AND userID = " + utils.quoteWrap(qID);
        
        System.out.println(query);
        
        // ResultSet rs;
        try {
            // rs = dbquery.getSt().executeQuery(query);
            Statement st = dbquery.getConn().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(query);
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        current_user = hellose.HelloSE.getCurrent_user();
        dbconn = new dbConnection();
        dbquery = new dbQuery(dbconn.getConn());
        nhanKhau_list = FXCollections.observableArrayList();
        
        // Danh sách thu phí theo căn hộ
        String query = "SELECT * FROM nhankhau";
        // ResultSet rs;
        try {
            // rs = dbquery.getSt().executeQuery(query);
            Statement st = dbquery.getConn().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(query);
            while(rs.next()){
            nhanKhau tmp = new nhanKhau(rs);
            nhanKhau_list.add(tmp);
        }
        } catch (SQLException ex) {
            Logger.getLogger(AdminNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Đẩy danh sách lên GUI
        showList(nhanKhau_list);
    }    
    
}
