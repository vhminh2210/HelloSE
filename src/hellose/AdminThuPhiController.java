/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hellose;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class AdminThuPhiController extends SceneController implements Initializable {
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
    private TextField tbNam;
    @FXML
    private Text txLog;
    @FXML
    private TextField tbMaCanHo;
    @FXML
    private Button btnKhoanThuMoi;
    
    private ObservableList <thuPhi> thuPhi_list;
    private dbQuery dbquery;
    private dbConnection dbconn;
    private User current_user;
    @FXML
    private TableColumn<thuPhi, Integer> colID;
    @FXML
    private Button btnXoaKhoanThu;
    @FXML
    private TextField tbID;
    @FXML
    private Label lbNam;
    @FXML
    private Label lbMaCanHo;
    @FXML
    private Label lbID;
    @FXML
    private Button btnChinhSuaKhoanThu;
    @FXML
    private Button btnXuat;
    private Label lbChuaDong;
    @FXML
    private Label lbDaDong;
    @FXML
    private Label lbSum;
    @FXML
    private Label lbxChuaDong;
    @FXML
    private Label lbxDaDong;
    @FXML
    private Label lbxSum;
    
    private ResultSet rs;

    public void thuPhiQuery(){
        String qTrangThai = tbTrangThai.getText();
        String qKhoanThu = tbKhoanThu.getText();
        String qThang = tbThang.getText();
        String qNam = tbNam.getText();
        String qMaCanHo = tbMaCanHo.getText();
        String qID = tbID.getText();
        
        String backquery = "FROM thuphi WHERE Thang > 0";
        if(qTrangThai.length() > 0) backquery += " AND trangThai = " + utils.quoteWrap(qTrangThai);
        if(qKhoanThu.length() > 0) backquery += " AND tenKhoanThu LIKE " + utils.quoteWrap("%" + qKhoanThu + "%");
        if(qThang.length() > 0) backquery += " AND Thang = " + String.valueOf(qThang);
        if(qNam.length() > 0) backquery += " AND Nam = " + String.valueOf(qNam);
        if(qMaCanHo.length() > 0) backquery += " AND maCanHo = " + utils.quoteWrap(qMaCanHo);
        if(qID.length() > 0) backquery += " AND maKhoanThu = " + String.valueOf(qID);

        String query = "SELECT * " + backquery;
        String querysum = "SELECT SUM(soTien) AS tong " + backquery;
        String queryDaDong = "SELECT SUM(soTien) AS tong " + backquery + " AND trangThai = \"Đã đóng\"";
        String queryChuaDong= "SELECT SUM(soTien) AS tong " + backquery + " AND trangThai = \"Chưa đóng\"";
        
        System.out.println(query);
        
        try {
            Statement st = dbquery.getConn().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(query);
            ObservableList <thuPhi> list = FXCollections.observableArrayList();
            while(rs.next()){
                thuPhi tmp = new thuPhi(rs);
                list.add(tmp);
            }
            ResultSet trs;
            trs = dbquery.getSt().executeQuery(querysum);
            if(trs.next() && trs.getString("tong") != null) lbxSum.setText(trs.getString("tong"));
            else lbxSum.setText("0");
            trs = dbquery.getSt().executeQuery(queryDaDong);
            if(trs.next() && trs.getString("tong") != null) lbxDaDong.setText(trs.getString("tong"));
            else lbxDaDong.setText("0");
            trs = dbquery.getSt().executeQuery(queryChuaDong);
            if(trs.next() && trs.getString("tong") != null) lbxChuaDong.setText(trs.getString("tong"));
            else lbxChuaDong.setText("0");
            if(list != null) showList(list);
            else txLog.setText("Không tìm thấy kết quả phù hợp!");
        } catch (SQLException ex) {
            Logger.getLogger(ThuPhiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void xoaKhoanPhi(){
        String qTrangThai = tbTrangThai.getText();
        String qKhoanThu = tbKhoanThu.getText();
        String qThang = tbThang.getText();
        String qNam = tbNam.getText();
        String qMaCanHo = tbMaCanHo.getText();
        String qID = tbID.getText();
    
        String info = "";
        String query = "DELETE FROM thuphi WHERE Thang > 0";
        
        if(qTrangThai.length() > 0) {
            query += " AND trangThai = " + utils.quoteWrap(qTrangThai);
            info += "Trạng thái: " + qTrangThai + "\n";
        } 
        if(qKhoanThu.length() > 0) {
            query += " AND tenKhoanThu LIKE " + utils.quoteWrap("%" + qKhoanThu + "%");
            info += "Tên Khoản thu: " + qKhoanThu + "\n";
        }
        if(qThang.length() > 0) {
            query += " AND Thang = " + String.valueOf(qThang);
            info += "Tháng: " + String.valueOf(qThang) + "\n";
        }
        if(qNam.length() > 0) {
            query += " AND Nam = " + String.valueOf(qNam);
            info += "Năm: " + String.valueOf(qNam) + "\n";
        }
        if(qMaCanHo.length() > 0) {
            query += " AND maCanHo = " + utils.quoteWrap(qMaCanHo);
            info += "Mã căn hộ: " + qMaCanHo + "\n";
        }
        if(qID.length() > 0) {
            query += " AND maKhoanThu = " + String.valueOf(qID);
            info += "Mã khoản thu: " + qID + "\n";
        }
        /*Tham khảo: https://openplanning.net/11529/javafx-alert-dialog#1101511*/
        Alert alert = new Alert(AlertType.CONFIRMATION);
            
        alert.setTitle("Xóa khoản thu");
        alert.setHeaderText("Bạn chắc chắn muốn xóa các khoản thu này chứ?");
        alert.setContentText(info);
        
        Optional<ButtonType> option = alert.showAndWait();
        
	if (option.get() == null) {
		txLog.setText("Chưa đưa ra lựa chọn!");
	} else if (option.get() == ButtonType.OK) {
                try {
                dbquery.getSt().executeUpdate(query);
                } catch (SQLException ex) {
                Logger.getLogger(AdminThuPhiController.class.getName()).log(Level.SEVERE, null, ex);
                }
		txLog.setText("Đã xóa các khoản thu được chọn!");
	}
        System.out.println(query);
       
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource()== btnXuat)try{
            if(rs != null) utils.xuatFile(rs, "thuphi");
        } catch (Exception e){
            e.printStackTrace();
        }
        if(event.getSource() == btnHome) try {
            switchScene(event, "HomePage.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(event.getSource() == btnKhoanThuMoi) try {
            switchScene(event, "AdminKhoanThuMoi.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(event.getSource() == btnTimKiem) thuPhiQuery();
        if(event.getSource() == btnXoaKhoanThu) xoaKhoanPhi();
        if(event.getSource() == btnChinhSuaKhoanThu) {
            try {
                switchScene(event, "AdminChinhSuaKhoanThu.fxml");
            } catch (IOException ex) {
                Logger.getLogger(AdminThuPhiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        colID.setCellValueFactory(new PropertyValueFactory<thuPhi, Integer>("maKhoanThu"));
        
        tbQuanLy.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        current_user = hellose.HelloSE.getCurrent_user();
        dbconn = new dbConnection();
        dbquery = new dbQuery(dbconn.getConn());
        thuPhi_list = FXCollections.observableArrayList();
        
        // Danh sách thu phí theo căn hộ
        String query = "SELECT * FROM thuphi";
        String querysum = "SELECT SUM(soTien) AS tong FROM thuphi";
        String queryDaDong = "SELECT SUM(soTien) AS tong FROM thuphi WHERE trangThai = \"Đã đóng\"";
        String queryChuaDong= "SELECT SUM(soTien) AS tong FROM thuphi WHERE trangThai = \"Chưa đóng\"";
        try {
            Statement st = dbquery.getConn().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(query);
            while(rs.next()){
                thuPhi tmp = new thuPhi(rs);
                thuPhi_list.add(tmp);
            }
            ResultSet trs;
            trs = dbquery.getSt().executeQuery(querysum);
            if(trs.next() && trs.getString("tong") != null) lbxSum.setText(trs.getString("tong"));
            else lbxSum.setText("0");
            trs = dbquery.getSt().executeQuery(queryDaDong);
            if(trs.next() && trs.getString("tong") != null) lbxDaDong.setText(trs.getString("tong"));
            else lbxDaDong.setText("0");
            trs = dbquery.getSt().executeQuery(queryChuaDong);
            if(trs.next() && trs.getString("tong") != null) lbxChuaDong.setText(trs.getString("tong"));
            else lbxChuaDong.setText("0");
        } catch (SQLException ex) {
            Logger.getLogger(ThuPhiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Đẩy danh sách lên GUI
        showList(thuPhi_list);
    }    
    
}