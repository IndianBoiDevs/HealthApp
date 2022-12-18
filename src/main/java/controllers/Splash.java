package controllers;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.util.ResourceBundle;

public class Splash implements Initializable {

    @FXML
    private Label load;
    @FXML
    public static Label label;

    public void initialize(URL url, ResourceBundle bundle){
        label = load;
    }
}
