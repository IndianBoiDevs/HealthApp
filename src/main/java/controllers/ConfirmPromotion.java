package controllers;

import assets.ConfirmPopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmPromotion implements Initializable {

    @FXML
    private Button noBt;

    @FXML
    private Label userHere;

    @FXML
    private Button yesBt;

    @FXML
    void noPressed(ActionEvent event) {
        ConfirmPopUp.choice = false;
        ConfirmPopUp.close();
    }

    @FXML
    void yesPressed(ActionEvent event) {
        ConfirmPopUp.choice = true;
        ConfirmPopUp.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userHere.setText(ConfirmPopUp.name);
    }
}