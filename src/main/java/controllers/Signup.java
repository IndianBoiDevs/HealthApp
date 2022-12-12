package controllers;

import assets.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Signup {

    //create a stage manager
    private static StageManager manager = new StageManager();

    @FXML
    private Button backButton;

    public void backPressed(ActionEvent a) throws IOException {
        System.out.println("[Debug]: User has selected to go back to login");
        //change the scene to the sign up
        manager.changeScene("login//login.fxml");
    }

}
