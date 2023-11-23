package hellose;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DangPhatTrienController extends SceneController {

    @FXML
    private Button btnHome;

    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnHome) try {
            switchScene(event, "HomePage.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
