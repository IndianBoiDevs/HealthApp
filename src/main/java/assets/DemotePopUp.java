package assets;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DemotePopUp {

    private static Stage stage;

    public static void display() throws IOException {

        //here we have a blank windows
        stage = new Stage();

        //block user interaction with other windows until this windows is taken care of
        stage.initModality(Modality.APPLICATION_MODAL);

        //set the title for the window
        stage.setTitle("Demote Account");

        //set the minimum width
        stage.setMinWidth(300);

        //this is the Parent created vy fxml loader
        DemotePopUp p = new DemotePopUp();
        Parent root = p.getScreen();

        //Adding the scene to Stage
        stage.setScene(new Scene(root,600,300));

        //Create an image object
        Image icon = new Image("views/icons/staff.png");

        //set the icon as the icon
        stage.getIcons().add(icon);
        stage.setResizable(false);

        //finally show and wait until this windows is closed
        stage.showAndWait();

    }

    public static void close(){
        stage.close();
    }

    private Parent getScreen() throws IOException {
        //this is the Parent created vy fxml loader
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/demoteWindow.fxml"));
        return root;

    }
}
