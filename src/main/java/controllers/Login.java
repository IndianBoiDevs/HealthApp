package controllers;

import assets.LoginTool;
import assets.Message;
import assets.PersonHelper;
import assets.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Login implements Initializable {

    //instance variables these are the exact name of the components in the FXML
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;
    @FXML
    private Label warning;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    //create a stage manager
    private static StageManager manager = new StageManager();

    //create a single login tool object
    private static LoginTool loginTool = new LoginTool();

    /**
     *  Method called on button click
     * */
    public void loginPressed(ActionEvent a) throws IOException{
        System.out.println("[Debug]: User has selected login in");
        checkCredentials();
    }

    public void signUpPressed(ActionEvent a) throws IOException{
        if(loginTool.isOfflineMode() == false) {
            System.out.println("[Debug]: User has selected sign up");
            //change the scene to the sign up
            manager.changeScene("login/signup.fxml");
        }
        else{
            warning.setTextFill(Color.color(1, 0, 0));
            warning.setText("Offline,user registration unavailable");
        }
    }

    public void nextFieldRequired(KeyEvent k){
        //if the user clicks enter while in username
        if( k.getCode() == KeyCode.ENTER ) {
            //set focus to password
            password.requestFocus();
        }
    }

    public void enterPressed(KeyEvent k) throws IOException {
        //if the user button that they have pressed is the enter key
        if( k.getCode() == KeyCode.ENTER ) {
            //trigger the credntial set
            checkCredentials();
        }
    }

    private void checkCredentials() throws IOException {

        //extract the userName and password from the respective controls
        String user = username.getText().toString();
        String pass = password.getText().toString();

        //check to see if the person provided a username and password
        if(user.length() == 0){
            // if the username isn't provide
            warning.setTextFill(Color.color(1, 0, 0));
            warning.setText("Please provide a username!");
            //set focus to username
            username.requestFocus();
        }
        else if(pass.length() ==0){
            // if the password isn't provide
            warning.setTextFill(Color.color(1, 0, 0));
            warning.setText("Please provide a password!");
            //set focus to password
            password.requestFocus();
        }
        else{

            //call the check if user exists method
            int code = loginTool.checkIfUserExists(user,pass);

            //check to see if the person matches credentials for admin
            if(code == 0){
                System.out.println("[Debug]: The user of type admin is logged in successfully");
                warning.setTextFill(Color.color(0, 1, 0));
                warning.setText("Success!");
                //change the scene
                manager.changeScene("workspace/adminSpace.fxml");
                PersonHelper.setUserType(code);
            }
            //for staff
            else if (code == 1 ){
                System.out.println("[Debug]: The user of type staff is logged in successfully");
                warning.setTextFill(Color.color(0, 1, 0));
                warning.setText("Success!");
                //change the scene
                PersonHelper.setUserType(code);
                manager.changeScene("workspace/staffSpace.fxml");
            }
            //for patient
            else if (code == 2) {
                System.out.println("[Debug]: The user of type patient is logged in successfully");
                warning.setTextFill(Color.color(0, 1, 0));
                warning.setText("Success!");
                //change the scene
                PersonHelper.setUserType(code);
                manager.changeScene("workspace/patientSpace.fxml");
            }
            else{
                //reset the boxes
                username.setText("");
                password.setText("");

                // if the credentials don't match
                warning.setTextFill(Color.color(1, 0, 0));
                warning.setText("Please provide correct credentials!");
                //set focus
                username.requestFocus();
            }
        }




    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Message m = new Message();
        String message = m.getMessage();
        if(message != null){
            warning.setTextFill(Color.color(0, 1, 0));
            warning.setText(message);
            m.setMessage(null);
        }
    }
}
