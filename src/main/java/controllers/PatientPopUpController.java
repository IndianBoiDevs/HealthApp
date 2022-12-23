package controllers;

import assets.LoginTool;
import assets.PatientPopUp;
import assets.Person;
import assets.PersonHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatientPopUpController implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private TextField bloodPressure;

    @FXML
    private TextField bmi;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField city;

    @FXML
    private TextArea disabilities;

    @FXML
    private TextField firstName;

    @FXML
    private TextField gender;

    @FXML
    private TextField heartRate;

    @FXML
    private TextField height;

    @FXML
    private TextField id;

    @FXML
    private TextField insurance;

    @FXML
    private TextField job;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField race;

    @FXML
    private Button saveButton;

    @FXML
    private TextField state;

    @FXML
    private TextField weight;

    @FXML
    private TextField zipCode;

    @FXML
    private TextField birthday;

    private Person current;

    private LoginTool tool;
    @FXML
    void cancelPressed(ActionEvent event) {
        PatientPopUp.close();
    }

    @FXML
    void savePressed(ActionEvent event) {
        PatientPopUp.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //get the selected user
        current = PersonHelper.getSelectedUser();

        //fill in the information
        firstName.setText(current.getFirstName());
        lastName.setText(current.getLastName());
        id.setText(current.getInsuranceId());
        birthday.setText(current.getDateOfBirth());
        insurance.setText(current.getInsurance());

        //create a loginTool Object
        tool = new LoginTool();

        //execute query
        ResultSet r = tool.runQuery("SELECT * FROM login.information\n" +
                "where username = '" + current.getUserName() + "';");

        try {
            while(r.next()) {
                System.out.println(r.getString("username"));
                gender.setText(r.getString("gender"));
                race.setText(r.getString("race"));
                phoneNumber.setText(r.getString("phone"));
                job.setText(r.getString("occupation"));
                height.setText(r.getString("height"));
                address.setText(r.getString("address"));
                zipCode.setText(r.getString("zipcode"));
                weight.setText(r.getString("weight"));
                city.setText(r.getString("city"));
                state.setText(r.getString("state"));
                bloodPressure.setText(r.getString("bloodPressure"));
                heartRate.setText(r.getString("heartRate"));
                disabilities.setText(r.getString("disabilities"));

//                try{
//                    int weightHere = Integer.parseInt(r.getString("weight"));
//                    String heightReceived = r.getString("height");
//                    String feet;
//                    String inches;
//
//                    for(int i; i < )
//                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

