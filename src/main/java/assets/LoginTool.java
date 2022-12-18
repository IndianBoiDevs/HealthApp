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

                //process result set
                while (resultSet.next()) {
                    return Integer.parseInt(resultSet.getString("type"));
                }
            }
            catch (SQLException E){
                offlineMode = true;
                return -1;
            }
        }
        //if they fail to log in in any way
        return -1;
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

    public int addUser(String stmt){
        try {
            //create a statement
            Statement myStatement = connection.createStatement();

            int resultSet = myStatement.executeUpdate(stmt);
            return resultSet;
        }
        catch (SQLException e) {
            System.out.println("[Error]: Unable to add the user to the database due to connection!");
            return 0;
        }
    }
}
