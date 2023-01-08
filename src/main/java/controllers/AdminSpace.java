package controllers;

import assets.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.Initializable;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminSpace implements Initializable {

    @FXML
    private Button about;

    @FXML
    private MenuItem aboutdropdown;

    @FXML
    private MenuItem demote;

    @FXML
    private Button home;

    @FXML
    private MenuItem issue;

    @FXML
    private Button logOut;

    @FXML
    private MenuItem logoutdropdown;

    @FXML
    private BorderPane mainScreen;

    @FXML
    private MenuItem promote;

    @FXML
    private MenuItem close;

    @FXML
    private Button search;

    @FXML
    private Button settings;

    @FXML
    private MenuItem addPatientDropDown;

    public void logOutButtonPressed(ActionEvent event) throws IOException {
        logOut.setStyle("-fx-background-color: GREEN");
        StageManager manager = new StageManager();
        manager.changeScene("login//login.fxml");

    }

    @FXML
    void addPressed(ActionEvent event) throws IOException {
        AddPatientPopUp.display();
    }

    @FXML
    void demotePressed(ActionEvent event) throws IOException {
        DemotePopUp.display();
    }

    @FXML
    void promotePressed(ActionEvent event) throws IOException {
        PromotePopUp.display();
    }

    public void homePressed(ActionEvent event) {
        //set home to Green
        home.setStyle("-fx-background-color: GREEN");
        //set everything else to red
        logOut.setStyle("-fx-background-color: RED");
        search.setStyle("-fx-background-color: RED");
        about.setStyle("-fx-background-color: RED");
        settings.setStyle("-fx-background-color: RED");

        try {
            //Load the FXML File and store it in a pane
            Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("views//homeView.fxml"));
            //center the center to this
            mainScreen.setCenter(view);
        } catch (IOException e) {
            System.out.println("Unable to load homeView, program now closing!");
            System.exit(1);
        }
    }

    public void settingsPressed(ActionEvent event) {
        //set settings to Green
        settings.setStyle("-fx-background-color: GREEN");
        //set everything else to red
        logOut.setStyle("-fx-background-color: RED");
        home.setStyle("-fx-background-color: RED");
        about.setStyle("-fx-background-color: RED");
        search.setStyle("-fx-background-color: RED");
        try {
            //Load the FXML File and store it in a pane
            Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("views//settingsView.fxml"));
            //center the center to this
            mainScreen.setCenter(view);
        } catch (IOException e) {
            System.out.println("Unable to load settingsView, program now closing!");
            System.exit(1);
        }
    }

    public void aboutPressed(ActionEvent event) {
        //set about to Green
        about.setStyle("-fx-background-color: GREEN");
        //set everything else to red
        logOut.setStyle("-fx-background-color: RED");
        home.setStyle("-fx-background-color: RED");
        search.setStyle("-fx-background-color: RED");
        settings.setStyle("-fx-background-color: RED");

        try {
            //Load the FXML File and store it in a pane
            Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("views//aboutView.fxml"));
            //center the center to this
            mainScreen.setCenter(view);
        } catch (IOException e) {
            System.out.println("Unable to load aboutView, program now closing!");
            System.exit(1);
        }
    }


    public void searchPressed(ActionEvent event) {
        //set search to Green
        search.setStyle("-fx-background-color: GREEN");
        //set everything else to red
        logOut.setStyle("-fx-background-color: RED");
        home.setStyle("-fx-background-color: RED");
        about.setStyle("-fx-background-color: RED");
        settings.setStyle("-fx-background-color: RED");

        try {
            //Load the FXML File and store it in a pane
            Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("views//searchView.fxml"));
            //center the center to this
            mainScreen.setCenter(view);
        } catch (IOException e) {
            System.out.println("Unable to load searchView, program now closing!");
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
        home.setStyle("-fx-background-color: GREEN");

        try {
            //Load the FXML File and store it in a pane
            Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("views//homeView.fxml"));
            //center the center to this
            mainScreen.setCenter(view);
        } catch (IOException e) {
            System.out.println("Unable to load homeView, program now closing!");
            System.exit(1);
        }
    }
}
