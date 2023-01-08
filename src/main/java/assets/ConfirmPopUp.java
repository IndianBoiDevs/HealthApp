package assets;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfirmPopUp {

    private static Stage stage;
    public static boolean choice;

    public static String name;

    public static void display(String n,boolean whichDialog) throws IOException {

        name = n;

        //here we have a blank windows
        stage = new Stage();

        //block user interaction with other windows until this windows is taken care of
        stage.initModality(Modality.APPLICATION_MODAL);

        if(whichDialog == true) {
            //set the title for the window
            stage.setTitle("Confirm Promotion");
        }
        else{
            //set the title for the window
            stage.setTitle("Confirm Demotion");
        }

        //set the minimum width
        stage.setMinWidth(300);

        //this is the Parent created vy fxml loader
        ConfirmPopUp p = new ConfirmPopUp();
        Parent root = null;

        if(whichDialog = true) {
            root = p.getPromoScreen();
        }
        else{
            root = p.getDemoScreen();
        }

        //Adding the scene to Stage
        stage.setScene(new Scene(root,500,150));

        //Create an image object
        Image icon = new Image("views/icons/save.png");

        //set the icon as the icon
        stage.getIcons().add(icon);
        stage.setResizable(false);

        //finally show and wait until this windows is closed
        stage.showAndWait();

    }

    public static void close(){
        stage.close();
    }

    private Parent getPromoScreen() throws IOException {
        //this is the Parent created vy fxml loader
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/confirmPromotion.fxml"));
        return root;
    }

    private Parent getDemoScreen() throws IOException {
        //this is the Parent created vy fxml loader
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/confirmDemotion.fxml"));
        return root;
    }
}
