package examreport;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.SqliteConnection;
import studentInfo.StudentInfo_Data;

import javax.swing.text.DateFormatter;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ExamRecordEntryController implements Initializable {
    @FXML
    private AnchorPane parent;
    @FXML
    private Text txt_firstName;
    @FXML
    private Text txt_lastName;
    @FXML
    private Text txt_studentID;
    @FXML
    private Text txt_class;
    @FXML
    private JFXComboBox<String> combo_term;
    @FXML
    private Text txt_REcord;
    @FXML
    private Text txt_subjectID;
    @FXML
    private TextField textField_math;
    @FXML
    private TextField textField_eng;
    @FXML
    private TextField textField_intScience;
    @FXML
    private TextField textField_rme;
    @FXML
    private TextField textField_fante;
    @FXML
    private TextField textField_sStudies;
    @FXML
    private Text txt_recordID;
    @FXML
    private TextField textField_bdt;
    @FXML
    private TextField textField_numeracy;
    @FXML
    private TextField textField_cArt;
    @FXML
    private TextField textField_LLiteracy;
    @FXML
    private TextField textField_cEducation;
    @FXML
    private TextField textField_environStudies;
    @FXML
    private TextField textField_ict;
    @FXML
    public JFXButton btn_Add;
    @FXML
    public JFXButton btn_Save;

    @FXML
    private TextField txt_mathClassSscore;

    @FXML
    private TextField txt_engClassScore;

    @FXML
    private TextField txt_rmeClassScore;

    @FXML
    private TextField txt_fanteClassScore;

    @FXML
    private TextField txt_bdtClassScore;

    @FXML
    private TextField txt_sciClassScore;

    @FXML
    private TextField txt_sStudClassScore;

    @FXML
    private TextField txt_numClassScore;

    @FXML
    private TextField txt_cArtClassScore;

    @FXML
    private TextField txt_LLiteracyClassScore;

    @FXML
    private TextField txt_cEduClassScore;

    @FXML
    private TextField txt_eStudClassScore;

    @FXML
    private TextField txt_ictClassScore;


    ObservableList<String> Term = FXCollections.observableArrayList("ONE", "TWO", "THREE");

    int genKey;
    String recordTerm;
    String studentClass;
    String StudentId;

    Connection connection;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    private double Xoffset = 0;
    private double Yoffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        this.combo_term.setItems(Term);

        btn_Add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String termValue = combo_term.getValue();
                    if (termValue.isEmpty()) {

                    } else {
                        try {
                            insertIntoRecord();


                            showAlert("INFORMATION", "Successful!", "Result has been added");

                            clearEntry();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                } catch (Exception e) {
                    showAlert("Error", null, "Please choose Term/one or more fields required ");
                    e.printStackTrace();
                }


            }
        });

        btn_Save.setOnAction(event -> {

            try {
                updateExamsRecord();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

    }

    private void insertIntoSubject() throws SQLException {
        DateFormat year = new SimpleDateFormat("yyyy");
        java.util.Date d = new Date();

        String subjectQuerry = "INSERT INTO Subject(Subject_id,Mathematics,MathClassScore,English,EnglishClassScore,Science,ScienceClassScore,RME,RmeClassScore,Fante,FanteClassScore,SocialStudies,SStudiesClassScore,BDT,BdtClassScore,ICT,IctClassScore,Numeracy,NumeracyClassScore,CreativeArt,CartClassScore,Language_Literacy,LiteracyClassScore,CitizenshipEducation,CeduClassScore,EnvironmentalStudies,eEduClassScore,Record_id,Term,Class,TotalMarks,StudentID,Year) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            connection = SqliteConnection.Connector();

            pstmt = connection.prepareStatement(subjectQuerry);
            pstmt.setString(1, null);
            pstmt.setString(2, textField_math.getText());
            pstmt.setString(3, txt_mathClassSscore.getText());
            pstmt.setString(4, textField_eng.getText());
            pstmt.setString(5, txt_engClassScore.getText());
            pstmt.setString(6, textField_intScience.getText());
            pstmt.setString(7, txt_sciClassScore.getText());
            pstmt.setString(8, textField_rme.getText());
            pstmt.setString(9, txt_rmeClassScore.getText());
            pstmt.setString(10, textField_fante.getText());
            pstmt.setString(11, txt_fanteClassScore.getText());
            pstmt.setString(12, textField_sStudies.getText());
            pstmt.setString(13, txt_sStudClassScore.getText());
            pstmt.setString(14, textField_bdt.getText());
            pstmt.setString(15, txt_bdtClassScore.getText());
            pstmt.setString(16, textField_ict.getText());
            pstmt.setString(17, txt_ictClassScore.getText());
            pstmt.setString(18, textField_numeracy.getText());
            pstmt.setString(19, txt_numClassScore.getText());
            pstmt.setString(20, textField_cArt.getText());
            pstmt.setString(21, txt_cArtClassScore.getText());
            pstmt.setString(22, textField_LLiteracy.getText());
            pstmt.setString(23, txt_LLiteracyClassScore.getText());
            pstmt.setString(24, textField_cEducation.getText());
            pstmt.setString(25, txt_cEduClassScore.getText());
            pstmt.setString(26, textField_environStudies.getText());
            pstmt.setString(27, txt_eStudClassScore.getText());

            pstmt.setString(28, String.valueOf(genKey));
            pstmt.setString(29, recordTerm);
            pstmt.setString(30, studentClass);

            float math = Float.parseFloat(textField_math.getText());
            float eng = Float.parseFloat(textField_eng.getText());
            float science = Float.parseFloat(textField_intScience.getText());
            float rme = Float.parseFloat(textField_rme.getText());
            float fante = Float.parseFloat(textField_fante.getText());
            float sStudies = Float.parseFloat(textField_sStudies.getText());
            float bdt = Float.parseFloat(textField_bdt.getText());
            float ict = Float.parseFloat(textField_ict.getText());
            float numeracy = Float.parseFloat(textField_numeracy.getText());
            float cARt = Float.parseFloat(textField_cArt.getText());
            float literacy = Float.parseFloat(textField_LLiteracy.getText());
            float citizen = Float.parseFloat(textField_cEducation.getText());
            float environstudies = Float.parseFloat(textField_environStudies.getText());

            float mathClassScoreValue = Float.parseFloat(txt_mathClassSscore.getText());
            float engClassScoreValue = Float.parseFloat(txt_engClassScore.getText());
            float scienceClassScoreValue = Float.parseFloat(txt_sciClassScore.getText());
            float rmeClassScoreValue = Float.parseFloat(txt_rmeClassScore.getText());
            float fanteClassScoreValue = Float.parseFloat(txt_fanteClassScore.getText());
            float sStudiesClassScoreValue = Float.parseFloat(txt_sStudClassScore.getText());
            float bdtClassScoreValue = Float.parseFloat(txt_bdtClassScore.getText());
            float ictClassScoreValue = Float.parseFloat(txt_ictClassScore.getText());
            float numeracyClassScoreValue = Float.parseFloat(txt_numClassScore.getText());
            float cARtClassScoreValue = Float.parseFloat(txt_cArtClassScore.getText());
            float literacyClassScoreValue = Float.parseFloat(txt_LLiteracyClassScore.getText());
            float citizenClassScoreValue = Float.parseFloat(txt_cEduClassScore.getText());
            float environstudiesClassScoreValue = Float.parseFloat(txt_eStudClassScore.getText());

            float total = math + eng + science + rme + fante + sStudies + bdt + ict + numeracy + cARt + literacy + citizen + environstudies + mathClassScoreValue + engClassScoreValue + scienceClassScoreValue + rmeClassScoreValue + fanteClassScoreValue + sStudiesClassScoreValue + bdtClassScoreValue + ictClassScoreValue + numeracyClassScoreValue + cARtClassScoreValue + literacyClassScoreValue + citizenClassScoreValue + environstudiesClassScoreValue;
            String TotalMarks = String.valueOf(total);


            pstmt.setString(31, TotalMarks);
            pstmt.setString(32, StudentId);
            pstmt.setString(33, year.format(d));
            pstmt.execute();

            showAlert("Record ", "Successful", "Record added successfully");


        } catch (SQLException e) {
            showAlert("Error! ", "Could not Insert to Subject", "'" + e.getLocalizedMessage() + "'");
            e.printStackTrace();
        } finally {
            pstmt.close();
        }
    }

    private void insertIntoRecord() throws SQLException {

        String Student_surname = txt_lastName.getText();
        String Student_firstName = txt_firstName.getText();
        String studentId = txt_studentID.getText();
        StudentId = studentId;
        String student_CLASS = txt_class.getText();
        String term = combo_term.getValue();
        recordTerm = term;
        studentClass = student_CLASS;


        String recordQuerry = "INSERT INTO Record(Record_id,Surname,FirstName,Class,Term,Student_id) VALUES(?,?,?,?,?,?)";


        try {
            connection = SqliteConnection.Connector();

            pstmt = connection.prepareStatement(recordQuerry, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, null);
            pstmt.setString(2, Student_surname);
            pstmt.setString(3, Student_firstName);
            pstmt.setString(4, student_CLASS);
            pstmt.setString(5, term);
            pstmt.setString(6, studentId);
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                genKey = rs.getInt(1);

            }

            insertIntoSubject();


        } catch (SQLException e) {
            showAlert("Error", "Could not insert into record", "'" + e.getLocalizedMessage() + "'");
            e.printStackTrace();
        } finally {
            pstmt.close();
            rs.close();
        }

    }

    private void updateExamsRecord() throws SQLException {
        connection = SqliteConnection.Connector();

        try {
            String recordID = txt_recordID.getText();
            String updateRecordQuerry = "UPDATE Record SET Term=? WHERE Record_id='" + recordID + "'";

            pstmt = connection.prepareStatement(updateRecordQuerry);
            pstmt.setString(1, combo_term.getValue());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            showAlert("Error!", "Could not update record", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();

        } finally {
            pstmt.close();
        }

        try {
            String subjectID = txt_subjectID.getText();
            String updateSubjectQuerry = "UPDATE Subject SET Mathematics=?,MathClassScore=?,English=?,EnglishClassScore=?,Science=?,ScienceClassScore=?,RME=?,RmeClassScore=?,Fante=?,FanteClassScore=?,SocialStudies=?,SStudiesClassScore=?,BDT=?,BdtClassScore=?,ICT=?,IctClassScore=?,Numeracy=?,NumeracyClassScore=?,CreativeArt=?,CartClassScore=?,Language_Literacy=?,LiteracyClassScore=?,CitizenshipEducation=?,CeduClassScore=?,EnvironmentalStudies=?,eEduClassScore=?,Term=?,Class=?,TotalMarks=? WHERE Subject_id='" + subjectID + "'";


            pstmt = connection.prepareStatement(updateSubjectQuerry);
            pstmt.setString(1, textField_math.getText());
            pstmt.setString(2, txt_mathClassSscore.getText());
            pstmt.setString(3, textField_eng.getText());
            pstmt.setString(4, txt_engClassScore.getText());
            pstmt.setString(5, textField_intScience.getText());
            pstmt.setString(6, txt_sciClassScore.getText());
            pstmt.setString(7, textField_rme.getText());
            pstmt.setString(8, txt_rmeClassScore.getText());
            pstmt.setString(9, textField_fante.getText());
            pstmt.setString(10, txt_fanteClassScore.getText());
            pstmt.setString(11, textField_sStudies.getText());
            pstmt.setString(12, txt_sStudClassScore.getText());
            pstmt.setString(13, textField_bdt.getText());
            pstmt.setString(14, txt_bdtClassScore.getText());
            pstmt.setString(15, textField_ict.getText());
            pstmt.setString(16, txt_ictClassScore.getText());
            pstmt.setString(17, textField_numeracy.getText());
            pstmt.setString(18, txt_numClassScore.getText());
            pstmt.setString(19, textField_cArt.getText());
            pstmt.setString(20, txt_cArtClassScore.getText());
            pstmt.setString(21, textField_LLiteracy.getText());
            pstmt.setString(22, txt_LLiteracyClassScore.getText());
            pstmt.setString(23, textField_cEducation.getText());
            pstmt.setString(24, txt_cEduClassScore.getText());
            pstmt.setString(25, textField_environStudies.getText());
            pstmt.setString(26, txt_eStudClassScore.getText());

            recordTerm = combo_term.getValue();
            pstmt.setString(27, recordTerm);
            studentClass = txt_class.getText();
            pstmt.setString(28, studentClass);

            float math = Float.parseFloat(textField_math.getText());
            float eng = Float.parseFloat(textField_eng.getText());
            float science = Float.parseFloat(textField_intScience.getText());
            float rme = Float.parseFloat(textField_rme.getText());
            float fante = Float.parseFloat(textField_fante.getText());
            float sStudies = Float.parseFloat(textField_sStudies.getText());
            float bdt = Float.parseFloat(textField_bdt.getText());
            float ict = Float.parseFloat(textField_ict.getText());
            float numeracy = Float.parseFloat(textField_numeracy.getText());
            float cARt = Float.parseFloat(textField_cArt.getText());
            float literacy = Float.parseFloat(textField_LLiteracy.getText());
            float citizen = Float.parseFloat(textField_cEducation.getText());
            float environstudies = Float.parseFloat(textField_environStudies.getText());


            float mathClassScoreValue = Float.parseFloat(txt_mathClassSscore.getText());
            float engClassScoreValue = Float.parseFloat(txt_engClassScore.getText());
            float scienceClassScoreValue = Float.parseFloat(txt_sciClassScore.getText());
            float rmeClassScoreValue = Float.parseFloat(txt_rmeClassScore.getText());
            float fanteClassScoreValue = Float.parseFloat(txt_fanteClassScore.getText());
            float sStudiesClassScoreValue = Float.parseFloat(txt_sStudClassScore.getText());
            float bdtClassScoreValue = Float.parseFloat(txt_bdtClassScore.getText());
            float ictClassScoreValue = Float.parseFloat(txt_ictClassScore.getText());
            float numeracyClassScoreValue = Float.parseFloat(txt_numClassScore.getText());
            float cARtClassScoreValue = Float.parseFloat(txt_cArtClassScore.getText());
            float literacyClassScoreValue = Float.parseFloat(txt_LLiteracyClassScore.getText());
            float citizenClassScoreValue = Float.parseFloat(txt_cEduClassScore.getText());
            float environstudiesClassScoreValue = Float.parseFloat(txt_eStudClassScore.getText());

            float total = math + eng + science + rme + fante + sStudies + bdt + ict + numeracy + cARt + literacy + citizen + environstudies + mathClassScoreValue + engClassScoreValue + scienceClassScoreValue + rmeClassScoreValue + fanteClassScoreValue + sStudiesClassScoreValue + bdtClassScoreValue + ictClassScoreValue + numeracyClassScoreValue + cARtClassScoreValue + literacyClassScoreValue + citizenClassScoreValue + environstudiesClassScoreValue;
            String TotalMarks = String.valueOf(total);
            pstmt.setString(29, TotalMarks);


            pstmt.execute();

            showAlert("Record ", "Successful", "Record updated successfully");

        } catch (SQLException e) {
            showAlert("Error!", "Could not update subject", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {

            pstmt.close();
        }


    }


    public void fillRecord(StudentInfo_Data student) {
        this.txt_firstName.setText(student.getFirstName());
        this.txt_lastName.setText(student.getLastName());
        this.txt_studentID.setText(student.getID());
        this.txt_class.setText(student.getClaSS());


    }

    private void clearEntry() {
        textField_bdt.setText("");
        textField_cArt.setText("");
        textField_cEducation.setText("");
        textField_eng.setText("");
        textField_environStudies.setText("");
        textField_fante.setText("");
        textField_ict.setText("");
        textField_intScience.setText("");
        textField_LLiteracy.setText("");
        textField_math.setText("");
        textField_sStudies.setText("");
        textField_rme.setText("");
        textField_numeracy.setText("");

        txt_mathClassSscore.setText("");
        txt_engClassScore.setText("");
        txt_sciClassScore.setText("");
        txt_rmeClassScore.setText("");
        txt_fanteClassScore.setText("");
        txt_sStudClassScore.setText("");
        txt_cArtClassScore.setText("");
        txt_LLiteracyClassScore.setText("");
        txt_numClassScore.setText("");
        txt_ictClassScore.setText("");
        txt_eStudClassScore.setText("");
        txt_cEduClassScore.setText("");
        txt_bdtClassScore.setText("");

    }

    public void showAlert(String title, String headerText, String contentText) {


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }


    public void populateForm(ExamRecordData record) {

        this.txt_firstName.setText(record.getFirstName());
        this.txt_recordID.setText(record.getRecord_id());
        this.txt_lastName.setText(record.getSurname());
        this.txt_studentID.setText(record.getStudent_id());
        this.txt_class.setText(record.getClas());
        this.combo_term.setValue(record.getTerm());


        this.txt_subjectID.setText(record.getSubject_id());
        this.textField_bdt.setText(record.getBdt());
        this.textField_cArt.setText(record.getC_art());
        this.textField_cEducation.setText(record.getC_education());
        this.textField_eng.setText(record.getEnglish());
        this.textField_environStudies.setText(record.getE_studies());
        this.textField_fante.setText(record.getFante());
        this.textField_ict.setText(record.getIct());
        this.textField_intScience.setText(record.getScience());
        this.textField_LLiteracy.setText(record.getL_literacy());
        this.textField_math.setText(record.getMathematics());
        this.textField_sStudies.setText(record.getS_studies());
        this.textField_rme.setText(record.getRme());
        this.textField_numeracy.setText(record.getNumeracy());


        this.txt_bdtClassScore.setText(record.getBdt_cs());
        this.txt_cArtClassScore.setText(record.getC_art_cs());
        this.txt_cEduClassScore.setText(record.getC_education_cs());
        this.txt_engClassScore.setText(record.getEnglish_cs());
        this.txt_eStudClassScore.setText(record.getE_studies_cs());
        this.txt_fanteClassScore.setText(record.getFante_cs());
        this.txt_ictClassScore.setText(record.getIct_cs());
        this.txt_sciClassScore.setText(record.getScience_cs());
        this.txt_LLiteracyClassScore.setText(record.getL_literacy_cs());
        this.txt_mathClassSscore.setText(record.getMathematics_cs());
        this.txt_sStudClassScore.setText(record.getS_studies_cs());
        this.txt_rmeClassScore.setText(record.getRme_cs());
        this.txt_numClassScore.setText(record.getNumeracy_cs());

    }


}
