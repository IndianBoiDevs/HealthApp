import assets.StageManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //set the stage variable for our stage manager
        StageManager s = new StageManager(primaryStage);

        //this is the Parent created vy fxml loader
        Parent root = FXMLLoader.load(getClass().getResource("login//login.fxml"));

        //setup default screen values
        setUpDisplay(primaryStage);

        //Adding the scene to Stage
        primaryStage.setScene(new Scene(root,600,400));

        //Displaying the contents of the stage
        primaryStage.show();

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
        launch(args);
    }
}
