package examreport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sf.jasperreports.engine.JRException;
import sample.SqliteConnection;
import studentInfo.PrintReport;
import studentInfo.StudentInfo_Data;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ReportBookController implements Initializable {

    @FXML
    private TableView<ExamRecordData> JHSresultTable;

    @FXML
    private TableColumn<ExamRecordData, String> column_sName_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_fName_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_StudentID_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_Class_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_Term_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_SubjectID_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_RecordId_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_Math_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_maths_cs_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_Eng_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_eng_cs_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_Sci_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_sci_cs_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_RME_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_rme_cs_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_Fante_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_fante_cs_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_SocialStudies_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_sStud_cs_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_BDT_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_bdt_cs_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_Numeracy_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_numeracy_cs_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_CreativeArt_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_cArt_cs_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_L_iterature_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_Literacy_cs_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_Citizenship_EDU_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_cEdu_cs_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_Environmental_Stud_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_eStud_cs_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_ICT_JHS;

    @FXML
    private TableColumn<ExamRecordData, String> column_ICT_cs_JHS;

    @FXML
    private TableView<ExamRecordData> PRIMARYresultTable;

    @FXML
    private TableColumn<ExamRecordData, String> column_sName1;

    @FXML
    private TableColumn<ExamRecordData, String> column_fName1;

    @FXML
    private TableColumn<ExamRecordData, String> column_StudentID1;

    @FXML
    private TableColumn<ExamRecordData, String> column_Class1;

    @FXML
    private TableColumn<ExamRecordData, String> column_Term1;

    @FXML
    private TableColumn<ExamRecordData, String> column_SubjectID1;

    @FXML
    private TableColumn<ExamRecordData, String> column_RecordId1;

    @FXML
    private TableColumn<ExamRecordData, String> column_Math1;

    @FXML
    private TableColumn<ExamRecordData, String> column_maths_cs1;

    @FXML
    private TableColumn<ExamRecordData, String> column_Eng1;

    @FXML
    private TableColumn<ExamRecordData, String> column_eng_cs1;

    @FXML
    private TableColumn<ExamRecordData, String> column_Sci1;

    @FXML
    private TableColumn<ExamRecordData, String> column_sci_cs1;

    @FXML
    private TableColumn<ExamRecordData, String> column_RME1;

    @FXML
    private TableColumn<ExamRecordData, String> column_rme_cs1;

    @FXML
    private TableColumn<ExamRecordData, String> column_Fante1;

    @FXML
    private TableColumn<ExamRecordData, String> column_fante_cs1;

    @FXML
    private TableColumn<ExamRecordData, String> column_SocialStudies1;

    @FXML
    private TableColumn<ExamRecordData, String> column_sStud_cs1;

    @FXML
    private TableColumn<ExamRecordData, String> column_BDT1;

    @FXML
    private TableColumn<ExamRecordData, String> column_bdt_cs1;

    @FXML
    private TableColumn<ExamRecordData, String> column_Numeracy1;

    @FXML
    private TableColumn<ExamRecordData, String> column_numeracy_cs1;

    @FXML
    private TableColumn<ExamRecordData, String> column_CreativeArt1;

    @FXML
    private TableColumn<ExamRecordData, String> column_cArt_cs1;

    @FXML
    private TableColumn<ExamRecordData, String> column_L_iterature1;

    @FXML
    private TableColumn<ExamRecordData, String> column_Literacy_cs1;

    @FXML
    private TableColumn<ExamRecordData, String> column_Citizenship_EDU1;

    @FXML
    private TableColumn<ExamRecordData, String> column_cEdu_cs1;

    @FXML
    private TableColumn<ExamRecordData, String> column_Environmental_Stud1;

    @FXML
    private TableColumn<ExamRecordData, String> column_eStud_cs1;

    @FXML
    private TableColumn<ExamRecordData, String> column_ICT1;

    @FXML
    private TableColumn<ExamRecordData, String> column_ICT_cs1;

    @FXML
    private ComboBox<String> combo_class;

    @FXML
    private ComboBox<String> combo_term;

    @FXML
    private Button btn_view;

    @FXML
    private ImageView imageview;

    @FXML
    private Label lbl_fullname;

    @FXML
    private Label lbl_id;

    @FXML
    private Label lbl_math;

    @FXML
    private Label lbl_english;

    @FXML
    private Label lbl_science;

    @FXML
    private Label lbl_rme;

    @FXML
    private Label lbl_fante;

    @FXML
    private Label lbl_social;

    @FXML
    private Label lbl_bdt;

    @FXML
    private Label lbl_numeracy;

    @FXML
    private Label lbl_cArt;

    @FXML
    private Label lbl_languageLiteracy;

    @FXML
    private Label lbl_cEcuation;

    @FXML
    private Label lbl_eStudies;

    @FXML
    private Label lbl_ict;

    @FXML
    private Label lbl_mathClassScore;

    @FXML
    private Label lbl_engClassScore;

    @FXML
    private Label lbl_sciClassScore;

    @FXML
    private Label lbl_rmeClassScore;

    @FXML
    private Label lbl_FanteClassScore;

    @FXML
    private Label lbl_socialClassScore;

    @FXML
    private Label lbl_bdtClassScore;

    @FXML
    private Label lbl_numClassScore;

    @FXML
    private Label lbl_artClassScore;

    @FXML
    private Label lbl_literacyClassScore;

    @FXML
    private Label lbl_citizenClassScore;

    @FXML
    private Label lbl_envClassScore;

    @FXML
    private Label lbl_ictClassScore;

    @FXML
    private Label lbl_mathExamScore;

    @FXML
    private Label lbl_engExamScore;

    @FXML
    private Label lbl_sciExamScore;

    @FXML
    private Label lbl_rmeExamScore;

    @FXML
    private Label lbl_fanteExamScore;

    @FXML
    private Label lbl_socialExamScore;

    @FXML
    private Label lbl_bdtExamScore;

    @FXML
    private Label lbl_numExamScore;

    @FXML
    private Label lbl_artExamScore;

    @FXML
    private Label lbl_literacyExamScore;

    @FXML
    private Label lbl_citizenExamScore;

    @FXML
    private Label lbl_envExamScore;

    @FXML
    private Label lbl_ictExamScore;

    @FXML
    private Label lbl_class;

    @FXML
    private Label lbl_term;

    @FXML
    private Button btn_print;


    private Connection conn;
    private ObservableList<ExamRecordData> result;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    byte[] photo;
    String ID;

    ObservableList<String> classchooser = FXCollections.observableArrayList(
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

    ObservableList<String> termchooser = FXCollections.observableArrayList("THREE", "TWO", "ONE");
    ObservableList<String> grade_A_part = FXCollections.observableArrayList();
    ObservableList<String> grade_B_part = FXCollections.observableArrayList();
    int grade_1_A, grade_1_B, grade_2_A, grade_2_B, grade_3_A, grade_3_B, grade_4_A, grade_4_B, grade_5_A, grade_5_B, grade_6_A, grade_6_B, grade_7_A, grade_7_B, grade_8_A, grade_8_B, grade_9;


    public PrintReport printReport = new PrintReport();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.combo_class.setItems(classchooser);
        this.combo_term.setItems(termchooser);
        /*btn_view.setOnAction(event -> {
            try {
                loadStudentResult();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });*/

        try {
            pullGrades();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btn_print.setOnAction(event -> {
            try {
                String studentId = lbl_id.getText();
                String Sclass = lbl_class.getText();
                String mathTotal = lbl_math.getText();
                String engTotal = lbl_english.getText();
                String sciTotal = lbl_science.getText();
                String rmeTotal = lbl_rme.getText();
                String fanteTotal = lbl_fante.getText();
                String socialTotal = lbl_social.getText();
                String bdtTotal = lbl_bdt.getText();
                String ictTotal = lbl_ict.getText();
                String numeracyTotal = lbl_numeracy.getText();
                String artTotal = lbl_cArt.getText();
                String literacyTotal = lbl_languageLiteracy.getText();
                String citizenshipTotal = lbl_cEcuation.getText();
                String eStudiesTotal = lbl_eStudies.getText();

                try {
                    printReport.showTermReport(studentId, Sclass, mathTotal, engTotal, sciTotal, rmeTotal, fanteTotal, socialTotal, bdtTotal, ictTotal,
                            numeracyTotal, artTotal, literacyTotal, citizenshipTotal, eStudiesTotal);
                } catch (JRException e) {
                    showAlert("Report Error", null, e.getLocalizedMessage());
                    e.printStackTrace();
                }


            } catch (Exception e) {
                showAlert("Error", null, e.getMessage());
            }
        });

        JHSresultTable.setOnMouseClicked(event -> {

            try {
                loadStudentResult(JHSresultTable.getSelectionModel().getSelectedItem());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });


        PRIMARYresultTable.setOnMouseClicked(event -> {

            try {
                loadStudentResult(PRIMARYresultTable.getSelectionModel().getSelectedItem());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });


    }

    private void pullGrades() throws SQLException {
        String levelCondition = "JHS";
        String grade_A_Querry = "SELECT Range FROM PreferencesTable WHERE Level='" + levelCondition + "'";
        String grade_B_Querry = "SELECT Range_b FROM PreferencesTable WHERE Level='" + levelCondition + "'";

        try {
            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(grade_A_Querry);
            while (rs.next()) {
                grade_A_part.add(rs.getString(1));
            }

        } catch (SQLException e) {
            showAlert("SQLException", "Grade", e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            rs.close();


        }

        grade_1_A = Integer.valueOf(grade_A_part.get(0));
        grade_2_A = Integer.valueOf(grade_A_part.get(1));
        grade_3_A = Integer.valueOf(grade_A_part.get(2));
        grade_4_A = Integer.valueOf(grade_A_part.get(3));
        grade_5_A = Integer.valueOf(grade_A_part.get(4));
        grade_6_A = Integer.valueOf(grade_A_part.get(5));
        grade_7_A = Integer.valueOf(grade_A_part.get(6));
        grade_8_A = Integer.valueOf(grade_A_part.get(7));
        grade_9 = Integer.valueOf(grade_A_part.get(8));


        try {
            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(grade_B_Querry);
            while (rs.next()) {
                grade_B_part.add(rs.getString(1));
            }

        } catch (SQLException e) {
            showAlert("SQLException", "Grade", e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            rs.close();


        }

        grade_1_B = Integer.valueOf(grade_B_part.get(0));
        grade_2_B = Integer.valueOf(grade_B_part.get(1));
        grade_3_B = Integer.valueOf(grade_B_part.get(2));
        grade_4_B = Integer.valueOf(grade_B_part.get(3));
        grade_5_B = Integer.valueOf(grade_B_part.get(4));
        grade_6_B = Integer.valueOf(grade_B_part.get(5));
        grade_7_B = Integer.valueOf(grade_B_part.get(6));
        grade_8_B = Integer.valueOf(grade_B_part.get(7));


    }

    public void getName_Id(StudentInfo_Data info) throws SQLException {
        try {
            String firstName = info.getFirstName().toUpperCase();
            String lastName = info.getLastName().toUpperCase();
            String fullName = lastName + " " + firstName;

            ID = info.getID();

            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery("SELECT image FROM StudentData WHERE Student_id = '" + ID + "'");

            if (rs.next()) {
                photo = rs.getBytes(1);
            }
            lbl_fullname.setText(fullName);
            lbl_id.setText(ID);
            Image image = new Image(new ByteArrayInputStream(photo));
            imageview.setImage(image);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
            conn.close();
        }
    }

    private void loadStudentResult(ExamRecordData info) throws SQLException {

        try {
            String term = info.getTerm();
            String recordClass = info.getClas();


            String primaryQuerry = ";SELECT Subject.Mathematics,Subject.MathClassScore,Subject.English,Subject.EnglishClassScore,Subject.Science,Subject.ScienceClassScore,Subject.RME,RmeClassScore,\n" +
                    "Subject.Fante,Subject.FanteClassScore,Subject.SocialStudies,Subject.SStudiesClassScore,Subject.BDT,Subject.BdtClassScore,Subject.ICT,Subject.IctClassScore,Subject.Numeracy,Subject.NumeracyClassScore," +
                    "Subject.CreativeArt,Subject.CartClassScore,Subject.Language_Literacy,Subject.LiteracyClassScore,Subject.CitizenshipEducation,Subject.CeduClassScore,Subject.EnvironmentalStudies,Subject.eEduClassScore\n" +
                    "FROM Subject WHERE Subject.StudentID = '" + ID + "' AND  Subject.Term = '" + term + "' AND Subject.Class = '" + recordClass + "'";

            String jhsQuerry = ";SELECT jhsSubjectTable.Mathematics,jhsSubjectTable.MathClassScore,jhsSubjectTable.English,jhsSubjectTable.EnglishClassScore,jhsSubjectTable.Science,jhsSubjectTable.ScienceClassScore,jhsSubjectTable.RME,jhsSubjectTable.RmeClassScore,\n" +
                    "jhsSubjectTable.Fante,jhsSubjectTable.FanteClassScore,jhsSubjectTable.SocialStudies,jhsSubjectTable.SStudiesClassScore,jhsSubjectTable.BDT,jhsSubjectTable.BdtClassScore,jhsSubjectTable.ICT,jhsSubjectTable.IctClassScore,jhsSubjectTable.Numeracy,jhsSubjectTable.NumeracyClassScore," +
                    "jhsSubjectTable.CreativeArt,jhsSubjectTable.CartClassScore,jhsSubjectTable.Language_Literacy,jhsSubjectTable.LiteracyClassScore,jhsSubjectTable.CitizenshipEducation,jhsSubjectTable.CeduClassScore,jhsSubjectTable.EnvironmentalStudies,jhsSubjectTable.eEduClassScore\n" +
                    "FROM jhsSubjectTable WHERE jhsSubjectTable.StudentID = '" + ID + "' AND  jhsSubjectTable.Term = '" + term + "' AND jhsSubjectTable.Class = '" + recordClass + "'";


            conn = SqliteConnection.Connector();

            if (recordClass.equals("JHS3") || recordClass.equals("JHS2") || recordClass.equals("JHS1")) {
                rs = conn.createStatement().executeQuery(jhsQuerry);

                if (rs.next()) {

                    //MATHEMATICS
                    float mathematics = rs.getFloat(1);
                    if (!(mathematics < 1)) {
                        lbl_mathExamScore.setText(String.valueOf(mathematics));
                    } else {
                        lbl_mathExamScore.setText("n/a");
                    }

                    float math_ClassScoreValue = rs.getFloat(2);
                    if (!(math_ClassScoreValue < 1)) {
                        lbl_mathClassScore.setText(String.valueOf(math_ClassScoreValue));
                    } else {
                        lbl_mathClassScore.setText("n/a");
                    }

                    float mathScore = mathematics + math_ClassScoreValue;
                    if (mathScore >= grade_1_A) {
                        lbl_math.setText("1");

                    } else if ((mathScore >= grade_2_A) && (mathScore <= grade_2_B)) {
                        lbl_math.setText("2");
                    } else if ((mathScore >= grade_3_A) && (mathScore <= grade_3_B)) {
                        lbl_math.setText("3");
                    } else if ((mathScore >= grade_4_A) && (mathScore <= grade_4_B)) {
                        lbl_math.setText("4");
                    } else if ((mathScore >= grade_5_A) && (mathScore <= grade_5_B)) {
                        lbl_math.setText("5");
                    } else if ((mathScore >= grade_6_A) && (mathScore <= grade_6_B)) {
                        lbl_math.setText("6");
                    } else if ((mathScore >= grade_7_A) && (mathScore <= grade_7_B)) {
                        lbl_math.setText("7");
                    } else if ((mathScore >= grade_8_A) && (mathScore <= grade_8_B)) {
                        lbl_math.setText("8");
                    } else {
                        lbl_math.setText("9");
                    }


                    //ENGLISH
                    float english = rs.getFloat(3);
                    if (!(english < 1)) {
                        lbl_engExamScore.setText(String.valueOf(english));
                    } else {
                        lbl_engExamScore.setText("n/a");
                    }

                    float eng_ClassScoreValue = rs.getFloat(4);
                    if (!(eng_ClassScoreValue < 1)) {
                        lbl_engClassScore.setText(String.valueOf(eng_ClassScoreValue));
                    } else {
                        lbl_engClassScore.setText("n/a");
                    }

                    float englishScore = english + eng_ClassScoreValue;
                    if (englishScore >= grade_1_A) {
                        lbl_english.setText("1");

                    } else if ((englishScore >= grade_2_A) && (englishScore <= grade_2_B)) {
                        lbl_english.setText("2");
                    } else if ((englishScore >= grade_3_A) && (englishScore <= grade_3_B)) {
                        lbl_english.setText("3");
                    } else if ((englishScore >= grade_4_A) && (englishScore <= grade_4_B)) {
                        lbl_english.setText("4");
                    } else if ((englishScore >= grade_5_A) && (englishScore <= grade_5_B)) {
                        lbl_english.setText("5");
                    } else if ((englishScore >= grade_6_A) && (englishScore <= grade_6_B)) {
                        lbl_english.setText("6");
                    } else if ((englishScore >= grade_7_A) && (englishScore <= grade_7_B)) {
                        lbl_english.setText("7");
                    } else if ((englishScore >= grade_8_A) && (englishScore <= grade_8_B)) {
                        lbl_english.setText("8");
                    } else {
                        lbl_english.setText("9");
                    }


                    //SCIENCE
                    float science = rs.getFloat(5);
                    if (!(science < 1)) {
                        lbl_sciExamScore.setText(String.valueOf(science));
                    } else {
                        lbl_sciExamScore.setText("n/a");
                    }

                    float science_ClassScoreValue = rs.getFloat(6);
                    if (!(science_ClassScoreValue < 1)) {
                        lbl_sciClassScore.setText(String.valueOf(science_ClassScoreValue));
                    } else {
                        lbl_sciClassScore.setText("n/a");
                    }

                    float scienceScore = science + science_ClassScoreValue;
                    if (scienceScore >= grade_1_A) {
                        lbl_science.setText("1");

                    } else if ((scienceScore >= grade_2_A) && (scienceScore <= grade_2_B)) {
                        lbl_science.setText("2");
                    } else if ((scienceScore >= grade_3_A) && (scienceScore <= grade_3_B)) {
                        lbl_science.setText("3");
                    } else if ((scienceScore >= grade_4_A) && (scienceScore <= grade_4_B)) {
                        lbl_science.setText("4");
                    } else if ((scienceScore >= grade_5_A) && (scienceScore <= grade_5_B)) {
                        lbl_science.setText("5");
                    } else if ((scienceScore >= grade_6_A) && (scienceScore <= grade_6_B)) {
                        lbl_science.setText("6");
                    } else if ((scienceScore >= grade_7_A) && (scienceScore <= grade_7_B)) {
                        lbl_science.setText("7");
                    } else if ((scienceScore >= grade_8_A) && (scienceScore <= grade_8_B)) {
                        lbl_science.setText("8");
                    } else {
                        lbl_science.setText("9");
                    }


                    //RME
                    float rme = rs.getFloat(7);
                    if (!(rme < 1)) {
                        lbl_rmeExamScore.setText(String.valueOf(rme));
                    } else {
                        lbl_rmeExamScore.setText("n/a");
                    }

                    float rme_ClassScoreValue = rs.getFloat(8);
                    if (!(rme_ClassScoreValue < 1)) {
                        lbl_rmeClassScore.setText(String.valueOf(rme_ClassScoreValue));
                    } else {
                        lbl_rmeClassScore.setText("n/a");
                    }

                    float rmeScore = rme + rme_ClassScoreValue;
                    if (rmeScore >= grade_1_A) {
                        lbl_rme.setText("1");

                    } else if ((rmeScore >= grade_2_A) && (rmeScore <= grade_2_B)) {
                        lbl_rme.setText("2");
                    } else if ((rmeScore >= grade_3_A) && (rmeScore <= grade_3_B)) {
                        lbl_rme.setText("3");
                    } else if ((rmeScore >= grade_4_A) && (rmeScore <= grade_4_B)) {
                        lbl_rme.setText("4");
                    } else if ((rmeScore >= grade_5_A) && (rmeScore <= grade_5_B)) {
                        lbl_rme.setText("5");
                    } else if ((rmeScore >= grade_6_A) && (rmeScore <= grade_6_B)) {
                        lbl_rme.setText("6");
                    } else if ((rmeScore >= grade_7_A) && (rmeScore <= grade_7_B)) {
                        lbl_rme.setText("7");
                    } else if ((rmeScore >= grade_8_A) && (rmeScore <= grade_8_B)) {
                        lbl_rme.setText("8");
                    } else {
                        lbl_rme.setText("9");
                    }


                    //FANTE
                    float fante = rs.getFloat(9);
                    if (!(fante < 1)) {
                        lbl_fanteExamScore.setText(String.valueOf(fante));
                    } else {
                        lbl_fanteExamScore.setText("n/a");
                    }

                    float fante_ClassScoreValue = rs.getFloat(10);
                    if (!(fante_ClassScoreValue < 1)) {
                        lbl_FanteClassScore.setText(String.valueOf(fante_ClassScoreValue));
                    } else {
                        lbl_FanteClassScore.setText("n/a");
                    }

                    float fanteScore = fante + fante_ClassScoreValue;
                    if (fanteScore >= grade_1_A) {
                        lbl_fante.setText("1");

                    } else if ((fanteScore >= grade_2_A) && (fanteScore <= grade_2_B)) {
                        lbl_fante.setText("2");
                    } else if ((fanteScore >= grade_3_A) && (fanteScore <= grade_3_B)) {
                        lbl_fante.setText("3");
                    } else if ((fanteScore >= grade_4_A) && (fanteScore <= grade_4_B)) {
                        lbl_fante.setText("4");
                    } else if ((fanteScore >= grade_5_A) && (fanteScore <= grade_5_B)) {
                        lbl_fante.setText("5");
                    } else if ((fanteScore >= grade_6_A) && (fanteScore <= grade_6_B)) {
                        lbl_fante.setText("6");
                    } else if ((fanteScore >= grade_7_A) && (fanteScore <= grade_7_B)) {
                        lbl_fante.setText("7");
                    } else if ((fanteScore >= grade_8_A) && (fanteScore <= grade_8_B)) {
                        lbl_fante.setText("8");
                    } else {
                        lbl_fante.setText("9");
                    }


                    //SOCIAL STUDIES
                    float socialStudies = rs.getFloat(11);
                    if (!(socialStudies < 1)) {
                        lbl_socialExamScore.setText(String.valueOf(socialStudies));
                    } else {
                        lbl_socialExamScore.setText("n/a");
                    }

                    float sStudies_ClassScoreValue = rs.getFloat(12);
                    if (!(sStudies_ClassScoreValue < 1)) {
                        lbl_socialClassScore.setText(String.valueOf(sStudies_ClassScoreValue));
                    } else {
                        lbl_socialClassScore.setText("n/a");
                    }

                    float socialScore = socialStudies + sStudies_ClassScoreValue;
                    if (socialScore >= grade_1_A) {
                        lbl_social.setText("1");

                    } else if ((socialScore >= grade_2_A) && (socialScore <= grade_2_B)) {
                        lbl_social.setText("2");
                    } else if ((socialScore >= grade_3_A) && (socialScore <= grade_3_B)) {
                        lbl_social.setText("3");
                    } else if ((socialScore >= grade_4_A) && (socialScore <= grade_4_B)) {
                        lbl_social.setText("4");
                    } else if ((socialScore >= grade_5_A) && (socialScore <= grade_5_B)) {
                        lbl_social.setText("5");
                    } else if ((socialScore >= grade_6_A) && (socialScore <= grade_6_B)) {
                        lbl_social.setText("6");
                    } else if ((socialScore >= grade_7_A) && (socialScore <= grade_7_B)) {
                        lbl_social.setText("7");
                    } else if ((socialScore >= grade_8_A) && (socialScore <= grade_8_B)) {
                        lbl_social.setText("8");
                    } else {
                        lbl_social.setText("9");
                    }


                    //BDT
                    float bdt = rs.getFloat(13);
                    if (!(bdt < 1)) {
                        lbl_bdtExamScore.setText(String.valueOf(bdt));
                    } else {
                        lbl_bdtExamScore.setText("n/a");
                    }

                    float bdt_ClassScoreValue = rs.getFloat(14);
                    if (!(bdt_ClassScoreValue < 1)) {
                        lbl_bdtClassScore.setText(String.valueOf(bdt_ClassScoreValue));
                    } else {
                        lbl_bdtClassScore.setText("n/a");
                    }

                    float bdtScore = bdt + bdt_ClassScoreValue;
                    if (bdtScore >= grade_1_A) {
                        lbl_bdt.setText("1");

                    } else if ((bdtScore >= grade_2_A) && (bdtScore <= grade_2_B)) {
                        lbl_bdt.setText("2");
                    } else if ((bdtScore >= grade_3_A) && (bdtScore <= grade_3_B)) {
                        lbl_bdt.setText("3");
                    } else if ((bdtScore >= grade_4_A) && (bdtScore <= grade_4_B)) {
                        lbl_bdt.setText("4");
                    } else if ((bdtScore >= grade_5_A) && (bdtScore <= grade_5_B)) {
                        lbl_bdt.setText("5");
                    } else if ((bdtScore >= grade_6_A) && (bdtScore <= grade_6_B)) {
                        lbl_bdt.setText("6");
                    } else if ((bdtScore >= grade_7_A) && (bdtScore <= grade_7_B)) {
                        lbl_bdt.setText("7");
                    } else if ((bdtScore >= grade_8_A) && (bdtScore <= grade_8_B)) {
                        lbl_bdt.setText("8");
                    } else {
                        lbl_bdt.setText("9");
                    }


                    //ICT
                    float ict = rs.getFloat(15);
                    if (!(ict < 1)) {
                        lbl_ictExamScore.setText(String.valueOf(ict));
                    } else {
                        lbl_ictExamScore.setText("n/a");
                    }

                    float ict_ClassScoreValue = rs.getFloat(16);
                    if (!(ict_ClassScoreValue < 1)) {
                        lbl_ictClassScore.setText(String.valueOf(ict_ClassScoreValue));
                    } else {
                        lbl_ictClassScore.setText("n/a");
                    }

                    float ictScore = ict + ict_ClassScoreValue;
                    if (ictScore >= grade_1_A) {
                        lbl_ict.setText("1");

                    } else if ((ictScore >= grade_2_A) && (ictScore <= grade_2_B)) {
                        lbl_ict.setText("2");
                    } else if ((ictScore >= grade_3_A) && (ictScore <= grade_3_B)) {
                        lbl_ict.setText("3");
                    } else if ((ictScore >= grade_4_A) && (ictScore <= grade_4_B)) {
                        lbl_ict.setText("4");
                    } else if ((ictScore >= grade_5_A) && (ictScore <= grade_5_B)) {
                        lbl_ict.setText("5");
                    } else if ((ictScore >= grade_6_A) && (ictScore <= grade_6_B)) {
                        lbl_ict.setText("6");
                    } else if ((ictScore >= grade_7_A) && (ictScore <= grade_7_B)) {
                        lbl_ict.setText("7");
                    } else if ((ictScore >= grade_8_A) && (ictScore <= grade_8_B)) {
                        lbl_ict.setText("8");
                    } else {
                        lbl_ict.setText("9");
                    }


                    //NUMERACY
                    float numeracy = rs.getFloat(17);
                    if (!(numeracy < 1)) {
                        lbl_numExamScore.setText(String.valueOf(numeracy));
                    } else {
                        lbl_numExamScore.setText("n/a");
                    }

                    float numeracy_ClassScoreValue = rs.getFloat(18);
                    if (!(numeracy_ClassScoreValue < 1)) {
                        lbl_numClassScore.setText(String.valueOf(numeracy_ClassScoreValue));
                    } else {
                        lbl_numClassScore.setText("n/a");
                    }

                    float numeracyScore = numeracy + numeracy_ClassScoreValue;
                    if (!(numeracyScore < 1)) {
                        lbl_numeracy.setText(String.valueOf(numeracyScore));
                    } else {
                        lbl_numeracy.setText("n/a");
                    }


                    //CREATIVE ART
                    float cARt = rs.getFloat(19);
                    if (!(cARt < 1)) {
                        lbl_artExamScore.setText(String.valueOf(cARt));
                    } else {
                        lbl_artExamScore.setText("n/a");
                    }

                    float cARt_ClassScoreValue = rs.getFloat(19);
                    if (!(cARt_ClassScoreValue < 1)) {
                        lbl_artClassScore.setText(String.valueOf(cARt_ClassScoreValue));
                    } else {
                        lbl_artClassScore.setText("n/a");
                    }

                    float artScore = cARt + cARt_ClassScoreValue;
                    if (!(artScore < 1)) {
                        lbl_cArt.setText(String.valueOf(artScore));
                    } else {
                        lbl_cArt.setText("n/a");
                    }


                    //LANGUAGE AND LITERACY
                    float literacy = rs.getFloat(20);
                    if (!(literacy < 1)) {
                        lbl_literacyExamScore.setText(String.valueOf(literacy));
                    } else {
                        lbl_literacyExamScore.setText("n/a");
                    }

                    float literacy_ClassScoreValue = rs.getFloat(20);
                    if (!(literacy_ClassScoreValue < 1)) {
                        lbl_literacyClassScore.setText(String.valueOf(literacy_ClassScoreValue));
                    } else {
                        lbl_literacyClassScore.setText("n/a");
                    }

                    float literacyScore = literacy + literacy_ClassScoreValue;
                    if (!(literacyScore < 1)) {
                        lbl_languageLiteracy.setText(String.valueOf(literacyScore));
                    } else {
                        lbl_languageLiteracy.setText("n/a");
                    }


                    //CITIZENSHIP EDUCATION
                    float citizenshipEducatioin = rs.getFloat(21);
                    if (!(citizenshipEducatioin < 1)) {
                        lbl_citizenExamScore.setText(String.valueOf(citizenshipEducatioin));
                    } else {
                        lbl_citizenExamScore.setText("n/a");
                    }

                    float citizenship_ClassScoreValue = rs.getFloat(22);
                    if (!(citizenship_ClassScoreValue < 1)) {
                        lbl_citizenClassScore.setText(String.valueOf(citizenship_ClassScoreValue));
                    } else {
                        lbl_citizenClassScore.setText("n/a");
                    }

                    float citizenshipScore = citizenshipEducatioin + citizenship_ClassScoreValue;
                    if (!(citizenshipScore < 1)) {
                        lbl_cEcuation.setText(String.valueOf(citizenshipScore));
                    } else {
                        lbl_cEcuation.setText("n/a");
                    }


                    //ENVIRONMENTAL STUDIES
                    float environStudies = rs.getFloat(23);
                    if (!(environStudies < 1)) {
                        lbl_envExamScore.setText(String.valueOf(environStudies));
                    } else {
                        lbl_envExamScore.setText("n/a");
                    }

                    float environstudies_ClassScoreValue = rs.getFloat(24);
                    if (!(environstudies_ClassScoreValue < 1)) {
                        lbl_envClassScore.setText(String.valueOf(environstudies_ClassScoreValue));
                    } else {
                        lbl_envClassScore.setText("n/a");
                    }

                    float eStudiesScore = environStudies + environstudies_ClassScoreValue;
                    if (!(eStudiesScore < 1)) {
                        lbl_eStudies.setText(String.valueOf(eStudiesScore));
                    } else {
                        lbl_eStudies.setText("n/a");
                    }


                } else {
                    lbl_math.setText("");
                    lbl_english.setText("");
                    lbl_science.setText("");
                    lbl_rme.setText("");
                    lbl_fante.setText("");
                    lbl_social.setText("");
                    lbl_bdt.setText("");
                    lbl_numeracy.setText("");
                    lbl_cArt.setText("");
                    lbl_languageLiteracy.setText("");
                    lbl_cEcuation.setText("");
                    lbl_eStudies.setText("");
                    lbl_ict.setText("");
                    lbl_mathClassScore.setText("");
                    lbl_engClassScore.setText("");
                    lbl_sciClassScore.setText("");
                    lbl_rmeClassScore.setText("");
                    lbl_FanteClassScore.setText("");
                    lbl_socialClassScore.setText("");
                    lbl_bdtClassScore.setText("");
                    lbl_numClassScore.setText("");
                    lbl_artClassScore.setText("");
                    lbl_literacyClassScore.setText("");
                    lbl_citizenClassScore.setText("");
                    lbl_envClassScore.setText("");
                    lbl_ictClassScore.setText("");
                    lbl_mathExamScore.setText("");
                    lbl_engExamScore.setText("");
                    lbl_sciExamScore.setText("");
                    lbl_rmeExamScore.setText("");
                    lbl_fanteExamScore.setText("");
                    lbl_socialExamScore.setText("");
                    lbl_bdtExamScore.setText("");
                    lbl_numExamScore.setText("");
                    lbl_artExamScore.setText("");
                    lbl_literacyExamScore.setText("");
                    lbl_citizenExamScore.setText("");
                    lbl_envExamScore.setText("");
                    lbl_ictExamScore.setText("");

                    showAlert("Sorry!", "No record found", null);
                }
            } else {
                rs = conn.createStatement().executeQuery(primaryQuerry);

                if (rs.next()) {

                    //MATHEMATICS
                    float mathematics = rs.getFloat(1);
                    if (!(mathematics < 1)) {
                        lbl_mathExamScore.setText(String.valueOf(mathematics));
                    } else {
                        lbl_mathExamScore.setText("n/a");
                    }

                    float math_ClassScoreValue = rs.getFloat(2);
                    if (!(math_ClassScoreValue < 1)) {
                        lbl_mathClassScore.setText(String.valueOf(math_ClassScoreValue));
                    } else {
                        lbl_mathClassScore.setText("n/a");
                    }

                    float mathScore = mathematics + math_ClassScoreValue;
                    if (!(mathScore < 1)) {
                        lbl_math.setText(String.valueOf(mathScore));
                    } else {
                        lbl_math.setText("n/a");
                    }


                    //ENGLISH
                    float english = rs.getFloat(3);
                    if (!(english < 1)) {
                        lbl_engExamScore.setText(String.valueOf(english));
                    } else {
                        lbl_engExamScore.setText("n/a");
                    }

                    float eng_ClassScoreValue = rs.getFloat(4);
                    if (!(eng_ClassScoreValue < 1)) {
                        lbl_engClassScore.setText(String.valueOf(eng_ClassScoreValue));
                    } else {
                        lbl_engClassScore.setText("n/a");
                    }

                    float englishScore = english + eng_ClassScoreValue;
                    if (!(englishScore < 1)) {
                        lbl_english.setText(String.valueOf(englishScore));
                    } else {
                        lbl_english.setText("n/a");
                    }


                    //SCIENCE
                    float science = rs.getFloat(5);
                    if (!(science < 1)) {
                        lbl_sciExamScore.setText(String.valueOf(science));
                    } else {
                        lbl_sciExamScore.setText("n/a");
                    }

                    float science_ClassScoreValue = rs.getFloat(6);
                    if (!(science_ClassScoreValue < 1)) {
                        lbl_sciClassScore.setText(String.valueOf(science_ClassScoreValue));
                    } else {
                        lbl_sciClassScore.setText("n/a");
                    }

                    float scienceScore = science + science_ClassScoreValue;
                    if (!(scienceScore < 1)) {
                        lbl_science.setText(String.valueOf(scienceScore));
                    } else {
                        lbl_science.setText("n/a");
                    }


                    //RME
                    float rme = rs.getFloat(7);
                    if (!(rme < 1)) {
                        lbl_rmeExamScore.setText(String.valueOf(rme));
                    } else {
                        lbl_rmeExamScore.setText("n/a");
                    }

                    float rme_ClassScoreValue = rs.getFloat(8);
                    if (!(rme_ClassScoreValue < 1)) {
                        lbl_rmeClassScore.setText(String.valueOf(rme_ClassScoreValue));
                    } else {
                        lbl_rmeClassScore.setText("n/a");
                    }

                    float rmeScore = rme + rme_ClassScoreValue;
                    if (!(rmeScore < 1)) {
                        lbl_rme.setText(String.valueOf(rmeScore));
                    } else {
                        lbl_rme.setText("n/a");
                    }


                    //FANTE
                    float fante = rs.getFloat(9);
                    if (!(fante < 1)) {
                        lbl_fanteExamScore.setText(String.valueOf(fante));
                    } else {
                        lbl_fanteExamScore.setText("n/a");
                    }

                    float fante_ClassScoreValue = rs.getFloat(10);
                    if (!(fante_ClassScoreValue < 1)) {
                        lbl_FanteClassScore.setText(String.valueOf(fante_ClassScoreValue));
                    } else {
                        lbl_FanteClassScore.setText("n/a");
                    }

                    float fanteScore = fante + fante_ClassScoreValue;
                    if (!(fanteScore < 1)) {
                        lbl_fante.setText(String.valueOf(fanteScore));
                    } else {
                        lbl_fante.setText("n/a");
                    }


                    //SOCIAL STUDIES
                    float socialStudies = rs.getFloat(11);
                    if (!(socialStudies < 1)) {
                        lbl_socialExamScore.setText(String.valueOf(socialStudies));
                    } else {
                        lbl_socialExamScore.setText("n/a");
                    }

                    float sStudies_ClassScoreValue = rs.getFloat(12);
                    if (!(sStudies_ClassScoreValue < 1)) {
                        lbl_socialClassScore.setText(String.valueOf(sStudies_ClassScoreValue));
                    } else {
                        lbl_socialClassScore.setText("n/a");
                    }

                    float socialScore = socialStudies + sStudies_ClassScoreValue;
                    if (!(socialScore < 1)) {
                        lbl_social.setText(String.valueOf(socialScore));
                    } else {
                        lbl_social.setText("n/a");
                    }


                    //BDT
                    float bdt = rs.getFloat(13);
                    if (!(bdt < 1)) {
                        lbl_bdtExamScore.setText(String.valueOf(bdt));
                    } else {
                        lbl_bdtExamScore.setText("n/a");
                    }

                    float bdt_ClassScoreValue = rs.getFloat(14);
                    if (!(bdt_ClassScoreValue < 1)) {
                        lbl_bdtClassScore.setText(String.valueOf(bdt_ClassScoreValue));
                    } else {
                        lbl_bdtClassScore.setText("n/a");
                    }

                    float bdtScore = bdt + bdt_ClassScoreValue;
                    if (!(bdtScore < 1)) {
                        lbl_bdt.setText(String.valueOf(bdtScore));
                    } else {
                        lbl_bdt.setText("n/a");
                    }


                    //ICT
                    float ict = rs.getFloat(15);
                    if (!(ict < 1)) {
                        lbl_ictExamScore.setText(String.valueOf(ict));
                    } else {
                        lbl_ictExamScore.setText("n/a");
                    }

                    float ict_ClassScoreValue = rs.getFloat(16);
                    if (!(ict_ClassScoreValue < 1)) {
                        lbl_ictClassScore.setText(String.valueOf(ict_ClassScoreValue));
                    } else {
                        lbl_ictClassScore.setText("n/a");
                    }

                    float ictScore = ict + ict_ClassScoreValue;
                    if (!(ictScore < 1)) {
                        lbl_ict.setText(String.valueOf(ictScore));
                    } else {
                        lbl_ict.setText("n/a");
                    }


                    //NUMERACY
                    float numeracy = rs.getFloat(17);
                    if (!(numeracy < 1)) {
                        lbl_numExamScore.setText(String.valueOf(numeracy));
                    } else {
                        lbl_numExamScore.setText("n/a");
                    }

                    float numeracy_ClassScoreValue = rs.getFloat(18);
                    if (!(numeracy_ClassScoreValue < 1)) {
                        lbl_numClassScore.setText(String.valueOf(numeracy_ClassScoreValue));
                    } else {
                        lbl_numClassScore.setText("n/a");
                    }

                    float numeracyScore = numeracy + numeracy_ClassScoreValue;
                    if (!(numeracyScore < 1)) {
                        lbl_numeracy.setText(String.valueOf(numeracyScore));
                    } else {
                        lbl_numeracy.setText("n/a");
                    }


                    //CREATIVE ART
                    float cARt = rs.getFloat(19);
                    if (!(cARt < 1)) {
                        lbl_artExamScore.setText(String.valueOf(cARt));
                    } else {
                        lbl_artExamScore.setText("n/a");
                    }

                    float cARt_ClassScoreValue = rs.getFloat(19);
                    if (!(cARt_ClassScoreValue < 1)) {
                        lbl_artClassScore.setText(String.valueOf(cARt_ClassScoreValue));
                    } else {
                        lbl_artClassScore.setText("n/a");
                    }

                    float artScore = cARt + cARt_ClassScoreValue;
                    if (!(artScore < 1)) {
                        lbl_cArt.setText(String.valueOf(artScore));
                    } else {
                        lbl_cArt.setText("n/a");
                    }


                    //LANGUAGE AND LITERACY
                    float literacy = rs.getFloat(20);
                    if (!(literacy < 1)) {
                        lbl_literacyExamScore.setText(String.valueOf(literacy));
                    } else {
                        lbl_literacyExamScore.setText("n/a");
                    }

                    float literacy_ClassScoreValue = rs.getFloat(20);
                    if (!(literacy_ClassScoreValue < 1)) {
                        lbl_literacyClassScore.setText(String.valueOf(literacy_ClassScoreValue));
                    } else {
                        lbl_literacyClassScore.setText("n/a");
                    }

                    float literacyScore = literacy + literacy_ClassScoreValue;
                    if (!(literacyScore < 1)) {
                        lbl_languageLiteracy.setText(String.valueOf(literacyScore));
                    } else {
                        lbl_languageLiteracy.setText("n/a");
                    }


                    //CITIZENSHIP EDUCATION
                    float citizenshipEducatioin = rs.getFloat(21);
                    if (!(citizenshipEducatioin < 1)) {
                        lbl_citizenExamScore.setText(String.valueOf(citizenshipEducatioin));
                    } else {
                        lbl_citizenExamScore.setText("n/a");
                    }

                    float citizenship_ClassScoreValue = rs.getFloat(22);
                    if (!(citizenship_ClassScoreValue < 1)) {
                        lbl_citizenClassScore.setText(String.valueOf(citizenship_ClassScoreValue));
                    } else {
                        lbl_citizenClassScore.setText("n/a");
                    }

                    float citizenshipScore = citizenshipEducatioin + citizenship_ClassScoreValue;
                    if (!(citizenshipScore < 1)) {
                        lbl_cEcuation.setText(String.valueOf(citizenshipScore));
                    } else {
                        lbl_cEcuation.setText("n/a");
                    }


                    //ENVIRONMENTAL STUDIES
                    float environStudies = rs.getFloat(23);
                    if (!(environStudies < 1)) {
                        lbl_envExamScore.setText(String.valueOf(environStudies));
                    } else {
                        lbl_envExamScore.setText("n/a");
                    }

                    float environstudies_ClassScoreValue = rs.getFloat(24);
                    if (!(environstudies_ClassScoreValue < 1)) {
                        lbl_envClassScore.setText(String.valueOf(environstudies_ClassScoreValue));
                    } else {
                        lbl_envClassScore.setText("n/a");
                    }

                    float eStudiesScore = environStudies + environstudies_ClassScoreValue;
                    if (!(eStudiesScore < 1)) {
                        lbl_eStudies.setText(String.valueOf(eStudiesScore));
                    } else {
                        lbl_eStudies.setText("n/a");
                    }


                } else {
                    lbl_math.setText("");
                    lbl_english.setText("");
                    lbl_science.setText("");
                    lbl_rme.setText("");
                    lbl_fante.setText("");
                    lbl_social.setText("");
                    lbl_bdt.setText("");
                    lbl_numeracy.setText("");
                    lbl_cArt.setText("");
                    lbl_languageLiteracy.setText("");
                    lbl_cEcuation.setText("");
                    lbl_eStudies.setText("");
                    lbl_ict.setText("");
                    lbl_mathClassScore.setText("");
                    lbl_engClassScore.setText("");
                    lbl_sciClassScore.setText("");
                    lbl_rmeClassScore.setText("");
                    lbl_FanteClassScore.setText("");
                    lbl_socialClassScore.setText("");
                    lbl_bdtClassScore.setText("");
                    lbl_numClassScore.setText("");
                    lbl_artClassScore.setText("");
                    lbl_literacyClassScore.setText("");
                    lbl_citizenClassScore.setText("");
                    lbl_envClassScore.setText("");
                    lbl_ictClassScore.setText("");
                    lbl_mathExamScore.setText("");
                    lbl_engExamScore.setText("");
                    lbl_sciExamScore.setText("");
                    lbl_rmeExamScore.setText("");
                    lbl_fanteExamScore.setText("");
                    lbl_socialExamScore.setText("");
                    lbl_bdtExamScore.setText("");
                    lbl_numExamScore.setText("");
                    lbl_artExamScore.setText("");
                    lbl_literacyExamScore.setText("");
                    lbl_citizenExamScore.setText("");
                    lbl_envExamScore.setText("");
                    lbl_ictExamScore.setText("");

                    showAlert("Sorry!", "No record found", null);
                }
            }

            lbl_class.setText(recordClass);
            lbl_term.setText(term);


        } catch (SQLException e) {
            showAlert("SQLException!", "Couldn't load exam result", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }

    }

    public void showJHSresult(StudentInfo_Data id) throws SQLException {

        try {
            String sID = id.getID();
            this.result = FXCollections.observableArrayList();
            String examReport_qurerry = "SELECT Record.Record_id,Record.Surname,Record.FirstName,Record.Class,Record.Term,Record.Student_id,jhsSubjectTable.Mathematics,jhsSubjectTable.MathClassScore,jhsSubjectTable.English,jhsSubjectTable.EnglishClassScore,jhsSubjectTable.Science,jhsSubjectTable.ScienceClassScore,jhsSubjectTable.RME,jhsSubjectTable.RmeClassScore,jhsSubjectTable.Fante,jhsSubjectTable.FanteClassScore,jhsSubjectTable.SocialStudies,jhsSubjectTable.SStudiesClassScore,jhsSubjectTable.BDT,jhsSubjectTable.BdtClassScore,jhsSubjectTable.ICT,jhsSubjectTable.IctClassScore,jhsSubjectTable.Numeracy,jhsSubjectTable.NumeracyClassScore,jhsSubjectTable.CreativeArt,jhsSubjectTable.CartClassScore,jhsSubjectTable.Language_Literacy,jhsSubjectTable.LiteracyClassScore,jhsSubjectTable.CitizenshipEducation,jhsSubjectTable.CeduClassScore,jhsSubjectTable.EnvironmentalStudies,jhsSubjectTable.eEduClassScore,jhsSubjectTable_id FROM Record INNER JOIN jhsSubjectTable ON Record.Record_id=jhsSubjectTable.Record_id WHERE Record.Student_id = " + sID + " AND jhsSubjectTable.StudentID = " + sID + " ORDER BY Record.Record_id";
            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(examReport_qurerry);

            while (rs.next()) {
                this.result.add(new ExamRecordData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25), rs.getString(26), rs.getString(27), rs.getString(28), rs.getString(29), rs.getString(30), rs.getString(31), rs.getString(32), rs.getString(33)));
            }

            this.column_RecordId_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("record_id"));
            this.column_sName_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("surname"));
            this.column_fName_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("firstName"));
            this.column_Class_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("Clas"));
            this.column_Term_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("term"));
            this.column_StudentID_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("student_id"));
            this.column_Math_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("mathematics"));
            this.column_maths_cs_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("mathematics_cs"));
            this.column_Eng_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("english"));
            this.column_eng_cs_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("english_cs"));
            this.column_Sci_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("science"));
            this.column_sci_cs_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("science_cs"));
            this.column_RME_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("rme"));
            this.column_rme_cs_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("rme_cs"));
            this.column_Fante_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("fante"));
            this.column_fante_cs_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("fante_cs"));
            this.column_SocialStudies_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("s_studies"));
            this.column_sStud_cs_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("s_studies_cs"));
            this.column_BDT_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("bdt"));
            this.column_bdt_cs_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("bdt_cs"));
            this.column_ICT_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("ict"));
            this.column_ICT_cs_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("ict_cs"));
            /*this.column_Numeracy.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("numeracy"));
            this.column_numeracy_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("numeracy_cs"));
            this.column_CreativeArt.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("c_art"));
            this.column_cArt_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("c_art_cs"));
            this.column_L_iterature.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("l_literacy"));
            this.column_Literacy_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("l_literacy_cs"));
            this.column_Citizenship_EDU.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("c_education"));
            this.column_cEdu_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("c_education_cs"));
            this.column_Environmental_Stud.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("e_studies"));
            this.column_eStud_cs.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("e_studies_cs"));*/
            this.column_SubjectID_JHS.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("subject_id"));

            this.JHSresultTable.setItems(null);
            this.JHSresultTable.setItems(result);

        } catch (SQLException e) {
            showAlert("SQLException!", "Couldn't load exam result", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }
        //search.setText("");
    }


    public void showPrimaryResult(StudentInfo_Data id) throws SQLException {
        try {
            String sID = id.getID();
            this.result = FXCollections.observableArrayList();
            String examReport_qurerry = "SELECT Record.Record_id,Record.Surname,Record.FirstName,Record.Class,Record.Term,Record.Student_id,Subject.Mathematics,Subject.MathClassScore,Subject.English,Subject.EnglishClassScore,Subject.Science,Subject.ScienceClassScore,Subject.RME,Subject.RmeClassScore,Subject.Fante,Subject.FanteClassScore,Subject.SocialStudies,Subject.SStudiesClassScore,Subject.BDT,Subject.BdtClassScore,Subject.ICT,Subject.IctClassScore,Subject.Numeracy,Subject.NumeracyClassScore,Subject.CreativeArt,Subject.CartClassScore,Subject.Language_Literacy,Subject.LiteracyClassScore,Subject.CitizenshipEducation,Subject.CeduClassScore,Subject.EnvironmentalStudies,Subject.eEduClassScore,Subject.StudentID FROM Record INNER JOIN Subject ON Record.Record_id=Subject.Record_id WHERE Record.Student_id = " + sID + " AND Subject.StudentID = " + sID + " ORDER BY Record.Record_id";
            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(examReport_qurerry);

            while (rs.next()) {
                this.result.add(new ExamRecordData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25), rs.getString(26), rs.getString(27), rs.getString(28), rs.getString(29), rs.getString(30), rs.getString(31), rs.getString(32), rs.getString(33)));
            }

            this.column_RecordId1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("record_id"));
            this.column_sName1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("surname"));
            this.column_fName1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("firstName"));
            this.column_Class1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("Clas"));
            this.column_Term1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("term"));
            this.column_StudentID1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("student_id"));
            this.column_Math1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("mathematics"));
            this.column_maths_cs1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("mathematics_cs"));
            this.column_Eng1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("english"));
            this.column_eng_cs1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("english_cs"));
            this.column_Sci1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("science"));
            this.column_sci_cs1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("science_cs"));
            this.column_RME1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("rme"));
            this.column_rme_cs1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("rme_cs"));
            this.column_Fante1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("fante"));
            this.column_fante_cs1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("fante_cs"));
            this.column_SocialStudies1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("s_studies"));
            this.column_sStud_cs1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("s_studies_cs"));
            this.column_BDT1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("bdt"));
            this.column_bdt_cs1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("bdt_cs"));
            this.column_ICT1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("ict"));
            this.column_ICT_cs1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("ict_cs"));
            this.column_Numeracy1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("numeracy"));
            this.column_numeracy_cs1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("numeracy_cs"));
            this.column_CreativeArt1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("c_art"));
            this.column_cArt_cs1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("c_art_cs"));
            this.column_L_iterature1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("l_literacy"));
            this.column_Literacy_cs1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("l_literacy_cs"));
            this.column_Citizenship_EDU1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("c_education"));
            this.column_cEdu_cs1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("c_education_cs"));
            this.column_Environmental_Stud1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("e_studies"));
            this.column_eStud_cs1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("e_studies_cs"));
            this.column_SubjectID1.setCellValueFactory(new PropertyValueFactory<ExamRecordData, String>("subject_id"));

            this.PRIMARYresultTable.setItems(null);
            this.PRIMARYresultTable.setItems(result);

        } catch (SQLException e) {
            showAlert("SQLException!", "Couldn't load exam result", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }
        //search.setText("");
    }


    public void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
