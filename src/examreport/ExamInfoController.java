package examreport;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.SqliteConnection;

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

public class ExamInfoController implements Initializable {
    @FXML
    private TableView<ExamRecordData> resultTable;
    @FXML
    private TableColumn<ExamRecordData, String> column_sName;

    @FXML
    private TableColumn<ExamRecordData, String> column_fName;
    @FXML
    private TableColumn<ExamRecordData, String> column_StudentID;
    @FXML
    private TableColumn<ExamRecordData, String> column_Class;
    @FXML
    private TableColumn<ExamRecordData, String> column_Term;
    @FXML
    private TableColumn<ExamRecordData, String> column_SubjectID;
    @FXML
    private TableColumn<ExamRecordData, String> column_RecordId;
    @FXML
    private TableColumn<ExamRecordData, String> column_Math;
    @FXML
    private TableColumn<ExamRecordData, String> column_maths_cs;
    @FXML
    private TableColumn<ExamRecordData, String> column_Eng;
    @FXML
    private TableColumn<ExamRecordData, String> column_eng_cs;
    @FXML
    private TableColumn<ExamRecordData, String> column_Sci;
    @FXML
    private TableColumn<ExamRecordData, String> column_sci_cs;
    @FXML
    private TableColumn<ExamRecordData, String> column_RME;
    @FXML
    private TableColumn<ExamRecordData, String> column_rme_cs;
    @FXML
    private TableColumn<ExamRecordData, String> column_Fante;
    @FXML
    private TableColumn<ExamRecordData, String> column_fante_cs;
    @FXML
    private TableColumn<ExamRecordData, String> column_SocialStudies;
    @FXML
    private TableColumn<ExamRecordData, String> column_sStud_cs;
    @FXML
    private TableColumn<ExamRecordData, String> column_BDT;
    @FXML
    private TableColumn<ExamRecordData, String> column_bdt_cs;
    @FXML
    private TableColumn<ExamRecordData, String> column_Numeracy;
    @FXML
    private TableColumn<ExamRecordData, String> column_numeracy_cs;
    @FXML
    private TableColumn<ExamRecordData, String> column_CreativeArt;
    @FXML
    private TableColumn<ExamRecordData, String> column_cArt_cs;
    @FXML
    private TableColumn<ExamRecordData, String> column_L_iterature;
    @FXML
    private TableColumn<ExamRecordData, String> column_Literacy_cs;
    @FXML
    private TableColumn<ExamRecordData, String> column_Citizenship_EDU;
    @FXML
    private TableColumn<ExamRecordData, String> column_cEdu_cs;
    @FXML
    private TableColumn<ExamRecordData, String> column_Environmental_Stud;
    @FXML
    private TableColumn<ExamRecordData, String> column_eStud_cs;
    @FXML
    private TableColumn<ExamRecordData, String> column_ICT;
    @FXML
    private TableColumn<ExamRecordData, String> column_ICT_cs;
    @FXML
    private JFXButton btn_RefreshTable;
    @FXML
    private JFXButton btn_UpdateResult;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXButton btn_deleteResult;

    private ObservableList<ExamRecordData> result;
    private Connection conn;
    private ResultSet rs = null;
    private PreparedStatement pr = null;

