package controllers;

import assets.LoginTool;
import assets.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private ObservableList<Person> listOfPatients = FXCollections.observableArrayList();

    private LoginTool sqlTool;

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
        ResultSet rs = sqlTool.runQuery("SELECT * FROM login.information " +
                "LIMIT 100;");

        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                System.out.println("[Debug]: No patients were retrieved!");
            }
            try {
                String fname = rs.getString("firstName");
                String lname = rs.getString("lastName");
                String dob = rs.getString("birthday");
                String in = rs.getString("insurance");
                String id = rs.getString("insuranceID");
                Person here = new Person(fname,lname,dob,in,id);
                listOfPatients.add(here);
            } catch (SQLException e) {
                System.out.println("[Debug]: In offline mode no patients reachable!");
            }
        }

        patientTable.setItems(listOfPatients);
    }


}
