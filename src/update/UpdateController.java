package update;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.SqliteConnection;
import settings.SettingsController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateController implements Initializable {

    @FXML
    private TableView<StudentsData> Table_View;

    @FXML
    private TableColumn<StudentsData, String> Column_ID;

    @FXML
    private TableColumn<StudentsData, String> Column_LastName;

    @FXML
    private TableColumn<StudentsData, String> Column_FirstName;

    @FXML
    private TableColumn<StudentsData, String> Column_Gender;

    @FXML
    private TableColumn<StudentsData, String> Column_DOB;

    @FXML
    private TableColumn<StudentsData, String> Column_Religion;

    @FXML
    private TableColumn<StudentsData, String> Column_Residence;

    @FXML
    private TableColumn<StudentsData, String> Column_LandMark;

    @FXML
    private TableColumn<StudentsData, String> Column_class;

    @FXML
    private TableColumn<StudentsData, String> Column_DOA;

    @FXML
    private TableColumn<StudentsData, String> Column_LastSchool;

    @FXML
    private TableColumn<StudentsData, byte[]> Column_image;

    @FXML
    private TableColumn<StudentsData, String> Column_STATUS;

    @FXML
    private TableColumn<StudentsData, String> Column_currYear;

    @FXML
    private TableColumn<StudentsData, String > Column_prevY;

    @FXML
    private JFXButton btn_addStudent;

    @FXML
    private JFXButton btn_UpdateStudent;

    @FXML
    private JFXButton btn_deleteStudent;

    @FXML
    private JFXTextField searchField;

    @FXML
    private JFXButton btn_RefreshTable;

    @FXML
    private Button btn_promoteToNextClass;


    StudentsData person;


    private Connection conn;
    private ResultSet rs = null;
    private PreparedStatement pr = null;
    private ObservableList<StudentsData> students = FXCollections.observableArrayList();

    public SettingsController settingsController = new SettingsController();




    private double xOffset = 0;
    private double yOffset = 0;

    String upMarks;
    String lpMarks;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            LoadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btn_deleteStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    deleteData(Table_View.getSelectionModel().getSelectedItem());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn_RefreshTable.setOnAction(event -> {

            try {
                LoadTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


        FilteredList<StudentsData> filteredData = new FilteredList<>(students, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super StudentsData>) studentInfoData -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (studentInfoData.getID().contains(newValue)) {
                        return true;
                    } else if (studentInfoData.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (studentInfoData.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<StudentsData> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(Table_View.comparatorProperty());
            Table_View.setItems(sortedData);

        });





    }

    private void deleteData(StudentsData studentsData) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Confirm Delete operation.");
        Optional<ButtonType> bp = alert.showAndWait();
        if (bp.get() == ButtonType.OK) {
            String id = studentsData.getID();
            String deleteQuerry = "DELETE FROM StudentData WHERE Student_id = "+id +"";

            try {
                 conn = SqliteConnection.Connector();
                pr = conn.prepareStatement(deleteQuerry);
                pr.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Successful", null, "Student has been deleted!");
            } catch (SQLException e) {
                showAlert(Alert.AlertType.INFORMATION, "Error!", null, "Could not delete student!");
                e.printStackTrace();
            } finally {
                pr.close();
//                conn.close();
            }
        } else {
            alert.close();
        }


    }







   /* @FXML
    private void newRecordForm(ActionEvent event) {
        try {
//            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage formstage = new Stage();
            FXMLLoader Loader = new FXMLLoader();
            Pane root = Loader.load(getClass().getResource("/update/form.fxml").openStream());

            DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat year = new SimpleDateFormat("yyyy");
            Date d = new Date();

            FormController formController = Loader.getController();
            formController.disableUpdateRecordButton();
            formController.getTime(""+dateFormatter.format(d)+"");
            formController.getYear(""+year.format(d)+"");



            formstage.setTitle("Student Form");

            Image image = new Image("file:app_icon.png");
            formstage.getIcons().add(image);

            formstage.setScene(new Scene(root));
            formstage.setResizable(false);
            formstage.initStyle(StageStyle.UTILITY);

            formstage.initModality(Modality.APPLICATION_MODAL);
            formstage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    @FXML
    private void UpdateRecordForm(ActionEvent event) {

        try {
//            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage formstage = new Stage();
            FXMLLoader Loader = new FXMLLoader();
            Pane root = Loader.load(getClass().getResource("/update/form.fxml").openStream());


            FormController formController = Loader.getController();
            formController.populateForm(Table_View.getSelectionModel().getSelectedItem());

            formController.disableNewRecordButton();


            formstage.initModality(Modality.APPLICATION_MODAL);

            Image image = new Image("file:app_icon.png");
            formstage.getIcons().add(image);
            formstage.initStyle(StageStyle.UTILITY);
            formstage.setTitle("Form");

            formstage.setResizable(false);
            formstage.setScene(new Scene(root));
            formstage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void tabeleRowSelect(MouseEvent event) {

        TableColumn<String, String> column1 = new TableColumn<>();
//        Table_View.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        btn_UpdateStudent.disableProperty().bind(Bindings.isEmpty(Table_View.getSelectionModel().getSelectedItems()));
        btn_deleteStudent.disableProperty().bind(Bindings.isEmpty(Table_View.getSelectionModel().getSelectedItems()));

    }

    public void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {


        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private void LoadTable()throws SQLException{
        String condition = "COMPLETED";

        try {
            conn = SqliteConnection.Connector();
//            this.students = FXCollections.observableArrayList();

            rs = conn.createStatement().executeQuery("SELECT * FROM StudentData WHERE class!= '"+condition+"'");

            while (rs.next()){
                this.students.add(new StudentsData(rs.getString("Student_id"),rs.getString("lastName"),
                        rs.getString("firstName"),rs.getString("gender"),rs.getString("dob"),
                        rs.getString("religion"),rs.getString("residence"),rs.getString("landmark"),
                        rs.getString("class"),rs.getString("admission_date"),rs.getString("lastSchool_attended"),
                        rs.getBytes("image"),rs.getString("status"),rs.getString("currentYear"),rs.getString("preYear")));
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.INFORMATION,"Error!","Could not refresh Table",""+e.getMessage()+"");
            e.printStackTrace();
        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<StudentsData,String>("ID"));
        this.Column_LastName.setCellValueFactory(new PropertyValueFactory<StudentsData,String>("lastName"));
        this.Column_FirstName.setCellValueFactory(new PropertyValueFactory<StudentsData,String>("firstName"));
        this.Column_Gender.setCellValueFactory(new PropertyValueFactory<StudentsData,String>("Gender"));
        this.Column_DOB.setCellValueFactory(new PropertyValueFactory<StudentsData,String>("dateOfBirth"));
        this.Column_Religion.setCellValueFactory(new PropertyValueFactory<StudentsData,String>("Religion"));
        this.Column_Residence.setCellValueFactory(new PropertyValueFactory<StudentsData,String>("Residence"));
        this.Column_LandMark.setCellValueFactory(new PropertyValueFactory<StudentsData,String>("landMark"));
        this.Column_class.setCellValueFactory(new PropertyValueFactory<StudentsData,String>("claSS"));
        this.Column_DOA.setCellValueFactory(new PropertyValueFactory<StudentsData,String>("dateOfAdmission"));
        this.Column_LastSchool.setCellValueFactory(new PropertyValueFactory<StudentsData,String>("lastSchoolAttended"));
        this.Column_image.setCellValueFactory(new PropertyValueFactory<StudentsData,byte[]>("Student_image"));
        this.Column_STATUS.setCellValueFactory(new PropertyValueFactory<StudentsData,String>("Status"));
        this.Column_currYear.setCellValueFactory(new PropertyValueFactory<StudentsData,String>("currYear"));
        this.Column_prevY.setCellValueFactory(new PropertyValueFactory<StudentsData,String>("prevYear"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(students);
    }





    public void retreiveLpMarks()throws SQLException{
        String LPLevel = "LowerPrimary";

        try {
            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery("SELECT Marks FROM PreferencesTable WHERE Level = '"+LPLevel+"'");
            if (rs.next()){
              lpMarks = rs.getString("Marks");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
        }
    }

    @FXML
    private void promoteToNextClassActionPerformed(ActionEvent event){

        try {
//            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage promotionStage = new Stage();
            FXMLLoader Loader = new FXMLLoader();
            Pane root = Loader.load(getClass().getResource("/examreport/promotion.fxml").openStream());





            promotionStage.initModality(Modality.APPLICATION_MODAL);

            Image image = new Image("file:app_icon.png");
            promotionStage.getIcons().add(image);
            promotionStage.initStyle(StageStyle.UTILITY);
            promotionStage.setTitle("Promotion");

            promotionStage.setResizable(true);
            promotionStage.setMaximized(false);
            promotionStage.setScene(new Scene(root));
            promotionStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
