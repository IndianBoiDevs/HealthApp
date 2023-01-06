package controllers;

import assets.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SearchView implements Initializable {

    @FXML
    private TableColumn<Person, String> dateOfBirth;

    @FXML
    private TableColumn<Person, String> firstName;

    @FXML
    private TableColumn<Person, String> insurance;

    @FXML
    private TableColumn<Person, String> insuranceId;

    @FXML
    private TableColumn<Person, String> lastName;

    @FXML
    private TableView<Person> patientTable;

    @FXML
    private TextField search;
    @FXML
    private Label message;
    @FXML
    private Button finderButton;
    private Button add;
    @FXML
    private Button demote;
    @FXML
    private Button promote;

    private ObservableList<Person> listOfPatients = FXCollections.observableArrayList();

    private LoginTool sqlTool;

    private ResultSet searchQuery;

    private int userType;

    @FXML
    void findPerson(ActionEvent event) {
        String getMeThis = search.getText();
        System.out.println("[Debug]: Give me: " + getMeThis);
        findPersonFromDB(getMeThis);
    }

    @FXML
    public void addButtonClicked(ActionEvent event) throws IOException {
        System.out.println("[Debug]: Add Pressed");
        AddPatientPopUp.display();
        //run the query to get the result back
        searchQuery = sqlTool.runQuery("SELECT * FROM login.information " +
                "LIMIT 100;");

        //refresh the list
        refreshList(searchQuery);
    }

    @FXML
    public void enterPressed(KeyEvent k) throws IOException {
        //if the user button that they have pressed is the enter key
        if( k.getCode() == KeyCode.ENTER ) {
            //trigger search functionality
            String getMeThis = search.getText();
            System.out.println("[Debug]: Give me: " + getMeThis);
            findPersonFromDB(getMeThis);
        }
    }

    @FXML
    public void selectionMade(MouseEvent click) throws IOException {
        if(click.getButton().equals(MouseButton.PRIMARY)){
            if(click.getClickCount() == 2){
                System.out.println("[Debug]: Double clicked");
                Person person = patientTable.getSelectionModel().getSelectedItem();
                System.out.println("[Debug]: " + person.getFirstName());

                //set the person
                PersonHelper.setUserSelected(person);

                PatientPopUp.display();

                //run the query to get the result back
                searchQuery = sqlTool.runQuery("SELECT * FROM login.information " +
                        "LIMIT 100;");

                //refresh the list
                refreshList(searchQuery);


            }
        }
    }

    private void findPersonFromDB(String name){

        //this is to go back to all the people
        if(name.length() == 0){
            //run the query to get the resultset back
            searchQuery = sqlTool.runQuery("SELECT * FROM login.information " +
                    "LIMIT 100;");
            //refresh the list
            refreshList(searchQuery);
        }
        //if the person doesn't want to go back and they instead want to see the people
        else {
            try{
                int year = Integer.parseInt(name.substring(0,3));
                //if the user has selected dob look up
                System.out.println("[Debug]: User would like to look up a user via DOB");

                //check to see if the user exists with that first name
                searchQuery = sqlTool.runQuery("SELECT * FROM login.information\n" +
                        "where birthday = '" + name + "'"
                        +
                        "LIMIT 100;");
                refreshList(searchQuery);

            } catch (Exception e) {
                ArrayList<String> input = new ArrayList<>(Arrays.asList(name.split(",")));

                if (input.size() == 1) {
                    System.out.println("[Debug]: User would like to look up a user via name");
                    //check to see if the user exists with that first name
                    searchQuery = sqlTool.runQuery("SELECT * FROM login.information\n" +
                            "where firstName = '" + name + "'"
                            +
                            "LIMIT 100;");
                    refreshList(searchQuery);
                } else {

                    try{
                        System.out.println("[Debug]: User would like to look up a user via insurance");
                        //check if they are searching via insurance number
                        Integer.parseInt(input.get(1));
                        searchQuery = sqlTool.runQuery("SELECT * FROM login.information"
                                        +  " where insurance = '" + input.get(0) + "'"
                                        + " AND insuranceID = '" + input.get(1)  + "'"
                                        + " LIMIT 100;");
                        refreshList(searchQuery);

                    }
                    catch (Exception f) {
                        System.out.println("[Debug]: User would like to look up a user via first and last name");
                        //check to see if the user exists with that first and last name
                        searchQuery = sqlTool.runQuery("SELECT * FROM login.information\n" +
                                "where firstName = '" + input.get(1) + "'"
                                + "AND lastName = '" + input.get(0) + "'"
                                + "LIMIT 100;");
                        refreshList(searchQuery);
                    }
                }
            }
        }

    }

    private void refreshList(ResultSet r){
        ObservableList<Person> found = FXCollections.observableArrayList();

        while(true){
            try {
                if (!r.next()) break;
            } catch (SQLException e) {
                System.out.println("[Debug]: No patients were retrieved!");
            }
            try {
                String fname = r.getString("firstName");
                String lname = r.getString("lastName");
                String dob = r.getString("birthday");
                String in = r.getString("insurance");
                String id = r.getString("insuranceID");
                Person here = new Person(fname,lname,dob,in,id);
                here.setUserName(r.getString("username"));
                found.add(here);
            } catch (SQLException e) {
                System.out.println("[Debug]: In offline mode no patients reachable!");
            }
        }

        patientTable.setItems(found);

    }

    @FXML
    void demoteButtonClicked(ActionEvent event) {

    }
    @FXML
    void promoteButtonClicked(ActionEvent event) throws IOException {
        PromotePopUp.display();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //create an sqlTool
        sqlTool = new LoginTool();

        //set the values of the cell
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<Person,String>("dateOfBirth"));
        firstName.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));
        insurance.setCellValueFactory(new PropertyValueFactory<Person,String>("insurance"));
        insuranceId.setCellValueFactory(new PropertyValueFactory<Person,String>("insuranceId"));

        //run the query to get the resultset back
        searchQuery = sqlTool.runQuery("SELECT * FROM login.information " +
                "LIMIT 100;");

        //refresh the list
        refreshList(searchQuery);

        //check if the user is and admin or not
        if(PersonHelper.getUserType() !=0 ){
            //if they aren't an admin go ahead and hide the promoted and demote buttons
            promote.setDisable(true);
            demote.setDisable(true);
            promote.setVisible(false);
            demote.setVisible(false);
        }
        else{
            promote.setDisable(false);
            demote.setDisable(false);
            promote.setVisible(true);
            demote.setVisible(true);
        }

    }


}
