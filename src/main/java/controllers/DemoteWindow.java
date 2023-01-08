package controllers;

import assets.ConfirmPopUp;
import assets.DemotePopUp;
import assets.LoginTool;
import assets.PromotePopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DemoteWindow {

    @FXML
    private Button cancelBt;

    @FXML
    private Button demoteBt;

    @FXML
    private Label response;

    @FXML
    private TextField username;
    private LoginTool tool = new LoginTool();

    @FXML
    void cancelPressed(ActionEvent event) {
        DemotePopUp.close();
    }

    @FXML
    void demotePressed(ActionEvent event) throws SQLException {
        System.out.println("[Debug]: demote pressed");

        if(username.getText().equals("")){
            System.out.println("[Debug]: Give me a username");
            response.setText("Give a username!");
            response.setTextFill(Color.RED);
        }else{
            ResultSet set = tool.runQuery("SELECT * FROM login.credentials " +
                    "where username = '" + username.getText() +"';");

            boolean exists = false;
            int userType = -1;

            while(set.next()){
                userType = Integer.parseInt(set.getString("type"));
                exists = true;
            }

            if(exists == false && userType == -1){
                response.setText("User with username: " + username.getText() + " does not exist!");
                username.setText("");
                response.setTextFill(Color.RED);
            }
            else{
                if(userType == 0){
                    response.setText("User with username: " + username.getText() + " cannot be demoted!");
                    username.setText("");
                    response.setTextFill(Color.RED);
                }
                else if(userType == 2){
                    response.setText("User with username: " + username.getText() + " is already a patient!");
                    username.setText("");
                    response.setTextFill(Color.RED);
                }
                else {
                    try {
                        ConfirmPopUp.display(username.getText(), false);
                        if(ConfirmPopUp.choice == true) {
                            userType++;
                            tool.updateDatabase("UPDATE `login`.`credentials` SET `type` = '" + userType + "' WHERE (`username` = '" + username.getText() + "');");
                            response.setText("User with username: " + username.getText() + " has been demoted!");
                            username.setText("");
                            response.setTextFill(Color.GREEN);
                        }
                        else{
                            username.setText("");
                            response.setText("Give a username!");
                            response.setTextFill(Color.RED);
                        }
                    }
                    catch (Exception e){
                        response.setText("Unable to demote person try again later!");
                        username.setText("");
                        response.setTextFill(Color.RED);
                    }
                }

            }
        }
    }

}
