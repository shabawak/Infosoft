package dashboard;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import sample.SqliteConnection;
import studentInfo.PrintReport;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardTableController implements Initializable {
    @FXML
    private AnchorPane parent;

    @FXML
    private Label label;

    @FXML
    private TableView<dashboard_Data> table;

    @FXML
    private TableColumn<dashboard_Data, String> column_id;

    @FXML
    private TableColumn<dashboard_Data, String> column_lastName;

    @FXML
    private TableColumn<dashboard_Data, String> column_firstName;

    @FXML
    private TableColumn<dashboard_Data, String> column_gender;

    @FXML
    private TableColumn<dashboard_Data, String> column_class;

    @FXML
    private JFXButton btn_printTotal;

    @FXML
    private JFXButton btn_printActive;

    @FXML
    private JFXButton btn_printInactive;

    @FXML
    private JFXButton btn_printMale;

    @FXML
    private JFXButton btn_printFemale;

    ObservableList<dashboard_Data> result = FXCollections.observableArrayList();

    Connection conn;
    PreparedStatement pr = null;
    ResultSet rs = null;



    public PrintReport printReport = new PrintReport();


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        btn_printActive.setOnAction(event -> {

            try {
                printReport.printActiveStudents();
            } catch (JRException e) {
                e.printStackTrace();
            }

        });


        btn_printInactive.setOnAction(event -> {

            try {
                printReport.printInactiveStudents();
            } catch (JRException e) {
                e.printStackTrace();
            }

        });


        btn_printMale.setOnAction(event -> {

            try {
                printReport.printAllMale();
            } catch (JRException e) {
                e.printStackTrace();
            }

        });


        btn_printFemale.setOnAction(event -> {

            try {
                printReport.printAllFeMale();
            } catch (JRException e) {
                e.printStackTrace();
            }

        });


    }

    public void displayMaleStudents() throws SQLException {
        String gender = "MALE";
        String status = "ACTIVE";
        String mQuerry = "Select Student_id,lastName,firstName,gender,class from StudentData where gender= '" + gender + "' and status = '" + status + "' And class!='COMPLETED' Order by class";
        try {
            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement(mQuerry);
            rs = pr.executeQuery();

            while (rs.next()) {
                this.result.add(new dashboard_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            this.column_id.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("Student_id"));
            this.column_lastName.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SLastName"));
            this.column_firstName.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SFirstName"));
            this.column_gender.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("Gender"));
            this.column_class.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SClass"));

            this.table.setItems(null);
            this.table.setItems(result);


        } catch (SQLException e) {
            showAlert("Erro!", "SQLException", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {

            pr.close();
            rs.close();
        }

    }

    public void displayFemaleStudents() throws SQLException {
        String gender = "FEMALE";
        String status = "ACTIVE";
        String mQuerry = "Select Student_id,lastName,firstName,gender,class from StudentData where gender= '" + gender + "' and status = '" + status + "' And class!= 'COMPLETED' Order by class";
        try {
            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement(mQuerry);
            rs = pr.executeQuery();

            while (rs.next()) {
                this.result.add(new dashboard_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            this.column_id.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("Student_id"));
            this.column_lastName.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SLastName"));
            this.column_firstName.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SFirstName"));
            this.column_gender.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("Gender"));
            this.column_class.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SClass"));

            this.table.setItems(null);
            this.table.setItems(result);


        } catch (SQLException e) {
            showAlert("Erro!", "SQLException", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {

            pr.close();
            rs.close();
        }

    }

    public void displayActiveStudents() throws SQLException {


        String mQuerry = "Select Student_id,lastName,firstName,gender,class from StudentData where  status = 'ACTIVE' And class!= 'COMPLETED' Order by class";
        try {
            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement(mQuerry);
            rs = pr.executeQuery();

            while (rs.next()) {
                this.result.add(new dashboard_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            this.column_id.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("Student_id"));
            this.column_lastName.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SLastName"));
            this.column_firstName.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SFirstName"));
            this.column_gender.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("Gender"));
            this.column_class.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SClass"));

            this.table.setItems(null);
            this.table.setItems(result);


        } catch (SQLException e) {
            showAlert("Erro!", "SQLException", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {

            pr.close();
            rs.close();
        }

    }

    public void displayInactiveStudents() throws SQLException {


        String mQuerry = "Select Student_id,lastName,firstName,gender,class from StudentData where status = 'INACTIVE' And class!='COMPLETED' Order by class";
        try {
            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement(mQuerry);
            rs = pr.executeQuery();

            while (rs.next()) {
                this.result.add(new dashboard_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            this.column_id.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("Student_id"));
            this.column_lastName.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SLastName"));
            this.column_firstName.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SFirstName"));
            this.column_gender.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("Gender"));
            this.column_class.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SClass"));

            this.table.setItems(null);
            this.table.setItems(result);


        } catch (SQLException e) {
            showAlert("Erro!", "SQLException", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {

            pr.close();
            rs.close();
        }

    }

    public void displayTotalStudents() throws SQLException {
        String classCondition = "COMPLETED";

        String mQuerry = "Select Student_id,lastName,firstName,gender,class from StudentData WHERE class != 'COMPLETED' Order by class";
        try {
            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement(mQuerry);
            rs = pr.executeQuery();

            while (rs.next()) {
                this.result.add(new dashboard_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            this.column_id.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("Student_id"));
            this.column_lastName.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SLastName"));
            this.column_firstName.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SFirstName"));
            this.column_gender.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("Gender"));
            this.column_class.setCellValueFactory(new PropertyValueFactory<dashboard_Data, String>("SClass"));

            this.table.setItems(null);
            this.table.setItems(result);


        } catch (SQLException e) {
            showAlert("Erro!", "SQLException", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {

            pr.close();
            rs.close();
        }

    }



    public void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void enablePrintTotalButton() {
        btn_printActive.setVisible(false);
        btn_printActive.setDisable(true);

        btn_printFemale.setVisible(false);
        btn_printFemale.setDisable(true);

        btn_printInactive.setVisible(false);
        btn_printInactive.setDisable(true);

        btn_printMale.setVisible(false);
        btn_printMale.setDisable(true);
    }

    public void enablePrintActiveButton() {
        btn_printTotal.setVisible(false);
        btn_printTotal.setDisable(true);

        btn_printFemale.setVisible(false);
        btn_printFemale.setDisable(true);

        btn_printInactive.setVisible(false);
        btn_printInactive.setDisable(true);

        btn_printMale.setVisible(false);
        btn_printMale.setDisable(true);
    }

    public void enablePrintInactiveButton() {
        btn_printActive.setVisible(false);
        btn_printActive.setDisable(true);

        btn_printFemale.setVisible(false);
        btn_printFemale.setDisable(true);

        btn_printTotal.setVisible(false);
        btn_printTotal.setDisable(true);

        btn_printMale.setVisible(false);
        btn_printMale.setDisable(true);
    }

    public void enablePrintMaleButton() {
        btn_printActive.setVisible(false);
        btn_printActive.setDisable(true);

        btn_printFemale.setVisible(false);
        btn_printFemale.setDisable(true);

        btn_printInactive.setVisible(false);
        btn_printInactive.setDisable(true);

        btn_printTotal.setVisible(false);
        btn_printTotal.setDisable(true);
    }

    public void enablePrintFemalButton() {
        btn_printActive.setVisible(false);
        btn_printActive.setDisable(true);

        btn_printTotal.setVisible(false);
        btn_printTotal.setDisable(true);

        btn_printInactive.setVisible(false);
        btn_printInactive.setDisable(true);

        btn_printMale.setVisible(false);
        btn_printMale.setDisable(true);
    }

    public void setLabelText(String text){
        label.setText(text);
    }
}
