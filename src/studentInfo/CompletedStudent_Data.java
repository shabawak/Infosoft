package studentInfo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CompletedStudent_Data {

    private final StringProperty ID;
    private final StringProperty lastName;
    private final StringProperty firstName;
    private final StringProperty Gender;
    private final StringProperty preYEAR;



    public CompletedStudent_Data(String id, String lastName , String firstName, String gender, String preYEAR) {
        ID = new SimpleStringProperty(id);
        this.lastName = new SimpleStringProperty(lastName);
        this.firstName = new SimpleStringProperty(firstName);
        Gender = new SimpleStringProperty(gender);
        this.preYEAR = new SimpleStringProperty(preYEAR);
    }

    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getGender() {
        return Gender.get();
    }

    public StringProperty genderProperty() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender.set(gender);
    }

    public String getPreYEAR() {
        return preYEAR.get();
    }

    public StringProperty preYEARProperty() {
        return preYEAR;
    }

    public void setPreYEAR(String preYEAR) {
        this.preYEAR.set(preYEAR);
    }
}
