package examreport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.SqliteConnection;

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

public class promotionController implements Initializable {

    @FXML
    private TableView<promotionData> tableView;

    @FXML
    private TableColumn<promotionData, CheckBox> select_column;

    @FXML
    private TableColumn<promotionData, String> sid_column;

    @FXML
    private TableColumn<promotionData, String> class_column;

    @FXML
    private TableColumn<promotionData, String> term_column;

    @FXML
    private TableColumn<promotionData, String> marks_column;

    @FXML
    private TableColumn<promotionData, String> year_column;

    @FXML
    private ComboBox<String> combo_class;

    @FXML
    private ComboBox<String> combo_level;

    @FXML
    private Button btn_generate;

    @FXML
    private Button btn_promote;

    @FXML
    private CheckBox select_checkbox;

    @FXML
    private TextField txt_year;

    @FXML
    private Label clearSelection;

    private ObservableList<String> selectedClass = FXCollections.observableArrayList("JHS3", "JHS2", "JHS1", "P6", "P5", "P4", "P3", "P2", "P1", "KG2", "KG1", "NURSERY");
    private ObservableList<String> selectedLevel = FXCollections.observableArrayList("JHS", "UpperPrimary", "LowerPrimary", "KG2", "KG1", "NURSERY");

    private ObservableList<promotionData> studentList;

    private Connection conn;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    String jhsLevleMark;
    String upLevleMark;
    String lpLevleMark;
    String level = null;

    String getClassValue;
    String getLevelValue;
    String yearValue;
    String term = "THREE";

    ObservableList<promotionData> resultIds = FXCollections.observableArrayList();
    ObservableList<unqualifiedStudent_Data> failedStudents_id = FXCollections.observableArrayList();


    DateFormat year = new SimpleDateFormat("yyyy");
    Date d = new Date();


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        this.combo_class.setItems(selectedClass);
        this.combo_level.setItems(selectedLevel);


