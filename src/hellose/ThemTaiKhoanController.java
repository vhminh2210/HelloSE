package hellose;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import prj_class.User;
import prj_class.dbConnection;
import prj_class.dbQuery;

public class ThemTaiKhoanController extends SceneController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnNewUser;

    @FXML
    private Label lbPassword;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbUserID;

    @FXML
    private Label lbUserName;

    @FXML
    private Label lbUserPermission;

    @FXML
    private Label lbUserPhone;

    @FXML
    private TextField tbPassWord;

    @FXML
    private TextField tbUserID;

    @FXML
    private TextField tbUserName;

    @FXML
    private TextField tbUserPermission;

    @FXML
    private TextField tbUserPhone;

    @FXML
    private Text txWarning;

    private dbQuery dbquery;
    private dbConnection conn;

    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnHome) {
            try {
                switchScene(event, "HomePage.fxml");
            } catch (IOException ex) {
                Logger.getLogger(ThemTaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(event.getSource() == btnBack) {
            try {
                switchScene(event, "TaiKhoan.fxml");
            } catch (IOException ex) {
                Logger.getLogger(ThemTaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(event.getSource() == btnNewUser) ThemTaiKhoanQuery();
    }

    public void ThemTaiKhoanQuery(){
        String newID = tbUserID.getText();
        String newName = tbUserName.getText();
        String newPass = tbPassWord.getText();
        String newPhone = tbUserPhone.getText();
        String newPermission = tbUserPermission.getText();
        String query;
        
        if(newID.compareTo("") != 0 && newName.compareTo("") != 0){
            int x = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Xác nhận", 0);
            if(x == JOptionPane.YES_OPTION){
                if(newPass.compareTo("") != 0 && newPermission.compareTo("") != 0) query = "INSERT INTO user VALUES("+ newID +", \""+ newName +"\", \""+ newPhone +"\", \""+ newPass +"\", \""+ newPermission +"\")";
                else if(newPass.compareTo("") != 0) query = "INSERT INTO user(`userID`, `userName`, `sdt`, `passWd`) VALUES("+ newID +", \""+ newName +"\", \""+ newPhone +"\", \""+ newPass +"\")";
                else if(newPermission.compareTo("") != 0) query = "INSERT INTO user(`userID`, `userName`, `sdt`, `quyenHan`) VALUES("+ newID +", \""+ newName +"\", \""+ newPhone +"\", \""+ newPermission +"\")";
                else query = "INSERT INTO user(`userID`, `userName`, `sdt`) VALUES("+ newID +", \""+ newName +"\", \""+ newPhone +"\")";
                
                System.out.println(query);

                try {
                    dbquery.getSt().executeUpdate(query);
                    txWarning.setText("Thêm tài khoản mới thành công!");
                } catch (SQLException ex) {
                    Logger.getLogger(ThemTaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
                    txWarning.setText("Thêm tài khoản mới không thành công!");
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = new dbConnection();
        dbquery = new dbQuery(conn.getConn());
    }

}
