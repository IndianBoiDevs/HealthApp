package controllers;

import assets.SavePopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConfirmSave {

    @FXML
    private Button no;

    @FXML
    private Button yes;

    @FXML
    void noPressed(ActionEvent event) {
        SavePopUp.choice = false;
        SavePopUp.close();
    }

    @FXML
    void yesPressed(ActionEvent event) {
        SavePopUp.choice = true;
        SavePopUp.close();
    }

}
