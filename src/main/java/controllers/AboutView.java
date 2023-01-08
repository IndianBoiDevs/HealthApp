package controllers;

import assets.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import java.util.ArrayList;
import java.util.Arrays;

public class AboutView implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private TextField bloodpressure;

    @FXML
    private TextField bmi;

    @FXML
    private TextField city;

    @FXML
    private TextArea disabilities;

    @FXML
    private TextField dob;

    @FXML
    private TextField email;

    @FXML
    private TextField feet;

    @FXML
    private TextField first_name;

    @FXML
    private TextField gender;

    @FXML
    private TextField heartrate;

    @FXML
    private TextField inch;

    @FXML
    private TextField insurance;

    @FXML
    private TextField insuranceid;

    @FXML
    private TextField last_name;

    @FXML
    private TextField occupation;

    @FXML
    private TextField phone;

    @FXML
    private TextField race;

    @FXML
    private TextField state;

    @FXML
    private Label title_name;

    @FXML
    private ImageView user_image;

    @FXML
    private TextField weight;

    @FXML
    private TextField zip;
    @FXML
    private Button saveBt;
    @FXML
    private Button cancelBt;

    @FXML
    private Button edit;

    private String job;
    private String gmail;
    private String phoneNum;
    private String in;
    private String inId;
    private String addr;
    private String st;
    private String ci;
    private String zipCode;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        saveBt.setVisible(false);
        cancelBt.setVisible(false);
        saveBt.setDisable(true);
        cancelBt.setDisable(true);

        refresh();

    }

    private void refresh(){
        String personName = PersonHelper.getCurrentUser();
        LoginTool tool = new LoginTool();

        Person person = new Person();

        ResultSet query = tool.runQuery("SELECT * FROM login.information WHERE username = '" + personName + "';");

        try {
            while (query.next()) {
                first_name.setText(query.getString("firstName"));
                last_name.setText(query.getString("lastName"));
                race.setText(query.getString("race"));
                gender.setText(query.getString("gender"));
                dob.setText(query.getString("birthday"));

                job = query.getString("occupation");

                phoneNum = query.getString("phone");
                in = query.getString("insurance");
                inId = query.getString("insuranceID");
                addr = query.getString("address");
                st = query.getString("state");
                ci = query.getString("city");
                zipCode = query.getString("zipcode");

                occupation.setText(job);
                phone.setText(phoneNum);
                insuranceid.setText(inId);
                insurance.setText(in);
                address.setText(addr);
                state.setText(st);
                city.setText(ci);
                zip.setText(zipCode);

                bloodpressure.setText(query.getString("bloodPressure"));
                heartrate.setText(query.getString("heartRate"));
                disabilities.setText(query.getString("disabilities"));
                weight.setText(query.getString("weight"));


                double weight = -1;
                String feetInfo = " ";
                String inchInfo = " ";
                if (query.getString("weight") != null) {
                    weight = Double.parseDouble(query.getString("weight"));
                }

                double height = -1;
                if (query.getString("height") != null) {
                    String rawHeight = query.getString("height");
                    ArrayList<String> entry = new ArrayList<>(Arrays.asList(rawHeight.split("-")));

                    if(entry.size() == 2) {
                        feetInfo = entry.get(0);
                        inchInfo = entry.get(1);
                        //convert to get the BMI
                        int fValue = Integer.parseInt(entry.get(0));
                        double iValue = Double.parseDouble(entry.get(1));

                        double hValue = fValue * 12;
                        hValue = hValue + iValue;
                        height = hValue;
                    }

                    String bmiValue = "";

                    if(weight > -1 && height > -1) {
                        bmiValue = person.getBmi(weight, height);
                        //System.out.println(bmiValue);
                    }

                    bmi.setText(bmiValue);
                    feet.setText(feetInfo);
                    inch.setText(inchInfo);
                }

                query = tool.runQuery("SELECT * FROM login.credentials WHERE username = '" + personName + "';");
                while (query.next()) {
                    gmail = query.getString("email");
                    email.setText(gmail);
                }

            }
        } catch (SQLException e) {
            System.out.println("About page cannot be reached.");
            e.printStackTrace();
        }
    }

    @FXML
    void cancelButtonPressed(ActionEvent event) throws IOException {

        //confirm cancel
        CancelPopUp.display();

        if(CancelPopUp.choice == true){

            saveBt.setVisible(false);
            cancelBt.setVisible(false);
            saveBt.setDisable(true);
            cancelBt.setDisable(true);
            edit.setDisable(false);
            edit.setVisible(true);

            //make fields uneditable
            address.setEditable(false);
            state.setEditable(false);
            city.setEditable(false);
            zip.setEditable(false);
            insuranceid.setEditable(false);
            insurance.setEditable(false);

            refresh();
        }
    }

    @FXML
    void editClicked(ActionEvent event) {

        //hide buttons
        edit.setDisable(true);
        edit.setVisible(false);

        //show main buttons
        saveBt.setVisible(true);
        cancelBt.setVisible(true);
        saveBt.setDisable(false);
        cancelBt.setDisable(false);

        //make fields editable
        address.setEditable(true);
        state.setEditable(true);
        city.setEditable(true);
        zip.setEditable(true);
        insuranceid.setEditable(true);
        insurance.setEditable(true);
        occupation.setEditable(true);
        phone.setEditable(true);
        email.setEditable(true);

    }

    @FXML
    void saveButtonClicked(ActionEvent event) throws IOException {

        String updateStmt = "";

        if(occupation.getText() != null){
            if(occupation.getText().length() != 0){
                if(!occupation.getText().equals(job)){
                    if(updateStmt.length() == 0){
                        updateStmt = updateStmt + "occupation = '" + occupation.getText() + "'";
                    }
                    else{
                        updateStmt = updateStmt + ", occupation = '" + occupation.getText() + "'";
                    }
                }
            }
        }
        if(email.getText() != null){
            if(email.getText().length() != 0){
                if(!email.getText().equals(gmail)){
                    LoginTool tool = new LoginTool();
                    String personName = PersonHelper.getCurrentUser();
                    int updated = tool.updateDatabase("UPDATE `login`.`credentials` SET " + "email = '" +
                            email.getText() + "'" +
                            "WHERE (`username` = '" + personName + "');");

                }
            }
        }
        if(phone.getText() != null){
            if(phone.getText().length() != 0){
                if(!phone.getText().equals(phoneNum)){
                    if(updateStmt.length() == 0){
                        updateStmt = updateStmt + "phone = '" + phone.getText() + "'";
                    }
                    else{
                        updateStmt = updateStmt + ", phone = '" + phone.getText() + "'";
                    }
                }
            }
        }
        if(insuranceid.getText() != null){
            if(insuranceid.getText().length() != 0){
                if(!insuranceid.getText().equals(inId)){
                    if(updateStmt.length() == 0){
                        updateStmt = updateStmt + "insuranceID = '" + insuranceid.getText() + "'";
                    }
                    else{
                        updateStmt = updateStmt + ", insuranceID = '" + insuranceid.getText() + "'";
                    }
                }
            }
        }
        if(insurance.getText() != null){
            if(insurance.getText().length() != 0){
                if(!insurance.getText().equals(in)){
                    if(updateStmt.length() == 0){
                        updateStmt = updateStmt + "insurance = '" + insurance.getText() + "'";
                    }
                    else{
                        updateStmt = updateStmt + ", insurance = '" + insurance.getText() + "'";
                    }
                }
            }
        }
        if(address.getText() != null){
            if(address.getText().length() != 0){
                if(!address.getText().equals(addr)){
                    if(updateStmt.length() == 0){
                        updateStmt = updateStmt + "address = '" + address.getText() + "'";
                    }
                    else{
                        updateStmt = updateStmt + ", address = '" + address.getText() + "'";
                    }
                }
            }
        }
        if(zip.getText() != null){
            if(zip.getText().length() != 0){
                if(!zip.getText().equals(zipCode)){
                    if(updateStmt.length() == 0){
                        updateStmt = updateStmt + "zipcode = '" + zip.getText() + "'";
                    }
                    else{
                        updateStmt = updateStmt + ", zipcode = '" + zip.getText() + "'";
                    }
                }
            }
        }
        if(state.getText() != null){
            if(state.getText().length() != 0){
                if(!state.getText().equals(st)){
                    if(updateStmt.length() == 0){
                        updateStmt = updateStmt + "state = '" + state.getText() + "'";
                    }
                    else{
                        updateStmt = updateStmt + ", state = '" + state.getText() + "'";
                    }
                }
            }
        }
        if(city.getText() != null){
            if(city.getText().length() != 0){
                if(!city.getText().equals(ci)){
                    if(updateStmt.length() == 0){
                        updateStmt = updateStmt + "city = '" + city.getText() + "'";
                    }
                    else{
                        updateStmt = updateStmt + ", city = '" + city.getText() + "'";
                    }
                }
            }
        }

        if(updateStmt.length() == 0){
            saveBt.setVisible(false);
            cancelBt.setVisible(false);
            saveBt.setDisable(true);
            cancelBt.setDisable(true);
            edit.setDisable(false);
            edit.setVisible(true);

            //make fields uneditable
            address.setEditable(false);
            state.setEditable(false);
            city.setEditable(false);
            zip.setEditable(false);
            insuranceid.setEditable(false);
            insurance.setEditable(false);

        }
        else{
            SavePopUp.display();
            if(SavePopUp.choice == true) {

                saveBt.setVisible(false);
                cancelBt.setVisible(false);
                saveBt.setDisable(true);
                cancelBt.setDisable(true);
                edit.setDisable(false);
                edit.setVisible(true);

                //make fields uneditable
                address.setEditable(false);
                state.setEditable(false);
                city.setEditable(false);
                zip.setEditable(false);
                insuranceid.setEditable(false);
                insurance.setEditable(false);

                LoginTool tool = new LoginTool();
                String personName = PersonHelper.getCurrentUser();

                int updated = tool.updateDatabase("UPDATE `login`.`information` SET " + updateStmt +
                        "WHERE (`username` = '" + personName + "');");

                if (updated >= 1) {
                    System.out.println("[Debug]: User: " + personName + " has been updated");
                } else {
                    System.out.println("[Error]: User: " + personName + " has not been updated");
                }
            }
        }


    }

}
