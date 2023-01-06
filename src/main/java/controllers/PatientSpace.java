package controllers;

import assets.AboutPopup;
import assets.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientSpace implements Initializable {
    @FXML
    private Button logOut;
    @FXML
    private Button about;
    @FXML
    private BorderPane mainScreen;

    @FXML
    private MenuItem aboutdropdown;

    @FXML
    private MenuItem logoutdropdown;

    @FXML
    private MenuItem close;

    @FXML
    private MenuItem issue;

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

    @FXML
    void aboutClicked(ActionEvent e) throws IOException {
        AboutPopup.display();
    }

    @FXML
    void closeProgram(ActionEvent event){
        System.exit(0);
    }

    @FXML
    void menuLogOutButton(ActionEvent event) {
        StageManager manager = new StageManager();
        try {
            manager.changeScene("login//login.fxml");
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("Error: Unable to logout");
        }

    }

    @FXML
    void reportIssues(ActionEvent event) {
        Desktop d = Desktop.getDesktop();
        try {
            d.browse(new URI("https://github.com/IndianBoiDevs/HealthApp/issues/new"));
        } catch (IOException | URISyntaxException e2) {
            e2.printStackTrace();
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
