package controllers;

import assets.LoginTool;
import assets.Message;
import assets.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.Collections;

public class Signup {

    //create a stage manager
    private static StageManager manager = new StageManager();

    @FXML
    private Button backButton;
    @FXML
    private Button submitButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private TextField city;
    @FXML
    private TextField zip;
    @FXML
    private ComboBox state;
    @FXML
    private TextField insurance;
    @FXML
    private TextField id;
    @FXML
    private TextField email;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextArea response;
    @FXML
    private ComboBox month;
    @FXML
    private ComboBox day;
    @FXML
    private ComboBox year;

    @FXML
    private ComboBox gender;

    ObservableList<String> states = FXCollections.observableArrayList("AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI"
            ,"ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT"
            ,"NE","NV","NH","NJ","NM","NY","NC","ND","OH"
            ,"OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY","AS","GU","MP","PR","VI","DC");
    ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female");

    ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    ObservableList<String> years = FXCollections.observableArrayList();

    ObservableList<String> thirtyOneDays = FXCollections.observableArrayList();

    ObservableList<String> thirtyDays = FXCollections.observableArrayList();

    ObservableList<String> leapYear = FXCollections.observableArrayList();

    ObservableList<String> commonYear = FXCollections.observableArrayList();

    boolean isLeapYear;

    LoginTool tool = new LoginTool();

    @FXML
    private void initialize(){

        //set up the days of the month
        for(int i = 1; i < 32; i++){

            thirtyOneDays.add(i + "");

            if(i < 31){
                thirtyDays.add(i + "");
            }

            if(i < 30){
                leapYear.add(i + "");
            }

            if(i < 29){
                commonYear.add(i + "");
            }

        }
        System.out.println("[Debug]: Size of thirtyOneDays is " + thirtyOneDays.size());
        System.out.println("[Debug]: Size of thirtyDays is " + thirtyDays.size());
        System.out.println("[Debug]: Size of leapYear is " + leapYear.size());
        System.out.println("[Debug]: Size of commonYear is " + commonYear.size());

        //setup the collection for years
        for(int i = 2023; i > 1899; i--){
            years.add( i + "");
        }
        System.out.println("[Debug]: Size of Years is " + years.size());

        //set the state combo box
        state.setItems(states);
        System.out.println("[Debug]: Size of States is " + states.size());

        //setup the gender combo box
        gender.setItems(genders);

        //setup the month combo
        month.setItems(months);

        year.setItems(years);

        setComboBoxForDay();

    }
    public void backPressed(ActionEvent a) throws IOException {
        System.out.println("[Debug]: User has selected to go back to login");
        //change the scene to the sign up
        manager.changeScene("login//login.fxml");
    }

    private void setComboBoxForDay(){
        String monthChosen = (String) month.getValue();
        String yearChosen = (String) year.getValue();

        if(yearChosen == null || monthChosen == null) {
            day.setItems(commonYear);
        }

        else{
            // February case
            if(monthChosen.equals("February") ) {
                if(isLeapYear == true){
                    day.setItems(leapYear);
                }
                else {
                    day.setItems(commonYear);
                }
            }
            //Months that have 30 days: April, June, Sept, Nov
            else if(monthChosen.equals("April") || monthChosen.equals("June") || monthChosen.equals("September") || monthChosen.equals("November")) {
                day.setItems(thirtyDays);
            }
            //Months that have 31 days: Jan, March, May, July, Aug, Oct, Dec
            else{
                day.setItems(thirtyOneDays);
            }
        }

    }

    private String getFormattedDate(){
        //yyyy-mm-dd
        String date = "";
        date = date + (String) year.getValue() + "-";

        //calculate the integer of the month
        String monthSelected = (String) month.getValue();
        int monthHere = months.indexOf(monthSelected) + 1;
        //append a zero to the beginning if you are between 1-9
        if(monthHere < 10 && monthHere > -1){
            date = date + "0" + monthHere + "-";
        }
        else{
            //otherwise if it is a month that is in the double digits just append
            date = date + monthHere + "-";
        }

        //set the day
        int dayHere = Integer.parseInt((String) day.getValue());
        if(dayHere < 10 && dayHere > -1){
            date = date + "0" + dayHere;
        }
        else{
            //otherwise if it is a month that is in the double digits just append
            date = date + dayHere;
        }

        return date;

    }
    public void monthSelected(){
        setComboBoxForDay();
    }

