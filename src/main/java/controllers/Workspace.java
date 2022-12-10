package controllers;

import assets.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Workspace {
    @FXML
    private Button logOut;


    public void logOutButtonPressed(ActionEvent event) throws IOException {
        StageManager manager = new StageManager();
        manager.changeScene("login\\login.fxml");

    }
}