        try {
            querryLevelsJhs();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            querryLevelsLp();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            querryLevelsUp();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btn_generate.setOnAction(event -> {

            try {
                if ((combo_class.getValue().equals("JHS1")) && (combo_level.getValue().equals("JHS"))) {
                    jhs1QualifiedStudents();
                    JHSUnqualified_students();

                } else if ((combo_class.getValue().equals("P6")) && (combo_level.getValue().equals("UpperPrimary"))) {

                    p6QualifiedStudents();
                    upperPrimaryFailedStudents();

                } else if ((combo_class.getValue().equals("P5")) && (combo_level.getValue().equals("UpperPrimary"))) {

                    p5QualifiedStudents();
                    upperPrimaryFailedStudents();

                } else if ((combo_class.getValue().equals("P4")) && (combo_level.getValue().equals("UpperPrimary"))) {

                    p4QualifiedStudents();
                    upperPrimaryFailedStudents();

                } else if ((combo_class.getValue().equals("P3")) && (combo_level.getValue().equals("LowerPrimary"))) {

                    p3QualifiedStudents();
                    lowerPrimaryFailedStudents();

                } else if ((combo_class.getValue().equals("P2")) && (combo_level.getValue().equals("LowerPrimary"))) {

                    p2QualifiedStudents();
                    lowerPrimaryFailedStudents();

                } else if ((combo_class.getValue().equals("P1")) && (combo_level.getValue().equals("LowerPrimary"))) {

                    p1QualifiedStudents();
                    lowerPrimaryFailedStudents();

                } else if ((combo_class.getValue().equals("KG2")) && (combo_level.getValue().equals("KG2"))) {

                    kg2QualifiedStudents();
                } else if ((combo_class.getValue().equals("KG1")) && (combo_level.getValue().equals("KG1"))) {

                    kg1QualifiedStudents();
                } else if ((combo_class.getValue().equals("NURSERY")) && (combo_level.getValue().equals("NURSERY"))) {

                    nurseryQualifiedStudents();
                } else if ((combo_class.getValue().equals("JHS3")) && (combo_level.getValue().equals("JHS"))) {

                    jhs3QualifiedStudents();
                } else if ((combo_class.getValue().equals("JHS2")) && (combo_level.getValue().equals("JHS"))) {

                    jhs2QualifiedStudents();
                    JHSUnqualified_students();
                } else {

                    showAlert("Exception!", "Error", "Nothing selected/entered!");
                }
                combo_class.setDisable(true);
                combo_level.setDisable(true);
                txt_year.setEditable(false);
                clearSelection.setVisible(true);
                btn_generate.setDisable(true);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        btn_promote.setOnAction(event -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmation");
            alert.setContentText("Are you sure you want to Promote Students to the next class?\nThis cannot be undone!");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                getIds();


                try {
                    if ((combo_class.getValue().equals("JHS1")) && (combo_level.getValue().equals("JHS"))) {
                        try {
                            promoteToJHS2();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    } else if ((combo_class.getValue().equals("P6")) && (combo_level.getValue().equals("UpperPrimary"))) {
                        try {
                            promoteToJHS1();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    } else if ((combo_class.getValue().equals("P5")) && (combo_level.getValue().equals("UpperPrimary"))) {
                        try {
                            promoteToP6();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    } else if ((combo_class.getValue().equals("P4")) && (combo_level.getValue().equals("UpperPrimary"))) {
                        try {
                            promoteToP5();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    } else if ((combo_class.getValue().equals("P3")) && (combo_level.getValue().equals("LowerPrimary"))) {
                        try {
                            promoteToP4();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    } else if ((combo_class.getValue().equals("P2")) && (combo_level.getValue().equals("LowerPrimary"))) {
                        try {
                            promoteToP3();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    } else if ((combo_class.getValue().equals("P1")) && (combo_level.getValue().equals("LowerPrimary"))) {
                        try {
                            promoteToP2();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    } else if ((combo_class.getValue().equals("KG2")) && (combo_level.getValue().equals("KG2"))) {
                        try {
                            promoteToP1();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    } else if ((combo_class.getValue().equals("KG1")) && (combo_level.getValue().equals("KG1"))) {
                        try {
                            promoteToKG2();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    } else if ((combo_class.getValue().equals("NURSERY")) && (combo_level.getValue().equals("NURSERY"))) {
                        try {
                            promoteToKG1();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    } else if ((combo_class.getValue().equals("JHS3")) && (combo_level.getValue().equals("JHS"))) {
                        try {
                            completedStudent();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    } else if ((combo_class.getValue().equals("JHS2")) && (combo_level.getValue().equals("JHS"))) {
                        try {
                            promoteToJHS3();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    } else {

                        showAlert("Exception!", "Error", "Nothing selected/entered!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Update_FailedStudentsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                tableView.setItems(null);



            }
            else {
                alert.close();
            }
        });

        clearSelection.setOnMouseClicked(event -> {
            clearSelection.setVisible(false);

            combo_class.setDisable(false);
            combo_level.setDisable(false);
            txt_year.setEditable(true);
            btn_generate.setDisable(false);

            combo_level.setValue(null);
            combo_class.setValue(null);
            txt_year.setText("");
            tableView.setItems(null);
        });


    }

    private void querryLevelsJhs() throws SQLException {
        level = "JHS";
        try {
            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery("SELECT Marks FROM PreferencesTable WHERE Level='" + level + "'");
            if (rs.next()) {
                jhsLevleMark = rs.getString(1);
            }
        } catch (SQLException e) {
            showAlert("SQLException", "jhsLevel", "" + e.getMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }

    }

    private void querryLevelsUp() throws SQLException {
        level = "UpperPrimary";
        try {
            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery("SELECT Marks FROM PreferencesTable WHERE Level='" + level + "'");
            if (rs.next()) {
                upLevleMark = rs.getString(1);
            }
        } catch (SQLException e) {
            showAlert("SQLException", "upLevel", "" + e.getMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }

    }

    private void querryLevelsLp() throws SQLException {
        level = "LowerPrimary";

        try {
            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery("SELECT Marks FROM PreferencesTable WHERE Level='" + level + "'");


            if (rs.next()) {
                lpLevleMark = rs.getString(1);
            }
        } catch (SQLException e) {
            showAlert("SQLException", "lpLevel", "" + e.getMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }

    }

  /*  private void loadStudentToBePromoted()throws SQLException{




            if (combo_class.getValue().isEmpty() || combo_level.getValue().isEmpty() || txt_year.getText().isEmpty()){
                showAlert("Information",null,"Nothing was selected/entered!");

            }
            if ((combo_class.getValue().equals("P6"))&& (combo_level.getValue().equals("UpperPrimary"))){

               p6QualifiedStudents();
            }

            if ((combo_class.getValue().equals("P5"))&& (combo_level.getValue().equals("UpperPrimary"))){

               p5QualifiedStudents();
            }

            if ((combo_class.getValue().equals("P4"))&& (combo_level.getValue().equals("UpperPrimary"))){

                p4QualifiedStudents();
            }

            if ((combo_class.getValue().equals("P3"))&& (combo_level.getValue().equals("LowerPrimary"))){

                p3QualifiedStudents();
            }

            if ((combo_class.getValue().equals("P2"))&& (combo_level.getValue().equals("LowerPrimary"))){

                p2QualifiedStudents();
            }

            if ((combo_class.getValue().equals("P1"))&& (combo_level.getValue().equals("LowerPrimary"))){

                p1QualifiedStudents();
            }

            if ((combo_class.getValue().equals("KG2"))&& (combo_level.getValue().equals("KG2"))){

                kg2QualifiedStudents();
            }

            if ((combo_class.getValue().equals("KG1"))&& (combo_level.getValue().equals("KG1"))){

                kg1QualifiedStudents();
            }

            if ((combo_class.getValue().equals("NURSERY"))&& (combo_level.getValue().equals("NURSERY"))){

               nurseryQualifiedStudents();
            }

            if ((combo_class.getValue().equals("JHS3"))&& (combo_level.getValue().equals("JHS"))){

                jhs3QualifiedStudents();
            }

            if ((combo_class.getValue().equals("JHS2"))&& (combo_level.getValue().equals("JHS"))){

                jhs2QualifiedStudents();
            }

            if ((combo_class.getValue().equals("JHS1"))&& (combo_level.getValue().equals("JHS"))){

                jhs1QualifiedStudents();
            }




    }*/


    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
    }


    private void jhs3QualifiedStudents() throws SQLException {


        try {

            this.studentList = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();
            String querry = "SELECT StudentID,Term,Year,Class,TotalMarks FROM jhsSubjectTable WHERE Year = '" + yearValue + "'  AND Term = '" + term + "' AND Class='" + getClassValue + "'";

            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()) {

                this.studentList.add(new promotionData(rs.getString("StudentID"), rs.getString("Class"), rs.getString("Term"), rs.getString("TotalMarks"), rs.getString("Year")));
            }


        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }

        this.select_column.setCellValueFactory(new PropertyValueFactory<promotionData, CheckBox>("Select"));
        this.sid_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentID"));
        this.class_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentClass"));
        this.term_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("term"));
        this.marks_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("totalMarks"));
        this.year_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("Year"));

        this.tableView.setItems(null);
        this.tableView.setItems(studentList);
    }


    private void JHSUnqualified_students()throws SQLException{
        try {
            this.failedStudents_id = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();
            String querry = "SELECT StudentID FROM jhsSubjectTable WHERE TotalMarks >'" + jhsLevleMark + "'  AND  Year = '" + yearValue + "'  AND Term = '" + term + "' AND Class='" + getClassValue + "'";

            conn = SqliteConnection.Connector();

            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()){
                this.failedStudents_id.add(new unqualifiedStudent_Data(rs.getString("StudentID")));
            }

        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }
    }

    private void jhs2QualifiedStudents() throws SQLException {

        try {
            this.studentList = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();
            String querry = "SELECT StudentID,Term,Year,Class,TotalMarks FROM jhsSubjectTable WHERE TotalMarks <='" + jhsLevleMark + "'  AND  Year = '" + yearValue + "'  AND Term = '" + term + "' AND Class='" + getClassValue + "'";

            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()) {

                this.studentList.add(new promotionData(rs.getString("StudentID"), rs.getString("Class"), rs.getString("Term"), rs.getString("TotalMarks"), rs.getString("Year")));
            }

        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }

        this.select_column.setCellValueFactory(new PropertyValueFactory<promotionData, CheckBox>("Select"));
        this.sid_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentID"));
        this.class_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentClass"));
        this.term_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("term"));
        this.marks_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("totalMarks"));
        this.year_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("Year"));

        this.tableView.setItems(null);
        this.tableView.setItems(studentList);
    }

    private void jhs1QualifiedStudents() throws SQLException {

        try {
            this.studentList = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();

            String querry = "SELECT jhsSubjectTable.StudentID,jhsSubjectTable.Class,jhsSubjectTable.Term,jhsSubjectTable.TotalMarks,jhsSubjectTable.Year FROM jhsSubjectTable WHERE jhsSubjectTable.Year = '" + yearValue + "' AND jhsSubjectTable.TotalMarks <= '" + jhsLevleMark + "' AND jhsSubjectTable.Term = '" + term + "'";

            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()) {

                this.studentList.add(new promotionData(rs.getString("StudentID"), rs.getString("Class"), rs.getString("Term"), rs.getString("TotalMarks"), rs.getString("Year")));
            }

            JHSUnqualified_students();


        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }

        this.select_column.setCellValueFactory(new PropertyValueFactory<promotionData, CheckBox>("Select"));
        this.sid_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentID"));
        this.class_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentClass"));
        this.term_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("term"));
        this.marks_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("totalMarks"));
        this.year_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("Year"));

        this.tableView.setItems(null);
        this.tableView.setItems(studentList);

    }

    //Method to select uperPrimary failed students
    private void upperPrimaryFailedStudents()throws SQLException{
        try {
            this.failedStudents_id = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();
            String querry =  "SELECT StudentID,Term,Year,Class,TotalMarks FROM Subject WHERE TotalMarks >='" + upLevleMark + "'  AND  Year = '" + yearValue + "'  AND Term = '" + term + "' AND Class='" + getClassValue + "'";
            conn = SqliteConnection.Connector();

            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()){
                this.failedStudents_id.add(new unqualifiedStudent_Data(rs.getString("StudentID")));
            }

        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }

    }

    //Method to get Lower primary failed students
    private void lowerPrimaryFailedStudents()throws SQLException{
        try {
            this.failedStudents_id = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();
            String querry =  "SELECT StudentID,Term,Year,Class,TotalMarks FROM Subject WHERE TotalMarks >='" + lpLevleMark + "'  AND  Year = '" + yearValue + "'  AND Term = '" + term + "' AND Class='" + getClassValue + "'";
            conn = SqliteConnection.Connector();

            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()){
                this.failedStudents_id.add(new unqualifiedStudent_Data(rs.getString("StudentID")));
            }

        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }

    }

    private void p6QualifiedStudents() throws SQLException {

        try {
            this.studentList = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();
            String querry = "SELECT StudentID,Term,Year,Class,TotalMarks FROM Subject WHERE TotalMarks >='" + upLevleMark + "'  AND  Year = '" + yearValue + "'  AND Term = '" + term + "' AND Class='" + getClassValue + "'";

            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()) {

                this.studentList.add(new promotionData(rs.getString("StudentID"), rs.getString("Class"), rs.getString("Term"), rs.getString("TotalMarks"), rs.getString("Year")));
            }


        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }
        this.select_column.setCellValueFactory(new PropertyValueFactory<promotionData, CheckBox>("Select"));
        this.sid_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentID"));
        this.class_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentClass"));
        this.term_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("term"));
        this.marks_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("totalMarks"));
        this.year_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("Year"));

