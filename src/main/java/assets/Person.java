package assets;

public class Person {

    String dateOfBirth;
    String firstName;
    String lastName;
    String insurance;
    String insuranceId;
    String gender;
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
    public String getGender() {
        return gender;
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

    public String getAddress() {
        return address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public String getCity() {
        return city;
    }

    public String getDisability() {
        return disability;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public String getHeight() {
        return height;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getPhone() {
        return phone;
    }

    public String getRace() {
        return race;
    }

    public String getState() {
        return state;
    }

    public String getWeight() {
        return weight;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public String getBmi(double w, double h){

        //bmi calculation
        double bmi = 0;
        bmi = w / (h * h);
        bmi = bmi * 703;

        if(bmi <= 18.5){
            return "Underweight";
        }
        else if(bmi <= 24.9){
            return "Normal";
        }
        else if(bmi <= 29.9){
            return "Overweight";
        }
        else if(bmi < 34.9){
            return "Obese Class 1";
        }
        else if(bmi < 39.9){
            return "Obese Class 2";
        }
        else{
            return "Extreme Obesity";
        }
    }



}
