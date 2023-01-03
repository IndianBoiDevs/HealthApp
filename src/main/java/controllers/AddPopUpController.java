package controllers;

import assets.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddPopUpController implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private TextField birthday;

    @FXML
    private TextField bottomBloodPressure;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField city;

    @FXML
    private TextArea disabilities;

    @FXML
    private TextField email;

    @FXML
    private TextField feet;

    @FXML
    private TextField firstName;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField heartRate;

    @FXML
    private TextField id;

    @FXML
    private TextField inch;

    @FXML
    private TextField insurance;

    @FXML
    private TextField job;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;
    @FXML
    private TextField phoneNumber;

    @FXML
    private ComboBox<String> race;

    @FXML
    private TextArea responseBox;

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<String> state;

    @FXML
    private TextField topBloodPressure;

    @FXML
    private TextField userName;

    @FXML
    private TextField weight;

    @FXML
    private TextField zipCode;
    private LoginTool  tool;

    ObservableList<String> races = FXCollections.observableArrayList("White", "African American", "Asian", "Middle-Eastern", "European", "Native American", "Pacific Islander", "Other");
    ObservableList<String> states = FXCollections.observableArrayList("AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI"
            ,"ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT"
            ,"NE","NV","NH","NJ","NM","NY","NC","ND","OH"
            ,"OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY","AS","GU","MP","PR","VI","DC");
    ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female");

    @FXML
    void cancelPressed(ActionEvent event) throws IOException {

        //display the cancel popup
        CancelPopUp.display();

        if(CancelPopUp.choice) {
            AddPatientPopUp.close();
        }
    }

    @FXML
    void savePressed(ActionEvent event) throws IOException {
        if(allInformationProvided() == true){

            SavePopUp.display();

            if(SavePopUp.choice) {
                System.out.println("[Debug]: User has selected to submit data to be inserted into the database");

                //add the new user to the credentials table
                int credentialAdded = tool.addUser("INSERT INTO `login`.`credentials` (`username`, `password`, `email`, `type`) " +
                        "VALUES ('" + userName.getText().toString() + "', '" + password.getText().toString() + "', '"
                        + email.getText().toString() + "', '2');");

                //add user to the information table
                int information = tool.addUser("INSERT INTO `login`.`information` (`birthday`, `gender`, `firstName`, `lastName`, `address`, `phone`, `insurance`" +
                        ", `insuranceID`, `username`, `city`, `state`, `zipcode` , `race` , `occupation`) VALUES ('" + birthday.getText().toString() + "', '" + (String) gender.getValue() + "', '" + firstName.getText().toString() + "'" +
                        ", '" + lastName.getText().toString() + "', '" + address.getText().toString() + "', '" + phoneNumber.getText().toString() + "', '" + insurance.getText().toString() + "', '" + id.getText().toString()
                        + "', '" + userName.getText().toString() + "', '" + city.getText().toString() + "', '" + (String) state.getValue() + "', '" + zipCode.getText().toString() + "', '" + (String) race.getValue() + "', '" +
                        job.getText().toString() + "');");

                //if the user filled out optional fields
                checkOptionalFields(userName.getText().toString());

                if (credentialAdded != 0) {
                    System.out.println("Patient Registered successfully");

                } else {
                    System.out.println("Try Again: Patient not Registered");
                }

                AddPatientPopUp.close();
            }
        }
    }

    private void checkOptionalFields(String user){

        //update the height
        int f;
        double i;
        if(feet != null){
            try{
                f = Integer.parseInt(feet.getText().toString());
                if(inch != null){
                    i = Double.parseDouble(inch.getText().toString());
                    String combined = f + "-" + i;
                    String query = "UPDATE `login`.`information` SET `height` = '" + combined +"' WHERE (`username` = '" + user +"');";
                    tool.updateDatabase(query);
                }
            } catch (NumberFormatException e) {
                System.out.println("[Debug]: No new info added for height");
            }
        }

        //update the weight
        if(weight != null){
            try{
                Double.parseDouble(weight.getText().toString());
                String query = "UPDATE `login`.`information` SET `weight` = '" + weight.getText().toString() +"' WHERE (`username` = '" + user +"');";
                tool.updateDatabase(query);
            } catch (NumberFormatException e) {
                System.out.println("[Debug]: No new info added for weight");
            }
        }

        //update the blood pressure
        if(topBloodPressure != null){
            try{
                Integer.parseInt(topBloodPressure.getText().toString());
                if(bottomBloodPressure != null){
                    Integer.parseInt(bottomBloodPressure.getText().toString());
                    String combined = topBloodPressure.getText().toString() + "/" + bottomBloodPressure.getText().toString();
                    String query = "UPDATE `login`.`information` SET `bloodPressure` = '" + combined +"' WHERE (`username` = '" + user +"');";
                    tool.updateDatabase(query);
                }
            } catch (NumberFormatException e) {
                System.out.println("[Debug]: No new info added for bloodPressure");
            }
        }

        //update the heart rate
        if(heartRate != null){
            try{
                Integer.parseInt(heartRate.getText().toString());
                String query = "UPDATE `login`.`information` SET `heartRate` = '" + heartRate.getText().toString() +"' WHERE (`username` = '" + user +"');";
                tool.updateDatabase(query);
            } catch (NumberFormatException e) {
                System.out.println("[Debug]: No new info added for heartRate");
            }
        }

        //update the disabilities
        if(disabilities != null){
            String query = "UPDATE `login`.`information` SET `disabilities` = '" + disabilities.getText().toString() +"' WHERE (`username` = '" + user +"');";
            tool.updateDatabase(query);
        }
    }

    private boolean allInformationProvided(){

        responseBox.setText("");
        String warning = "";

        //check username
        String user = userName.getText().toString();
        if(user.length() == 0){
            //if the user fails to provide us with a username
            warning = warning + " - Please provide a username" + "\n";
        }
        else{
            //user gives us a username check if this user exists in the system.
            ResultSet result  = tool.runQuery("SELECT username\n" +
                    "FROM login.credentials\n" +
                    "WHERE username = '" + user + "'");

            //if the result is null
            if(result == null){
                userName.setText("");
                System.out.println("[Debug]: Username is unavailable!");
                warning = warning + " - Please provide a different username" + "\n";
            }
            else{
                try {
                    if (result.next() == false) {
                        System.out.println("[Debug]: Username is available!");
                    } else {
                        userName.setText("");
                        System.out.println("[Debug]: Username is unavailable!");
                        warning = warning + " - Please provide a different username" + "\n";
                    }
                } catch (SQLException e) {
                    userName.setText("");
                    System.out.println("[Debug]: Username is unavailable and result set failed to return properly!");
                    warning = warning + " - Please provide a different username" + "\n";
                }
            }

        }

        //check password
        String pass = password.getText().toString();
        String passConfirmed = confirmPassword.getText().toString();
        if(pass.length() == 0){
            //if the user fails to provide us with a username
            warning = warning + " - Please provide a password" + "\n";
        }
        if(passConfirmed.length() == 0){
            //if the user fails to provide us with a username
            warning = warning + " - Please confirm your password" + "\n";
        }
        if(pass.length() != 0) {
            if (pass.length() <= 5) {
                password.setText("");
                confirmPassword.setText("");
                warning = warning + " - Please make password more than 5 characters" + "\n";
            }
            if (pass.equals(passConfirmed) != true){
                password.setText("");
                confirmPassword.setText("");
                warning = warning + " - Please match the passwords in the password fields" + "\n";
            }
        }

        //check phone number
        String phoneNum = phoneNumber.getText().toString();
        if(phoneNum.length() == 0){
            //if the user fails to provide us with a phone number
            warning = warning + " - Please provide a phone number" + "\n";
        }
        if(phoneNum.length() != 0 && phoneNum.length() != 10) {
            warning = warning + " - Please provide a valid ten digit phone number" + "\n";
            phoneNumber.setText("");
        }

        //check address
        String addr = address.getText().toString();
        if(addr.length() == 0){
            //if the user fails to provide us with a address
            warning = warning + " - Please provide an address" + "\n";
        }

        //get the zip code
        String cityHere = city.getText().toString();
        if(cityHere.length() == 0){
            //if the user fails to provide us with a zip
            warning = warning + " - Please provide an city" + "\n";
        }

        //check if state is provided
        if(state.getValue() == null){
            warning = warning + " - Please provide a state" + "\n";
        }

        //get the zip code
        String zipcode = zipCode.getText().toString();
        if(zipcode.length() == 0){
            //if the user fails to provide us with a zip
            warning = warning + " - Please provide an zip code" + "\n";
        }
        if(zipcode.length() != 0 && zipcode.length() != 5){
            warning = warning + " - Please provide a valid five digit zip code" + "\n";
            zipCode.setText("");
        }

        //check insurance
        String insure = insurance.getText().toString();
        if(insure.length() == 0){
            //if the user fails to provide us with a insurance
            warning = warning + " - Please provide an insurance" + "\n";
        }

        //check insurance id
        String insureId = id.getText().toString();
        if(insureId.length() == 0){
            //if the user fails to provide us with a insurance id
            warning = warning + " - Please provide an insurance id" + "\n";
        }

        //check email
        String emailAddress = email.getText().toString();
        if(emailAddress.length() == 0){
            //if the user fails to provide us with a email
            warning = warning + " - Please provide an email" + "\n";
        }

        //check firstName
        String fName = firstName.getText().toString();
        if(fName.length() == 0){
            //if the user fails to provide us with a first name
            warning = warning + " - Please provide a first name" + "\n";
        }

        //check LastName
        String lName = lastName.getText().toString();
        if(lName.length() == 0){
            //if the user fails to provide us with a last name
            warning = warning + " - Please provide a last name" + "\n";
        }

        //check gender
        String genderValue = (String) gender.getValue();
        if(genderValue == null){
            warning = warning + " - Please provide a gender" + "\n";
        }

        //check race
        String raceValue = (String) race.getValue();
        if(raceValue == null){
            warning = warning + " - Please provide a race" + "\n";
        }

        //check job
        if(job == null){
            warning = warning + " - Please provide a occupation" + "\n";
        }

        //check the birthday
        if(birthday == null){
            warning = warning + " - Please provide a birthday" + "\n";
        }
        else{
            String bDay = birthday.getText().toString();
            ArrayList<String> entry = new ArrayList<>(Arrays.asList(bDay.split("-")));
            if(entry.size() == 3){
                try{
                    int y = Integer.parseInt(entry.get(0));
                    int m = Integer.parseInt(entry.get(1));
                    int d = Integer.parseInt(entry.get(2));

                    if(d <= 0){
                        warning = warning + " - Please provide a valid day!" + "\n";
                    }
                    if(y <= 0){
                        warning = warning + " - Please provide a valid year!" + "\n";
                    }
                    if(m > 12 || m <= 0){
                        warning = warning + " - Please provide a month from 1-12!" + "\n";
                    }
                    if(m == 2){
                        if(y % 4 == 0){
                            if(d >= 30){
                                warning = warning + " - Please provide a day from 1-29!" + "\n";
                            }
                        }
                        else{
                            if(d >= 29){
                                warning = warning + " - Please provide a day from 1-28!" + "\n";
                            }
                        }
                    }
                    if(m == 1 || m ==3 || m ==5 | m == 7 || m == 8 || m == 10 || m == 12){
                        if(d >= 32){
                            warning = warning + " - Please provide a day from 1-31!" + "\n";
                        }
                    }
                    if(m== 4 || m== 6 || m == 9 || m == 11){
                        if(d >= 31){
                            warning = warning + " - Please provide a day from 1-30!" + "\n";
                        }
                    }

                } catch (NumberFormatException e) {
                    warning = warning + " - Please provide birthday in YYYY-MM-DD format and only integers allowed!" + "\n";
                }
            }
            else{
                warning = warning + " - Please provide birthday in YYYY-MM-DD format!" + "\n";
            }

        }

        //Display the error messages
        responseBox.setText(warning);

        //if the user has no warnings
        if(warning.length() == 0){
            return true;
        }

        //if the user has some warnings
        return false;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tool = new LoginTool();
        race.setItems(races);
        state.setItems(states);
        gender.setItems(genders);
    }
}
