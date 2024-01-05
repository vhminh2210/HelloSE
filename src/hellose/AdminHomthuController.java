package hellose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import prj_class.*;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminHomthuController extends SceneController implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private TableColumn<homthu,String > colmahomthu;

    @FXML
    private TableColumn<homthu,String > colngaygopy;

    @FXML
    private TableColumn<homthu,String > colnoidung;

    @FXML
    private TableColumn<homthu,String > coluserid;

    @FXML
    private TableView<homthu> tbHomthu;

    @FXML
    private Text txLog;

    private dbQuery dbquery;
    private dbConnection dbconn;
    private ObservableList <homthu> homthu_list;

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnBack) try {
            switchScene(event, "AdminThongBao.fxml");
        } catch (IOException ex) {
            Logger.getLogger(AdminHomthuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        dbconn = new dbConnection();
        dbquery = new dbQuery(dbconn.getConn());
        homthu_list = FXCollections.observableArrayList();

        String query = "SELECT * FROM adminhomthu";
        try {
            ResultSet rs = dbquery.getSt().executeQuery(query);
            while (rs.next()) {
                homthu tmp = new homthu(rs);
                homthu_list.add(tmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminHomthuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        showList(homthu_list);
    }
    public void showList(ObservableList <homthu> list){
        if(list.size() == 0){
            txLog.setText("Không tìm thấy kết quả phù hợp!");
            return;
        }
        if(colnoidung==null) return;
        colmahomthu.setCellValueFactory(new PropertyValueFactory<homthu, String>("mahomthu"));
        colngaygopy.setCellValueFactory(new PropertyValueFactory<homthu, String>("ngayGopY"));
        coluserid.setCellValueFactory(new PropertyValueFactory<homthu, String>("userID"));
        colnoidung.setCellValueFactory(new PropertyValueFactory<homthu, String>("noidung"));
        if(list != null) tbHomthu.setItems(list);
    }
}
