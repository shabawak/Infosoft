package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import home.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    @FXML
    private Label isConnected;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton loginButton;

    @FXML
    private Label loginStatus;

    @FXML
    private JFXCheckBox adminCheckBox;

    @FXML
    private MaterialIconView font_eyeOff;




    HomeController homeController = new HomeController();
    public LoginModel loginModel = new LoginModel();



    public Controller() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (loginModel.isDbConnected()) {
            isConnected.setText("Connected");
        } else {
            isConnected.setText("Not Connected");
        }

       /* loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Login(event);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });*/

    }
    @FXML
    public void Login(ActionEvent event) throws SQLException {

        try {
            if (adminCheckBox.isSelected()) {
                try {
                    if (loginModel.isAdminLogin(txtUserName.getText(), txtPassword.getText())) {
                        loginStatus.setText("Login you in please wait...");


                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/home/home.fxml"));
                        try{
                            loader.load();
                        }catch (IOException ex){
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,null,ex);
                        }
                        HomeController home = loader.getController();

                        home.GetUser(txtUserName.getText());
                        home.inserIntoLogTable(txtUserName.getText());
                        home.btn_Max.setDisable(true);
                        home.btn_Restore.setDisable(false);
                        home.menuItem_edit.setVisible(false);
                        home.menuItem_modify.setVisible(false);

                        Parent parent = loader.getRoot();
                        Stage adminStage = new Stage();
                        adminStage.initStyle(StageStyle.UNDECORATED);
                        adminStage.setMaximized(true);
                        adminStage.setResizable(true);
                        adminStage.setScene(new Scene(parent));
                        Image image = new Image("file:app_icon.png");
                        adminStage.getIcons().add(image);

                        adminStage.show();
                        ((Node) event.getSource()).getScene().getWindow().hide();


                    } else {
                        loginStatus.setText("Wrong Credentials!");
                    }
                } catch (SQLException e) {

                    showAlert("Error!","SQLEception",""+e.getLocalizedMessage()+"");
                    e.printStackTrace();
                }
            } else if ((! adminCheckBox.isSelected()) & loginModel.isUserLogin(txtUserName.getText(), txtPassword.getText())){
                try {

                    loginStatus.setText("Login you in please wait...");


                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/home/home.fxml"));
                    try{
                        fxmlLoader.load();
                    }catch (IOException ex){
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,null,ex);
                    }
                    HomeController homeController = fxmlLoader.getController();

                    homeController.GetUser(txtUserName.getText());
                    homeController.disableTabs();
                    homeController.inserIntoLogTable(txtUserName.getText());
                    homeController.btn_Max.setDisable(true);
                    homeController.btn_Restore.setDisable(false);
                    /*homeController.MenuItem_settings.setDisable(true);
                    homeController.resetItem.setDisable(true);*/
                    homeController.menuItem_edit.setVisible(false);
                    homeController.menuItem_modify.setVisible(false);

                    Parent p = fxmlLoader.getRoot();
                    Stage userStage = new Stage();
                    userStage.initStyle(StageStyle.UNDECORATED);


                    Image image = new Image("file:app_icon.png");
                    userStage.getIcons().add(image);

                    userStage.setMaximized(true);
                    userStage.setResizable(true);
                    userStage.setScene(new Scene(p));
                    userStage.show();



                    ((Node) event.getSource()).getScene().getWindow().hide();

                } catch (SQLException e) {
                    showAlert("Error!","SQLEception",""+e.getLocalizedMessage()+"");
                    e.printStackTrace();
                }

            }
            else if ((!adminCheckBox.isSelected())& loginModel.snrAdmin(txtUserName.getText() ,txtPassword.getText())){
                try {

                    loginStatus.setText("Login you in please wait...");


                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/home/home.fxml"));
                    try{
                        fxmlLoader.load();
                    }catch (IOException ex){
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,null,ex);
                    }
                    HomeController homeController = fxmlLoader.getController();

                    homeController.GetUser(txtUserName.getText());
                    homeController.inserIntoLogTable(txtUserName.getText());
                    homeController.btn_Max.setDisable(true);
                    homeController.btn_Restore.setDisable(false);
                    /*homeController.MenuItem_settings.setDisable(false);
                    homeController.resetItem.setDisable(false);*/
                    homeController.menuItem_edit.setVisible(true);
                    homeController.menuItem_modify.setVisible(true);




                    Parent p = fxmlLoader.getRoot();
                    Stage userStage = new Stage();
                    userStage.initStyle(StageStyle.UNDECORATED);


                    Image image = new Image("file:app_icon.png");
                    userStage.getIcons().add(image);

                    userStage.setMaximized(true);
                    userStage.setResizable(true);
                    userStage.setScene(new Scene(p));
                    userStage.show();



                    ((Node) event.getSource()).getScene().getWindow().hide();

                } catch (SQLException e) {
                    showAlert("Error!","SQLEception",""+e.getLocalizedMessage()+"");
                    e.printStackTrace();
                }

            }
            else {
                loginStatus.setText("Wrong Credentials");
            }

        } catch (Exception e) {
            e.printStackTrace();
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
