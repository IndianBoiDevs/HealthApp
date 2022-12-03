package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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



    /**
    * The default constructor
     * */
    public Login(){

    }

    /**
     *  Method called on button click
     * */
    public void loginPressed(ActionEvent a) throws IOException{
        checkCredentials();
    }

    private void checkCredentials(){

        //future functionality: get user via SQL Database!

        //extract the userName and password from the respective controls
        String user = username.getText().toString();
        String pass = password.getText().toString();

        //check to see if the person provided a username and password
        if(user.length() == 0){
            warning.setText("Please provide a username!");
        }
        else if(pass.length() ==0){
            warning.setText("Please provide a password!");
        }
        else{
            //check to see if the person matches credntials
            if(user.equals("admin") && pass.equals("admin")){
                warning.setTextFill((Paint)Color.GREEN);
                warning.setText("Success!");
            }
        }




    }


}
