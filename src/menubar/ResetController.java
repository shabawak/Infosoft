package menubar;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.SqliteConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResetController implements Initializable {
    @FXML
    private AnchorPane parent;

    @FXML
    private JFXToggleButton adminCredentials;

    @FXML
    private JFXToggleButton userCredentials;

    @FXML
    private JFXToggleButton loginLogs;

    @FXML
    private JFXToggleButton studentData;

    @FXML
    private JFXToggleButton examRecord;

    @FXML
    private JFXCheckBox checkResetAll;

    @FXML
    private JFXToggleButton parentRecord;

    @FXML
    private JFXButton btn_commite;


    Connection conn;
    PreparedStatement pr = null;
    ResultSet rs = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentData.setDisable(true);


        checkResetAll.setOnAction(event -> {

            if (checkResetAll.isSelected()) {
                adminCredentials.setSelected(true);
                adminCredentials.setDisable(true);

                loginLogs.setSelected(true);
                loginLogs.setDisable(true);

                examRecord.setSelected(true);
                examRecord.setDisable(true);

                userCredentials.setSelected(true);
                userCredentials.setDisable(true);

               /* studentData.setSelected(true);
                studentData.setDisable(true);*/

                parentRecord.setSelected(true);
                parentRecord.setDisable(true);

            } else {
                adminCredentials.setSelected(false);
                adminCredentials.setDisable(false);

                loginLogs.setSelected(false);
                loginLogs.setDisable(false);

                examRecord.setSelected(false);
                examRecord.setDisable(false);

                userCredentials.setSelected(false);
                userCredentials.setDisable(false);

                /*studentData.setSelected(false);
                studentData.setDisable(false);*/

                parentRecord.setSelected(false);
                parentRecord.setDisable(false);
            }

        });

        /*studentData.setOnAction(event -> {

            if (studentData.isSelected()) {
                examRecord.setSelected(true);
                examRecord.setDisable(true);
            } else {
                examRecord.setSelected(false);
                examRecord.setDisable(false);
            }

        });*/

        parentRecord.setOnAction(event -> {

            if (parentRecord.isSelected()){
                examRecord.setSelected(true);
                examRecord.setDisable(true);
            }else {
                examRecord.setSelected(false);
                examRecord.setDisable(false);
            }
        });


        btn_commite.setOnAction(event -> {

            try {
                if (checkResetAll.isSelected()) {

                    try {
                        clearAdminCredentials();
                        clearExaminationRecords();
                        clearLoginLogs();
//                        clearStudentData();
                        clearUserCredential();
                        clearParentData();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    checkResetAll.setSelected(false);
                }
                if (adminCredentials.isSelected()) {

                    try {
                        clearAdminCredentials();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    adminCredentials.setSelected(false);
                }
                if (userCredentials.isSelected()) {

                    try {
                        clearUserCredential();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    userCredentials.setSelected(false);
                }
                /*if (studentData.isSelected()) {
                    try {
                        clearStudentData();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    studentData.setSelected(false);
                }*/
                if (examRecord.isSelected()) {

                    try {
                        clearExaminationRecords();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    examRecord.setSelected(false);
                }
                if (parentRecord.isSelected()){
                    try {
                        clearParentData();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    parentRecord.setSelected(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        });
    }

    private void clearUserCredential() throws SQLException {

        try {
            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement("DELETE FROM UsersTable");

            pr.executeUpdate();

        } catch (SQLException e) {
            showAlert("SQLException", "Could not reset UserCredentials to default!", "" + e.getMessage() + "");
            e.printStackTrace();

        } finally {
            pr.close();
        }
    }

   /* private void clearStudentData() throws SQLException {

        try {
            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement("DELETE FROM StudentData");

            pr.executeUpdate();
            showAlert("Student Data", "Successful!", null);

        } catch (SQLException e) {
            showAlert("SQLException", "Could not reset StudentData to default!", "" + e.getMessage() + "");
            e.printStackTrace();

        } finally {
            pr.close();
        }
    }*/

    private void clearLoginLogs() throws SQLException {

        try {
            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement("DELETE FROM LogTable");

            pr.executeUpdate();
            showAlert("Logs", "Successful!", null);

        } catch (SQLException e) {
            showAlert("SQLException", "Could not reset LoginLogs to default!", "" + e.getMessage() + "");
            e.printStackTrace();

        } finally {
            pr.close();
        }
    }

    private void clearExaminationRecords() throws SQLException {
        conn = SqliteConnection.Connector();


        try {

            pr = conn.prepareStatement("DELETE FROM Record");

            pr.executeUpdate();
            showAlert("Records", "Successful!", null);

        } catch (SQLException e) {
            showAlert("SQLException", "Could not reset ExamRecords to default!", "" + e.getMessage() + "");
            e.printStackTrace();

        } finally {
            pr.close();
        }
    }

    private void clearAdminCredentials() throws SQLException {

        try {
            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement("DELETE FROM AdminTable");

            pr.executeUpdate();
            showAlert("Admin Credentials", "Successful!", null);

        } catch (SQLException e) {
            showAlert("SQLException", "Could not reset Admin Credentials to default!", "" + e.getMessage() + "");
            e.printStackTrace();

        } finally {
            pr.close();
        }
    }

    private void clearParentData() throws SQLException {

        try {

            pr = conn.prepareStatement("DELETE FROM Parent");

            pr.executeUpdate();
            showAlert("Success", "Successful!", null);

        } catch (SQLException e) {
            showAlert("SQLException", "Could not reset Parent to default!", "" + e.getMessage() + "");
            e.printStackTrace();

        } finally {
            pr.close();
        }


    }


    public void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }


}
