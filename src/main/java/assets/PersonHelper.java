package assets;

public class PersonHelper {

    private static Person currentUser;
    private static Person selected;

    public static void setCurrentUser(Person p){
        currentUser = p;
    }
    public static void setUserSelected(Person p){
        selected = p;
    }

    public static String getCurrentUser(){
        return currentUser.getUserName();
    }

    public static Person getSelectedUser(){
        return selected;
    }
}
