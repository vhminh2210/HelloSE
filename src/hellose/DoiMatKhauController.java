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

public class DoiMatKhauController extends SceneController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnChangePassword;

    @FXML
    private Button btnHome;

    @FXML
    private Label lbNewPassword1;

    @FXML
    private Label lbNewPassword2;

    @FXML
    private Label lbOldPassword;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbUserID;

    @FXML
    private Label lbUserName;

    @FXML
    private Label lbtxUserID;

    @FXML
    private Label lbtxUserName;

    @FXML
    private TextField tbNewPassword1;

    @FXML
    private TextField tbNewPassword2;

    @FXML
    private TextField tbOldPassword;

    @FXML
    private Text txWarning;

    private User current_user, change_user;
    private dbQuery dbquery;
    private dbConnection conn;

    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnHome) {
            try {
                switchScene(event, "HomePage.fxml");
            } catch (IOException ex) {
                Logger.getLogger(DoiMatKhauController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(event.getSource() == btnBack) {
            try {
                switchScene(event, "TaiKhoan.fxml");
            } catch (IOException ex) {
                Logger.getLogger(DoiMatKhauController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(event.getSource() == btnChangePassword) DoiMatKhauQuery();
    }

    public void loadUser(User user){
        lbtxUserID.setText(String.valueOf(user.getUserID()));
        lbtxUserName.setText(user.getUserName());
    }

    public void DoiMatKhauQuery(){
        String oldPass = tbOldPassword.getText();
        String newPass1 = tbNewPassword1.getText();
        String newPass2 = tbNewPassword2.getText();
        
        if(oldPass.compareTo(change_user.getPassWd()) == 0){
            if(newPass1.compareTo("")*newPass2.compareTo("") != 0){
                if(newPass1.compareTo(newPass2) == 0){
                    int x = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Xác nhận", 0);
                    if(x == JOptionPane.YES_OPTION){
                        String query1 = "UPDATE user SET passWD = " + newPass1 + " WHERE userID = " + String.valueOf(change_user.getUserID());
                        String query2 = "SELECT * FROM user WHERE userID = " + String.valueOf(change_user.getUserID());
                        
                        System.out.println(query1);
                        System.out.println(query2);
                        
                        ResultSet rs;
                        try {
                            dbquery.getSt().executeUpdate(query1);
                            rs = dbquery.getSt().executeQuery(query2);
                            if(rs.next()) change_user = new User(rs);
                            if(change_user.getPassWd().compareTo(newPass1) == 0){
                                txWarning.setText("Đổi mật khẩu thành công!");
                                if(change_user.getUserID() == current_user.getUserID()) hellose.HelloSE.setCurrent_user(change_user);
                            }else txWarning.setText("Đổi mật khẩu không thành công!");
                        } catch (SQLException ex) {
                            Logger.getLogger(DoiMatKhauController.class.getName()).log(Level.SEVERE, null, ex);
                            txWarning.setText("Đổi mật khẩu không thành công!");
                        }
                    }
                }else txWarning.setText("Mật khẩu mới chưa trùng nhau!");
            }else txWarning.setText("Mật khẩu mới không được bỏ trống!");
        }else txWarning.setText("Sai mật khẩu hiện tại!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        current_user = hellose.HelloSE.getCurrent_user();
        change_user = hellose.TaiKhoanController.getChangeUser();
        conn = new dbConnection();
        dbquery = new dbQuery(conn.getConn());

        loadUser(change_user);
    }

}

