package controllers;

import assets.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientSpace implements Initializable {
    @FXML
    private Button logOut;
    @FXML
    private Button about;
    @FXML
    private BorderPane mainScreen;
    public void logOutButtonPressed(ActionEvent event) throws IOException {
        StageManager manager = new StageManager();
        manager.changeScene("login//login.fxml");

    }

    public void aboutPressed(ActionEvent event){
        try {
            //Load the FXML File and store it in a pane
            Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("views//aboutView.fxml"));
            //center the center to this
            mainScreen.setCenter(view);
        } catch (IOException e) {
            System.out.println("Unable to load homeView, program now closing!");
            System.exit(1);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        about.setStyle("-fx-background-color: GREEN");

        try {
            //Load the FXML File and store it in a pane
            Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("views//aboutView.fxml"));
            //center the center to this
            mainScreen.setCenter(view);
        } catch (IOException e) {
            System.out.println("Unable to load homeView, program now closing!");
            System.exit(1);
        }
    }
}
