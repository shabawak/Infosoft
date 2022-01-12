package studentInfo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentInfo_Data {
    private final StringProperty ID;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty claSS;
    private final StringProperty dateOfBirth;
    private final StringProperty Gender;
    private final StringProperty Status;
    private final StringProperty parentID;

    public StudentInfo_Data(String id, String lastName, String firstName, String gender, String dateOfBirth, String claSS, String status, String parentID) {
        this.ID = new SimpleStringProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.claSS = new SimpleStringProperty(claSS);
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        this.Gender = new SimpleStringProperty(gender);
        this.Status = new SimpleStringProperty(status);


        this.parentID = new SimpleStringProperty(parentID);
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

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
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

    public String getClaSS() {
        return claSS.get();
    }

    public StringProperty claSSProperty() {
        return claSS;
    }

    public void setClaSS(String claSS) {
        this.claSS.set(claSS);
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public StringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
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

    public String getStatus() {
        return Status.get();
    }

    public StringProperty statusProperty() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status.set(status);
    }

    public String getParentID() {
        return parentID.get();
    }

    public StringProperty parentIDProperty() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID.set(parentID);
    }
}
