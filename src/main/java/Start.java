import assets.MyPreloader;
import assets.StageManager;
import com.sun.javafx.application.LauncherImpl; //FIXME
import controllers.Splash;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.stage.Stage;

public class Start extends Application {

    private static final int COUNT_LIMIT = 50000;
    @Override
    public void start(Stage primaryStage) throws Exception {

        //set the stage variable for our stage manager
        StageManager s = new StageManager(primaryStage);

        //this is the Parent created vy fxml loader
        Parent root = FXMLLoader.load(getClass().getResource("login//login.fxml"));

        //setup default screen values
        setUpDisplay(primaryStage);

        //Adding the scene to Stage
        primaryStage.setScene(new Scene(root,600,360));

        //Displaying the contents of the stage
        primaryStage.show();

    }

    public void init() throws Exception {

        //perform some heavy lifting (i.e. database start, check for application updates, etc.)
        for(int i = 0; i < COUNT_LIMIT; i++){
            double progress = (100 * i) / COUNT_LIMIT;
            // update the progress
            notifyPreloader(new Preloader.ProgressNotification(progress));
        }

    }

    private void setUpDisplay(Stage primaryStage) {

        //Setting the title to Stage.
        primaryStage.setTitle("Health App");

        //Create an image object
        Image icon = new Image("icon.png");

        //set the icon as the icon
        primaryStage.getIcons().add(icon);

        //make sure screen cannot be resized
        primaryStage.setResizable(false);

    }


    public static void main(String args[]) {

        try {
            System.setProperty("javafx.preloader", MyPreloader.class.getCanonicalName());
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
