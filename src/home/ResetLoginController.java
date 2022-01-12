package home;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.LoginModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResetLoginController implements Initializable {
    @FXML
    private AnchorPane parent;

    @FXML
    private Pane pane;

    @FXML
    private JFXPasswordField txt_pass;

    @FXML
    private JFXButton btn_okey;

    @FXML
    private Label lbl_status;



    public LoginModel loginModel = new LoginModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        btn_okey.setOnAction(event -> {


            String inputPass = txt_pass.getText();
            try {
                if (loginModel.isReset(inputPass)){
                    lbl_status.setText("");

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/menubar/reset.fxml"));
                    try{
                        loader.load();
                    }catch (IOException ex){
                        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE,null,ex);
                    }
                    lbl_status.setText("");

                    Parent parent = loader.getRoot();
                    Stage resetLoginStage = new Stage();

                    resetLoginStage.initModality(Modality.APPLICATION_MODAL);
                    resetLoginStage.initStyle(StageStyle.UTILITY);
                    resetLoginStage.setTitle("Reset Default");
                    resetLoginStage.setScene(new Scene(parent));
                    Image image = new Image("file:app_icon.png");
                    resetLoginStage.getIcons().add(image);

                    resetLoginStage.show();

                    ((Node)event.getSource()).getScene().getWindow().hide();

                }
                else {
                    lbl_status.setText("Wrong Password!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }







}
