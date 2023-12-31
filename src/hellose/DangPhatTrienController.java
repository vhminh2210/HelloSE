package hellose;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class DangPhatTrienController extends SceneController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnHome) try {
            switchScene(event, "HomePage.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
}
