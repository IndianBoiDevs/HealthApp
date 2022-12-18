package controllers;

import assets.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientSpace implements Initializable {
    @FXML
    private Button logOut;

    public void logOutButtonPressed(ActionEvent event) throws IOException {
        StageManager manager = new StageManager();
        manager.changeScene("login//login.fxml");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
