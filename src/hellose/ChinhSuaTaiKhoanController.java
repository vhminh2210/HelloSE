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

public class ChinhSuaTaiKhoanController extends SceneController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnChangeInfo;

    @FXML
    private Button btnHome;

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
    private TextField tbUserID;

    @FXML
    private TextField tbUserName;

    @FXML
    private TextField tbUserPermission;

    @FXML
    private TextField tbUserPhone;

    @FXML
    private Text txUserID;

    @FXML
    private Text txUserName;

    @FXML
    private Text txUserPermission;

    @FXML
    private Text txUserPhone;

    @FXML
    private Text txWarning;

    private User current_user, change_user, new_user;
    private dbQuery dbquery;
    private dbConnection conn;

    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnHome) {
            try {
                switchScene(event, "HomePage.fxml");
            } catch (IOException ex) {
                Logger.getLogger(ChinhSuaTaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(event.getSource() == btnBack) {
            try {
                switchScene(event, "TaiKhoan.fxml");
            } catch (IOException ex) {
                Logger.getLogger(ChinhSuaTaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(event.getSource() == btnChangeInfo) ChinhSuaTaiKhoanQuery();
    }

    public void loadUser(User user){
        txUserID.setText(String.valueOf(user.getUserID()));
        txUserName.setText(user.getUserName());
        txUserPhone.setText(user.getSdt());
        txUserPermission.setText(user.getQuyenHan());
    }

    public void ChinhSuaTaiKhoanQuery(){
        String newID = tbUserID.getText();
        String newName = tbUserName.getText();
        String newPhone = tbUserPhone.getText();
        String newPermission = tbUserPermission.getText();

        int x = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Xác nhận", 0);
        if(x == JOptionPane.YES_OPTION){
            String queryName = "UPDATE user SET userName = \"" + newName + "\" WHERE userID = " + String.valueOf(change_user.getUserID());
            String queryPhone = "UPDATE user SET sdt = \"" + newPhone + "\" WHERE userID = " + String.valueOf(change_user.getUserID());
            String queryPermission = "UPDATE user SET quyenHan = \"" + newPermission + "\" WHERE userID = " + String.valueOf(change_user.getUserID());
            String query2 = "SELECT * FROM user WHERE userID = " + String.valueOf(change_user.getUserID());
            
            System.out.println(queryName);
            System.out.println(queryPhone);
            System.out.println(queryPermission);
            System.out.println(query2);
            
            ResultSet rs;
            try {
                String warning = "";
                if(newID.compareTo("") != 0) warning += "Không thể sửa ID!\n";
                if(newName.compareTo("") != 0 && newName.compareTo(change_user.getUserName()) != 0) dbquery.getSt().executeUpdate(queryName);
                if(newPhone.compareTo(change_user.getSdt()) != 0) dbquery.getSt().executeUpdate(queryPhone);
                if(newPermission.compareTo("") != 0 && newPermission.compareTo(change_user.getQuyenHan()) != 0){
                    if(current_user.getQuyenHan().equals("ADMIN")) dbquery.getSt().executeUpdate(queryPermission);
                    else warning += "Bạn cần là Admin để sửa quyền hạn!\n";
                }
                rs = dbquery.getSt().executeQuery(query2);
                if(rs.next()) new_user = new User(rs);
                if(String.valueOf(new_user.getUserID()).compareTo(newID) == 0 && new_user.getUserID() != change_user.getUserID()) warning += "Sửa ID người dùng thành công!";
                if(new_user.getUserName().compareTo(newName) == 0 && new_user.getUserName().compareTo(change_user.getUserName()) != 0) warning += "Sửa Tên người dùng thành công!";
                if(new_user.getSdt().compareTo(newPhone) == 0 && new_user.getSdt().compareTo(change_user.getSdt()) != 0) warning += "Sửa SĐT người dùng thành công!";
                if(new_user.getQuyenHan().compareTo(newPermission) == 0 && new_user.getQuyenHan().compareTo(change_user.getQuyenHan()) != 0) warning += "Sửa Quyền hạn người dùng thành công!";
                if(new_user != null) change_user = new_user;
                txWarning.setText(warning);
                loadUser(change_user);
                if(change_user.getUserID() == current_user.getUserID()) hellose.HelloSE.setCurrent_user(change_user);
            } catch (SQLException ex) {
                Logger.getLogger(ChinhSuaTaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
                txWarning.setText("Sửa thông tin không thành công!");
            }
        }
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
