package controllers;

import assets.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class PatientPopUpController implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private TextField topBloodPressure;
    @FXML
    private TextField bottomBloodPressure;

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
    private ComboBox gender;

    @FXML
    private TextField heartRate;

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
    private ComboBox race;

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox state;

    @FXML
    private TextField weight;

    @FXML
    private TextField zipCode;

    @FXML
    private TextField birthday;

    @FXML
    private TextField feet;

    @FXML
    private TextField inch;

    private Person current;

    private LoginTool tool;
    ObservableList<String> races = FXCollections.observableArrayList("White", "African American", "Asian", "Middle-Easter", "European", "Native American", "Pacific Islander", "Other");
    ObservableList<String> states = FXCollections.observableArrayList("AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI"
            ,"ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT"
            ,"NE","NV","NH","NJ","NM","NY","NC","ND","OH"
            ,"OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY","AS","GU","MP","PR","VI","DC");
    ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female");
    @FXML
    void cancelPressed(ActionEvent event) {
        PatientPopUp.close();
    }

    @FXML
    void savePressed(ActionEvent event) {
        String stuffToUpdate = "";
        System.out.println("==========================================");
        //get all fields and do checks
        String bDay = birthday.getText();
        if(bDay != null){
            if(bDay.length() != 0){
                if(bDay.equals(current.getDateOfBirth())){
                    System.out.println("[Debug]: Date Of Birth has not changed!");
                }
                else{
                    try{
                        ArrayList<String> here = new ArrayList<>(Arrays.asList(bDay.split("-")));
                        if(here.size() != 3){
                            birthday.setText(current.getDateOfBirth());
                        }
                        else{
                            int year = Integer.parseInt(here.get(0));
                            int day = Integer.parseInt(here.get(0));
                            int month = Integer.parseInt(here.get(0));
                            System.out.println("[Debug]: Date Of Birth has changed!");
                            stuffToUpdate = stuffToUpdate + " `birthday` = '" + bDay + "'";
                            System.out.println("[Debug]: Staff has updated Birthday! ");
                        }
                    }
                    catch (Exception e){
                        birthday.setText(current.getDateOfBirth());
                    }
                }
            }
            else{
                birthday.setText(current.getDateOfBirth());
            }
        }
        else{
            birthday.setText(current.getDateOfBirth());
        }

        String fName = firstName.getText();
        if(fName != null){
            if(fName.length() != 0){
                if(!fName.equals(current.getFirstName())){
                    if(stuffToUpdate.length() == 0) {
                        stuffToUpdate = stuffToUpdate + "`firstName` = '" + firstName.getText() + "'";

                    }
                    else{
                        stuffToUpdate = stuffToUpdate  + "', `firstName` = '" + firstName.getText() +"'";
                    }
                    System.out.println("[Debug]: Staff has updated First Name! ");
                }
            }
        }

        String lName = lastName.getText();
        if(lName != null){
            if(lName.length() != 0){
                if(!lName.equals(current.getLastName())){
                    if(stuffToUpdate.length() == 0) {
                        stuffToUpdate = stuffToUpdate + "'`lastName` = '" + lastName.getText() +"'";

                    }
                    else{
                        stuffToUpdate = stuffToUpdate  +"', `lastName` = '" + lastName.getText() +"'";
                    }
                    System.out.println("[Debug]: Staff has updated lastName! ");
                }
            }
        }

        String addy = address.getText();
        if(addy != null){
            if(addy.length() != 0){
                if(!addy.equals(current.getAddress())){
                    if(stuffToUpdate.length() == 0) {
                        stuffToUpdate = stuffToUpdate +  "`address` = '" + address.getText() +"'";

                    }
                    else{
                        stuffToUpdate = stuffToUpdate   + ", `address` = '" + address.getText() +"'";
                    }
                    System.out.println("[Debug]: Staff has updated Address! ");
                }
            }
        }

        String num = phoneNumber.getText();
        if(num != null){
            if(num.length() == 10){
                try{
                    Integer.parseInt(num);

                    if(!num.equals(current.getPhone())){
                        if(stuffToUpdate.length() == 0) {
                            stuffToUpdate = stuffToUpdate +  " `phone` = '" + phoneNumber.getText() +"'";

                        }
                        else{
                            stuffToUpdate = stuffToUpdate   + ", `phone` = '" + phoneNumber.getText() + "'";
                        }
                        System.out.println("[Debug]: Staff has updated PhoneNumber! ");
                    }
                } catch (Exception e) {
                    System.out.println("Incorrect Phone Number provided");
                }
            }
        }

        String in = insurance.getText();
        if(in != null){
            if(in.length() != 0){
                if(!in.equals(current.getInsurance())){
                    if(stuffToUpdate.length() == 0) {
                        stuffToUpdate = stuffToUpdate +  " `insurance` = '" + insurance.getText() + "'";

                    }
                    else{
                        stuffToUpdate = stuffToUpdate   + ", `insurance` = '" + insurance.getText() +"'";
                    }
                    System.out.println("[Debug]: Staff has updated Insurance! ");
                }
            }
        }

        String inId = id.getText();
        if(inId != null){
            if(inId.length() != 0){
                try{
                    Integer.parseInt(inId);
                    if(!inId.equals(current.getInsuranceId())){
                        if(stuffToUpdate.length() == 0) {
                            stuffToUpdate = stuffToUpdate +  " `insuranceID` = '" + id.getText() +"'";

                        }
                        else{
                            stuffToUpdate = stuffToUpdate   + ", `insuranceID` = '" + id.getText() +"'";
                        }
                        System.out.println("[Debug]: Staff has updated Insurance ID! ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect id given!");
                }
            }
        }

        String ci = city.getText();

        if(ci != null){
            if(current.getCity() != null){
                if(!ci.equals(current.getCity())){
                    if(stuffToUpdate.length() == 0) {
                        stuffToUpdate = stuffToUpdate +  " ``city` = '" + city.getText() + "'";

                    }
                    else{
                        stuffToUpdate = stuffToUpdate   + "', `city` = '" + city.getText() + "'";
                    }
                    System.out.println("[Debug]: Staff has updated City! ");
                }
            }
        }

        String st = (String) state.getValue();
        if(st != null){
            if(!st.equals(current.getState())){
                if(stuffToUpdate.length() == 0) {
                    stuffToUpdate = stuffToUpdate +  "`state` = '" + state.getValue() + "'";

                }
                else{
                    stuffToUpdate = stuffToUpdate   + ", `state` = '" + state.getValue() + "'";
                }
                System.out.println("[Debug]: Staff has updated state! ");
            }
        }

        String zip = zipCode.getText();
        if(zip != null){
            if(zip.length() == 5){
                if(!zip.equals(current.getZipCode())){
                    try{
                        Integer.parseInt(zip);

                        if(stuffToUpdate.length() == 0) {
                            stuffToUpdate = stuffToUpdate +  "`zipcode` = '" + zipCode.getText() + "'";

                        }
                        else{
                            stuffToUpdate = stuffToUpdate   + ", `zipcode` = '" + zipCode.getText() + "'";
                        }

                        System.out.println("[Debug]: Staff has updated zipcode! ");
                    } catch (Exception e) {
                        System.out.println("Invalid zip!");
                    }
                }
            }
        }

        String w = weight.getText();
        if(w != null){
            try {
                Double.parseDouble(w);
                if (current.getWeight() == null) {
                    if(stuffToUpdate.length() == 0) {
                        stuffToUpdate = stuffToUpdate +  "`weight` = '" + weight.getText() +"'";

                    }
                    else{
                        stuffToUpdate = stuffToUpdate   + ", `weight` = '" + weight.getText() +"'";
                    }
                    System.out.println("[Debug]: Staff has updated weight! ");
                } else {
                    if(!w.equals(current.getWeight())){
                        if(stuffToUpdate.length() == 0) {
                            stuffToUpdate = stuffToUpdate +  "`weight` = '" + weight.getText() +"'";

                        }
                        else{
                            stuffToUpdate = stuffToUpdate   + ", `weight` = '" + weight.getText() +"'";
                        }
                        System.out.println("[Debug]: Staff has updated weight! ");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid weight!");
            }
        }

        String ra = (String) race.getValue();
        if(ra != null){
            if(current.getRace() == null){
                if(stuffToUpdate.length() == 0) {
                    stuffToUpdate = stuffToUpdate +  "`race` = '" + race.getValue() +"'";

                }
                else{
                    stuffToUpdate = stuffToUpdate   + ", `race` = '" + race.getValue() +"'";
                }
                System.out.println("[Debug]: Staff has updated race! ");
            }
            else{
                if(!ra.equals(current.getRace())){
                    if(stuffToUpdate.length() == 0) {
                        stuffToUpdate = stuffToUpdate +  "`race` = '" + race.getValue() +"'";

                    }
                    else{
                        stuffToUpdate = stuffToUpdate   + ", `race` = '" + race.getValue() +"'";
                    }
                    System.out.println("[Debug]: Staff has updated race! ");
                }
            }
        }

        String jo = job.getText();
        if(jo != null){
            if(jo.length() > 0){
                if(current.getOccupation() == null){
                    if(stuffToUpdate.length() == 0) {
                        stuffToUpdate = stuffToUpdate +  "`occupation` = '" + job.getText() + "'";

                    }
                    else{
                        stuffToUpdate = stuffToUpdate   + ", `occupation` = '" + job.getText() +"'";
                    }
                    System.out.println("[Debug]: Staff has updated Job! ");
                }
                else{
                    if(!jo.equals(current.getOccupation())){
                        if(stuffToUpdate.length() == 0) {
                            stuffToUpdate = stuffToUpdate +  "`occupation` = '" + job.getText() + "'";

                        }
                        else{
                            stuffToUpdate = stuffToUpdate   + ", `occupation` = '" + job.getText() +"'";
                        }
                        System.out.println("[Debug]: Staff has updated Job! ");
                    }
                }
            }
        }

        String h = feet.getText() + "-" + inch.getText();
        if(feet.getText() != null && inch.getText() != null){
            try{

                Integer.parseInt(feet.getText());
                Integer.parseInt(inch.getText());

                if(current.getHeight() == null){
                    if(stuffToUpdate.length() == 0) {
                        stuffToUpdate = stuffToUpdate +  "`height` = '" + h + "'";

                    }
                    else{
                        stuffToUpdate = stuffToUpdate   + ", `height` = '" + h +"'";
                    }
                    System.out.println("[Debug]: Staff has updated height! ");
                }
                else{
                    if(!h.equals(current.getHeight())){
                        if(stuffToUpdate.length() == 0) {
                            stuffToUpdate = stuffToUpdate +  "`height` = '" + h + "'";

                        }
                        else{
                            stuffToUpdate = stuffToUpdate   + ", `height` = '" + h +"'";
                        }
                        System.out.println("[Debug]: Staff has updated height! ");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid heart height!");
            }

        }

        String bp = topBloodPressure.getText() + "/" + bottomBloodPressure.getText();
        if(topBloodPressure != null && bottomBloodPressure != null){
            try{
                Integer.parseInt(topBloodPressure.getText());
                Integer.parseInt(bottomBloodPressure.getText());

                if(current.getBloodPressure() == null){
                    if(stuffToUpdate.length() == 0) {
                        stuffToUpdate = stuffToUpdate +  "`bloodPressure` = '" + bp + "'";

                    }
                    else{
                        stuffToUpdate = stuffToUpdate   + ", `bloodPressure` = '" + bp +"'";
                    }
                    System.out.println("[Debug]: Staff has updated bloodPressure! ");
                }
                else{
                    if(!bp.equals(current.getBloodPressure())){
                        if(stuffToUpdate.length() == 0) {
                            stuffToUpdate = stuffToUpdate +  "`bloodPressure` = '" + bp + "'";

                        }
                        else{
                            stuffToUpdate = stuffToUpdate   + ", `bloodPressure` = '" + bp +"'";
                        }
                        System.out.println("[Debug]: Staff has updated bloodPressure! ");
                    }

                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Blood Pressure!");
            }

        }

        String hr = heartRate.getText();
        if(hr != null){
            try{
                Integer.parseInt(hr);
                if(current.getHeartRate() == null){
                    if(stuffToUpdate.length() == 0) {
                        stuffToUpdate = stuffToUpdate +  "`heartRate` = '" + heartRate.getText() + "'";

                    }
                    else{
                        stuffToUpdate = stuffToUpdate   + ", `heartRate` = '" + heartRate.getText() +"'";
                    }
                    System.out.println("[Debug]: Staff has updated heart rate! ");
                }
                else{
                    if(!hr.equals(current.getHeartRate())){
                        if(stuffToUpdate.length() == 0) {
                            stuffToUpdate = stuffToUpdate +  "`heartRate` = '" + heartRate.getText() + "'";

                        }
                        else{
                            stuffToUpdate = stuffToUpdate   + ", `heartRate` = '" + heartRate.getText() +"'";
                        }
                        System.out.println("[Debug]: Staff has updated heart rate! ");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid heart rate!");
            }

        }

        String dis = disabilities.getText();
        if(dis != null){
            if(current.getDisability() == null){
                if(stuffToUpdate.length() == 0) {
                    stuffToUpdate = stuffToUpdate +  "`disabilities` = '" + disabilities.getText() + "'";

                }
                else{
                    stuffToUpdate = stuffToUpdate   + ", `disabilities` = '" + disabilities.getText() +"'";
                }
                System.out.println("[Debug]: Staff has updated disabilities! ");
            }
            else{
                if(!dis.equals(current.getDisability())){
                    if(stuffToUpdate.length() == 0) {
                        stuffToUpdate = stuffToUpdate +  "`disabilities` = '" + disabilities.getText() + "'";

                    }
                    else{
                        stuffToUpdate = stuffToUpdate   + ", `disabilities` = '" + disabilities.getText() +"'";
                    }
                    System.out.println("[Debug]: Staff has updated disabilities! ");
                }
            }
        }

        System.out.println("==========================================");

        if(stuffToUpdate.length() == 0){
            System.out.println("[Debug]: Nothing to update!");
        }
        else {
            //run sql update command
            int updated = tool.updateDatabase("UPDATE `login`.`information` SET " + stuffToUpdate +
                    "WHERE (`username` = '" + current.getUserName() + "');");

            if (updated >= 1) {
                System.out.println("[Debug]: User: " + current.getUserName() + " has been updated");
            } else {
                System.out.println("[Error]: User: " + current.getUserName() + " has not been updated");
            }
        }

        //close the dialog
        PatientPopUp.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //setup the combo boxes
        gender.setItems(genders);
        state.setItems(states);

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

                //setup person
                current.setGender(r.getString("gender"));
                current.setRace(r.getString("race"));
                current.setPhone(r.getString("phone"));
                current.setOccupation(r.getString("occupation"));
                current.setAddress(r.getString("address"));
                current.setZipCode(r.getString("zipcode"));
                current.setWeight(r.getString("weight"));
                current.setCity(r.getString("city"));
                current.setState(r.getString("state"));
                current.setHeartRate(r.getString("heartRate"));
                current.setDisability(r.getString("disabilities"));
                current.setBloodPressure(r.getString("bloodPressure"));
                current.setHeight(r.getString("height"));

                System.out.println(r.getString("username"));

                //set the gender value
                String g = r.getString("gender");
                gender.setValue(g);

                //set race
                String ra = r.getString("race");
                race.setItems(races);
                if(ra != null){
                    race.setValue(ra);
                }

                phoneNumber.setText(r.getString("phone"));
                job.setText(r.getString("occupation"));

                address.setText(r.getString("address"));
                zipCode.setText(r.getString("zipcode"));


                //get wieght
                double wValue = 0;
                String givenWeight = r.getString("weight");

                if(givenWeight != null){
                    try {
                        wValue = Double.parseDouble(givenWeight);
                    }
                    catch (Exception e){
                        System.out.println("[Error]: Not valid weight!!!");
                    }
                    weight.setText( "" + wValue);
                }

                weight.setText(r.getString("weight"));
                city.setText(r.getString("city"));

                //set the state value
                String s = r.getString("state");
                state.setValue(s);

                heartRate.setText(r.getString("heartRate"));
                disabilities.setText(r.getString("disabilities"));

                //fill with blood pressure info
                String bp = r.getString("bloodPressure");
                if(bp != null) {
                    //split the String given
                    ArrayList<String> entry = new ArrayList<>(Arrays.asList(bp.split("/")));
                    if(entry.size() == 2) {
                        topBloodPressure.setText(entry.get(0));
                        bottomBloodPressure.setText(entry.get(1));
                    }
                }

                //fill in feet and inches
                String height = r.getString("height");
                if(height != null) {
                    //split the String given
                    ArrayList<String> entry = new ArrayList<>(Arrays.asList(height.split("-")));

                    if(entry.size() == 2) {
                        feet.setText(entry.get(0));
                        inch.setText(entry.get(1));

                        //convert to get the BMI
                        int fValue = Integer.parseInt(entry.get(0));
                        double iValue = Double.parseDouble(entry.get(1));

                        double hValue = fValue * 12;
                        hValue = hValue + iValue;

                        bmi.setText(current.getBmi(wValue, hValue));
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}

