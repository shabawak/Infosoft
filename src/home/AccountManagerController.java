package home;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import sample.LoginModel;
import sample.SqliteConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountManagerController implements Initializable {

    @FXML
    private AnchorPane parent;


    @FXML
    private Pane pane;

    @FXML
    private JFXPasswordField txt_oldPass;

    @FXML
    private JFXPasswordField txt_newPass;

    @FXML
    private JFXPasswordField txt_confirmPass;

    @FXML
    private JFXButton btn_verify;

    @FXML
    private Label lbl_oldPStatus;

    @FXML
    private Label lbl_nPassStatus;

    @FXML
    private JFXButton btn_save;

    @FXML
    private JFXRadioButton radio;

    @FXML
    private JFXButton btn_dismis;


    public LoginModel loginModel = new LoginModel();

    PreparedStatement pr = null;
    ResultSet rs = null;
    Connection conn;
    String personName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        btn_verify.setOnAction(event -> {

            try {
                if (radio.isSelected() & loginModel.administrator(txt_oldPass.getText())) {
                    lbl_oldPStatus.setText("Verified");
                    lbl_oldPStatus.setTextFill(Paint.valueOf("#00E676"));
                    txt_newPass.setDisable(false);
                    txt_confirmPass.setDisable(false);
                    btn_save.setDisable(false);
                    btn_verify.setDisable(true);
                    radio.setDisable(true);

                } else if (!radio.isSelected() & loginModel.checkCredentials(txt_oldPass.getText())) {


                    lbl_oldPStatus.setText("Verified");
                    lbl_oldPStatus.setTextFill(Paint.valueOf("#00E676"));
                    txt_newPass.setDisable(false);
                    txt_confirmPass.setDisable(false);
                    btn_save.setDisable(false);
                    btn_verify.setDisable(true);
                    radio.setDisable(true);

                }else {
                    lbl_oldPStatus.setText("Wrong Password!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        btn_save.setOnAction(event -> {

            try {
                changPassword(personName);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        btn_dismis.setOnAction(event -> {
            ((Node) event.getSource()).getScene().getWindow().hide();

        });

    }


    public void changPassword(String name) throws SQLException {

        String newPass = txt_newPass.getText();
        String confirmPass = txt_confirmPass.getText();

        if (newPass.isEmpty() || confirmPass.isEmpty()) {
            lbl_nPassStatus.setText("New Password required");
        } else if (!confirmPass.equals(newPass)) {
            lbl_nPassStatus.setText("Password does not match!");
        } else if (radio.isSelected()) {
            try {
                String mquerry = "UPDATE AdminTable SET password=? WHERE fullName='" + name + "'";
                conn = SqliteConnection.Connector();

                pr = conn.prepareStatement(mquerry);
                pr.setString(1, newPass);

                pr.executeUpdate();

                showAlert("Success!", null, "Password change successful");
                txt_oldPass.setDisable(true);
                txt_newPass.setDisable(true);
                txt_confirmPass.setDisable(true);
                lbl_oldPStatus.setVisible(false);
                lbl_nPassStatus.setVisible(false);
                btn_dismis.setVisible(true);
                btn_dismis.setDisable(false);
                btn_save.setDisable(true);
                btn_save.setVisible(false);
                btn_verify.setDisable(true);


            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                pr.close();


            }


        } else {
            try {
                String sql = "UPDATE UsersTable SET password=?  WHERE fullName = '" + name + "'";
                conn = SqliteConnection.Connector();


                pr = conn.prepareStatement(sql);
                pr.setString(1, newPass);

                pr.executeUpdate();

                showAlert("Success!", null, "Password change successful");
                txt_oldPass.setDisable(true);
                txt_newPass.setDisable(true);
                txt_confirmPass.setDisable(true);
                lbl_oldPStatus.setVisible(false);
                lbl_nPassStatus.setVisible(false);
                btn_dismis.setDisable(false);
                btn_dismis.setVisible(true);
                btn_save.setDisable(true);
                btn_save.setVisible(false);
                btn_verify.setDisable(true);

            } catch (SQLException e) {
                showAlert("Error!", "SQLException", "" + e.getLocalizedMessage() + "");
                e.printStackTrace();
            } finally {
                pr.close();
            }

        }
    }

    public void getName(String userName) {
        this.personName = userName;

    }

    public void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

}
