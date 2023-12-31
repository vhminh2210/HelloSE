package hellose;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import prj_class.User;

/**
 *
 * @author admin
 */
public class HelloSE extends Application {
    
    private static User current_user;
    
    @Override
    public void start(Stage primaryStage) {
        
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        System.out.println(screenBounds);
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HelloSE.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("HelloSE!");
            primaryStage.setScene(scene);
//            primaryStage.setMaximized(true);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(HelloSE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static User getCurrent_user() {
        return current_user;
    }

    public static void setCurrent_user(User user) {
        current_user = user;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
