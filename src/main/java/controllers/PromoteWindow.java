package controllers;

import assets.ConfirmPopUp;
import assets.LoginTool;
import assets.PromotePopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PromoteWindow {

    @FXML
    private Button cancelBt;

    @FXML
    private Button promoteBt;

    @FXML
    private Label response;

    @FXML
    private TextArea username;

    private LoginTool tool = new LoginTool();

    @FXML
    void cancelPressed(ActionEvent event)  {
        PromotePopUp.close();
    }

    @FXML
    void promotePressed(ActionEvent event) throws SQLException {
        System.out.println("[Debug]: promote pressed");

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
                    response.setText("User with username: " + username.getText() + " cannot be promoted!");
                    username.setText("");
                    response.setTextFill(Color.RED);
                }
                else if(userType == 1){
                    response.setText("User with username: " + username.getText() + " is already a staff member!");
                    username.setText("");
                    response.setTextFill(Color.RED);
                }
                else {
                    try {
                        ConfirmPopUp.display(username.getText());
                        if(ConfirmPopUp.choice == true) {
                            userType--;
                            tool.updateDatabase("UPDATE `login`.`credentials` SET `type` = '" + userType + "' WHERE (`username` = '" + username.getText() + "');");
                            response.setText("User with username: " + username.getText() + " has been promoted!");
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
                        response.setText("Unable to promote person try again later!");
                        username.setText("");
                        response.setTextFill(Color.RED);
                    }
                }

            }
        }
    }

}
