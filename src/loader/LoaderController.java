package loader;

import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LoaderController implements Initializable {


    @FXML
    public JFXProgressBar pb;
    public static JFXProgressBar progressBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressBar = pb;


    }
}
