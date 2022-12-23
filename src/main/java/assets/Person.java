package assets;

public class Person {

    String dateOfBirth;
    String firstName;
    String lastName;
    String insurance;
    String insuranceId;

    String userName;
    String address;
    String phone;
    String city;
    String state;
    String zipCode;
    String weight;
    String race;
    String occupation;
    String height;
    String bloodPressure;
    String heartRate;
    String disability;
    String bmi;


    public Person(String fname,String lName, String dob, String in, String id){
        firstName = fname;
        lastName = lName;
        dateOfBirth = dob;
        insurance = in;
        insuranceId = id;
    }

    public String getUserName(){
        return userName;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getInsurance() {
        return insurance;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUserName(String user){
        userName = user;
    }

    public String getbmi(double w, double h){
        double bmi = 0;

        bmi = w / (h * h);

        if(bmi <= 18){
            return "UnderWeight";
        }
        else if(bmi <= 24){
            return "Normal";
        }
        else if(bmi <= 29){
            return "OverWeight";
        }
        else if(bmi < 35){
            return "Obese Class 1";
        }
        else if(bmi < 40){
            return "Obese Class 2";
        }
        else{
            return "Extreme Obesity";
        }
    }

}
