package hellose;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import prj_class.NhanKhauVBoxController;
import prj_class.User;
import prj_class.dbConnection;
import prj_class.dbQuery;
import prj_class.nhanKhau;
import prj_class.utils;

public class TaiKhoanController extends SceneController implements Initializable {

    @FXML
    private Button btnDeleteUser;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnNewUser;

    @FXML
    private Button btnOtherChangeInfo;

    @FXML
    private Button btnOtherChangePassword;

    @FXML
    private Button btnTimKiem;

    @FXML
    private Button btnUserChangeInfo;

    @FXML
    private Button btnUserChangePassword;

    @FXML
    private Button btnUserSignOut;

    @FXML
    private Label lbOther;

    @FXML
    private Label lbOtherID;

    @FXML
    private Label lbOtherName;

    @FXML
    private Label lbOtherPermission;

    @FXML
    private Label lbOtherPhone;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbUser;

    @FXML
    private Label lbUserID;

    @FXML
    private Label lbUserName;

    @FXML
    private Label lbUserPermission;

    @FXML
    private Label lbUserPhone;

    @FXML
    private Label lbtxUserID;

    @FXML
    private Label lbtxUserName;

    @FXML
    private Label lbtxUserPermission;

    @FXML
    private Label lbtxUserPhone;

    @FXML
    private TextField tbOtherID;

    @FXML
    private Text txWarning;

    @FXML
    private Text txOtherName;

    @FXML
    private Text txOtherPermission;

    @FXML
    private Text txOtherPhone;

    private User current_user, query_user;
    private static User change_user;
    private dbQuery dbquery;
    private dbConnection conn;

    @FXML
    void handleButtonAction(ActionEvent event) {
        change_user = null;
        // clearQuery();

        if(event.getSource() == btnHome) {
            try {
                switchScene(event, "HomePage.fxml");
            } catch (IOException ex) {
                Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else if(event.getSource() == btnUserChangeInfo) {
            try {
                change_user = current_user;
                switchScene(event, "ChinhSuaTaiKhoan.fxml");
                change_user = null;
            } catch (IOException ex) {
                Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else if(event.getSource() == btnUserChangePassword) {
            try {
                change_user = current_user;
                switchScene(event, "DoiMatKhau.fxml");
                change_user = null;
            } catch (IOException ex) {
                Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else if(event.getSource() == btnUserSignOut) {
            try {
                current_user = null;
                hellose.HelloSE.setCurrent_user(null);
                switchScene(event, "HelloSE.fxml");
            } catch (IOException ex) {
                Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else if(current_user != null && current_user.getQuyenHan().equals("ADMIN")){
            if(event.getSource() == btnNewUser) {
                try {
                    switchScene(event, "ThemTaiKhoan.fxml");
                } catch (IOException ex) {
                    Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            else if(event.getSource() == btnTimKiem) TaiKhoanQuery();

            else if(event.getSource() == btnOtherChangeInfo) {
                try {
                    change_user = query_user;
                    if(change_user != null) switchScene(event, "ChinhSuaTaiKhoan.fxml");
                    else txWarning.setText("Người dùng không hợp lệ!");
                    change_user = null;
                } catch (IOException ex) {
                    Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            else if(event.getSource() == btnOtherChangePassword) {
                try {
                    change_user = query_user;
                    if(change_user != null) switchScene(event, "DoiMatKhau.fxml");
                    else txWarning.setText("Người dùng không hợp lệ!");
                    change_user = null;
                } catch (IOException ex) {
                    Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            else if(event.getSource() == btnDeleteUser) {
                change_user = query_user;
                if(change_user != null){
                    String password = JOptionPane.showInputDialog(null, "Nhập mật khẩu");
                    if(password != null && password.compareTo(change_user.getPassWd()) == 0){
                        int x = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Xác nhận", 0);
                        if(x == JOptionPane.YES_OPTION){
                            String query = "DELETE FROM user WHERE userID = " + String.valueOf(change_user.getUserID());

                            System.out.println(query);

                            try {
                                dbquery.getSt().executeUpdate(query);
                                txWarning.setText("Xoá tài khoản thành công!");
                                query_user = null;
                                if(change_user.getUserID() == current_user.getUserID()){
                                    try {
                                        current_user = null;
                                        hellose.HelloSE.setCurrent_user(null);
                                        switchScene(event, "HelloSE.fxml");
                                    } catch (IOException ex) {
                                        Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
                                txWarning.setText("Xoá tài khoản không thành công!");
                            }
                        }
                    }else JOptionPane.showMessageDialog(null, "Sai mật khẩu");
                }else txWarning.setText("Người dùng không hợp lệ!");
                change_user = null;
                // query_user = null;
            }
        }else txWarning.setText("Chức năng chỉ dành cho Admin!");
    }

    public void loadUser(User user){
        lbtxUserID.setText(String.valueOf(user.getUserID()));
        lbtxUserName.setText(user.getUserName());
        lbtxUserPhone.setText(user.getSdt());
        lbtxUserPermission.setText(user.getQuyenHan());
    }

    public void loadOther(User other){
        txOtherName.setText(other.getUserName());
        txOtherPhone.setText(other.getSdt());
        txOtherPermission.setText(other.getQuyenHan());
    }

    public void clearQuery(){
        txWarning.setText("");
        txOtherName.setText("");
        txOtherPhone.setText("");
        txOtherPermission.setText("");
    }

    public void TaiKhoanQuery(){
        clearQuery();

        String OtherID = tbOtherID.getText();
        
        String query = "SELECT * FROM user WHERE userID = " + OtherID;
        
        System.out.println(query);
        
        ResultSet rs;
        User Other = null;
        try {
            rs = dbquery.getSt().executeQuery(query);
            if(rs.next()) Other = new User(rs);
            if(Other != null){
                loadOther(Other);
                query_user = Other;
            }
            else txWarning.setText("Không tìm thấy kết quả phù hợp!");
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static User getChangeUser(){
        return change_user;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        current_user = hellose.HelloSE.getCurrent_user();
        conn = new dbConnection();
        dbquery = new dbQuery(conn.getConn());

        loadUser(current_user);
    }

}