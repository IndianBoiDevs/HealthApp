package assets;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageManager {

    //instance variable
    private static Stage stage;

    public StageManager(Stage stg){
        stage = stg;
    }

    public StageManager(){

    }

    public Stage getStage() {
        return stage;
    }

    public void changeScene(String fxmlFile) throws IOException {

        //change the parent by loading in the fxml
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlFile));

        //check which package this file is in
        String packageName = fxmlFile.substring(0, 5);
        System.out.println("[Debug]: We are in package " + packageName);

        if(packageName.equals("login")){

            //run app in non full screen
            stage.setMaximized(false);

            //set the scene to specified size
            stage.setScene(new Scene(pane,600,400));

            //Disable Resizing
            stage.setResizable(false);
        }
        else {

            //Allow Resizing
            stage.setResizable(true);

            //run app in full screen
            stage.setMaximized(true);

            //set the stage
            stage.getScene().setRoot(pane);
        }


    }
}
