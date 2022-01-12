package examreport;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.SqliteConnection;
import studentInfo.StudentInfo_Data;

import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class JHS_examEntryController implements Initializable {

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
    private Text txt_REcord;

    @FXML
    private JFXComboBox<String> combo_term;

    @FXML
    public JFXButton btn_Add;

    @FXML
    public JFXButton btn_Save;

    @FXML
    private Text txt_recordID;

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
    private TextField txt_mathClassSscore;

    @FXML
    private TextField txt_engClassScore;

    @FXML
    private TextField txt_rmeClassScore;

    @FXML
    private TextField txt_sciClassScore;

    @FXML
    private TextField textField_ict;

    @FXML
    private TextField txt_ictClassScore;

    @FXML
    private TextField textField_bdt;

    @FXML
    private TextField txt_bdtClassScore;

    @FXML
    private TextField textField_sStudies;

    @FXML
    private TextField txt_sStudClassScore;

    @FXML
    private TextField textField_fante;

    @FXML
    private TextField txt_fanteClassScore;


    ObservableList<String> Term = FXCollections.observableArrayList("ONE", "TWO", "THREE");
    ObservableList<String> grade_A_part = FXCollections.observableArrayList();
    ObservableList<String> grade_B_part = FXCollections.observableArrayList();

    int genKey;
    String recordTerm ;
    String studentClass ;
    String StudentId;

    Connection connection;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    private double Xoffset = 0;
    private double Yoffset = 0;

     private int grade_1_A,grade_1_B,grade_2_A,grade_2_B,grade_3_A,grade_3_B,grade_4_A,grade_4_B,grade_5_A,grade_5_B,grade_6_A,grade_6_B,grade_7_A,grade_7_B,grade_8_A,grade_8_B,grade_9;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
      /*  grade_A_part.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                System.out.println("list changed");
                System.out.println(grade_A_part.size());


            }
        });



        grade_B_part.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                System.out.println("part b changed");
                System.out.println(grade_B_part.size());
            }
        });*/

        this.combo_term.setItems(Term);
        try {
            pullGrades();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        btn_Add.setOnAction(event -> {


            try {
                String termValue = combo_term.getValue();
                if (termValue.isEmpty()){
                   //do noting

                }
                else {
                    try {
                        insertIntoRecord();


                        showAlert("INFORMATION","Successful!","Result has been added");

                        clearEntry();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                showAlert("Error",null,"Please choose Term/one or more fields required ");
                e.printStackTrace();
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

    private void pullGrades()throws SQLException{
        String levelCondition = "JHS";
        String grade_A_Querry = "SELECT Range FROM PreferencesTable WHERE Level='"+levelCondition+"'";
        String grade_B_Querry = "SELECT Range_b FROM PreferencesTable WHERE Level='"+levelCondition+"'";

        try {
            connection = SqliteConnection.Connector();
            rs = connection.createStatement().executeQuery(grade_A_Querry);
            while (rs.next()){
                grade_A_part.add(rs.getString(1));
            }

        } catch (SQLException e) {
            showAlert("SQLException","Grade",e.getLocalizedMessage());
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
            connection = SqliteConnection.Connector();
            rs = connection.createStatement().executeQuery(grade_B_Querry);
            while (rs.next()){
                grade_B_part.add(rs.getString(1));
            }

        } catch (SQLException e) {
            showAlert("SQLException","Grade",e.getLocalizedMessage());
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

    private void insertIntoSubject() throws SQLException {
        DateFormat year = new SimpleDateFormat("yyyy");
        java.util.Date d = new Date();

        String subjectQuerry = "INSERT INTO jhsSubjectTable(jhsSubjectTable_id,Mathematics,MathClassScore,English,EnglishClassScore,Science,ScienceClassScore,RME,RmeClassScore,Fante,FanteClassScore,SocialStudies,SStudiesClassScore,BDT,BdtClassScore,ICT,IctClassScore,Record_id,Term,Class,TotalMarks,StudentID,Year) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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


            pstmt.setString(18, String.valueOf(genKey));
            pstmt.setString(19, recordTerm);
            pstmt.setString(20, studentClass);

            float math = Float.valueOf(textField_math.getText());
            float eng = Float.valueOf(textField_eng.getText());
            float science = Float.valueOf(textField_intScience.getText());
            float rme = Float.valueOf(textField_rme.getText());
            float fante = Float.valueOf(textField_fante.getText());
            float sStudies = Float.valueOf(textField_sStudies.getText());
            float bdt = Float.valueOf(textField_bdt.getText());
            float ict = Float.valueOf(textField_ict.getText());


            float mathClassScoreValue = Float.valueOf(txt_mathClassSscore.getText());
            float engClassScoreValue = Float.valueOf(txt_engClassScore.getText());
            float scienceClassScoreValue = Float.valueOf(txt_sciClassScore.getText());
            float rmeClassScoreValue = Float.valueOf(txt_rmeClassScore.getText());
            float fanteClassScoreValue = Float.valueOf(txt_fanteClassScore.getText());
            float sStudiesClassScoreValue = Float.valueOf(txt_sStudClassScore.getText());
            float bdtClassScoreValue = Float.valueOf(txt_bdtClassScore.getText());
            float ictClassScoreValue = Float.valueOf(txt_ictClassScore.getText());

            float math_Total,eng_Total,sci_Total,rme_Total,fante_Total,social_Total,bdt_Total,ict_Total;
            int math_Grade,eng_Grade,sci_Grade,rme_Grade,fante_Grde,social_Grade,bdt_Grade,ict_Grade;

            //MATH GRADE CALCULATION
            math_Total = math + mathClassScoreValue;
            if (math_Total >= grade_1_A){
                math_Grade = 1;

            }
            else if ((math_Total >= grade_2_A) && (math_Total <=grade_2_B)){
                math_Grade = 2;
            }
            else if ((math_Total >= grade_3_A) && (math_Total <=grade_3_B)){
                math_Grade = 3;
            }
            else if ((math_Total >= grade_4_A) && (math_Total <=grade_4_B)){
                math_Grade = 4;
            }
            else if ((math_Total >= grade_5_A) && (math_Total <=grade_5_B)){
                math_Grade = 5;
            }
            else if ((math_Total >= grade_6_A) && (math_Total <=grade_6_B)){
                math_Grade = 6;
            }
            else if ((math_Total >= grade_7_A) && (math_Total <=grade_7_B)){
                math_Grade = 7;
            }
            else if ((math_Total >= grade_8_A) && (math_Total <=grade_8_B)){
                math_Grade = 8;
            }
            else {
                math_Grade = 9;
            }


            //ENGLISH GRADE CALCULATION
            eng_Total = eng + engClassScoreValue;

            if (eng_Total >= grade_1_A){
                eng_Grade = 1;

            }
            else if ((eng_Total >= grade_2_A) && (eng_Total <=grade_2_B)){
                eng_Grade = 2;
            }
            else if ((eng_Total >= grade_3_A) && (eng_Total <=grade_3_B)){
                eng_Grade = 3;
            }
            else if ((eng_Total >= grade_4_A) && (eng_Total <=grade_4_B)){
                eng_Grade = 4;
            }
            else if ((eng_Total >= grade_5_A) && (eng_Total <=grade_5_B)){
                eng_Grade = 5;
            }
            else if ((eng_Total >= grade_6_A) && (eng_Total <=grade_6_B)){
                eng_Grade = 6;
            }
            else if ((eng_Total >= grade_7_A) && (eng_Total <=grade_7_B)){
                eng_Grade = 7;
            }
            else if ((eng_Total >= grade_8_A) && (eng_Total <=grade_8_B)){
                eng_Grade = 8;
            }
            else {
                eng_Grade = 9;
            }


            //SCIENCE GRADE CALCULATION
            sci_Total = science + scienceClassScoreValue;

            if (sci_Total >= grade_1_A){
                sci_Grade = 1;

            }
            else if ((sci_Total >= grade_2_A) && (sci_Total <=grade_2_B)){
                sci_Grade = 2;
            }
            else if ((sci_Total >= grade_3_A) && (sci_Total <=grade_3_B)){
                sci_Grade = 3;
            }
            else if ((sci_Total >= grade_4_A) && (sci_Total <=grade_4_B)){
                sci_Grade = 4;
            }
            else if ((sci_Total >= grade_5_A) && (sci_Total <=grade_5_B)){
                sci_Grade = 5;
            }
            else if ((sci_Total >= grade_6_A) && (sci_Total <=grade_6_B)){
                sci_Grade = 6;
            }
            else if ((sci_Total >= grade_7_A) && (sci_Total <=grade_7_B)){
                sci_Grade = 7;
            }
            else if ((sci_Total >= grade_8_A) && (sci_Total <=grade_8_B)){
                sci_Grade = 8;
            }
            else {
                sci_Grade = 9;
            }


            //RME GRADE CALCULATION
            rme_Total = rme + rmeClassScoreValue;

            if (rme_Total >= grade_1_A){
                rme_Grade = 1;

            }
            else if ((rme_Total >= grade_2_A) && (rme_Total <=grade_2_B)){
                rme_Grade = 2;
            }
            else if ((rme_Total >= grade_3_A) && (rme_Total <=grade_3_B)){
                rme_Grade = 3;
            }
            else if ((rme_Total >= grade_4_A) && (rme_Total <=grade_4_B)){
                rme_Grade = 4;
            }
            else if ((rme_Total >= grade_5_A) && (rme_Total <=grade_5_B)){
                rme_Grade = 5;
            }
            else if ((rme_Total >= grade_6_A) && (rme_Total <=grade_6_B)){
                rme_Grade = 6;
            }
            else if ((rme_Total >= grade_7_A) && (rme_Total <=grade_7_B)){
                rme_Grade = 7;
            }
            else if ((rme_Total >= grade_8_A) && (rme_Total <=grade_8_B)){
                rme_Grade = 8;
            }
            else {
                rme_Grade = 9;
            }


            //FANTE GRADE CALCULATION
            fante_Total = fante + fanteClassScoreValue;

            if (fante_Total >= grade_1_A){
                fante_Grde = 1;

            }
            else if ((fante_Total >= grade_2_A) && (fante_Total <=grade_2_B)){
                fante_Grde = 2;
            }
            else if ((fante_Total >= grade_3_A) && (fante_Total <=grade_3_B)){
                fante_Grde = 3;
            }
            else if ((fante_Total >= grade_4_A) && (fante_Total <=grade_4_B)){
                fante_Grde = 4;
            }
            else if ((fante_Total >= grade_5_A) && (fante_Total <=grade_5_B)){
                fante_Grde = 5;
            }
            else if ((fante_Total >= grade_6_A) && (fante_Total <=grade_6_B)){
                fante_Grde = 6;
            }
            else if ((fante_Total >= grade_7_A) && (fante_Total <=grade_7_B)){
                fante_Grde = 7;
            }
            else if ((fante_Total >= grade_8_A) && (fante_Total <=grade_8_B)){
                fante_Grde = 8;
            }
            else {
                fante_Grde = 9;
            }


            //SOCIAL STUDIES GRADE CALCULATION
            social_Total = sStudies + sStudiesClassScoreValue;

            if (social_Total >= grade_1_A){
                social_Grade = 1;

            }
            else if ((social_Total >= grade_2_A) && (social_Total <=grade_2_B)){
                social_Grade = 2;
            }
            else if ((social_Total >= grade_3_A) && (social_Total <=grade_3_B)){
                social_Grade = 3;
            }
            else if ((social_Total >= grade_4_A) && (social_Total <=grade_4_B)){
                social_Grade = 4;
            }
            else if ((social_Total >= grade_5_A) && (social_Total <=grade_5_B)){
                social_Grade = 5;
            }
            else if ((social_Total >= grade_6_A) && (social_Total <=grade_6_B)){
                social_Grade = 6;
            }
            else if ((social_Total >= grade_7_A) && (social_Total <=grade_7_B)){
                social_Grade = 7;
            }
            else if ((social_Total >= grade_8_A) && (social_Total <=grade_8_B)){
                social_Grade = 8;
            }
            else {
                social_Grade = 9;
            }


            //BDT GRADE CALCULATION
            bdt_Total = bdt + bdtClassScoreValue;

            if (bdt_Total >= grade_1_A){
                bdt_Grade = 1;

            }
            else if ((bdt_Total >= grade_2_A) && (bdt_Total <=grade_2_B)){
                bdt_Grade = 2;
            }
            else if ((bdt_Total >= grade_3_A) && (bdt_Total <=grade_3_B)){
                bdt_Grade = 3;
            }
            else if ((bdt_Total >= grade_4_A) && (bdt_Total <=grade_4_B)){
                bdt_Grade = 4;
            }
            else if ((bdt_Total >= grade_5_A) && (bdt_Total <=grade_5_B)){
                bdt_Grade = 5;
            }
            else if ((bdt_Total >= grade_6_A) && (bdt_Total <=grade_6_B)){
                bdt_Grade = 6;
            }
            else if ((bdt_Total >= grade_7_A) && (bdt_Total <=grade_7_B)){
                bdt_Grade = 7;
            }
            else if ((bdt_Total >= grade_8_A) && (bdt_Total <=grade_8_B)){
                bdt_Grade = 8;
            }
            else {
                bdt_Grade = 9;
            }


            //ICT GRADE CALCULATION
            ict_Total = ict + ictClassScoreValue;

            if (ict_Total >= grade_1_A){
                ict_Grade = 1;

            }
            else if ((ict_Total >= grade_2_A) && (ict_Total <=grade_2_B)){
                ict_Grade = 2;
            }
            else if ((ict_Total >= grade_3_A) && (ict_Total <=grade_3_B)){
                ict_Grade = 3;
            }
            else if ((ict_Total >= grade_4_A) && (ict_Total <=grade_4_B)){
                ict_Grade = 4;
            }
            else if ((ict_Total >= grade_5_A) && (ict_Total <=grade_5_B)){
                ict_Grade = 5;
            }
            else if ((ict_Total >= grade_6_A) && (ict_Total <=grade_6_B)){
                ict_Grade = 6;
            }
            else if ((ict_Total >= grade_7_A) && (ict_Total <=grade_7_B)){
                ict_Grade = 7;
            }
            else if ((ict_Total >= grade_8_A) && (ict_Total <=grade_8_B)){
                ict_Grade = 8;
            }
            else {
                ict_Grade = 9;
            }





            float total = math_Grade + eng_Grade + sci_Grade + rme_Grade + fante_Grde + social_Grade + bdt_Grade + ict_Grade;
            String TotalMarks = String.valueOf(total);


            pstmt.setString(21,TotalMarks );
            pstmt.setString(22,StudentId );
            pstmt.setString(23, year.format(d));
            pstmt.execute();

            showAlert("Record ","Successful","Record added successfully");


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
        String studentId =  txt_studentID.getText();
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

    private void updateExamsRecord()throws SQLException{
        connection = SqliteConnection.Connector();

        try {
            String recordID = txt_recordID.getText();
            String updateRecordQuerry = "UPDATE Record SET Term=? WHERE Record_id='"+recordID+"'";

            pstmt = connection.prepareStatement(updateRecordQuerry);
            pstmt.setString(1,combo_term.getValue());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            showAlert("Error!", "Could not update record", ""+e.getLocalizedMessage()+"");
            e.printStackTrace();

        } finally {
            pstmt.close();
        }

        try {
            String subjectID = txt_subjectID.getText();
            String updateSubjectQuerry = "UPDATE jhsSubjectTable SET Mathematics=?,MathClassScore=?,English=?,EnglishClassScore=?,Science=?,ScienceClassScore=?,RME=?,RmeClassScore=?,Fante=?,FanteClassScore=?,SocialStudies=?,SStudiesClassScore=?,BDT=?,BdtClassScore=?,ICT=?,IctClassScore=?,Term=?,Class=?,TotalMarks=? WHERE jhsSubjectTable_id='"+subjectID+"'";


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


            recordTerm = combo_term.getValue();
            pstmt.setString(17, recordTerm);
            studentClass = txt_class.getText();
            pstmt.setString(18, studentClass);

            float math = Float.parseFloat(textField_math.getText());
            float eng = Float.parseFloat(textField_eng.getText());
            float science = Float.parseFloat(textField_intScience.getText());
            float rme = Float.parseFloat(textField_rme.getText());
            float fante = Float.parseFloat(textField_fante.getText());
            float sStudies = Float.parseFloat(textField_sStudies.getText());
            float bdt = Float.parseFloat(textField_bdt.getText());
            float ict = Float.parseFloat(textField_ict.getText());


            float mathClassScoreValue = Float.parseFloat(txt_mathClassSscore.getText());
            float engClassScoreValue = Float.parseFloat(txt_engClassScore.getText());
            float scienceClassScoreValue = Float.parseFloat(txt_sciClassScore.getText());
            float rmeClassScoreValue = Float.parseFloat(txt_rmeClassScore.getText());
            float fanteClassScoreValue = Float.parseFloat(txt_fanteClassScore.getText());
            float sStudiesClassScoreValue = Float.parseFloat(txt_sStudClassScore.getText());
            float bdtClassScoreValue = Float.parseFloat(txt_bdtClassScore.getText());
            float ictClassScoreValue = Float.parseFloat(txt_ictClassScore.getText());

            float math_Total,eng_Total,sci_Total,rme_Total,fante_Total,social_Total,bdt_Total,ict_Total;
            int math_Grade,eng_Grade,sci_Grade,rme_Grade,fante_Grde,social_Grade,bdt_Grade,ict_Grade;

            //MATH GRADE CALCULATION
            math_Total = math + mathClassScoreValue;
            if (math_Total >= grade_1_A){
                math_Grade = 1;

            }
            else if ((math_Total >= grade_2_A) && (math_Total <=grade_2_B)){
                math_Grade = 2;
            }
            else if ((math_Total >= grade_3_A) && (math_Total <=grade_3_B)){
                math_Grade = 3;
            }
            else if ((math_Total >= grade_4_A) && (math_Total <=grade_4_B)){
                math_Grade = 4;
            }
            else if ((math_Total >= grade_5_A) && (math_Total <=grade_5_B)){
                math_Grade = 5;
            }
            else if ((math_Total >= grade_6_A) && (math_Total <=grade_6_B)){
                math_Grade = 6;
            }
            else if ((math_Total >= grade_7_A) && (math_Total <=grade_7_B)){
                math_Grade = 7;
            }
            else if ((math_Total >= grade_8_A) && (math_Total <=grade_8_B)){
                math_Grade = 8;
            }
            else {
                math_Grade = 9;
            }


            //ENGLISH GRADE CALCULATION
            eng_Total = eng + engClassScoreValue;

            if (eng_Total >= grade_1_A){
                eng_Grade = 1;

            }
            else if ((eng_Total >= grade_2_A) && (eng_Total <=grade_2_B)){
                eng_Grade = 2;
            }
            else if ((eng_Total >= grade_3_A) && (eng_Total <=grade_3_B)){
                eng_Grade = 3;
            }
            else if ((eng_Total >= grade_4_A) && (eng_Total <=grade_4_B)){
                eng_Grade = 4;
            }
            else if ((eng_Total >= grade_5_A) && (eng_Total <=grade_5_B)){
                eng_Grade = 5;
            }
            else if ((eng_Total >= grade_6_A) && (eng_Total <=grade_6_B)){
                eng_Grade = 6;
            }
            else if ((eng_Total >= grade_7_A) && (eng_Total <=grade_7_B)){
                eng_Grade = 7;
            }
            else if ((eng_Total >= grade_8_A) && (eng_Total <=grade_8_B)){
                eng_Grade = 8;
            }
            else {
                eng_Grade = 9;
            }


            //SCIENCE GRADE CALCULATION
            sci_Total = science + scienceClassScoreValue;

            if (sci_Total >= grade_1_A){
                sci_Grade = 1;

            }
            else if ((sci_Total >= grade_2_A) && (sci_Total <=grade_2_B)){
                sci_Grade = 2;
            }
            else if ((sci_Total >= grade_3_A) && (sci_Total <=grade_3_B)){
                sci_Grade = 3;
            }
            else if ((sci_Total >= grade_4_A) && (sci_Total <=grade_4_B)){
                sci_Grade = 4;
            }
            else if ((sci_Total >= grade_5_A) && (sci_Total <=grade_5_B)){
                sci_Grade = 5;
            }
            else if ((sci_Total >= grade_6_A) && (sci_Total <=grade_6_B)){
                sci_Grade = 6;
            }
            else if ((sci_Total >= grade_7_A) && (sci_Total <=grade_7_B)){
                sci_Grade = 7;
            }
            else if ((sci_Total >= grade_8_A) && (sci_Total <=grade_8_B)){
                sci_Grade = 8;
            }
            else {
                sci_Grade = 9;
            }


            //RME GRADE CALCULATION
            rme_Total = rme + rmeClassScoreValue;

            if (rme_Total >= grade_1_A){
                rme_Grade = 1;

            }
            else if ((rme_Total >= grade_2_A) && (rme_Total <=grade_2_B)){
                rme_Grade = 2;
            }
            else if ((rme_Total >= grade_3_A) && (rme_Total <=grade_3_B)){
                rme_Grade = 3;
            }
            else if ((rme_Total >= grade_4_A) && (rme_Total <=grade_4_B)){
                rme_Grade = 4;
            }
            else if ((rme_Total >= grade_5_A) && (rme_Total <=grade_5_B)){
                rme_Grade = 5;
            }
            else if ((rme_Total >= grade_6_A) && (rme_Total <=grade_6_B)){
                rme_Grade = 6;
            }
            else if ((rme_Total >= grade_7_A) && (rme_Total <=grade_7_B)){
                rme_Grade = 7;
            }
            else if ((rme_Total >= grade_8_A) && (rme_Total <=grade_8_B)){
                rme_Grade = 8;
            }
            else {
                rme_Grade = 9;
            }


            //FANTE GRADE CALCULATION
            fante_Total = fante + fanteClassScoreValue;

            if (fante_Total >= grade_1_A){
                fante_Grde = 1;

            }
            else if ((fante_Total >= grade_2_A) && (fante_Total <=grade_2_B)){
                fante_Grde = 2;
            }
            else if ((fante_Total >= grade_3_A) && (fante_Total <=grade_3_B)){
                fante_Grde = 3;
            }
            else if ((fante_Total >= grade_4_A) && (fante_Total <=grade_4_B)){
                fante_Grde = 4;
            }
            else if ((fante_Total >= grade_5_A) && (fante_Total <=grade_5_B)){
                fante_Grde = 5;
            }
            else if ((fante_Total >= grade_6_A) && (fante_Total <=grade_6_B)){
                fante_Grde = 6;
            }
            else if ((fante_Total >= grade_7_A) && (fante_Total <=grade_7_B)){
                fante_Grde = 7;
            }
            else if ((fante_Total >= grade_8_A) && (fante_Total <=grade_8_B)){
                fante_Grde = 8;
            }
            else {
                fante_Grde = 9;
            }


            //SOCIAL STUDIES GRADE CALCULATION
            social_Total = sStudies + sStudiesClassScoreValue;

            if (social_Total >= grade_1_A){
                social_Grade = 1;

            }
            else if ((social_Total >= grade_2_A) && (social_Total <=grade_2_B)){
                social_Grade = 2;
            }
            else if ((social_Total >= grade_3_A) && (social_Total <=grade_3_B)){
                social_Grade = 3;
            }
            else if ((social_Total >= grade_4_A) && (social_Total <=grade_4_B)){
                social_Grade = 4;
            }
            else if ((social_Total >= grade_5_A) && (social_Total <=grade_5_B)){
                social_Grade = 5;
            }
            else if ((social_Total >= grade_6_A) && (social_Total <=grade_6_B)){
                social_Grade = 6;
            }
            else if ((social_Total >= grade_7_A) && (social_Total <=grade_7_B)){
                social_Grade = 7;
            }
            else if ((social_Total >= grade_8_A) && (social_Total <=grade_8_B)){
                social_Grade = 8;
            }
            else {
                social_Grade = 9;
            }


            //BDT GRADE CALCULATION
            bdt_Total = bdt + bdtClassScoreValue;

            if (bdt_Total >= grade_1_A){
                bdt_Grade = 1;

            }
            else if ((bdt_Total >= grade_2_A) && (bdt_Total <=grade_2_B)){
                bdt_Grade = 2;
            }
            else if ((bdt_Total >= grade_3_A) && (bdt_Total <=grade_3_B)){
                bdt_Grade = 3;
            }
            else if ((bdt_Total >= grade_4_A) && (bdt_Total <=grade_4_B)){
                bdt_Grade = 4;
            }
            else if ((bdt_Total >= grade_5_A) && (bdt_Total <=grade_5_B)){
                bdt_Grade = 5;
            }
            else if ((bdt_Total >= grade_6_A) && (bdt_Total <=grade_6_B)){
                bdt_Grade = 6;
            }
            else if ((bdt_Total >= grade_7_A) && (bdt_Total <=grade_7_B)){
                bdt_Grade = 7;
            }
            else if ((bdt_Total >= grade_8_A) && (bdt_Total <=grade_8_B)){
                bdt_Grade = 8;
            }
            else {
                bdt_Grade = 9;
            }


            //ICT GRADE CALCULATION
            ict_Total = ict + ictClassScoreValue;

            if (ict_Total >= grade_1_A){
                ict_Grade = 1;

            }
            else if ((ict_Total >= grade_2_A) && (ict_Total <=grade_2_B)){
                ict_Grade = 2;
            }
            else if ((ict_Total >= grade_3_A) && (ict_Total <=grade_3_B)){
                ict_Grade = 3;
            }
            else if ((ict_Total >= grade_4_A) && (ict_Total <=grade_4_B)){
                ict_Grade = 4;
            }
            else if ((ict_Total >= grade_5_A) && (ict_Total <=grade_5_B)){
                ict_Grade = 5;
            }
            else if ((ict_Total >= grade_6_A) && (ict_Total <=grade_6_B)){
                ict_Grade = 6;
            }
            else if ((ict_Total >= grade_7_A) && (ict_Total <=grade_7_B)){
                ict_Grade = 7;
            }
            else if ((ict_Total >= grade_8_A) && (ict_Total <=grade_8_B)){
                ict_Grade = 8;
            }
            else {
                ict_Grade = 9;
            }





            float total = math_Grade + eng_Grade + sci_Grade + rme_Grade + fante_Grde + social_Grade + bdt_Grade + ict_Grade;
            String TotalMarks = String.valueOf(total);
            pstmt.setString(19, TotalMarks);


            pstmt.executeUpdate();

            showAlert("Record ","Successful","Record updated successfully");

        } catch (SQLException e) {
            showAlert("Error!", "Could not update subject", ""+e.getLocalizedMessage()+"");
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

    private void clearEntry(){
        textField_bdt.setText("");

        textField_eng.setText("");

        textField_fante.setText("");
        textField_ict.setText("");
        textField_intScience.setText("");

        textField_math.setText("");
        textField_sStudies.setText("");
        textField_rme.setText("");


        txt_mathClassSscore.setText("");
        txt_engClassScore.setText("");
        txt_sciClassScore.setText("");
        txt_rmeClassScore.setText("");
        txt_fanteClassScore.setText("");
        txt_sStudClassScore.setText("");

        txt_ictClassScore.setText("");

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

        this.textField_eng.setText(record.getEnglish());

        this.textField_fante.setText(record.getFante());
        this.textField_ict.setText(record.getIct());
        this.textField_intScience.setText(record.getScience());

        this.textField_math.setText(record.getMathematics());
        this.textField_sStudies.setText(record.getS_studies());
        this.textField_rme.setText(record.getRme());



        this.txt_bdtClassScore.setText(record.getBdt_cs());

        this.txt_engClassScore.setText(record.getEnglish_cs());

        this.txt_fanteClassScore.setText(record.getFante_cs());
        this.txt_ictClassScore.setText(record.getIct_cs());
        this.txt_sciClassScore.setText(record.getScience_cs());

        this.txt_mathClassSscore.setText(record.getMathematics_cs());
        this.txt_sStudClassScore.setText(record.getS_studies_cs());
        this.txt_rmeClassScore.setText(record.getRme_cs());


    }
}
