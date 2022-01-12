package settings;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.SqliteConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditPreference implements Initializable {
    @FXML
    private Label lbl_title;

    @FXML
    private TextField txt_range;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_cancel;

    Connection conn;
    PreparedStatement pr = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btn_save.setOnAction(event -> {
            String titleText = lbl_title.getText();

            if (titleText.equals("Upper Primary")){

                try {
                    editUpperPrimaryMarks();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if (titleText.equals("Lower Primary")){

                try {
                    editLowerPrimaryMarks();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {

            }

        });

        btn_cancel.setOnAction(event -> {

            ((Node) event.getSource()).getScene().getWindow().hide();

        });

    }

    public void getOldMarks(String marks, String title){
        txt_range.setText(marks);
        lbl_title.setText(title);

    }

    private void editUpperPrimaryMarks()throws SQLException{
        try {
            String level = "UpperPrimary";
            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement("UPDATE PreferencesTable SET Marks = ? WHERE Level='"+level+"'");

            pr.setString(1, txt_range.getText());

            pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pr.close();
        }

    }

    private void editLowerPrimaryMarks()throws SQLException{
        try {
            String level = "LowerPrimary";
            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement("UPDATE PreferencesTable SET Marks = ? WHERE Level='"+level+"'");

            pr.setString(1, txt_range.getText());

            pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pr.close();
        }

    }

    private void editJHSMarks()throws SQLException{
        try {
            String level = "UpperPrimary";
            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement("UPDATE PreferencesTable SET Marks = ? WHERE Level='"+level+"'");

            pr.setString(1, txt_range.getText());

            pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pr.close();
        }

    }
}