    public void yearSelected(){
        int intYear = Integer.parseInt((String) year.getValue());
        System.out.println("[Debug]: The User has selected " + intYear);

        //check if the year is a leap year
        if(intYear % 4 == 0 ){
            isLeapYear = true;
            System.out.println("[Debug]: This is a leap year!");
        }
        else{
            isLeapYear = false;
            System.out.println("[Debug]: This is not a leap year!");
        }
        setComboBoxForDay();
    }

    public void submitPressed(ActionEvent a) throws IOException {

        if(allInformationProvided() == true) {
            System.out.println("[Debug]: User has selected to submit data to be inserted into the database");
            //change the scene to the sign-up
            Message m = new Message();

            //add the new user to the credentials table
            int credentialAdded = tool.addUser("INSERT INTO `login`.`credentials` (`username`, `password`, `email`, `type`) " +
                    "VALUES ('" + username.getText().toString() + "', '"+password.getText().toString()+"', '"
                    + email.getText().toString() +"', '2');");
            String date = getFormattedDate();
            //add user to the information table
            int information = tool.addUser("INSERT INTO `login`.`information` (`birthday`, `gender`, `firstName`, `lastName`, `address`, `phone`, `insurance`" +
                    ", `insuranceID`, `username`, `city`, `state`, `zipcode`) VALUES ('"+ date +"', '"+ (String) gender.getValue() +"', '"+ firstName.getText().toString() +"'" +
                    ", '"+ lastName.getText().toString() +"', '"+ address.getText().toString()+"', '"+ phone.getText().toString() +"', '"+ insurance.getText().toString()+"', '"+ id.getText().toString()
                    +"', '"+ username.getText().toString()+"', '"+ city.getText().toString()+"', '"+  (String) state.getValue() +"', '"+  zip.getText().toString() +"');");

            if(credentialAdded!= 0) {
                m.setMessage("Patient Registered successfully");
                manager.changeScene("login//login.fxml");
            }
            else{
                m.setMessage("Try Again: Patient not Registered");
                manager.changeScene("login//login.fxml");
            }
        }
    }

    private boolean allInformationProvided(){
        response.setText("");
        String warning = "";

        //check username
        String user = username.getText().toString();
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
                username.setText("");
                System.out.println("[Debug]: Username is unavailable!");
                warning = warning + " - Please provide a different username" + "\n";
            }
            else{
                try {
                    if (result.next() == false) {
                        System.out.println("[Debug]: Username is available!");
                    } else {
                        username.setText("");
                        System.out.println("[Debug]: Username is unavailable!");
                        warning = warning + " - Please provide a different username" + "\n";
                    }
                } catch (SQLException e) {
                    username.setText("");
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
        String phoneNum = phone.getText().toString();
        if(phoneNum.length() == 0){
            //if the user fails to provide us with a phone number
            warning = warning + " - Please provide a phone number" + "\n";
        }
        if(phoneNum.length() != 0 && phoneNum.length() != 10) {
            warning = warning + " - Please provide a valid ten digit phone number" + "\n";
            phone.setText("");
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
        String zipCode = zip.getText().toString();
        if(zipCode.length() == 0){
            //if the user fails to provide us with a zip
            warning = warning + " - Please provide an zip code" + "\n";
        }
        if(zipCode.length() != 0 && zipCode.length() != 5){
            warning = warning + " - Please provide a valid five digit zip code" + "\n";
            zip.setText("");
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

        //check year, month and day
        if(year.getValue() == null){
            warning = warning + " - Please provide a year" + "\n";
        }
        if(month.getValue() == null){
            warning = warning + " - Please provide a month" + "\n";
        }
        if(day.getValue() == null){
            warning = warning + " - Please provide a day" + "\n";
        }

        //Display the error messages
        response.setText(warning);

        //if the user has no warnings
        if(warning.length() == 0){
            return true;
        }

        //if the user has some warnings
        return false;
    }

}
