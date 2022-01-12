package studentInfo;

import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import sample.SqliteConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PrintReport extends JFrame {
    byte[] image;
    Connection conn = SqliteConnection.Connector();
    PreparedStatement pr = null;
    ResultSet rs = null;


    public PrintReport() throws HeadlessException {

    }

    public void showTermReport(String id, String studentClass, String mathTotal, String engTotal, String sciTotal, String rmeTotal, String fanteTotal, String socialTotal,
                               String bdtTotal, String ictTotal, String numeracyTotal, String artTotal, String literacyTotal, String citizenshipTotal, String eStudiesTotal) throws JRException {


        Map param = new HashMap();
        param.put("mathTotal", mathTotal);
        param.put("engTotal", engTotal);
        param.put("sciTotal", sciTotal);
        param.put("rmeTotal", rmeTotal);
        param.put("fanteTotal", fanteTotal);
        param.put("socialTotal", socialTotal);
        param.put("bdtTotal", bdtTotal);
        param.put("ictTotal", ictTotal);
        param.put("numeracy", numeracyTotal);
        param.put("artTotal", artTotal);
        param.put("literacyTotal", literacyTotal);
        param.put("citizenshipTotal", citizenshipTotal);
        param.put("eStudiesTotal", eStudiesTotal);

        try {
            if (studentClass.equals("JHS3") || studentClass.equals("JHS2") || studentClass.equals("JHS1")) {


                JasperDesign jasperDesign = JRXmlLoader.load("src/JHSreportCard.jrxml");

                String sqlqurry = "SELECT jhsSubjectTable.StudentID, jhsSubjectTable.Class,jhsSubjectTable.Term,jhsSubjectTable.Year,jhsSubjectTable.Mathematics,jhsSubjectTable.MathClassScore,jhsSubjectTable.English,jhsSubjectTable.EnglishClassScore,jhsSubjectTable.Science,jhsSubjectTable.ScienceClassScore,jhsSubjectTable.RME,jhsSubjectTable.RmeClassScore,jhsSubjectTable.Fante,jhsSubjectTable.FanteClassScore,jhsSubjectTable.SocialStudies,jhsSubjectTable.SStudiesClassScore,jhsSubjectTable.BDT,jhsSubjectTable.BdtClassScore,jhsSubjectTable.ICT,jhsSubjectTable.IctClassScore,jhsSubjectTable.Numeracy,jhsSubjectTable.NumeracyClassScore,jhsSubjectTable.CreativeArt,jhsSubjectTable.CartClassScore,jhsSubjectTable.Language_Literacy,jhsSubjectTable.LiteracyClassScore,jhsSubjectTable.CitizenshipEducation,jhsSubjectTable.CeduClassScore,jhsSubjectTable.EnvironmentalStudies,jhsSubjectTable.eEduClassScore,jhsSubjectTable.TotalMarks,StudentData.firstName,StudentData.lastName FROM jhsSubjectTable,StudentData WHERE jhsSubjectTable.StudentID =" + id + " AND StudentData.Student_id =" + id + " ";
                JRDesignQuery jrDesignQuery = new JRDesignQuery();
                jrDesignQuery.setText(sqlqurry);
                jasperDesign.setQuery(jrDesignQuery);


                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);
                JasperViewer.viewReport(jasperPrint, false);


            } else {

                JasperDesign jasperDesign = JRXmlLoader.load("src/reportCard.jrxml");
                String sqlqurry = "SELECT \"Subject\".\"StudentID\",\n" +
                        "\t\"Subject\".\"Class\",\n" +
                        "\t\"Subject\".\"Term\",\n" +
                        "\t\"Subject\".\"Mathematics\",\n" +
                        "\t\"Subject\".\"MathClassScore\",\n" +
                        "\t\"Subject\".\"English\",\n" +
                        "\t\"Subject\".\"EnglishClassScore\",\n" +
                        "\t\"Subject\".\"Science\",\n" +
                        "\t\"Subject\".\"ScienceClassScore\",\n" +
                        "\t\"Subject\".\"RME\",\n" +
                        "\t\"Subject\".\"RmeClassScore\",\n" +
                        "\t\"Subject\".\"Fante\",\n" +
                        "\t\"Subject\".\"FanteClassScore\",\n" +
                        "\t\"Subject\".\"SocialStudies\",\n" +
                        "\t\"Subject\".\"SStudiesClassScore\",\n" +
                        "\t\"Subject\".\"BDT\",\n" +
                        "\t\"Subject\".\"BdtClassScore\",\n" +
                        "\t\"Subject\".\"ICT\",\n" +
                        "\t\"Subject\".\"IctClassScore\",\n" +
                        "\t\"Subject\".\"Numeracy\",\n" +
                        "\t\"Subject\".\"NumeracyClassScore\",\n" +
                        "\t\"Subject\".\"CreativeArt\",\n" +
                        "\t\"Subject\".\"CartClassScore\",\n" +
                        "\t\"Subject\".\"Language_Literacy\",\n" +
                        "\t\"Subject\".\"LiteracyClassScore\",\n" +
                        "\t\"Subject\".\"CitizenshipEducation\",\n" +
                        "\t\"Subject\".\"CeduClassScore\",\n" +
                        "\t\"Subject\".\"EnvironmentalStudies\",\n" +
                        "\t\"Subject\".\"eEduClassScore\",\n" +
                        "\t\"StudentData\".\"firstName\",\n" +
                        "\t\"StudentData\".\"lastName\"\n" +
                        "FROM \"Subject\",\n" +
                        "\t\"StudentData\"\n" +
                        "WHERE \"Subject\".StudentID =" + id + "\n" +
                        "AND \"StudentData\".Student_id =" + id + " ";
                JRDesignQuery jrDesignQuery = new JRDesignQuery();
                jrDesignQuery.setText(sqlqurry);
                jasperDesign.setQuery(jrDesignQuery);


                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);
                JasperViewer.viewReport(jasperPrint, false);

            }
        } catch (JRException e) {
            e.printStackTrace();
        }


    }


    public void showStudentInformation(StudentInfo_Data student) throws SQLException {
        String studentID = student.getID();
        String Imagequerry = "Select image from StudentData where Student_id = '" + studentID + "'";
        String querry = "SELECT StudentData.Student_id,StudentData.lastName, StudentData.firstName, StudentData.gender, StudentData.dob,StudentData.religion,StudentData.residence,StudentData.landmark,StudentData.class,StudentData.admission_date,StudentData.lastSchool_attended,StudentData.status,Parent.f_lastName,Parent.f_firstName,Parent.m_lastName,Parent.m_firstName,Parent.f_phone,Parent.m_phone,Parent.f_email,Parent.m_email FROM StudentData INNER JOIN Parent ON StudentData.parent_id = Parent.parent_id WHERE StudentData.Student_id ='" + studentID + "'";


        try {
            pr = conn.prepareStatement(Imagequerry);
            rs = pr.executeQuery();

            if (rs != null) {
                image = rs.getBytes(1);


                ImageIcon imageIcon = new ImageIcon(image);


                Map param = new HashMap();
                param.put("sPhoto", imageIcon.getImage());


                JasperDesign jasperDesign = JRXmlLoader.load("src/student_details.jrxml");

                JRDesignQuery jrDesignQuery = new JRDesignQuery();
                jrDesignQuery.setText(querry);
                jasperDesign.setQuery(jrDesignQuery);


                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);
                JasperViewer.viewReport(jasperPrint, false);

            } else {
                JasperDesign jasperDesign = JRXmlLoader.load("src/student_details.jrxml");
                JRDesignQuery jrDesignQuery = new JRDesignQuery();
                jrDesignQuery.setText(querry);
                jasperDesign.setQuery(jrDesignQuery);


                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
                JasperViewer.viewReport(jasperPrint, false);
            }


        } catch (JRException e) {
            showAlert("Error!", "Print:JRException", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } catch (SQLException ex) {
            showAlert("Error!", "Print:JRException", "" + ex.getLocalizedMessage() + "");
            ex.printStackTrace();
        } finally {
            pr.close();
            rs.close();
        }


    }

    public void showLogReport() throws JRException {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/log_history.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            showAlert("Error!", "Print:JRException", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        }

    }


    public void printAllMale() throws JRException {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/male_students.jrxml");

            String querry = "SELECT \"StudentData\".\"Student_id\",\n" +
                    "\t\"StudentData\".\"lastName\",\n" +
                    "\t\"StudentData\".\"firstName\",\n" +
                    "\t\"StudentData\".gender,\n" +
                    "\t\"StudentData\".class\n" +
                    "FROM \"StudentData\"\n" +
                    "WHERE \"StudentData\".gender = \"MALE\"\n" +
                    "AND \"StudentData\".class != \"COMPLETED\"\n" +
                    "AND \"StudentData\".status = \"ACTIVE\"\n" +
                    "ORDER BY class";

            JRDesignQuery jrDesignQuery = new JRDesignQuery();
            jrDesignQuery.setText(querry);
            jasperDesign.setQuery(jrDesignQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            showAlert("Error!", "Print:JRException", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        }

    }

    public void printAllFeMale() throws JRException {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/female_students.jrxml");

            String querry = "SELECT \"StudentData\".\"Student_id\",\n" +
                    "\t\"StudentData\".\"lastName\",\n" +
                    "\t\"StudentData\".\"firstName\",\n" +
                    "\t\"StudentData\".gender,\n" +
                    "\t\"StudentData\".class\n" +
                    "FROM \"StudentData\"\n" +
                    "WHERE \"StudentData\".gender = \"FEMALE\"\n" +
                    "AND \"StudentData\".status = \"ACTIVE\"\n" +
                    "AND \"StudentData\".class != \"COMPLETED\"\n" +
                    "AND \"StudentData\".status = \"ACTIVE\"\n" +
                    "ORDER BY class";

            JRDesignQuery jrDesignQuery = new JRDesignQuery();
            jrDesignQuery.setText(querry);
            jasperDesign.setQuery(jrDesignQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            showAlert("Error!", "Print:JRException", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        }

    }

    public void printActiveStudents() throws JRException {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/active.jrxml");

            String querry = "SELECT \"StudentData\".\"Student_id\",\n" +
                    "\t\"StudentData\".\"lastName\",\n" +
                    "\t\"StudentData\".\"firstName\",\n" +
                    "\t\"StudentData\".gender,\n" +
                    "\t\"StudentData\".class\n" +
                    "FROM \"StudentData\"\n" +
                    "WHERE \"StudentData\".status = \"ACTIVE\"\n" +
                    "AND \"StudentData\".class != \"COMPLETED\"\n" +
                    "AND \"StudentData\".status = \"ACTIVE\"\n" +
                    "ORDER BY class";


            JRDesignQuery jrDesignQuery = new JRDesignQuery();
            jrDesignQuery.setText(querry);
            jasperDesign.setQuery(jrDesignQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            showAlert("Error!", "Print:JRException", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        }

    }

    public void printInactiveStudents() throws JRException {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/inactive.jrxml");

            String querry = "SELECT \"StudentData\".\"Student_id\",\n" +
                    "\t\"StudentData\".\"lastName\",\n" +
                    "\t\"StudentData\".\"firstName\",\n" +
                    "\t\"StudentData\".gender,\n" +
                    "\t\"StudentData\".class\n" +
                    "FROM \"StudentData\"\n" +
                    "WHERE \"StudentData\".status = \"INACTIVE\"\n" +
                    "AND \"StudentData\".class != \"COMPLETED\"\n" +
                    "AND \"StudentData\".status = \"ACTIVE\"\n" +
                    "ORDER BY class";


            JRDesignQuery jrDesignQuery = new JRDesignQuery();
            jrDesignQuery.setText(querry);
            jasperDesign.setQuery(jrDesignQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            showAlert("Error!", "Print:JRException", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        }

    }

    public void printSelectedClass(String selectedClass, String gender) throws JRException {
        if (gender.equals("ALL")) {

            try {
                JasperDesign jasperDesign = JRXmlLoader.load("src/specific_classAll.jrxml");

                String querry = "SELECT \"StudentData\".\"Student_id\",\n" +
                        "\t\"StudentData\".\"lastName\",\n" +
                        "\t\"StudentData\".\"firstName\",\n" +
                        "\t\"StudentData\".gender,\n" +
                        "\t\"StudentData\".dob,\n" +
                        "\t\"StudentData\".class,\n" +
                        "\t\"StudentData\".status\n" +
                        "FROM \"StudentData\"\n" +
                        "WHERE \"StudentData\".class = '" + selectedClass + "'\n" +
                        "ORDER BY \"StudentData\".lastName";


                JRDesignQuery jrDesignQuery = new JRDesignQuery();
                jrDesignQuery.setText(querry);
                jasperDesign.setQuery(jrDesignQuery);

                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
                JasperViewer.viewReport(jasperPrint, false);
            } catch (JRException e) {
                showAlert("Error!", "Print:JRException", "" + e.getLocalizedMessage() + "");
                e.printStackTrace();
            }
        } else {
            try {
                JasperDesign jasperDesign = JRXmlLoader.load("src/specific_class.jrxml");

                String querry = "SELECT \"StudentData\".\"Student_id\",\n" +
                        "\t\"StudentData\".\"lastName\",\n" +
                        "\t\"StudentData\".\"firstName\",\n" +
                        "\t\"StudentData\".gender,\n" +
                        "\t\"StudentData\".dob,\n" +
                        "\t\"StudentData\".class,\n" +
                        "\t\"StudentData\".status\n" +
                        "FROM \"StudentData\"\n" +
                        "WHERE \"StudentData\".gender = '" + gender + "'\n" +
                        "AND \"StudentData\".class = '" + selectedClass + "'\n" +
                        "ORDER BY \"StudentData\".lastName";


                JRDesignQuery jrDesignQuery = new JRDesignQuery();
                jrDesignQuery.setText(querry);
                jasperDesign.setQuery(jrDesignQuery);

                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
                JasperViewer.viewReport(jasperPrint, false);
            } catch (JRException e) {
                showAlert("Error!", "Print:JRException", "" + e.getLocalizedMessage() + "");
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
