package update;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class parentData {



    private final StringProperty id;
    private final StringProperty fatherLastName;
    private final StringProperty fatherFirstName;
    private final StringProperty motherLastName;
    private final StringProperty motherFirstName;
    private final StringProperty fatherPhone;
    private final StringProperty motherphone;
    private final StringProperty fatherEmail;
    private final StringProperty motherEmail;



    public parentData(String id, String fatherLastName, String fatherFirstName, String motherLastName, String motherFirstName, String fatherPhone, String motherphone, String fatherEmail, String motherEmail) {
        this.id = new SimpleStringProperty(id);
        this.fatherLastName = new SimpleStringProperty(fatherLastName);
        this.fatherFirstName = new SimpleStringProperty(fatherFirstName);
        this.motherLastName = new SimpleStringProperty(motherLastName);
        this.motherFirstName = new SimpleStringProperty(motherFirstName);
        this.fatherPhone = new SimpleStringProperty(fatherPhone);
        this.motherphone = new SimpleStringProperty(motherphone);
        this.fatherEmail = new SimpleStringProperty(fatherEmail);
        this.motherEmail = new SimpleStringProperty(motherEmail);
    }


    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getFatherLastName() {
        return fatherLastName.get();
    }

    public StringProperty fatherLastNameProperty() {
        return fatherLastName;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName.set(fatherLastName);
    }

    public String getFatherFirstName() {
        return fatherFirstName.get();
    }

    public StringProperty fatherFirstNameProperty() {
        return fatherFirstName;
    }

    public void setFatherFirstName(String fatherFirstName) {
        this.fatherFirstName.set(fatherFirstName);
    }

    public String getMotherLastName() {
        return motherLastName.get();
    }

    public StringProperty motherLastNameProperty() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName.set(motherLastName);
    }

    public String getMotherFirstName() {
        return motherFirstName.get();
    }

    public StringProperty motherFirstNameProperty() {
        return motherFirstName;
    }

    public void setMotherFirstName(String motherFirstName) {
        this.motherFirstName.set(motherFirstName);
    }

    public String getFatherPhone() {
        return fatherPhone.get();
    }

    public StringProperty fatherPhoneProperty() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone.set(fatherPhone);
    }

    public String getMotherphone() {
        return motherphone.get();
    }

    public StringProperty motherphoneProperty() {
        return motherphone;
    }

    public void setMotherphone(String motherphone) {
        this.motherphone.set(motherphone);
    }

    public String getFatherEmail() {
        return fatherEmail.get();
    }

    public StringProperty fatherEmailProperty() {
        return fatherEmail;
    }

    public void setFatherEmail(String fatherEmail) {
        this.fatherEmail.set(fatherEmail);
    }

    public String getMotherEmail() {
        return motherEmail.get();
    }

    public StringProperty motherEmailProperty() {
        return motherEmail;
    }

    public void setMotherEmail(String motherEmail) {
        this.motherEmail.set(motherEmail);
    }

}
