package hellose;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import prj_class.User;
import prj_class.dbConnection;
import prj_class.dbQuery;

public class SuaThongBaoController extends SceneController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSend;

    @FXML
    private Label lbDate;

    @FXML
    private Label lbMaTB;

    @FXML
    private Label lbNoiDung;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbWarning;

    @FXML
    private Label lbxMaTB;

    @FXML
    private TextArea txaNoiDung;

    @FXML
    private TextField txfDate;

    private User current_user;
    private dbQuery dbquery;
    private dbConnection dbconn;
    private String maTB;

    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnBack) try {
            switchScene(event, "AdminThongBao.fxml");
        } catch (IOException ex) {
            Logger.getLogger(ThongBaoMoiController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(event.getSource() == btnSend) ThemThongBaoQuery();
    }

    public void ThemThongBaoQuery(){
        String date = txfDate.getText();
        String Noidung = txaNoiDung.getText();
        
        if(Noidung != null && Noidung.compareTo("") != 0){
            int x = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Xác nhận", 0);
            if(x == JOptionPane.YES_OPTION){
                String query = "UPDATE adminthongbao SET ngaythongbao = \""+ date +"\", noidung = \""+ Noidung +" \" WHERE mathongbao = \""+ maTB +"\"";
                System.out.println(query);

                try {
                    dbquery.getSt().executeUpdate(query);
                    lbWarning.setText("Sửa thông báo thành công!");
                } catch (SQLException ex) {
                    Logger.getLogger(SuaThongBaoController.class.getName()).log(Level.SEVERE, null, ex);
                    lbWarning.setText("Sửa thông báo không thành công!");
                }
            }
        }
    }

    public void initialize(URL url, ResourceBundle rb){
        current_user = hellose.HelloSE.getCurrent_user();
        dbconn = new dbConnection();
        dbquery = new dbQuery(dbconn.getConn());
        maTB = hellose.AdminThongBaoController.getMaTB();
        lbxMaTB.setText(maTB);
    }
}