    public ExamInfoController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            loadExamReport();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btn_RefreshTable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    loadExamReport();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_UpdateResult.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/examreport/examRecordEntry.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(ExamInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ExamRecordEntryController examRecordEntryController = loader.getController();
            examRecordEntryController.populateForm(resultTable.getSelectionModel().getSelectedItem());
            examRecordEntryController.btn_Add.setVisible(false);
            examRecordEntryController.btn_Save.setVisible(true);

            Parent parent = loader.getRoot();
            Stage recordInputStage = new Stage();
            recordInputStage.initStyle(StageStyle.UNIFIED);
            recordInputStage.initModality(Modality.APPLICATION_MODAL);
            recordInputStage.setScene(new Scene(parent));
            Image image = new Image("file:app_icon.png");
            recordInputStage.getIcons().add(image);

            recordInputStage.show();

        });

        btn_deleteResult.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Confirm Delete operation.");
            Optional<ButtonType> bp = alert.showAndWait();
            if (bp.get() == ButtonType.OK) {

                try {
                    deleteExamResult(resultTable.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                alert.close();
            }


        });

        FilteredList<ExamRecordData> filteredData = new FilteredList<>(result, e -> true);
        search.setOnKeyReleased(e -> {
            search.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super ExamRecordData>) ExamData -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (ExamData.getStudent_id().contains(newValue)) {
                        return true;
                    } else if (ExamData.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (ExamData.getSurname().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<ExamRecordData> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(resultTable.comparatorProperty());
            resultTable.setItems(sortedData);

        });

    }

    private void loadExamReport() throws SQLException {
        try {
            this.result = FXCollections.observableArrayList();
            String examReport_qurerry = "SELECT Record.Record_id,Record.Surname,Record.FirstName,Record.Class,Record.Term,Record.Student_id,Subject.Mathematics,Subject.MathClassScore,Subject.English,Subject.EnglishClassScore,Subject.Science,Subject.ScienceClassScore,Subject.RME,Subject.RmeClassScore,\n" +
                    "Subject.Fante,Subject.FanteClassScore,Subject.SocialStudies,Subject.SStudiesClassScore,Subject.BDT,Subject.BdtClassScore,Subject.ICT,Subject.IctClassScore,Subject.Numeracy,Subject.NumeracyClassScore,Subject.CreativeArt,Subject.CartClassScore,Subject.Language_Literacy,Subject.LiteracyClassScore,Subject.CitizenshipEducation,Subject.CeduClassScore,Subject.EnvironmentalStudies,Subject.eEduClassScore,Subject.Subject_id\n" +
                    "FROM Record INNER JOIN Subject ON Record.Record_id=Subject.Record_id ORDER BY Record.Record_id";
            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(examReport_qurerry);

            while (rs.next()) {
                this.result.add(new ExamRecordData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25), rs.getString(26), rs.getString(27), rs.getString(28), rs.getString(29), rs.getString(30), rs.getString(31), rs.getString(32), rs.getString(33)));
            }

            this.column_RecordId.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("record_id"));
            this.column_sName.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("surname"));
            this.column_fName.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("firstName"));
            this.column_Class.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("Clas"));
            this.column_Term.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("term"));
            this.column_StudentID.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("student_id"));
            this.column_Math.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("mathematics"));
            this.column_maths_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("mathematics_cs"));
            this.column_Eng.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("english"));
            this.column_eng_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("english_cs"));
            this.column_Sci.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("science"));
            this.column_sci_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("science_cs"));
            this.column_RME.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("rme"));
            this.column_rme_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("rme_cs"));
            this.column_Fante.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("fante"));
            this.column_fante_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("fante_cs"));
            this.column_SocialStudies.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("s_studies"));
            this.column_sStud_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("s_studies_cs"));
            this.column_BDT.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("bdt"));
            this.column_bdt_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("bdt_cs"));
            this.column_ICT.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("ict"));
            this.column_ICT_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("ict_cs"));
            this.column_Numeracy.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("numeracy"));
            this.column_numeracy_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("numeracy_cs"));
            this.column_CreativeArt.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("c_art"));
            this.column_cArt_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("c_art_cs"));
            this.column_L_iterature.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("l_literacy"));
            this.column_Literacy_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("l_literacy_cs"));
            this.column_Citizenship_EDU.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("c_education"));
            this.column_cEdu_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("c_education_cs"));
            this.column_Environmental_Stud.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("e_studies"));
            this.column_eStud_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("e_studies_cs"));
            this.column_SubjectID.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("subject_id"));

            this.resultTable.setItems(null);
            this.resultTable.setItems(result);

        } catch (SQLException e) {
            showAlert("SQLException!", "Couldn't load exam result", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }
        search.setText("");
    }


    private void deleteExamResult(ExamRecordData exam_record) throws SQLException {
        String id = exam_record.getRecord_id();
        conn = SqliteConnection.Connector();

       /* // Delete from Subjects
        try {

            pr = conn.prepareStatement("DELETE FROM Subject WHERE Subject.Record_id = '" + id + "'");
            pr.execute();

            showAlert("Successful", null, "Result has been deleted!");
        } catch (SQLException e) {
            showAlert("Error!", null, "Could not delete result!");
            e.printStackTrace();
        } finally {
            pr.close();

        }*/

        // Delete from Record
        try {

            pr = conn.prepareStatement("DELETE FROM Record WHERE Record_id = '" + id + "'");
            pr.execute();

            showAlert("Successful", null, "Record has been deleted!");
        } catch (SQLException e) {
            showAlert("Error!", null, "Could not delete record!");
            e.printStackTrace();
        } finally {
            pr.close();
            conn.close();
        }
    }


    @FXML
    private void tabeleRowSelect(MouseEvent event) {

        TableColumn<String, String> column1 = new TableColumn<>();
        btn_deleteResult.disableProperty().bind(Bindings.isEmpty(resultTable.getSelectionModel().getSelectedItems()));
        btn_UpdateResult.disableProperty().bind(Bindings.isEmpty(resultTable.getSelectionModel().getSelectedItems()));

    }


    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
