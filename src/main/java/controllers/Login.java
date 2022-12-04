package controllers;

import assets.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;


public class Login {

    //instance variables these are the exact name of the components in the FXML
    @FXML
    private Button loginButton;
    @FXML
    private Label warning;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    private static Stage stage;

    /**
     *  Method called on button click
     * */
    public void loginPressed(ActionEvent a) throws IOException{
        checkCredentials();
    }

    private void checkCredentials() throws IOException {
        //create a stage manager
        StageManager manager = new StageManager();

        //extract the userName and password from the respective controls
        String user = username.getText().toString();
        String pass = password.getText().toString();

        //check to see if the person provided a username and password
        if(user.length() == 0){
            // if the username isn't provide
            warning.setTextFill(Color.color(1, 0, 0));
            warning.setText("Please provide a username!");
        }
        else if(pass.length() ==0){
            // if the password isn't provide
            warning.setTextFill(Color.color(1, 0, 0));
            warning.setText("Please provide a password!");
        }
        else{
            //future functionality: get user via SQL Database!
            //check to see if the person matches credentials
            if(user.equals("admin") && pass.equals("admin")){
                warning.setTextFill(Color.color(0, 1, 0));
                warning.setText("Success!");

                //change the scene
                manager.changeScene("workspace\\workspace.fxml");
            }
        }




    }


}
