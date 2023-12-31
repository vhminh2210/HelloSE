/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hellose;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import prj_class.thuPhi;
import prj_class.utils;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ThuPhiController extends SceneController implements Initializable {
    @FXML
    private TableColumn<thuPhi, String> colMaCanHo;
    @FXML
    private TableColumn<thuPhi, String> colTenKhoanThu;
    @FXML
    private TableColumn<thuPhi, String> colThang;
    @FXML
    private TableColumn<thuPhi, Integer> colSoTien;
    @FXML
    private TableColumn<thuPhi, String> colTrangThai;
    @FXML
    private TableColumn<thuPhi, String> colGhiChu;
    @FXML
    private TextField tbTrangThai;
    @FXML
    private TextField tbThang;
    @FXML
    private TextField tbKhoanThu;
    @FXML
    private TableView<thuPhi> tbQuanLy;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnTimKiem;
    @FXML
    private Label lbNam;
    @FXML
    private TextField tbNam;
    @FXML
    private Text txLog;
    
    private ObservableList <thuPhi> thuPhi_list;
    private dbQuery dbquery;
    private dbConnection dbconn;
    private User current_user;
    private String current_maCanHo;
    
    public void thuPhiQuery(){
        String qTrangThai = tbTrangThai.getText();
        String qKhoanThu = tbKhoanThu.getText();
        String qThang = tbThang.getText();
        String qNam = tbNam.getText();
        
        String query = "SELECT * FROM thuphi WHERE Thang > 0";
        if(qTrangThai.length() > 0) query += " AND trangThai = " + utils.quoteWrap(qTrangThai);
        if(qKhoanThu.length() > 0) query += " AND tenKhoanThu LIKE " + utils.quoteWrap("%" + qKhoanThu + "%");
        if(qThang.length() > 0) query += " AND Thang = " + String.valueOf(qThang);
        if(qNam.length() > 0) query += " AND Nam = " + String.valueOf(qNam);
        
        System.out.println(query);
        
        ResultSet rs;
        try {
            rs = dbquery.getSt().executeQuery(query);
            ObservableList <thuPhi> list = FXCollections.observableArrayList();
            while(rs.next()){
                thuPhi tmp = new thuPhi(rs);
                list.add(tmp);
            }
            if(list != null) showList(list);
            else txLog.setText("Không tìm thấy kết quả phù hợp!");
        } catch (SQLException ex) {
            Logger.getLogger(ThuPhiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnHome) try {
            switchScene(event, "HomePage.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(event.getSource() == btnTimKiem) thuPhiQuery();
    }
    
    public void showList(ObservableList <thuPhi> list){
        if(list.size() == 0){
            txLog.setText("Không tìm thấy kết quả phù hợp!");
            return;
        }
        colMaCanHo.setCellValueFactory(new PropertyValueFactory<thuPhi, String>("maCanHo"));
        colTenKhoanThu.setCellValueFactory(new PropertyValueFactory<thuPhi, String>("tenKhoanThu"));
        colTrangThai.setCellValueFactory(new PropertyValueFactory<thuPhi, String>("trangThai"));
        colGhiChu.setCellValueFactory(new PropertyValueFactory<thuPhi, String>("ghiChu"));
        colThang.setCellValueFactory(new PropertyValueFactory<thuPhi, String>("thangNam"));
        colSoTien.setCellValueFactory(new PropertyValueFactory<thuPhi, Integer>("soTien"));
        
        tbQuanLy.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        current_user = hellose.HelloSE.getCurrent_user();
        dbconn = new dbConnection();
        dbquery = new dbQuery(dbconn.getConn());
        thuPhi_list = FXCollections.observableArrayList();
        
        // Tìm căn hộ của người dùng
        String query = "SELECT nhankhau.maCanHo FROM user, nhankhau WHERE user.UserID = nhanKhau.userID";
        try {
            ResultSet rs = dbquery.getSt().executeQuery(query);
            rs.next();
            current_maCanHo = rs.getString("maCanHo");
        } catch (SQLException ex) {
            Logger.getLogger(ThuPhiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Danh sách thu phí theo căn hộ
        query = "SELECT * FROM thuphi WHERE maCanHo = " + utils.quoteWrap(current_maCanHo);
        try {
            ResultSet rs = dbquery.getSt().executeQuery(query);
            while(rs.next()){
                thuPhi tmp = new thuPhi(rs);
                thuPhi_list.add(tmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuPhiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Đẩy danh sách lên GUI
        showList(thuPhi_list);
    }    
    
}
