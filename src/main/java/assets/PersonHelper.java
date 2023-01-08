package assets;

public class PersonHelper {

    private static Person currentUser;
    private static Person selected;

    private static int uType;

    public static void setUserType(int i){
        uType = i;
    }
    public static int getUserType(){
        return  uType;
    }

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
