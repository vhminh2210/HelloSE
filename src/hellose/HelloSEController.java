/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hellose;

<<<<<<< Updated upstream
import java.sql.*;
=======
// import com.mysql.jdbc.Connection;
import java.sql.Connection;
>>>>>>> Stashed changes
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import prj_class.dbConnection;
import prj_class.dbQuery;
import hellose.HelloSE;
import prj_class.User;

public class HelloSEController extends SceneController implements Initializable {

    @FXML
    private Label lbUsername;
    @FXML
    private Label lbPsswd;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegister;
    @FXML
    private Label lbStatus;
    @FXML
    private ImageView imgvBg;
    @FXML
    private TextField txbUsername;
    @FXML
    private PasswordField txbPassword;
    
    private dbQuery query;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException{
        
        if(event.getSource() == btnLogin){
            try {
//                 Kiểm tra mật khẩu 
//                switchScene(event, "HomePage.fxml");
                
                String userName = txbUsername.getText();
                String passWd = txbPassword.getText();
                
                ResultSet rs = query.findUser(userName);
                if(rs.isBeforeFirst() == false)
                {
                    lbStatus.setText("Tên người dùng không tồn tại!");
                    return;
                }
                
                while(rs.next()){
                    if(rs.getString("passWd").equals(passWd)){
                        User current_user = new User(rs);
                        hellose.HelloSE.setCurrent_user(current_user);
                        
                        lbStatus.setText("Đăng nhập thành công!");
                        switchScene(event, "HomePage.fxml");
                        return;
                    }
                }
                
                lbStatus.setText("Sai tên đăng nhập hoặc mật khẩu!");
                
            } catch (IOException ex) {
                Logger.getLogger(HelloSEController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(event.getSource() == btnRegister){
            lbStatus.setText("Tính năng đang phát triển!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dbConnection dbconn = new dbConnection();
        this.query = new dbQuery(dbconn.getConn());
    }    
}
