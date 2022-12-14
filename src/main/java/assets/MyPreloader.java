package assets;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.stage.Stage; //what is this supposed to be
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.stage.StageStyle;
import controllers.Splash; //FIXME

import java.util.Objects;


public class MyPreloader extends Preloader {

    private Stage preloaderStage;
    private Scene scene;

    public MyPreloader() {

    }

    @Override
    public void init() throws Exception {

        try {
            Parent root1 = FXMLLoader.load((getClass().getResource("splash\\splash.fxml")));
            scene = new Scene(root1);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.preloaderStage = primaryStage;

        //Set preloader scene and show stage
        preloaderStage.setScene(scene);
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();

    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info){

        //Updates the progress percentage on the screen
        if (info instanceof ProgressNotification){
            Splash.label.setText("Loading " + ((ProgressNotification) info).getProgress() + "%"); //FIXME
        }

    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info){

        StateChangeNotification.Type type = info.getType();

        switch(type){

            case BEFORE_START:
                //Called after MyApplication#init and before MyApplication# start is called
                System.out.println("BEFORE_START");
                preloaderStage.hide();
                break;
        }

    }




}
