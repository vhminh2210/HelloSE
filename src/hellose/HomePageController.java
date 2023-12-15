/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hellose;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import prj_class.User;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class HomePageController extends SceneController implements Initializable {
    
    @FXML
    private BorderPane rootPane;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnThuPhi;
    @FXML
    private Button btnTaiKhoan;
    @FXML
    private Button btnNhanKhau;
    @FXML
    private Button btnHomThu;
    @FXML
    private Button btnQuanTri;
    @FXML
    private ImageView backgroundImage;
    
    private User current_user;
    
    @FXML
    private void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnLogout) try {
            switchScene(event, "HelloSE.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(event.getSource() == btnThuPhi) try {
            if(current_user.getQuyenHan().equals("ADMIN")) switchScene(event, "AdminThuPhi.fxml");
            else switchScene(event, "ThuPhi.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(event.getSource() == btnNhanKhau) try {
            switchScene(event, "NhanKhau.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(event.getSource() == btnTaiKhoan) try {
            switchScene(event, "DangPhatTrien.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(event.getSource() == btnHomThu) try {
            switchScene(event, "DangPhatTrien.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(event.getSource() == btnQuanTri) try {
            switchScene(event, "DangPhatTrien.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());
        current_user = hellose.HelloSE.getCurrent_user();
    }    
    
}
