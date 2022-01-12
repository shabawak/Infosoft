package settings;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.SqliteConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsController implements Initializable {

    @FXML
    private Label lbl_editJhs;

    @FXML
    private Label lbl_jhs;

    @FXML
    private Label lbl_editUprimary;

    @FXML
    private Label lbl_uPrimary;

    @FXML
    private MaterialIconView lbl_editLprimary;

    @FXML
    private Label lbl_Lprimary;

    @FXML
    private JFXButton btn_okey;

    @FXML
    private JFXButton btn_cancel;

    @FXML
    private JFXButton btn_apply;

    @FXML
    private Label lbl_log;


    Connection conn;
    ResultSet rs = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            retreiveUpMarks();
            retreiveLpMarks();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lbl_editUprimary.setOnMouseClicked(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/settings/editPreference.fxml"));
            try{
                loader.load();
            }catch (IOException ex){
                Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE,null,ex);
            }

            EditPreference editPreference = loader.getController();
            editPreference.getOldMarks(lbl_uPrimary.getText(), "Upper Primary");

            Parent parent = loader.getRoot();
            Stage preference = new Stage();
            preference.initStyle(StageStyle.UTILITY);
            preference.setTitle("Preferences");
            preference.initModality(Modality.APPLICATION_MODAL);
            preference.setScene(new Scene(parent));
            Image image = new Image("file:app_icon.png");
            preference.getIcons().add(image);

            preference.show();

        });

        lbl_editLprimary.setOnMouseClicked(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/settings/editPreference.fxml"));
            try{
                loader.load();
            }catch (IOException ex){
                Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE,null,ex);
            }

            EditPreference editPreference = loader.getController();
            editPreference.getOldMarks(lbl_Lprimary.getText(), "Lower Primary");

            Parent parent = loader.getRoot();
            Stage preference = new Stage();
            preference.initStyle(StageStyle.UTILITY);
            preference.setTitle("Preferences");
            preference.initModality(Modality.APPLICATION_MODAL);
            preference.setScene(new Scene(parent));
            Image image = new Image("file:app_icon.png");
            preference.getIcons().add(image);

            preference.show();
        });

        btn_cancel.setOnAction(event -> {

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        });

    }

    public void retreiveUpMarks()throws SQLException{
        String UPLevel = "UpperPrimary";

        try {
            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery("SELECT Marks FROM PreferencesTable WHERE Level = '"+UPLevel+"'");
            if (rs.next()){
                lbl_uPrimary.setText(rs.getString("Marks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
        }
    }

    public void retreiveLpMarks()throws SQLException{
        String LPLevel = "LowerPrimary";

        try {
            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery("SELECT Marks FROM PreferencesTable WHERE Level = '"+LPLevel+"'");
            if (rs.next()){
                lbl_Lprimary.setText(rs.getString("Marks"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
        }
    }

    private void retreiveJhsMarks(){
        String UPLevel = "UpperPrimary";

        try {
            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery("SELECT Marks FROM PreferencesTable WHERE Level = '"+UPLevel+"'");
            if (rs.next()){
                lbl_editUprimary.setText(rs.getString("Marks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
