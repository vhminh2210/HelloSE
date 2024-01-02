/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package hellose;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class SceneController{
    private Stage stage;
    private Scene scene;
    private Parent root;
    static HashMap<String,Boolean> resizable = new HashMap<>();
    static boolean filled = false;
    
    public void switchScene(ActionEvent event, String fxml) throws IOException{
        if(filled == false){
            resizable.put("HomePage.fxml",true);
            resizable.put("HelloSE.fxml",true);
            resizable.put("NhanKhau.fxml",false);
            resizable.put("ThuPhi.fxml",false);
            resizable.put("AdminNhanKhau.fxml",false);
            resizable.put("AdminNhanKhauMoi.fxml",false);
            resizable.put("AdminXoaNhanKhau.fxml",false);
            resizable.put("DangPhatTrien.fxml",false);
            resizable.put("AdminThuPhi.fxml",false);
            resizable.put("AdminKhoanThuMoi.fxml",false);
            resizable.put("TaiKhoan.fxml",false);
            resizable.put("DoiMatKhau.fxml",false);
            resizable.put("ChinhSuaTaiKhoan.fxml",false);
            resizable.put("ThemTaiKhoan.fxml",false);
            SceneController.filled = true;
        }
        boolean res = resizable.get(fxml);

        root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(res);
        stage.show();
    }
    
    public void switchScene(KeyEvent event, String fxml) throws IOException{
        if(filled == false){
            resizable.put("HomePage.fxml",true);
            resizable.put("HelloSE.fxml",true);
            resizable.put("NhanKhau.fxml",false);
            resizable.put("ThuPhi.fxml",false);
            resizable.put("AdminNhanKhau.fxml",false);
            resizable.put("AdminNhanKhauMoi.fxml",false);
            resizable.put("AdminXoaNhanKhau.fxml",false);
            resizable.put("DangPhatTrien.fxml",false);
            resizable.put("AdminThuPhi.fxml",false);
            resizable.put("AdminKhoanThuMoi.fxml",false);
            resizable.put("TaiKhoan.fxml",false);
            resizable.put("DoiMatKhau.fxml",false);
            resizable.put("ChinhSuaTaiKhoan.fxml",false);
            resizable.put("ThemTaiKhoan.fxml",false);
            SceneController.filled = true;
        }
        boolean res = resizable.get(fxml);

        root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(res);
        stage.show();
    }
    
}
