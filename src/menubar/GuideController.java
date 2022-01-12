package menubar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class GuideController implements Initializable {

    @FXML
    private WebView webview;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine webEngine = webview.getEngine();


        webEngine.load(getClass().getClassLoader().getResource("infoSOFT guide/index.html").toExternalForm());


        webview.setZoom(1.25);
        webEngine.setJavaScriptEnabled(true);

    }
}
