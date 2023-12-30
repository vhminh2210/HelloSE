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
import prj_class.thuPhi;

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
    
    private ObservableList <nhanKhau> nhanKhau_list;
    private dbQuery dbquery;
    private dbConnection dbconn;
    private User current_user;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
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
        
        tbQuanLy.setItems(list);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        current_user = hellose.HelloSE.getCurrent_user();
        dbconn = new dbConnection();
        dbquery = new dbQuery(dbconn.getConn());
        nhanKhau_list = FXCollections.observableArrayList();
        
        // Danh sách thu phí theo căn hộ
        String query = "SELECT * FROM nhankhau";
        ResultSet rs;
        try {
            rs = dbquery.getSt().executeQuery(query);
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
