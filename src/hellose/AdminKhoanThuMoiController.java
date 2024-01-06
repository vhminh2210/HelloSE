/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hellose;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import prj_class.canHo;
import prj_class.dbConnection;
import prj_class.dbQuery;
import prj_class.utils;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AdminKhoanThuMoiController extends SceneController implements Initializable {

    @FXML
    private Label lbLog;
    @FXML
    private Label lbTLog;
    @FXML
    private Button btnBack;
    @FXML
    private TextField tbTieuDe;
    @FXML
    private TextArea tbbSQL;
    @FXML
    private TextField tbHeSodv;
    @FXML
    private TextField tbHeSoXeMay;
    @FXML
    private TextField tbHeSoXeHoi;
    @FXML
    private TextField tbThang;
    @FXML
    private TextField tbNam;
    @FXML
    private TextField tbMaCanHo;
    @FXML
    private TextField tbSoTien;
    @FXML
    private TextField tbGhiChu;

    private dbQuery dbquery;
    private dbConnection dbconn;
    private ArrayList<canHo> dsCanHo = new ArrayList<canHo>();
    String Thang, Nam, tieuDe;
    @FXML
    private Button btnTaoKhoanThu;
    @FXML
    private Button btnPhiDV;
    @FXML
    private Button btnPhiGuiXe;
    @FXML
    private Button btnPhiTuyChon;
    @FXML
    private Button btnSQL;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnBack) try {
            switchScene(event, "AdminThuPhi.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(event.getSource() == btnTaoKhoanThu) {
            boolean tmp = queryKhoanThuMoi();
        }
        
        if(event.getSource() == btnPhiDV){
            lbTLog.setText("Tạo biểu phí dịch vụ.");
        }
        
        if(event.getSource() == btnPhiGuiXe){
            lbTLog.setText("Tạo biểu phí gửi xe.");
        }
        
        if(event.getSource() == btnPhiTuyChon){
            lbTLog.setText("Tạo biểu phí tùy chọn.");
        }
        
        if(event.getSource() == btnSQL){
            lbTLog.setText("Tạo biểu phí bằng truy vấn SQL.");
        }
    }
    
    public void queryPhiDV(){
        tieuDe = "Phí dịch vụ " + Thang + "/" + Nam;
        tbTieuDe.setText(tieuDe);
        String base_query = "INSERT INTO thuphi (maCanHo, tenKhoanThu, Thang, Nam, soTien, trangThai, ghiChu) VALUES ";
        int heSo = Integer.parseInt(tbHeSodv.getText()) * 1000;
        System.out.println(base_query);
        for(int i=0; i<dsCanHo.size(); ++i){
            String value = utils.quoteWrap(dsCanHo.get(i).getMaCanHo()) + ", ";
            value += (utils.quoteWrap(tieuDe) + ", ");
            value += (Thang + ", ");
            value += (Nam + ", ");
            int soTien = Math.round(heSo * dsCanHo.get(i).getDienTich()); 
            value += (String.valueOf(Integer.toString(soTien)) + ", ");
            value += (utils.quoteWrap("Chưa đóng") + ", ");
            value += (utils.quoteWrap("Bắt buộc"));
            
            String query = base_query + "(" + value + ")";
            System.out.println(query);
            
            try {
                dbquery.getSt().executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(AdminKhoanThuMoiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        lbLog.setText("Tạo khoản thu mới thành công");
    }
    
    public void queryPhiGuiXe(){
        tieuDe = "Phí gửi xe " + Thang + "/" + Nam;
        tbTieuDe.setText(tieuDe);
        String base_query = "INSERT INTO thuphi (maCanHo, tenKhoanThu, Thang, Nam, soTien, trangThai, ghiChu) VALUES ";
        int heSoXeMay = Integer.parseInt(tbHeSoXeMay.getText()) * 1000;
        int heSoXeHoi = Integer.parseInt(tbHeSoXeHoi.getText()) * 1000;
        System.out.println(base_query);
        for(int i=0; i<dsCanHo.size(); ++i){
            String value = utils.quoteWrap(dsCanHo.get(i).getMaCanHo()) + ", ";
            value += (utils.quoteWrap(tieuDe) + ", ");
            value += (Thang + ", ");
            value += (Nam + ", ");
            int soTien = Math.round(heSoXeMay * dsCanHo.get(i).getSoXeMay() + heSoXeHoi * dsCanHo.get(i).getSoXeHoi()); 
            value += (String.valueOf(Integer.toString(soTien)) + ", ");
            value += (utils.quoteWrap("Chưa đóng") + ", ");
            value += (utils.quoteWrap("Bắt buộc"));
            
            String query = base_query + "(" + value + ")";
            System.out.println(query);
            
            try {
                dbquery.getSt().executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(AdminKhoanThuMoiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        lbLog.setText("Tạo khoản thu mới thành công");
    }
    
    public boolean queryCustom(){
        String base_query = "INSERT INTO thuphi (maCanHo, tenKhoanThu, Thang, Nam, soTien, trangThai, ghiChu) VALUES ";
        System.out.println(base_query);
        ArrayList<canHo> customList = new ArrayList<canHo>();
        if(tbMaCanHo.getText().length() > 0){
            String tmp_query = "SELECT * FROM canho WHERE maCanHo = " + utils.quoteWrap(tbMaCanHo.getText());
            try {
                ResultSet tmprs = dbquery.getSt().executeQuery(tmp_query);
                while(tmprs.next()){
                    canHo tmp = new canHo(tmprs);
                    customList.add(tmp);
                }
                if(customList.size() == 0){
                    lbLog.setText("Không tồn tại mã căn hộ.");
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminKhoanThuMoiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else customList = dsCanHo;
        for(int i=0; i<customList.size(); ++i){
            String value = utils.quoteWrap(customList.get(i).getMaCanHo()) + ", ";
            value += (utils.quoteWrap(tieuDe) + ", ");
            value += (Thang + ", ");
            value += (Nam + ", ");
            if(tbSoTien.getText().length() == 0){
                lbLog.setText("Hãy bổ sung số tiền cần đóng");
                return false;
            }
            int soTien = Integer.parseInt(tbSoTien.getText());
            value += (String.valueOf(Integer.toString(soTien)) + ", ");
            value += (utils.quoteWrap("Chưa đóng") + ", ");
            value += utils.quoteWrap(tbGhiChu.getText());
            
            String query = base_query + "(" + value + ")";
            System.out.println(query);
            
            try {
                dbquery.getSt().executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(AdminKhoanThuMoiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lbLog.setText("Tạo khoản thu mới thành công!");
        return true;
    }
    
    public boolean querySQL(){
        String QUERY = tbbSQL.getText();
        try {
            dbquery.getSt().executeUpdate(QUERY);
            lbLog.setText("Tạo truy vấn trên SQL thành công!");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminKhoanThuMoiController.class.getName()).log(Level.SEVERE, null, ex);
            lbLog.setText("Truy vấn trên CSDL không thành công. Hãy kiểm tra lại truy vấn.");
            return false;
        }
    }
    
    public boolean queryKhoanThuMoi(){
        Thang = tbThang.getText();
        if(Thang.length() <= 0) {
            lbLog.setText("Chưa xác định tháng thu phí");
            return false;
        }
        
        Nam = tbNam.getText();
        if(Nam.length() <= 0) {
            lbLog.setText("Chưa xác định năm thu phí");
            return false;
        }
        
        tieuDe = tbTieuDe.getText();
        if(tieuDe.length() <= 0) {
            lbLog.setText("Chưa xác định tên khoản phí");
            return false;
        }
        
        if(lbTLog.getText().contains("dịch vụ") == true) queryPhiDV();
        else if(lbTLog.getText().contains("gửi xe") == true) queryPhiGuiXe();
        else if(lbTLog.getText().contains("tùy chọn") == true) {
            boolean tmp = queryCustom();
        }
        else if(lbTLog.getText().contains("SQL") == true) {
            boolean tmp = querySQL();
        }
        return true;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbconn = new dbConnection();
        dbquery = new dbQuery(dbconn.getConn());
        
        // Nạp danh sách căn hộ
        String query = "SELECT * FROM canho";
        try {
            ResultSet rs = dbquery.getSt().executeQuery(query);
            while(rs.next()){
                canHo tmp = new canHo(rs);
                dsCanHo.add(tmp);
                System.out.println(rs.getString("maCanHo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminKhoanThuMoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
