package studentInfo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import examreport.ExamRecordEntryController;
import examreport.JHS_examEntryController;
import examreport.ReportBookController;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import sample.SqliteConnection;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class studentInfoController implements Initializable {
    @FXML
    private JFXTextField txt_search;

    @FXML
    private JFXComboBox<String> combo_sort;

    @FXML
    private ComboBox<String> genderCombo;

    @FXML
    private ComboBox<String> spClassCombo;

    @FXML
    private JFXButton btn_printSelectedClass;

    @FXML
    private JFXButton btn_completedStudentTable;

    @FXML
    private ImageView img_back;

    @FXML
    private JFXButton btn_loadTable;

    @FXML
    private TableView<StudentInfo_Data> Table_View;

    @FXML
    private TableColumn<StudentInfo_Data, String> Column_ID;

    @FXML
    private TableColumn<StudentInfo_Data, String> Column_LastName;

    @FXML
    private TableColumn<StudentInfo_Data, String> Column_FirstName;

    @FXML
    private TableColumn<StudentInfo_Data, String> Column_Gender;

    @FXML
    private TableColumn<StudentInfo_Data, String> Column_DOB;


    @FXML
    private TableColumn<StudentInfo_Data, String> Column_class;


    @FXML
    private TableColumn<StudentInfo_Data, String> Column_STATUS;

    @FXML
    private TableColumn<StudentInfo_Data, String> Column_PARENTID;

    @FXML
    private ImageView img_studentPhoto;

    @FXML
    private Label m_firstName;

    @FXML
    private Label f_eMail;

    @FXML
    private Label f_phone;

    @FXML
    private Label f_lastName;

    @FXML
    private Label f_firstName;

    @FXML
    private Label lbl_landmark;

    @FXML
    private Label lbl_residence;

    @FXML
    private Label lbl_religion;

    @FXML
    private Label lbl_lastSchool;

    @FXML
    private Label lbl_status;

    @FXML
    private Label lbl_doa;

    @FXML
    private Label lbl_class;

    @FXML
    private Label lbl_dob;

    @FXML
    private Label lbl_gender;

    @FXML
    private Label lbl_firstName;

    @FXML
    private Label lbl_lastName;

    @FXML
    private Label lbl_id;

    @FXML
    private Label m_lastName;

    @FXML
    private Label m_phone;

    @FXML
    private Label m_eMail;

    @FXML
    private JFXButton btn_print;


    ObservableList<String> sortOption = FXCollections.observableArrayList(
            "All",
            "JHS3",
            "JHS2",
            "JHS1",
            "P6",
            "P5",
            "P4",
            "P3",
            "P2",
            "P1",
            "KG2",
            "KG1",
            "NURSERY");

    ObservableList<String> sortClass = FXCollections.observableArrayList(

            "JHS3",
            "JHS2",
            "JHS1",
            "P6",
            "P5",
            "P4",
            "P3",
            "P2",
            "P1",
            "KG2",
            "KG1",
            "NURSERY");

    ObservableList<String> gender = FXCollections.observableArrayList(
            "ALL",
            "MALE",
            "FEMALE");


    private ObservableList<StudentInfo_Data> data;
    Connection conn;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    StudentInfo_Data person;

    private String sql = "SELECT Student_id,lastName,firstName,gender,dob,class,status,parent_id FROM StudentData order by lastName";

    public PrintReport printReport = new PrintReport();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.data = FXCollections.observableArrayList();
        this.spClassCombo.setItems(sortClass);
        this.genderCombo.setItems(gender);

        try {
            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        combo_sort.setItems(sortOption);
        combo_sort.setPromptText("All");
        combo_sort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (combo_sort.getValue().equals("All")) {
                        refreshTable();

                    } else if (combo_sort.getValue().equals("JHS3")) {
                        LoadJ3Student();
                    } else if (combo_sort.getValue().equals("JHS2")) {
                        LoadJ2Student();
                    } else if (combo_sort.getValue().equals("JHS1")) {
                        LoadJ1Student();
                    } else if (combo_sort.getValue().equals("P6")) {
                        LoadP6Student();
                    } else if (combo_sort.getValue().equals("P5")) {
                        LoadP5Student();
                    } else if (combo_sort.getValue().equals("P4")) {
                        LoadP4Student();
                    } else if (combo_sort.getValue().equals("P3")) {
                        LoadP3Student();
                    } else if (combo_sort.getValue().equals("P2")) {
                        LoadP2Student();
                    } else if (combo_sort.getValue().equals("P1")) {
                        LoadP1Student();
                    } else if (combo_sort.getValue().equals("KG2")) {
                        LoadKG2Student();
                    } else if (combo_sort.getValue().equals("KG1")) {
                        LoadKG1Student();
                    } else {
                        LoadNURSERYStudent();
                    }

                    clearInformationView();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        Table_View.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    clearInformationView();
                    viewStudentInformation(Table_View.getSelectionModel().getSelectedItem());


                    btn_print.disableProperty().bind(Bindings.isEmpty(Table_View.getSelectionModel().getSelectedItems()));

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }


        });

        this.Table_View.setRowFactory(new Callback<TableView<StudentInfo_Data>, TableRow<StudentInfo_Data>>() {
            @Override
            public TableRow<StudentInfo_Data> call(TableView<StudentInfo_Data> param) {
                final TableRow<StudentInfo_Data> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem addResultItem = new MenuItem("Add Result");
                final MenuItem viewResultItem = new MenuItem("View Result");

                addResultItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        checkLevel(event, Table_View.getSelectionModel().getSelectedItem());
                    }


                });

                viewResultItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Stage viewResultStage = new Stage();
                            FXMLLoader loader = new FXMLLoader();

                            Pane root = loader.load(getClass().getResource("/examreport/report_book.fxml").openStream());


                            ReportBookController reportBookController = loader.getController();
                            try {
                                reportBookController.getName_Id(Table_View.getSelectionModel().getSelectedItem());

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }


                            try {
                                reportBookController.showJHSresult(Table_View.getSelectionModel().getSelectedItem());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            try {
                                reportBookController.showPrimaryResult(Table_View.getSelectionModel().getSelectedItem());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }


                            viewResultStage.initModality(Modality.APPLICATION_MODAL);
                            viewResultStage.setTitle("Result");
                            viewResultStage.setResizable(false);
                            viewResultStage.initStyle(StageStyle.UNIFIED);

                            Image image = new Image("file:app_icon.png");
                            viewResultStage.getIcons().add(image);


                            viewResultStage.setScene(new Scene(root));
                            viewResultStage.show();
                        } catch (IOException e) {
                            showAlert("Exception", "Load Error", "couldn't load result");
                            e.printStackTrace();
                        }

                    }
                });

                contextMenu.getItems().add(addResultItem);
                contextMenu.getItems().add(viewResultItem);

                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu) null)
                                .otherwise(contextMenu)
                );

                return row;
            }
        });


        FilteredList<StudentInfo_Data> filteredData = new FilteredList<>(data, e -> true);
        txt_search.setOnKeyReleased(e -> {
            txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super StudentInfo_Data>) studentInfoData -> {
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
            SortedList<StudentInfo_Data> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(Table_View.comparatorProperty());
            Table_View.setItems(sortedData);

        });

        btn_print.setOnAction(event -> {

            try {
                printReport.showStudentInformation(Table_View.getSelectionModel().getSelectedItem());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        btn_printSelectedClass.setOnAction(event -> {

            classPrinting();
        });

        btn_completedStudentTable.setOnAction(event -> {
            try {
                Stage completedTable = new Stage();
                FXMLLoader loader = new FXMLLoader();

                Pane root = loader.load(getClass().getResource("/studentInfo/completed.fxml").openStream());


                completedTable.initModality(Modality.APPLICATION_MODAL);
                completedTable.setTitle("Result");
                completedTable.setResizable(false);
                completedTable.initStyle(StageStyle.UNIFIED);

                Image image = new Image("file:app_icon.png");
                completedTable.getIcons().add(image);


                completedTable.setScene(new Scene(root));
                completedTable.show();
            } catch (IOException e) {
                showAlert("Exception", "Load Error", "couldn't completed table");
                e.printStackTrace();
            }

        });

    }

    private void classPrinting() {
        String gender = genderCombo.getValue();
        String spClass = spClassCombo.getValue();

        try {
            if (gender.isEmpty() || spClass.isEmpty()) {
                showAlert("INFORMATION", null, "Both gender and class required");
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMATION");
                alert.setTitle(null);
                alert.setContentText("YOu are about to print " + gender + " students in " + spClass + " ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                    try {
                        printReport.printSelectedClass(spClass, gender);
                    } catch (JRException e) {
                        e.printStackTrace();
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTable() throws SQLException {
        try {

            conn = SqliteConnection.Connector();


            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                this.data.add(new StudentInfo_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

            clearInformationView();


        } catch (SQLException e) {
            showAlert("Error!", "Refresh error", "" + e.getMessage() + "");
            e.printStackTrace();

        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("ID"));
        this.Column_LastName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("lastName"));
        this.Column_FirstName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("firstName"));
        this.Column_Gender.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Gender"));
        this.Column_DOB.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("dateOfBirth"));
        this.Column_class.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("claSS"));
        this.Column_STATUS.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Status"));
        this.Column_PARENTID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("parentID"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(this.data);
    }

    private void LoadJ3Student() throws SQLException {
        String studentClass = "JHS3";
        String J3querry = "SELECT  Student_id,lastName,firstName,gender,dob,class,status,parent_id FROM StudentData WHERE class = '" + studentClass + "'";

        try {

            conn = SqliteConnection.Connector();
            this.data = FXCollections.observableArrayList();

            rs = conn.createStatement().executeQuery(J3querry);
            while (rs.next()) {
                this.data.add(new StudentInfo_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (SQLException e) {
            showAlert("Error!", "Could not load JHS3 students", "" + e.getLocalizedMessage() + "");
            System.err.println("Error" + e);

        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("ID"));
        this.Column_LastName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("lastName"));
        this.Column_FirstName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("firstName"));
        this.Column_Gender.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Gender"));
        this.Column_DOB.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("dateOfBirth"));
        this.Column_class.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("claSS"));
        this.Column_STATUS.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Status"));
        this.Column_PARENTID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("parentID"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(this.data);
    }

    private void LoadJ2Student() throws SQLException {
        String studentClass = "JHS2";
        String J3querry = "SELECT Student_id,lastName,firstName,gender,dob,class,status,parent_id FROM StudentData WHERE class = '" + studentClass + "'";

        try {

            conn = SqliteConnection.Connector();
            this.data = FXCollections.observableArrayList();

            rs = conn.createStatement().executeQuery(J3querry);
            while (rs.next()) {
                this.data.add(new StudentInfo_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (SQLException e) {
            showAlert("Error!", "Could not load JHS2 students", "" + e.getLocalizedMessage() + "");
            System.err.println("Error" + e);

        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("ID"));
        this.Column_LastName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("lastName"));
        this.Column_FirstName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("firstName"));
        this.Column_Gender.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Gender"));
        this.Column_DOB.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("dateOfBirth"));
        this.Column_class.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("claSS"));
        this.Column_STATUS.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Status"));
        this.Column_PARENTID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("parentID"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(this.data);
    }

    private void LoadJ1Student() throws SQLException {
        String studentClass = "JHS1";
        String J3querry = "SELECT Student_id,lastName,firstName,gender,dob,class,status,parent_id FROM StudentData WHERE class = '" + studentClass + "'";

        try {

            conn = SqliteConnection.Connector();
            this.data = FXCollections.observableArrayList();

            rs = conn.createStatement().executeQuery(J3querry);
            while (rs.next()) {
                this.data.add(new StudentInfo_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (SQLException e) {
            showAlert("Error!", "Could not load JHS1 students", "" + e.getLocalizedMessage() + "");
            System.err.println("Error" + e);

        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("ID"));
        this.Column_LastName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("lastName"));
        this.Column_FirstName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("firstName"));
        this.Column_Gender.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Gender"));
        this.Column_DOB.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("dateOfBirth"));
        this.Column_class.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("claSS"));
        this.Column_STATUS.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Status"));
        this.Column_PARENTID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("parentID"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(this.data);


    }

    private void LoadP6Student() throws SQLException {
        String studentClass = "P6";
        String J3querry = "SELECT  Student_id,lastName,firstName,gender,dob,class,status,parent_id FROM StudentData WHERE class = '" + studentClass + "'";

        try {

            conn = SqliteConnection.Connector();
            this.data = FXCollections.observableArrayList();

            rs = conn.createStatement().executeQuery(J3querry);
            while (rs.next()) {
                this.data.add(new StudentInfo_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (SQLException e) {
            showAlert("Error!", "Could not load P6 students", "" + e.getLocalizedMessage() + "");
            System.err.println("Error" + e);

        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("ID"));
        this.Column_LastName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("lastName"));
        this.Column_FirstName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("firstName"));
        this.Column_Gender.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Gender"));
        this.Column_DOB.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("dateOfBirth"));
        this.Column_class.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("claSS"));
        this.Column_STATUS.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Status"));
        this.Column_PARENTID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("parentID"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(this.data);
    }

    private void LoadP5Student() throws SQLException {
        String studentClass = "P5";
        String J3querry = "SELECT  Student_id,lastName,firstName,gender,dob,class,status,parent_id FROM StudentData WHERE class = '" + studentClass + "'";

        try {

            conn = SqliteConnection.Connector();
            this.data = FXCollections.observableArrayList();

            rs = conn.createStatement().executeQuery(J3querry);
            while (rs.next()) {
                this.data.add(new StudentInfo_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (SQLException e) {
            showAlert("Error!", "Could not load P5 students", "" + e.getLocalizedMessage() + "");
            System.err.println("Error" + e);

        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("ID"));
        this.Column_LastName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("lastName"));
        this.Column_FirstName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("firstName"));
        this.Column_Gender.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Gender"));
        this.Column_DOB.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("dateOfBirth"));
        this.Column_class.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("claSS"));
        this.Column_STATUS.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Status"));
        this.Column_PARENTID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("parentID"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(this.data);
    }

    private void LoadP4Student() throws SQLException {
        String studentClass = "P4";
        String J3querry = "SELECT  Student_id,lastName,firstName,gender,dob,class,status,parent_id FROM StudentData WHERE class = '" + studentClass + "'";

        try {

            conn = SqliteConnection.Connector();
            this.data = FXCollections.observableArrayList();

            rs = conn.createStatement().executeQuery(J3querry);
            while (rs.next()) {
                this.data.add(new StudentInfo_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (SQLException e) {
            showAlert("Error!", "Could not load P4 students", "" + e.getLocalizedMessage() + "");
            System.err.println("Error" + e);

        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("ID"));
        this.Column_LastName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("lastName"));
        this.Column_FirstName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("firstName"));
        this.Column_Gender.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Gender"));
        this.Column_DOB.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("dateOfBirth"));
        this.Column_class.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("claSS"));
        this.Column_STATUS.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Status"));
        this.Column_PARENTID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("parentID"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(this.data);
    }

    private void LoadP3Student() throws SQLException {
        String studentClass = "P3";
        String J3querry = "SELECT  Student_id,lastName,firstName,gender,dob,class,status,parent_id FROM StudentData WHERE class = '" + studentClass + "'";

        try {

            conn = SqliteConnection.Connector();
            this.data = FXCollections.observableArrayList();

            rs = conn.createStatement().executeQuery(J3querry);
            while (rs.next()) {
                this.data.add(new StudentInfo_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (SQLException e) {
            showAlert("Error!", "Could not load P3 students", "" + e.getLocalizedMessage() + "");
            System.err.println("Error" + e);

        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("ID"));
        this.Column_LastName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("lastName"));
        this.Column_FirstName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("firstName"));
        this.Column_Gender.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Gender"));
        this.Column_DOB.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("dateOfBirth"));
        this.Column_class.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("claSS"));
        this.Column_STATUS.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Status"));
        this.Column_PARENTID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("parentID"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(this.data);
    }

    private void LoadP2Student() throws SQLException {
        String studentClass = "P2";
        String J3querry = "SELECT  Student_id,lastName,firstName,gender,dob,class,status,parent_id FROM StudentData WHERE class = '" + studentClass + "'";

        try {

            conn = SqliteConnection.Connector();
            this.data = FXCollections.observableArrayList();

            rs = conn.createStatement().executeQuery(J3querry);
            while (rs.next()) {
                this.data.add(new StudentInfo_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (SQLException e) {
            showAlert("Error!", "Could not load P2 students", "" + e.getLocalizedMessage() + "");
            System.err.println("Error" + e);

        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("ID"));
        this.Column_LastName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("lastName"));
        this.Column_FirstName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("firstName"));
        this.Column_Gender.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Gender"));
        this.Column_DOB.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("dateOfBirth"));
        this.Column_class.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("claSS"));
        this.Column_STATUS.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Status"));
        this.Column_PARENTID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("parentID"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(this.data);
    }

    private void LoadP1Student() throws SQLException {
        String studentClass = "P1";
        String J3querry = "SELECT  Student_id,lastName,firstName,gender,dob,class,status,parent_id FROM StudentData WHERE class = '" + studentClass + "'";

        try {

            conn = SqliteConnection.Connector();
            this.data = FXCollections.observableArrayList();

            rs = conn.createStatement().executeQuery(J3querry);
            while (rs.next()) {
                this.data.add(new StudentInfo_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (SQLException e) {
            showAlert("Error!", "Could not load P1 students", "" + e.getLocalizedMessage() + "");
            System.err.println("Error" + e);

        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("ID"));
        this.Column_LastName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("lastName"));
        this.Column_FirstName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("firstName"));
        this.Column_Gender.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Gender"));
        this.Column_DOB.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("dateOfBirth"));
        this.Column_class.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("claSS"));
        this.Column_STATUS.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Status"));
        this.Column_PARENTID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("parentID"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(this.data);
    }

    private void LoadKG2Student() throws SQLException {
        String studentClass = "KG2";
        String J3querry = "SELECT  Student_id,lastName,firstName,gender,dob,class,status,parent_id FROM StudentData WHERE class = '" + studentClass + "'";

        try {

            conn = SqliteConnection.Connector();
            this.data = FXCollections.observableArrayList();

            rs = conn.createStatement().executeQuery(J3querry);
            while (rs.next()) {
                this.data.add(new StudentInfo_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (SQLException e) {
            showAlert("Error!", "Could not load KG2 students", "" + e.getLocalizedMessage() + "");
            System.err.println("Error" + e);

        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("ID"));
        this.Column_LastName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("lastName"));
        this.Column_FirstName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("firstName"));
        this.Column_Gender.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Gender"));
        this.Column_DOB.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("dateOfBirth"));
        this.Column_class.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("claSS"));
        this.Column_STATUS.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Status"));
        this.Column_PARENTID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("parentID"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(this.data);
    }

    private void LoadKG1Student() throws SQLException {
        String studentClass = "KG1";
        String J3querry = "SELECT  Student_id,lastName,firstName,gender,dob,class,status,parent_id FROM StudentData WHERE class = '" + studentClass + "'";

        try {

            conn = SqliteConnection.Connector();
            this.data = FXCollections.observableArrayList();

            rs = conn.createStatement().executeQuery(J3querry);
            while (rs.next()) {
                this.data.add(new StudentInfo_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (SQLException e) {
            showAlert("Error!", "Could not load KG1 students", "" + e.getLocalizedMessage() + "");
            System.err.println("Error" + e);

        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("ID"));
        this.Column_LastName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("lastName"));
        this.Column_FirstName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("firstName"));
        this.Column_Gender.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Gender"));
        this.Column_DOB.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("dateOfBirth"));
        this.Column_class.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("claSS"));
        this.Column_STATUS.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Status"));
        this.Column_PARENTID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("parentID"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(this.data);
    }

    private void LoadNURSERYStudent() throws SQLException {
        String studentClass = "NURSERY";
        String J3querry = "SELECT  Student_id,lastName,firstName,gender,dob,class,status,parent_id FROM StudentData WHERE class = '" + studentClass + "'";

        try {

            conn = SqliteConnection.Connector();
            this.data = FXCollections.observableArrayList();

            rs = conn.createStatement().executeQuery(J3querry);
            while (rs.next()) {
                this.data.add(new StudentInfo_Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (SQLException e) {
            showAlert("Error!", "Could not load Nursery students", "" + e.getLocalizedMessage() + "");
            System.err.println("Error" + e);

        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("ID"));
        this.Column_LastName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("lastName"));
        this.Column_FirstName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("firstName"));
        this.Column_Gender.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Gender"));
        this.Column_DOB.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("dateOfBirth"));
        this.Column_class.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("claSS"));
        this.Column_STATUS.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Status"));
        this.Column_PARENTID.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("parentID"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(this.data);
    }

    private void viewStudentInformation(StudentInfo_Data info) throws SQLException {
        person = info;

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            String id_parent = person.getParentID();
            String id_student = person.getID();
            String querry = "SELECT Student_id,lastName,firstName,gender,dob,religion,residence,landMark,class,admission_date,lastSchool_attended,image,status,f_lastName,f_firstName,m_lastName,m_firstName, f_phone,m_phone,f_email,m_email FROM StudentData,Parent WHERE StudentData.parent_id = " + id_parent + " AND Parent.parent_id = " + id_parent + " AND StudentData.Student_id=" + id_student + "";

            conn = SqliteConnection.Connector();


            preparedStatement = conn.prepareStatement(querry);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String studentId = rs.getString(1);
                String student_lastName = rs.getString(2);
                String student_firstName = rs.getString(3);
                String student_gender = rs.getString(4);
                String student_dob = rs.getString(5);
                String student_religion = rs.getString(6);
                String student_residence = rs.getString(7);
                String student_landmark = rs.getString(8);
                String student_class = rs.getString(9);
                String student_doa = rs.getString(10);
                String student_lastSchool = rs.getString(11);
                byte[] student_photo = rs.getBytes(12);
                String student_status = rs.getString(13);
                String student_fLastName = rs.getString(14);
                String student_fFirstName = rs.getString(15);
                String student_mLastName = rs.getString(16);
                String student_mFirstName = rs.getString(17);
                String student_fPhone = rs.getString(18);
                String student_mPhone = rs.getString(19);
                String student_fEmail = rs.getString(20);
                String student_mEmail = rs.getString(21);

                lbl_id.setText(studentId);
                lbl_lastName.setText(student_lastName);
                lbl_firstName.setText(student_firstName);
                lbl_gender.setText(student_gender);
                lbl_dob.setText(student_dob);
                lbl_religion.setText(student_religion);
                lbl_residence.setText(student_residence);
                lbl_landmark.setText(student_landmark);
                lbl_class.setText(student_class);
                lbl_doa.setText(student_doa);
                lbl_lastSchool.setText(student_lastSchool);
                f_lastName.setText(student_fLastName);
                f_firstName.setText(student_fFirstName);
                m_lastName.setText(student_mLastName);
                m_firstName.setText(student_mFirstName);
                f_phone.setText(student_fPhone);
                m_phone.setText(student_mPhone);
                f_eMail.setText(student_fEmail);
                m_eMail.setText(student_mEmail);

                Image image = new Image(new ByteArrayInputStream(student_photo));
                this.img_studentPhoto.setImage(image);

                lbl_status.setText(student_status);
            }
        } catch (SQLException e) {
            showAlert("Error!", "Could not display student Information", "" + e.getLocalizedMessage() + "");
            System.err.println("Error" + e);

        } finally {
            rs.close();
        }
    }

    private void clearInformationView() {
        combo_sort.setPromptText("All");
        txt_search.setText("");

        lbl_id.setText("");
        lbl_lastName.setText("");
        lbl_firstName.setText("");
        lbl_gender.setText("");
        lbl_dob.setText("");
        lbl_religion.setText("");
        lbl_residence.setText("");
        lbl_landmark.setText("");
        lbl_class.setText("");
        lbl_doa.setText("");
        lbl_lastSchool.setText("");
        f_lastName.setText("");
        f_firstName.setText("");
        m_lastName.setText("");
        m_firstName.setText("");
        f_phone.setText("");
        m_phone.setText("");
        f_eMail.setText("");
        m_eMail.setText("");
        Image image = new Image("/update/userImage.png");
        image.isSmooth();
        img_studentPhoto.setImage(image);
    }


    private void checkLevel(ActionEvent event, StudentInfo_Data info) {
        String studentClass = info.getClaSS();

        if (studentClass.equals("JHS3") || studentClass.equals("JHS2") || studentClass.equals("JHS1")) {

            try {
//                ((Node) event.getSource()).getScene().getWindow().hide();

                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/examreport/jhsExamEntry.fxml"));
                try {
                    Loader.load();
                } catch (IOException e) {
                    Logger.getLogger(studentInfoController.class.getName()).log(Level.SEVERE, null, e);
                }

                JHS_examEntryController jhs_examEntryController = Loader.getController();
                jhs_examEntryController.fillRecord(Table_View.getSelectionModel().getSelectedItem());

                Parent parent = Loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("JHS Exam Record Entry");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNIFIED);
                stage.setResizable(false);

                stage.setScene(new Scene(parent));

                Image image = new Image("file:app_icon.png");
                stage.getIcons().add(image);

                stage.show();
            } catch (Exception e) {
                showAlert("Error!", e.getLocalizedMessage(), "Couldn't load JHS Exam Entry");
                e.printStackTrace();
            }
        } else {
            try {
//                            ((Node) event.getSource()).getScene().getWindow().hide();
                Stage examRecordStage = new Stage();
                FXMLLoader Loader = new FXMLLoader();
                Pane root = Loader.load(getClass().getResource("/examreport/examRecordEntry.fxml").openStream());

                ExamRecordEntryController examRecordEntryController = Loader.getController();
                examRecordEntryController.fillRecord(Table_View.getSelectionModel().getSelectedItem());


                examRecordStage.initModality(Modality.APPLICATION_MODAL);
                examRecordStage.setTitle("Examination record entry");
                examRecordStage.setResizable(false);
                examRecordStage.initStyle(StageStyle.UNIFIED);

                Image image = new Image("file:app_icon.png");
                examRecordStage.getIcons().add(image);


                examRecordStage.setScene(new Scene(root));
                examRecordStage.show();


            } catch (Exception e) {
                showAlert("Error!", e.getLocalizedMessage(), "Couldn't Load Exam Entry");
                e.printStackTrace();
            }
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
