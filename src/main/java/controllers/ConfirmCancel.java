package controllers;

import assets.CancelPopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConfirmCancel {

    @FXML
    private Button no;

    @FXML
    private Button yes;

    @FXML
    void noPressed(ActionEvent event) {
        CancelPopUp.choice = false;
        CancelPopUp.close();
    }

    @FXML
    void yesPressed(ActionEvent event) {
        CancelPopUp.choice = true;
        CancelPopUp.close();
    }

}
