package assets;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PatientPopUp {

    public static void display(){

        //here we have a blank windows
        Stage stage = new Stage();

        //block user interaction with other windows until this windows is taken care of
        stage.initModality(Modality.APPLICATION_MODAL);

        //set the title for the window
        stage.setTitle("Modify Patient");

        //set the minimum width
        stage.setMinWidth(300);

        //Temporary code
        Label message = new Label();
        message.setText("You are attempting to modify patient");

        //create a button
        Button close = new Button("Close");

        //make the action close window
        close.setOnAction(e-> stage.close());

        VBox lay = new VBox(10);
        lay.getChildren().addAll(message,close);
        lay.setAlignment(Pos.CENTER);

        //create a new scene
        Scene scene = new Scene(lay);

        //set the scene
        stage.setScene(scene);

        //Create an image object
        Image icon = new Image("views//icons//patient.png");

        //set the icon as the icon
        stage.getIcons().add(icon);

        //finally show and wait until this windows is closed
        stage.showAndWait();



    }
}
