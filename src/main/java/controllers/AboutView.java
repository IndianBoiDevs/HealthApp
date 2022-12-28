package controllers;

import assets.LoginTool;
import assets.PersonHelper;
import assets.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.w3c.dom.Text;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                occupation.setText(query.getString("occupation"));
                phone.setText(query.getString("phone"));
                insuranceid.setText(query.getString("insuranceID"));
                insurance.setText(query.getString("insurance"));
                address.setText(query.getString("address"));
                state.setText(query.getString("state"));
                city.setText(query.getString("city"));
                zip.setText(query.getString("zipcode"));
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
                    email.setText(query.getString("email"));
                }

            }
        } catch (SQLException e) {
            System.out.println("About page cannot be reached.");
            e.printStackTrace();
        }
    }
}