        this.tableView.setItems(null);
        this.tableView.setItems(studentList);

    }

    private void p5QualifiedStudents() throws SQLException {

        try {
            this.studentList = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();
            String querry = "SELECT StudentID,Term,Year,Class,TotalMarks FROM Subject WHERE TotalMarks >='" + upLevleMark + "'  AND  Year = '" + yearValue + "'  AND Term = '" + term + "' AND Class='" + getClassValue + "'";


            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()) {

                this.studentList.add(new promotionData(rs.getString("StudentID"), rs.getString("Class"), rs.getString("Term"), rs.getString("TotalMarks"), rs.getString("Year")));
            }


        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }
        this.select_column.setCellValueFactory(new PropertyValueFactory<promotionData, CheckBox>("Select"));
        this.sid_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentID"));
        this.class_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentClass"));
        this.term_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("term"));
        this.marks_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("totalMarks"));
        this.year_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("Year"));

        this.tableView.setItems(null);
        this.tableView.setItems(studentList);

    }

    private void p4QualifiedStudents() throws SQLException {

        try {
            this.studentList = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();
            String querry = "SELECT StudentID,Term,Year,Class,TotalMarks FROM Subject WHERE TotalMarks >='" + upLevleMark + "'  AND  Year = '" + yearValue + "'  AND Term = '" + term + "' AND Class='" + getClassValue + "'";

            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()) {

                this.studentList.add(new promotionData(rs.getString("StudentID"), rs.getString("Class"), rs.getString("Term"), rs.getString("TotalMarks"), rs.getString("Year")));
            }


        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }
        this.select_column.setCellValueFactory(new PropertyValueFactory<promotionData, CheckBox>("Select"));
        this.sid_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentID"));
        this.class_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentClass"));
        this.term_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("term"));
        this.marks_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("totalMarks"));
        this.year_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("Year"));

        this.tableView.setItems(null);
        this.tableView.setItems(studentList);

    }

    private void p3QualifiedStudents() throws SQLException {

        try {
            this.studentList = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();
            String querry = "SELECT StudentID,Class,Term,TotalMarks,Year FROM Subject WHERE Year = '" + yearValue + "' AND TotalMarks >= '" + lpLevleMark + "' AND Term = '" + term + "' AND Class = '" + getClassValue + "'";

            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()) {

                this.studentList.add(new promotionData(rs.getString("StudentID"), rs.getString("Class"), rs.getString("Term"), rs.getString("TotalMarks"), rs.getString("Year")));
            }


        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }
        this.select_column.setCellValueFactory(new PropertyValueFactory<promotionData, CheckBox>("Select"));
        this.sid_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentID"));
        this.class_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentClass"));
        this.term_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("term"));
        this.marks_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("totalMarks"));
        this.year_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("Year"));

        this.tableView.setItems(null);
        this.tableView.setItems(studentList);

    }

    private void p2QualifiedStudents() throws SQLException {

        try {
            this.studentList = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();
            String querry = "SELECT StudentID,Class,Term,TotalMarks,Year FROM Subject WHERE Year = '" + yearValue + "' AND TotalMarks >= '" + lpLevleMark + "' AND Term = '" + term + "' AND Class= '" + getClassValue + "'";

            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()) {

                this.studentList.add(new promotionData(rs.getString("StudentID"), rs.getString("Class"), rs.getString("Term"), rs.getString("TotalMarks"), rs.getString("Year")));
            }


        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }
        this.select_column.setCellValueFactory(new PropertyValueFactory<promotionData, CheckBox>("Select"));
        this.sid_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentID"));
        this.class_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentClass"));
        this.term_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("term"));
        this.marks_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("totalMarks"));
        this.year_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("Year"));

        this.tableView.setItems(null);
        this.tableView.setItems(studentList);

    }

    private void p1QualifiedStudents() throws SQLException {

        try {
            this.studentList = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();
            String querry = "SELECT StudentID,Class,Term,TotalMarks,Year FROM Subject WHERE Year = '" + yearValue + "'AND TotalMarks >= '" + lpLevleMark + "' AND Term = '" + term + "' AND Class='" + getClassValue + "'";

            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()) {

                this.studentList.add(new promotionData(rs.getString("StudentID"), rs.getString("Class"), rs.getString("Term"), rs.getString("TotalMarks"), rs.getString("Year")));
            }


        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }
        this.select_column.setCellValueFactory(new PropertyValueFactory<promotionData, CheckBox>("Select"));
        this.sid_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentID"));
        this.class_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentClass"));
        this.term_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("term"));
        this.marks_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("totalMarks"));
        this.year_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("Year"));

        this.tableView.setItems(null);
        this.tableView.setItems(studentList);

    }

    private void kg2QualifiedStudents() throws SQLException {

        try {
            this.studentList = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();
            String querry = "SELECT StudentID,Class,Term,TotalMarks,Year FROM Subject WHERE Year = '" + yearValue + "' AND Term = '" + term + "' AND Class='" + getClassValue + "'";

            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()) {

                this.studentList.add(new promotionData(rs.getString("StudentID"), rs.getString("Class"), rs.getString("Term"), rs.getString("TotalMarks"), rs.getString("Year")));
            }


        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }
        this.select_column.setCellValueFactory(new PropertyValueFactory<promotionData, CheckBox>("Select"));
        this.sid_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentID"));
        this.class_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentClass"));
        this.term_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("term"));
        this.marks_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("totalMarks"));
        this.year_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("Year"));

        this.tableView.setItems(null);
        this.tableView.setItems(studentList);

    }

    private void kg1QualifiedStudents() throws SQLException {

        try {
            this.studentList = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();
            String querry = "SELECT StudentID,Term,Year,Class,TotalMarks FROM Subject WHERE Year = '" + yearValue + "'  AND Term = '" + term + "' AND Class='" + getClassValue + "'";

            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()) {

                this.studentList.add(new promotionData(rs.getString("StudentID"), rs.getString("Class"), rs.getString("Term"), rs.getString("TotalMarks"), rs.getString("Year")));
            }


        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }
        this.select_column.setCellValueFactory(new PropertyValueFactory<promotionData, CheckBox>("Select"));
        this.sid_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentID"));
        this.class_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentClass"));
        this.term_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("term"));
        this.marks_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("totalMarks"));
        this.year_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("Year"));

        this.tableView.setItems(null);
        this.tableView.setItems(studentList);

    }

    private void nurseryQualifiedStudents() throws SQLException {

        try {
            this.studentList = FXCollections.observableArrayList();
            getClassValue = combo_class.getValue();
            getLevelValue = combo_level.getValue();
            yearValue = txt_year.getText();
            String querry = "SELECT StudentID,Term,Year,Class,TotalMarks FROM Subject WHERE Year = '" + yearValue + "'  AND Term = '" + term + "' AND Class='" + getClassValue + "'";

            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()) {

                this.studentList.add(new promotionData(rs.getString("StudentID"), rs.getString("Class"), rs.getString("Term"), rs.getString("TotalMarks"), rs.getString("Year")));
            }


        } catch (SQLException e) {
            showAlert("SQLException", "Error!", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }
        this.select_column.setCellValueFactory(new PropertyValueFactory<promotionData, CheckBox>("Select"));
        this.sid_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentID"));
        this.class_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("studentClass"));
        this.term_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("term"));
        this.marks_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("totalMarks"));
        this.year_column.setCellValueFactory(new PropertyValueFactory<promotionData, String>("Year"));

        this.tableView.setItems(null);
        this.tableView.setItems(studentList);


    }

    //Method to get all the student id's from the generated promotion table(qualified)
    private void getIds() {


        for (promotionData bean : studentList) {
            if (bean.getSelect().isSelected()) {
                resultIds.add(bean);
            }
        }
    }



    private void promoteToJHS3() throws SQLException {

        try {
            conn = SqliteConnection.Connector();

            for (promotionData p : resultIds) {
                String id = p.getStudentID();

                String promoteQuerry = "UPDATE StudentData SET class = ?,preYear=(select currentYear),currentYear=(select currentYear + 1) WHERE Student_id = '" + id + "'";

                pstmt = conn.prepareStatement(promoteQuerry);

                pstmt.setString(1, "JHS3");

                pstmt.executeUpdate();
                showAlert("Promotion", "Succesful", "Students promoted");
            }
        } catch (SQLException e) {
            showAlert("Promotion", "Exception", "Students promotion not successful");
            e.printStackTrace();
        } finally {
            pstmt.close();
        }

    }



    // Method for JHS unqualified students
    private void Update_FailedStudentsData() throws SQLException {

        try {
            conn = SqliteConnection.Connector();

            for (unqualifiedStudent_Data q : failedStudents_id) {
                String id = q.getStudent_id();

                String promoteQuerry = "UPDATE StudentData SET preYear=(select currentYear),currentYear=(select currentYear + 1) WHERE Student_id = '" + id + "'";

                pstmt = conn.prepareStatement(promoteQuerry);


                pstmt.executeUpdate();
                showAlert("unqualified", "Succesful", "Students data modified");
            }
        } catch (SQLException e) {
            showAlert("unqualified", "Exception", "Students promotion not successful");
            e.printStackTrace();
        } finally {
            pstmt.close();
        }
    }


    private void promoteToJHS2() throws SQLException {

        try {
            conn = SqliteConnection.Connector();

            for (promotionData p : resultIds) {
                String id = p.getStudentID();

                String promoteQuerry = "UPDATE StudentData SET class = ?,preYear=(select currentYear),currentYear=(select currentYear + 1) WHERE Student_id = '" + id + "'";

                pstmt = conn.prepareStatement(promoteQuerry);

                pstmt.setString(1, "JHS2");

                pstmt.executeUpdate();
                showAlert("Promotion", "Succesful", "Students promoted");


            }
        } catch (SQLException e) {
            showAlert("Promotion", "Exception", "Students promotion not successful");
            e.printStackTrace();
        } finally {
            pstmt.close();
        }
    }

    private void promoteToJHS1() throws SQLException {

        try {
            conn = SqliteConnection.Connector();

            for (promotionData p : resultIds) {
                String id = p.getStudentID();

                String promoteQuerry = "UPDATE StudentData SET class = ?,preYear=(select currentYear),currentYear=(select currentYear + 1) WHERE Student_id = '" + id + "'";

                pstmt = conn.prepareStatement(promoteQuerry);

                pstmt.setString(1, "JHS1");

                pstmt.executeUpdate();
                showAlert("Promotion", "Succesful", "Students promoted");
            }
        } catch (SQLException e) {
            showAlert("Promotion", "Exception", "Students promotion not successful");
            e.printStackTrace();
        } finally {
            pstmt.close();
        }


    }

    private void promoteToKG1() throws SQLException {

        try {
            conn = SqliteConnection.Connector();

            for (promotionData p : resultIds) {
                String id = p.getStudentID();

                String promoteQuerry = "UPDATE StudentData SET class = ?,preYear=(select currentYear),currentYear=(select currentYear + 1) WHERE Student_id = '" + id + "'";

                pstmt = conn.prepareStatement(promoteQuerry);

                pstmt.setString(1, "KG1");

                pstmt.executeUpdate();

            }
            showAlert("Promotion", "Succesful", "Students promoted");

        } catch (SQLException e) {
            showAlert("Promotion", "Exception", "Students promotion not successful");
            e.printStackTrace();
        } finally {
            pstmt.close();
        }

    }

    private void promoteToKG2() throws SQLException {

        try {
            conn = SqliteConnection.Connector();

            for (promotionData p : resultIds) {
                String id = p.getStudentID();

                String promoteQuerry = "UPDATE StudentData SET class = ?,preYear=(select currentYear),currentYear=(select currentYear + 1) WHERE Student_id = '" + id + "'";

                pstmt = conn.prepareStatement(promoteQuerry);

                pstmt.setString(1, "KG2");

                pstmt.executeUpdate();

            }
            showAlert("Promotion", "Succesful", "Students promoted");

        } catch (SQLException e) {
            showAlert("Promotion", "Exception", "Students promotion not successful");
            e.printStackTrace();
        } finally {
            pstmt.close();
        }


    }

    private void promoteToP1() throws SQLException {

        try {
            conn = SqliteConnection.Connector();

            for (promotionData p : resultIds) {
                String id = p.getStudentID();

                String promoteQuerry = "UPDATE StudentData SET class = ?,preYear=(select currentYear),currentYear=(select currentYear + 1) WHERE Student_id = '" + id + "'";

                pstmt = conn.prepareStatement(promoteQuerry);

                pstmt.setString(1, "P1");

                pstmt.executeUpdate();

            }
            showAlert("Promotion", "Succesful", "Students promoted");

        } catch (SQLException e) {
            showAlert("Promotion", "Exception", "Students promotion not successful");
            e.printStackTrace();
        } finally {
            pstmt.close();
        }


    }

    private void promoteToP2() throws SQLException {

        try {
            conn = SqliteConnection.Connector();

            for (promotionData p : resultIds) {
                String id = p.getStudentID();

                String promoteQuerry = "UPDATE StudentData SET class = ?,preYear=(select currentYear),currentYear=(select currentYear + 1) WHERE Student_id = '" + id + "'";

                pstmt = conn.prepareStatement(promoteQuerry);

                pstmt.setString(1, "P2");

                pstmt.executeUpdate();

            }
            showAlert("Promotion", "Succesful", "Students promoted");

        } catch (SQLException e) {
            showAlert("Promotion", "Exception", "Students promotion not successful");
            e.printStackTrace();
        } finally {
            pstmt.close();
        }


    }

    private void promoteToP3() throws SQLException {

        try {
            conn = SqliteConnection.Connector();

            for (promotionData p : resultIds) {
                String id = p.getStudentID();

                String promoteQuerry = "UPDATE StudentData SET class = ?,preYear=(select currentYear),currentYear=(select currentYear + 1) WHERE Student_id = '" + id + "'";

                pstmt = conn.prepareStatement(promoteQuerry);

                pstmt.setString(1, "P3");

                pstmt.executeUpdate();

            }
            showAlert("Promotion", "Succesful", "Students promoted");

        } catch (SQLException e) {
            showAlert("Promotion", "Exception", "Students promotion not successful");
            e.printStackTrace();
        } finally {
            pstmt.close();
        }


    }

    private void promoteToP4() throws SQLException {

        try {
            conn = SqliteConnection.Connector();

            for (promotionData p : resultIds) {
                String id = p.getStudentID();

                String promoteQuerry = "UPDATE StudentData SET class = ?,preYear=(select currentYear),currentYear=(select currentYear + 1) WHERE Student_id = '" + id + "'";

                pstmt = conn.prepareStatement(promoteQuerry);

                pstmt.setString(1, "P4");

                pstmt.executeUpdate();

            }
            showAlert("Promotion", "Succesful", "Students promoted");

        } catch (SQLException e) {
            showAlert("Promotion", "Exception", "Students promotion not successful");
            e.printStackTrace();
        } finally {
            pstmt.close();
        }


    }

    private void promoteToP5() throws SQLException {

        try {
            conn = SqliteConnection.Connector();

            for (promotionData p : resultIds) {
                String id = p.getStudentID();

                String promoteQuerry = "UPDATE StudentData SET class = ?,preYear=(select currentYear),currentYear=(select currentYear + 1) WHERE Student_id = '" + id + "'";

                pstmt = conn.prepareStatement(promoteQuerry);

                pstmt.setString(1, "P5");

                pstmt.executeUpdate();

            }
            showAlert("Promotion", "Succesful", "Students promoted");

        } catch (SQLException e) {
            showAlert("Promotion", "Exception", "Students promotion not successful");
            e.printStackTrace();
        } finally {
            pstmt.close();
        }


    }

    private void promoteToP6() throws SQLException {

        try {
            conn = SqliteConnection.Connector();

            for (promotionData p : resultIds) {
                String id = p.getStudentID();

                String promoteQuerry = "UPDATE StudentData SET class = ?,preYear=(select currentYear),currentYear=(select currentYear + 1) WHERE Student_id = '" + id + "'";

                pstmt = conn.prepareStatement(promoteQuerry);

                pstmt.setString(1, "P6");

                pstmt.executeUpdate();

            }
            showAlert("Promotion", "Succesful", "Students promoted");

        } catch (SQLException e) {
            showAlert("Promotion", "Exception", "Students promotion not successful");
            e.printStackTrace();
        } finally {
            pstmt.close();
        }

    }

    private void completedStudent() throws SQLException {

        try {
            conn = SqliteConnection.Connector();

            for (promotionData p : resultIds) {
                String id = p.getStudentID();

                String promoteQuerry = "UPDATE StudentData SET class = ?,preYear=(select currentYear),currentYear=(select currentYear + 1) WHERE Student_id = '" + id + "'";

                pstmt = conn.prepareStatement(promoteQuerry);

                pstmt.setString(1, "COMPLETED");

                pstmt.executeUpdate();

            }
            showAlert("Promotion", "Succesful", "Students promoted");

        } catch (SQLException e) {
            showAlert("Promotion", "Exception", "Students promotion not successful");
            e.printStackTrace();
        } finally {
            pstmt.close();

        }

    }
}



