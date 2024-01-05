package hellose;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import prj_class.*;

public class AdminThongBaoController extends SceneController implements Initializable {

    @FXML
    private Button btnHome;

    @FXML
    private TableView<thongbao> tbthongbao;

    @FXML
    private TableColumn<thongbao, String> colmathongbao;

    @FXML
    private TableColumn<thongbao, String> colngaythongbao;

    @FXML
    private TableColumn<thongbao, String> colnoidung;

    @FXML
    private Button homthu;

    @FXML
    private Button suathongbao;

    @FXML
    private Button taothongbao;

    @FXML
    private Text txLog;

    @FXML
    private Button xoathongbao;

    @FXML
    private Label lbWarning;

    private dbQuery dbquery;
    private dbConnection dbconn;
    private ObservableList <thongbao> adminthongbao_list;

    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnHome) try {
            switchScene(event, "HomePage.fxml");
        } catch (IOException ex) {
            Logger.getLogger(AdminThongBaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(event.getSource() == xoathongbao)  {
            delthongbao();
        }
        if(event.getSource() == homthu) try {
            switchScene(event, "AdminHomThu.fxml");
        } catch (IOException ex) {
            Logger.getLogger(AdminThongBaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(current_user.getQuyenHan().equals("ADMIN")){
            if(event.getSource() == taothongbao){

            }
            if(event.getSource() == suathongbao)
        }else lbWarning.setText("Chức năng chỉ dành cho Admin!");
    }

    public void initialize(URL url, ResourceBundle rb) {
        dbconn = new dbConnection();
        dbquery = new dbQuery(dbconn.getConn());
        adminthongbao_list = FXCollections.observableArrayList();

        String query = "SELECT * FROM adminthongbao";

        System.out.println(query);

        try {
            ResultSet rs = dbquery.getSt().executeQuery(query);
            while (rs.next()) {
                thongbao tmp = new thongbao(rs);
                adminthongbao_list.add(tmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminThongBaoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        showList(adminthongbao_list);
    }
    public void showList(ObservableList <thongbao> list){
        if(list.size() == 0){
            txLog.setText("Không tìm thấy kết quả phù hợp!");
            return;
        }
        if (colmathongbao == null) return;
        colmathongbao.setCellValueFactory(new PropertyValueFactory<thongbao, String>("mathongbao"));
        colngaythongbao.setCellValueFactory(new PropertyValueFactory<thongbao, String>("ngaythongbao"));
        colnoidung.setCellValueFactory(new PropertyValueFactory<thongbao, String>("noidung"));
        if(list != null) tbthongbao.setItems(list);
    }
    public void delthongbao(){
        String query = "DELETE * FROM adminthongbao";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xóa thông báo");
        alert.setHeaderText("Bạn có chắc muốn xóa thông báo?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == null) {
            txLog.setText("Chưa đưa ra lựa chọn!");
        } else if (option.get() == ButtonType.OK) {
            try {
                dbquery.getSt().executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(AdminThongBaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            txLog.setText("Đã xóa các thông báo!");
        }
        System.out.println(query);
    }
    public void homthu(){

    }
}