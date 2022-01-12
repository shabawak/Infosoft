package loader;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyPreloader extends Preloader {

    private Stage preloaderStage;
    private Scene scene;

    public MyPreloader(){

    }

    @Override
    public void init() throws Exception {
        Parent root1 = FXMLLoader.load(getClass().getResource("/loader/loader.fxml"));
        scene = new Scene(root1);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;

        Image image = new Image("file:app_icon.png");
        preloaderStage.getIcons().add(image);

        preloaderStage.setScene(scene);
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();

    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
       if (info instanceof ProgressNotification){
           System.out.println("Value@ :" + ((ProgressNotification)info).getProgress());
           LoaderController.progressBar.setProgress(((ProgressNotification)info).getProgress());
       }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
       StateChangeNotification.Type type = info.getType();
       switch (type){

           case BEFORE_START:
               System.out.println("BEFORE_START");
               preloaderStage.hide();
               break;
       }
    }
}
