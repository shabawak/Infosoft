package update;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import home.HomeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import sample.SqliteConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class parentFormController implements Initializable {

    @FXML
    private TextField textField_id;

    @FXML
    private JFXTextField father_lastName;

    @FXML
    private JFXTextField mother_lastName;

    @FXML
    private JFXTextField father_firstName;

    @FXML
    private JFXTextField mother_firstName;

    @FXML
    private JFXTextField father_phone;

    @FXML
    private JFXTextField mother_phone;

    @FXML
    private JFXTextField father_email;

    @FXML
    private JFXTextField mother_email;

    @FXML
    private JFXButton btn_add;

    @FXML
    public JFXButton btn_update;

    @FXML
    private JFXButton btn_cancel;

    private Connection conn;
    private PreparedStatement pr = null;
    private ResultSet rs = null;

    HomeController homeController = new HomeController();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btn_add.setOnAction(event -> {

            try {
                addButtonAction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        btn_update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateButtonAction();
                    ((Node) event.getSource()).getScene().getWindow().hide();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((Node) event.getSource()).getScene().getWindow().hide();
            }
        });

    }

    public void populateFields(parentData info) {

        String parentID = info.getId();

        String fatherLastName = info.getFatherLastName();
        String fatherFirstName = info.getFatherFirstName();
        String fatherPhone = info.getFatherPhone();
        String fatherEmail = info.getFatherEmail();

        String motherLastName = info.getMotherLastName();
        String motherFirstName = info.getMotherFirstName();
        String motherPhone = info.getMotherphone();
        String motherEmail = info.getMotherEmail();

        this.textField_id.setText(parentID);

        this.father_lastName.setText(fatherLastName);
        this.father_firstName.setText(fatherFirstName);
        this.father_phone.setText(fatherPhone);
        this.father_email.setText(fatherEmail);

        this.mother_lastName.setText(motherLastName);
        this.mother_firstName.setText(motherFirstName);
        this.mother_phone.setText(motherPhone);
        this.mother_email.setText(motherEmail);
    }

    private void clearFields() {

        this.textField_id.setText("");

        this.father_lastName.setText("");
        this.father_firstName.setText("");
        this.father_phone.setText("");
        this.father_email.setText("");

        this.mother_lastName.setText("");
        this.mother_firstName.setText("");
        this.mother_phone.setText("");
        this.mother_email.setText("");

    }

    private void addButtonAction() throws SQLException {

        String addQuerry = "INSERT INTO Parent(parent_id,f_lastName,f_firstName,m_lastName,m_firstName,f_phone,m_phone,f_email,m_email) VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            String f_lastName = this.father_lastName.getText().toUpperCase();
            String f_firstName = this.father_firstName.getText().toUpperCase();
            String f_phone = this.father_phone.getText();
            String f_email = this.father_email.getText();

            String m_lastName = this.mother_lastName.getText().toUpperCase();
            String m_firtName = this.mother_firstName.getText().toUpperCase();
            String m_phone = this.mother_phone.getText();
            String m_email = this.mother_email.getText();

            if (f_lastName.isEmpty() || f_firstName.isEmpty() ||  f_email.isEmpty() ||
            m_lastName.isEmpty() || m_firtName.isEmpty()  || m_email.isEmpty()) {

                homeController.showAlert("Information",null,"One or more fields required");
            }else if ((f_phone.length()>10 || f_phone.length()<10) || (m_phone.length()>10 || m_phone.length()<10)) {
                homeController.showAlert("Information",null,"Phone number must be 10 digit long");
            }else {

                try {
                    conn = SqliteConnection.Connector();
                    pr = conn.prepareStatement(addQuerry);

                    pr.setString(1, null);
                    pr.setString(2, this.father_lastName.getText().toUpperCase());
                    pr.setString(3, this.father_firstName.getText().toUpperCase());
                    pr.setString(4, this.mother_lastName.getText().toUpperCase());
                    pr.setString(5, this.mother_firstName.getText().toUpperCase());
                    pr.setString(6, this.father_phone.getText().toUpperCase());
                    pr.setString(7, this.mother_phone.getText().toUpperCase());
                    pr.setString(8, this.father_email.getText().toUpperCase());
                    pr.setString(9, this.mother_email.getText().toUpperCase());

                    pr.execute();
                    homeController.showAlert("Success", null, "Parent added successfully");
                    clearFields();
                } catch (SQLException e) {
                    homeController.showAlert("Error", null, e.getLocalizedMessage());
                    e.printStackTrace();
                } finally {
                    pr.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void updateButtonAction() throws SQLException {
        String parent_id = this.textField_id.getText();
        String updateQuerry = "UPDATE Parent SET f_lastName=?,f_firstName=?,m_lastName=?,m_firstName=?,f_phone=?,m_phone=?,f_email=?,m_email=? WHERE parent_id=" + parent_id + " ";

        try {
            String f_lastName = this.father_lastName.getText().toUpperCase();
            String f_firstName = this.father_firstName.getText().toUpperCase();
            String f_phone = this.father_phone.getText();
            String f_email = this.father_email.getText();

            String m_lastName = this.mother_lastName.getText().toUpperCase();
            String m_firtName = this.mother_firstName.getText().toUpperCase();
            String m_phone = this.mother_phone.getText();
            String m_email = this.mother_email.getText();

            if (f_lastName.isEmpty() || f_firstName.isEmpty() ||  f_email.isEmpty() ||
                    m_lastName.isEmpty() || m_firtName.isEmpty()  || m_email.isEmpty()) {

                homeController.showAlert("Information",null,"One or more fields required");
            }else if ((f_phone.length()>10 || f_phone.length()<10) || (m_phone.length()>10 || m_phone.length()<10)) {
                homeController.showAlert("Information",null,"Phone number must be 10 digit long");
            }else {

                try {



                    conn = SqliteConnection.Connector();
                    pr = conn.prepareStatement(updateQuerry);

                    pr.setString(1, this.father_lastName.getText().toUpperCase());
                    pr.setString(2, this.father_firstName.getText().toUpperCase());
                    pr.setString(3, this.mother_lastName.getText().toUpperCase());
                    pr.setString(4, this.mother_firstName.getText().toUpperCase());
                    pr.setString(5, this.father_phone.getText().toUpperCase());
                    pr.setString(6, this.mother_phone.getText().toUpperCase());
                    pr.setString(7, this.father_email.getText().toUpperCase());
                    pr.setString(8, this.mother_email.getText().toUpperCase());

                    pr.executeUpdate();
                    homeController.showAlert("Success", null, "Data update successful");
                } catch (SQLException e) {
                    homeController.showAlert("Error", null, e.getLocalizedMessage());
                    e.printStackTrace();
                } finally {
                    pr.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
