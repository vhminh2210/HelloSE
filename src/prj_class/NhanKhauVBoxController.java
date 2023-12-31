/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package prj_class;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class NhanKhauVBoxController implements Initializable {

    private Font font = Font.font("System", FontWeight.BOLD, 12);
    private Font bigfont = Font.font("System", FontWeight.BOLD, 15);
    
    @FXML
    private Label txHoTen = new Label("HỌ VÀ TÊN");
    @FXML
    private TextFlow tfQuanHe = new TextFlow();
    @FXML
    private TextFlow tfCCCD = new TextFlow();
    @FXML
    private TextFlow tfDOB = new TextFlow();
    @FXML
    private TextFlow tfSDT = new TextFlow();
    @FXML
    private VBox vbox = new VBox();
    @FXML
    private Text txQuanHe = new Text();
    @FXML
    private Label lbQuanHe = new Label("Quan hệ với chủ hộ : ");
    @FXML
    private Text txCCCD = new Text();
    @FXML
    private Label lbCCCD = new Label("Số CCCD : ");
    @FXML
    private Text txDOB = new Text();
    @FXML
    private Label lbDOB = new Label("Ngày sinh : ");
    @FXML
    private Text txSDT = new Text();
    @FXML
    private Label lbSDT = new Label("SĐT : ");
    
    private nhanKhau nk;
    
    public VBox getVbox() {
        vbox.getChildren().clear();
        
        txHoTen.setText(nk.getHoTen());
        txHoTen.setFont(bigfont);
        
        lbQuanHe.setFont(font);
        lbCCCD.setFont(font);
        lbDOB.setFont(font);
        lbSDT.setFont(font);
        
        txQuanHe.setText(nk.getQuanHe());
        txCCCD.setText(nk.getCccd());
        txDOB.setText(nk.getNgaySinh() + "/" + nk.getThangSinh() + "/" + nk.getNamSinh());
        txSDT.setText(nk.getSDT());
        
        tfQuanHe.getChildren().addAll(lbQuanHe, txQuanHe);
        tfCCCD.getChildren().addAll(lbCCCD, txCCCD);
        tfDOB.getChildren().addAll(lbDOB, txDOB);
        tfSDT.getChildren().addAll(lbSDT, txSDT);
        
        vbox.getChildren().addAll(txHoTen, tfQuanHe, tfCCCD, tfDOB, tfSDT);
        return vbox;
        // TODO
    }

    public void setNk(nhanKhau nk) {
        this.nk = nk;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
