package sample;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import loader.MyPreloader;

public class Main extends Application {
    private FXMLLoader loader;
    private static final int COUNT_LIMIT = 10;


    @Override
    public void init() throws Exception {

        for (int i = 0; i < COUNT_LIMIT; i++){

            double progress = (double) i/10;
            System.out.println("Progress: " + progress);
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
            Thread.sleep(2000);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("infoSoft");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.setFullScreen(false);

        Image image = new Image("file:app_icon.png");
        primaryStage.getIcons().add(image);




       /* Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 4);*/
        primaryStage.show();
    }


    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, MyPreloader.class, args);
    }
}
