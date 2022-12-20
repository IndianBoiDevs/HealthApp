package assets;

public class Person {

    String dateOfBirth;
    String firstName;
    String lastName;
    String insurance;
    String insuranceId;

    public Person(String fname,String lName, String dob, String in, String id){
        firstName = fname;
        lastName = lName;
        dateOfBirth = dob;
        insurance = in;
        insuranceId = id;
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

}
