package home;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.LoginModel;
import sample.SqliteConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane parent;

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private Tab tab_Overview;

    @FXML
    private Tab tab_Logs;

    @FXML
    public MenuItem MenuItem_settings;

    @FXML
    private MenuItem guideItem;

    @FXML
    private MenuItem aboutItem;


    @FXML
    public MenuItem resetItem;

    @FXML
    public MenuItem modify_item_1;

    @FXML
    public Menu menuItem_edit;

    @FXML
    public Menu menuItem_modify;

    @FXML
    private BorderPane bp_Overview;

    @FXML
    private Tab tab_StudentInfo;

    @FXML
    private BorderPane bp_StudentInfo;

    @FXML
    private BorderPane bp_StudentInfo_RESULT_P;

    @FXML
    private BorderPane bp_StudentInfo_RESULT_JHS;

    @FXML
    private BorderPane bp_StudentInfoCompleted;

    @FXML
    private BorderPane bp_parentUpdate;

    @FXML
    private Tab tab_Update;

    @FXML
    private BorderPane bp_UserUpdate;

    @FXML
    private Tab tab_UserManagement;

    @FXML
    private BorderPane bp_UserManagement;

    @FXML
    private BorderPane bp_UserLogs;


    @FXML
    private JFXButton btn_signOut;

    @FXML
    private Label logedUser_name;

    @FXML
    private Label btn_Min;

    @FXML
    private Label btn_Close;

    @FXML
    public Label btn_Restore;

    @FXML
    public Label btn_Max;


    public LoginModel loginModel = new LoginModel();

    Connection conn;
    PreparedStatement stmt = null;
    ResultSet resultSet = null;

    Date date;
    DateFormat dateFormat;
    Calendar cal;

    int generatedKey;
    String username_1;
    String Fullname_1;

    private double Xoffset = 0;
    private double Yoffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        logedUser_name.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/home/account_manager.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }

                AccountManagerController accountManagerController = loader.getController();
                accountManagerController.getName(logedUser_name.getText());

                Parent parent = loader.getRoot();
                Stage account = new Stage();
                account.initStyle(StageStyle.UTILITY);
                account.setTitle("Reset Password");
                account.initModality(Modality.APPLICATION_MODAL);
                account.setScene(new Scene(parent));
                Image image = new Image("file:app_icon.png");
                account.getIcons().add(image);

                account.show();


            }
        });

        makeStageDragable();

        btn_Restore.setDisable(true);

        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.date = new Date();
        this.cal = Calendar.getInstance();


        // Overview
        Pane dashboard = null;
        try {
            dashboard = FXMLLoader.load(getClass().getResource("/dashboard/dashboard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bp_Overview.setCenter(dashboard);


        // Logs
        Pane userLogs = null;
        try {
            userLogs = FXMLLoader.load(getClass().getResource("/logger/log.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bp_UserLogs.setCenter(userLogs);

        //  Student info
        Pane studentInfo = null;
        try {
            studentInfo = FXMLLoader.load(getClass().getResource("/studentInfo/student_info.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bp_StudentInfo.setCenter(studentInfo);

        Pane examInfo_primary = null;
        try {
            examInfo_primary = FXMLLoader.load(getClass().getResource("/examreport/exam_info.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bp_StudentInfo_RESULT_P.setCenter(examInfo_primary);

        Pane examInfo_jhs = null;
        try {
            examInfo_jhs = FXMLLoader.load(getClass().getResource("/examreport/jhsExamInfo.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bp_StudentInfo_RESULT_JHS.setCenter(examInfo_jhs);


        // Update
        Pane update = null;
        try {
            update = FXMLLoader.load(getClass().getResource("/update/students_data.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bp_UserUpdate.setCenter(update);


        Pane update_parent = null;
        try {
            update_parent = FXMLLoader.load(getClass().getResource("/update/parents.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bp_parentUpdate.setCenter(update_parent);

        // User Management
        Pane userManagement = null;
        try {
            userManagement = FXMLLoader.load(getClass().getResource("/users/users.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bp_UserManagement.setCenter(userManagement);


        btn_signOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert signOutAlert = new Alert(Alert.AlertType.CONFIRMATION);
                signOutAlert.setHeaderText(null);
                signOutAlert.setContentText("Are you sure you want to signOut?");
                Optional<ButtonType> result = signOutAlert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    try {

                        try {
                            String updateLogQuerry = "UPDATE LogTable SET Time_out =? WHERE Log_id = '" + generatedKey + "'";
                            DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            Date d = new Date();
                            conn = SqliteConnection.Connector();
                            stmt = conn.prepareStatement(updateLogQuerry);
                            stmt.setString(1, dateFormatter.format(d));
                            stmt.executeUpdate();

                            ((Node) event.getSource()).getScene().getWindow().hide();
                            Stage HomeStage = new Stage();
                            FXMLLoader loader = new FXMLLoader();
                            Pane root = loader.load(getClass().getResource("/sample/sample.fxml").openStream());
                            HomeStage.setTitle("Login");
                            Image image = new Image("file:app_icon.png");
                            HomeStage.getIcons().add(image);
                            HomeStage.setScene(new Scene(root));
                            HomeStage.setResizable(false);
                            HomeStage.setMaximized(false);
                            HomeStage.show();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            stmt.close();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    signOutAlert.close();
                }
            }
        });

        modify_item_1.setOnAction(event -> {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/home/modify.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Parent parent = loader.getRoot();
            Stage modify = new Stage();
            //modify.initStyle(StageStyle.UNDECORATED);
            modify.initModality(Modality.APPLICATION_MODAL);
            modify.setScene(new Scene(parent));
            Image image = new Image("file:app_icon.png");
            modify.getIcons().add(image);

            modify.show();

        });

        resetItem.setOnAction(event -> {
            loadResetLoginWindow(event);
        });

        aboutItem.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/menubar/about.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Parent parent = loader.getRoot();
            Stage aboutStage = new Stage();
            aboutStage.initStyle(StageStyle.UTILITY);
            aboutStage.setTitle("About");
            aboutStage.initModality(Modality.APPLICATION_MODAL);
            aboutStage.setScene(new Scene(parent));
            Image image = new Image("file:app_icon.png");
            aboutStage.getIcons().add(image);

            aboutStage.show();

        });

        guideItem.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/menubar/guide.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Parent parent = loader.getRoot();
            Stage guideStage = new Stage();
            guideStage.initStyle(StageStyle.UNIFIED);
            guideStage.setTitle("Guide");
            guideStage.initModality(Modality.APPLICATION_MODAL);
            guideStage.setScene(new Scene(parent));
            Image image = new Image("file:app_icon.png");
            guideStage.getIcons().add(image);

            guideStage.show();
        });


        MenuItem_settings.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/settings/settings.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Parent parent = loader.getRoot();
            Stage settings = new Stage();
            settings.initStyle(StageStyle.UTILITY);
            settings.setTitle("Preferences");
            settings.initModality(Modality.APPLICATION_MODAL);
            settings.setScene(new Scene(parent));
            Image image = new Image("file:app_icon.png");
            settings.getIcons().add(image);

            settings.show();

        });
    }

    public void GetUser(String username) throws SQLException {
        username_1 = username;
        if (username.equals("admin")) {
            logedUser_name.setText("Ass ADMINISTRATOR");
        } else if (username.equals("administrator")) {
            logedUser_name.setText("ADMINISTRATOR");
        } else if (username.equals("snrAdministrator")) {
            logedUser_name.setText("SNR ADMINISTRATOR");
            modify_item_1.setVisible(true);
        } else {
            String LogedUser_Querry = "SELECT fullName FROM UsersTable WHERE username = '" + username + "'";
            try {
                conn = SqliteConnection.Connector();

                resultSet = conn.createStatement().executeQuery(LogedUser_Querry);

                if (resultSet.next()) {
                    String name = resultSet.getString(1);
                    logedUser_name.setText(name);
                    Fullname_1 = resultSet.getString(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {

                resultSet.close();
            }
        }

    }

    public void inserIntoLogTable(String username) throws SQLException {
        String inserQuerry = "INSERT INTO LogTable(Log_id,Username,Fullname,Time_in) VALUES(?,?,?,?)";

        try {
            conn = SqliteConnection.Connector();
            stmt = conn.prepareStatement(inserQuerry, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, null);
            stmt.setString(2, username);
            stmt.setString(3, logedUser_name.getText());
            stmt.setString(4, dateFormat.format(date));

            stmt.executeUpdate();
            resultSet = stmt.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) {
                generatedKey = resultSet.getInt(1);

            }
        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            stmt.close();
            resultSet.close();
        }

    }


    public void disableTabs() {
        tab_Update.setDisable(true);
        tab_UserManagement.setDisable(true);
        tab_Logs.setDisable(true);

    }

    public void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @FXML
    void closeButtonActionPerform(MouseEvent event) throws SQLException {
       /* Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        homeStage.close();*/
        try {
            String updateLogQuerry = "UPDATE LogTable SET Time_out =? WHERE Log_id = '" + generatedKey + "'";
            DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date d = new Date();
            conn = SqliteConnection.Connector();
            stmt = conn.prepareStatement(updateLogQuerry);
            stmt.setString(1, dateFormatter.format(d));
            stmt.executeUpdate();

            System.exit(0);

        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            stmt.close();
        }


    }


    @FXML
    void maxButtonAction(MouseEvent event) {


        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        homeStage.setMaximized(true);
        btn_Max.setDisable(true);
        btn_Restore.setDisable(false);
    }

    @FXML
    void minButtonAction(MouseEvent event) {
        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        homeStage.setIconified(true);

    }


    @FXML
    void restoreButtonAction(MouseEvent event) {


        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        homeStage.setMaximized(false);
        btn_Restore.setDisable(true);
        btn_Max.setDisable(false);

    }

    private void makeStageDragable() {
        parent.setOnMousePressed((event) -> {

            Xoffset = event.getScreenX();
            Yoffset = event.getScreenY();

        });
        parent.setOnMouseDragged((event) -> {
            Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            homeStage.setX(event.getScreenX() - Xoffset);
            homeStage.setY(event.getScreenY() - Yoffset);
            if (homeStage.isMaximized()) {

                homeStage.setMaximized(false);
                btn_Restore.setDisable(true);
                btn_Max.setDisable(false);
            }


        });

        parent.setOnDragDone((event) -> {
            Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            homeStage.setOpacity(1.0f);
        });

        parent.setOnMouseReleased((event) -> {
            Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            homeStage.setOpacity(1.0f);
        });


    }

    @FXML
    void loadResetLoginWindow(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/home/reset_login.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent parent = loader.getRoot();
        Stage resetLoginStage = new Stage();
        resetLoginStage.initStyle(StageStyle.UTILITY);
        resetLoginStage.setTitle("Reset Login");
        resetLoginStage.initModality(Modality.APPLICATION_MODAL);
        resetLoginStage.setScene(new Scene(parent));
        Image image = new Image("file:app_icon.png");
        resetLoginStage.getIcons().add(image);

        resetLoginStage.show();

    }

}
