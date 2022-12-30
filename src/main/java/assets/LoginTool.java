package assets;

import java.sql.*;

public class LoginTool {

    //setup instance variables that we need for connection to sql db
    private static String url;
    private static String user;
    private static String password;
    private static Connection connection;


    private static boolean offlineMode;

    public LoginTool(){

        if(connection == null) {

            //the connection information for the sql database
            url = "jdbc:mysql://healthapp.zapto.org:3306/login?allowPublicKeyRetrieval=true&useSSL=false";
            user = "root";
            password = "healthappdb";

            //try connecting to database
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                //get connection to database
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("[Debug]: Successfully connected to the database!");

                //ensure that offline mode is set to false
                offlineMode = false;
            } catch (Exception e) {
                System.out.println("[Error]: unable to connect to the database!");
                offlineMode = true;
            }
        }

        else{
            System.out.println("[Debug]: Already Connected to the Database!");
        }
    }

    public int checkIfUserExists(String username, String password){

        //return value based off userType
        int returnThis = -1;

        //this is for working offline as a demo
        if(offlineMode == true){
            //return 0 if this is the correct admin login
            if(username.equals("admin") && password.equals(password)){
                return 0;
            }
            //return 1 if it is the correct staff login,
            if(username.equals("staff") && password.equals(password)){
                return 1;
            }
            //and 2 if this is the correct patient login
            if(username.equals("patient") && password.equals(password)){
                return 2;
            }
        }
        else{
            try {
                //create a statement
                Statement myStatement = connection.createStatement();

                //execute sql query
                ResultSet resultSet = myStatement.executeQuery("SELECT * FROM login.credentials\n" +
                        "where username = '"+ username +"' AND password = '"+ password +"';");
                while(resultSet.next()){
                    //this will only execute if the person is found
                    returnThis = Integer.parseInt(resultSet.getString("type"));
                }

                System.out.println("[Debug]: Using view " + returnThis);

                ResultSet secondQuery = myStatement.executeQuery("SELECT * FROM login.information\n" +
                        "where username = '"+ username +"';");

                //process result set
                while (secondQuery.next()) {

                    //add the person
                    Person p = new Person(secondQuery.getString("firstName"),secondQuery.getString("lastName"),secondQuery.getString("birthday"),secondQuery.getString("insurance"), secondQuery.getString("insuranceID"));
                    p.setUserName(secondQuery.getString("username"));
                    //make it the current person
                    PersonHelper.setCurrentUser(p);

                    System.out.println("[Debug]: " + PersonHelper.getCurrentUser() + " is logging in!");
                }
            }
            catch (SQLException f){
                System.out.println("It looks like you are offline please use demo credentials!");
                offlineMode = true;
                return -1;
            }
        }
        //make the return at the end
        return returnThis;
    }

    public boolean isOfflineMode() {
        return offlineMode;
    }

    public ResultSet runQuery(String query){
        try {
            //create a statement
            Statement myStatement = connection.createStatement();

            ResultSet resultSet = myStatement.executeQuery(query);
            return resultSet;
        }
        catch (SQLException e) {
            System.out.println("[Error]: Unable to connect to the database!");
            return null;
        }
    }

    public int updateDatabase(String query){
        try {
            //create a statement
            Statement myStatement = connection.createStatement();

            int resultSet = myStatement.executeUpdate(query);
            return resultSet;
        }
        catch (SQLException e) {
            System.out.println("[Error]: Unable to connect to the database!");
            e.printStackTrace();
            return 0;
        }
    }

    public int addUser(String stmt){
        try {
            //create a statement
            Statement myStatement = connection.createStatement();

            int resultSet = myStatement.executeUpdate(stmt);
            return resultSet;
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[Error]: Unable to add the user to the database due to connection!");
            return 0;
        }
    }
}
