package update;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.SqliteConnection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    ObservableList<String> classList = FXCollections.observableArrayList(
            "NURSERY", "KG1", "KG2", "P1", "P2", "P3", "P4", "P5", "P6", "JHS1", "JHS2", "JHS3");
    ObservableList<String> gender = FXCollections.observableArrayList("MALE", "FEMALE");
    ObservableList<String> Status = FXCollections.observableArrayList("ACTIVE", "INACTIVE");
    PreparedStatement statement = null;
    byte[] byteImageArray;
    StackPane stackPane;
    @FXML
    private AnchorPane anchorpane_window;
    @FXML
    private ImageView image_view;
    @FXML
    private Label lbl_imagePath;
    @FXML
    private JFXTextField textField_ID;
    @FXML
    private JFXTextField textField_parentID;
    @FXML
    private JFXTextField textField_firstName;
    @FXML
    private JFXTextField textField_lastName;
    @FXML
    private JFXTextField textField_residence;
    @FXML
    private JFXTextField textField_LandMark;
    @FXML
    private JFXTextField textField_religion;
    @FXML
    private JFXTextField textField_lastSchoolAttended;

    @FXML
    private JFXButton btn_UpdateTable;
    @FXML
    private JFXButton btn_NRecord;
    @FXML
    private Label lbl_doa;

    @FXML
    private Label lbl_cYear;

    @FXML
    private Label lbl_pYear;
    @FXML
    private JFXDatePicker dob_DatePicker;
    @FXML
    private JFXButton btn_chooseImage;
    @FXML
    private JFXComboBox<String> status_combo;
    @FXML
    private JFXComboBox<String> gender_Combo;
    @FXML
    private JFXComboBox<String> class_Combo;
    @FXML
    private Label lbl_addminName;
    @FXML
    private StackPane sp_stackPane;
    @FXML


    private StudentsData selectedData;
    private Connection conn;
    private FileChooser fileChooser;
    private File file;
    private Stage stage;
    private ProgressIndicator progressIndicator;
    private FileInputStream fileInputStream;

    private double Xoffset = 0;
    private double Yoffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        this.stackPane = new StackPane();


        fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg", "*.JPG");
        FileChooser.ExtensionFilter extensionFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extensionFilterJPG, extensionFilterPNG);

        class_Combo.setItems(classList);
        gender_Combo.setItems(gender);
        status_combo.setItems(Status);

        this.progressIndicator = new ProgressIndicator(-1.0);

    }

    @FXML
    public void populateForm(StudentsData info) {
        selectedData = info;

        this.textField_ID.setText(selectedData.getID());
        this.textField_lastName.setText(selectedData.getLastName());
        this.textField_firstName.setText(selectedData.getFirstName());
        this.gender_Combo.setValue(selectedData.getGender());
        this.dob_DatePicker.getEditor().setText(selectedData.getDateOfBirth());
        this.textField_religion.setText(selectedData.getReligion());
        this.textField_residence.setText(selectedData.getResidence());
        this.textField_LandMark.setText(selectedData.getLandMark());
        this.class_Combo.setValue(selectedData.getClaSS());
        this.lbl_doa.setText(selectedData.getDateOfAdmission());
        this.textField_lastSchoolAttended.setText(selectedData.getLastSchoolAttended());
        this.lbl_cYear.setText(selectedData.getCurrYear());
        this.lbl_pYear.setText(selectedData.getPrevYear());

        byteImageArray = selectedData.getStudent_image();
        Image image1 = new Image(new ByteArrayInputStream(byteImageArray));
        this.image_view.setImage(image1);
        this.status_combo.setValue(selectedData.getStatus());

    }


    @FXML
    public void disableNewRecordButton() {
        textField_ID.setEditable(false);
        btn_NRecord.setVisible(false);
    }

    @FXML
    public void disableUpdateRecordButton() {
        textField_ID.setVisible(false);
        btn_UpdateTable.setVisible(false);
    }

    @FXML
    public void addNewRecord(ActionEvent event) {

//            String   studentId = textField_ID.getText();
        String student_lastName = textField_lastName.getText();
        String student_firstName = textField_firstName.getText();
        String student_gender = gender_Combo.getValue();
        String student_dob = dob_DatePicker.getEditor().getText();
        String student_religion = textField_religion.getText();
        String student_residence = textField_residence.getText();
        String student_landmark = textField_LandMark.getText();
        String student_class = class_Combo.getValue();
        String student_doa = lbl_doa.getText();
        String student_lastSchool = textField_lastSchoolAttended.getText();
        String student_status = status_combo.getValue();
        String current_Year = lbl_cYear.getText();


        if (
//                    studentId.isEmpty() ||
                student_lastName.isEmpty() ||
                        student_firstName.isEmpty() ||
                        student_gender.isEmpty() ||
                        student_dob.isEmpty() ||
                        student_religion.isEmpty() ||
                        student_residence.isEmpty() ||
                        student_landmark.isEmpty() ||
                        student_class.isEmpty() ||
                        student_doa.isEmpty() ||
                        student_lastSchool.isEmpty() ||
                        student_status.isEmpty() || current_Year.isEmpty()) {

            showDialog("Error", "One or more fields required!");

        }else {

            try {
                insertIntoStudentData();
                clearForm();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }


    @FXML
    private void buttonUpdateActionPerform(ActionEvent event) {

        String student_lastName = textField_lastName.getText().toUpperCase();
        String student_firstName = textField_firstName.getText().toUpperCase();
        String student_gender = gender_Combo.getValue();
        String student_dob = dob_DatePicker.getEditor().getText();
        String student_religion = textField_religion.getText().toUpperCase();
        String student_residence = textField_residence.getText().toUpperCase();
        String student_landmark = textField_LandMark.getText().toUpperCase();
        String student_class = class_Combo.getValue().toUpperCase();
        String student_doa = lbl_doa.getText();
        String student_lastSchool = textField_lastSchoolAttended.getText().toUpperCase();
        String student_status = status_combo.getValue();

        Alert confirmUpdate = new Alert(Alert.AlertType.CONFIRMATION);
        confirmUpdate.setTitle("");
        confirmUpdate.setContentText("Are you Sure to commit changes?");
        Optional<ButtonType> result = confirmUpdate.showAndWait();
        if (result.get() == ButtonType.OK) {

            if (student_lastName.isEmpty() ||
                    student_firstName.isEmpty() ||
                    student_gender.isEmpty() ||
                    student_dob.isEmpty() ||
                    student_religion.isEmpty() ||
                    student_residence.isEmpty() ||
                    student_landmark.isEmpty() ||
                    student_class.isEmpty() ||
                    student_doa.isEmpty() ||
                    student_lastSchool.isEmpty() ||
                    student_status.isEmpty()) {


                showDialog("Error", "One or more fields required!");
            }else {
                try {
                    UpdateStudentData();
                    ((Node) event.getSource()).getScene().getWindow().hide();


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


        } else {
            confirmUpdate.close();


        }
    }

    private void clearForm() {
        textField_ID.setText("");
        textField_lastName.setText("");
        textField_firstName.setText("");
        gender_Combo.setValue("");
        dob_DatePicker.getEditor().setText("");
        textField_religion.setText("");
        textField_residence.setText("");
        textField_LandMark.setText("");
        class_Combo.setValue("");
        lbl_doa.setText("");
        textField_lastSchoolAttended.setText("");
        status_combo.setValue("");
    }

    @FXML
    private void insertIntoStudentData() throws FileNotFoundException, SQLException {
        String sqlInsert = "INSERT INTO StudentData(Student_id,lastName,firstName,gender,dob,religion,residence,landmark,class,admission_date,lastSchool_attended,image,status,currentYear,parent_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        try {

            conn = SqliteConnection.Connector();
            statement = conn.prepareStatement(sqlInsert);

            statement.setString(1, null);
            statement.setString(2, this.textField_lastName.getText().toUpperCase());
            statement.setString(3, this.textField_firstName.getText().toUpperCase());
            statement.setString(4, this.gender_Combo.getValue().toUpperCase());
            statement.setString(5, this.dob_DatePicker.getEditor().getText());
            statement.setString(6, this.textField_religion.getText().toUpperCase());
            statement.setString(7, this.textField_residence.getText().toUpperCase());
            statement.setString(8, this.textField_LandMark.getText().toUpperCase());
            statement.setString(9, this.class_Combo.getValue().toUpperCase());
            statement.setString(10, this.lbl_doa.getText());
            statement.setString(11, this.textField_lastSchoolAttended.getText().toUpperCase());



            if (file != null) {
                fileInputStream = new FileInputStream(file);
                statement.setBinaryStream(12, fileInputStream, (int) file.length());
            } else {
                try {
                    BufferedImage imageViewImage = SwingFXUtils.fromFXImage(image_view.getImage(), null);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ImageIO.write(imageViewImage, "png", byteArrayOutputStream);
                    byte[] res = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    statement.setBytes(12, res);

                } catch (IOException e) {
                    e.printStackTrace();

                }

            }

            statement.setString(13, this.status_combo.getValue());
            statement.setString(14, this.lbl_cYear.getText());
            statement.setString(15, this.textField_parentID.getText().toUpperCase());
            progressIndicator.setVisible(true);
            progressIndicator.getProgress();
            statement.execute();


            showDialog("Successful`", "Student added Successfully!");
            progressIndicator.setVisible(false);
            clearForm();
        } catch (SQLException e) {
            showDialog("SQLException", "Error: '" + e.getLocalizedMessage() + "'");
            e.printStackTrace();
        } finally {
            statement.close();
        }


    }


    @FXML
    public void chooseImage(ActionEvent event) {


        //set ExtentionFilter


        //show open file dialog

        stage = (Stage) anchorpane_window.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            image_view.setImage(image);
//            image_view.setFitWidth(100.0);
//            image_view.setFitHeight(150.0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void UpdateStudentData() throws SQLException {

        String sqlUpdateQuery = "update StudentData set Student_id=?,lastName=?,firstName=?,gender=?,dob=?,religion=?,residence=?,landmark=?,class=?,admission_date=?,lastSchool_attended=?,image=?, status=? where Student_id='" + textField_ID.getText() + "' ";

        try {
            conn = SqliteConnection.Connector();
            statement = conn.prepareStatement(sqlUpdateQuery);

            statement.setString(1, this.textField_ID.getText().toUpperCase());
            statement.setString(2, this.textField_lastName.getText().toUpperCase());
            statement.setString(3, this.textField_firstName.getText().toUpperCase());
            statement.setString(4, this.gender_Combo.getValue().toUpperCase());
            statement.setString(5, this.dob_DatePicker.getEditor().getText());
            statement.setString(6, this.textField_religion.getText().toUpperCase());
            statement.setString(7, this.textField_residence.getText().toUpperCase());
            statement.setString(8, this.textField_LandMark.getText().toUpperCase());
            statement.setString(9, this.class_Combo.getValue().toUpperCase());
            statement.setString(10, this.lbl_doa.getText());
            statement.setString(11, this.textField_lastSchoolAttended.getText().toUpperCase());

            try {
                if (file != null) {
                    fileInputStream = new FileInputStream(file);
                    statement.setBinaryStream(16, fileInputStream, (int) file.length());
                } else {
                    try {
                        BufferedImage imageViewImage = SwingFXUtils.fromFXImage(image_view.getImage(), null);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ImageIO.write(imageViewImage, "png", byteArrayOutputStream);
                        byte[] res = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        statement.setBytes(16, res);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            statement.setString(17, this.status_combo.getValue());


            statement.execute();
            progressIndicator.setVisible(true);
            progressIndicator.getProgress();


            showAlert(Alert.AlertType.INFORMATION, "Successful", "Successful", "Student Information Updated Successfully");
            progressIndicator.setVisible(false);
        } catch (SQLException e) {
            showDialog("SQLException", "Error: '" + e.getLocalizedMessage() + "'");

            e.printStackTrace();
        } finally {
            statement.close();
        }

    }

    public void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {


        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private void showDialog(String headerText, String contentBodyText) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(headerText));
        content.setBody(new Text(contentBodyText));
        JFXDialog dialog = new JFXDialog(sp_stackPane, content, JFXDialog.DialogTransition.CENTER);

        JFXButton button = new JFXButton("Okay");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }

        });
        content.setActions(button);
        dialog.show();
    }

    public void getTime(String time) {
        lbl_doa.setText(time);
    }

    public void getYear(String year) {
        lbl_cYear.setText(year);
    }

    public void getParentId(parentData info){
        String parentID = info.getId();
        this.textField_parentID.setText(parentID);
    }


}
