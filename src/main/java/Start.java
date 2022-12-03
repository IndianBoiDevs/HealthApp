import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //creating a Group object
        Group group = new Group();

        //Creating a Scene by passing the group object, height and width
        Scene scene = new Scene(group ,600, 300);

        //setup default screen values
        setUpDisplay(primaryStage);

        //setting color for the scene
        scene.setFill(Color.WHITE);

        //Adding the scene to Stage
        primaryStage.setScene(scene);

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

        //run app in full screen
        primaryStage.setMaximized(true);
        
    }


    public static void main(String args[]) {
        launch(args);
    }
}
