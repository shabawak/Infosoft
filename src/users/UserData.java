package users;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserData {

    private final StringProperty id;
    private final StringProperty UserFullname;
    private final StringProperty UserName;
    private final StringProperty Gender;
    private final StringProperty Password;
    private final StringProperty Phone;



    public UserData(String id, String userFullname,String gender, String userName,  String password, String phone) {
        this.id = new SimpleStringProperty(id);
        this.UserFullname = new SimpleStringProperty(userFullname);
        this.UserName = new SimpleStringProperty(userName);
        this.Gender = new SimpleStringProperty(gender);
        this.Password = new SimpleStringProperty(password);
        this.Phone = new SimpleStringProperty(phone);
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

    public String getUserFullname() {
        return UserFullname.get();
    }

    public StringProperty userFullnameProperty() {
        return UserFullname;
    }

    public void setUserFullname(String userFullname) {
        this.UserFullname.set(userFullname);
    }

    public String getUserName() {
        return UserName.get();
    }

    public StringProperty userNameProperty() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName.set(userName);
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

    public String getPassword() {
        return Password.get();
    }

    public StringProperty passwordProperty() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password.set(password);
    }

    public String getPhone() {
        return Phone.get();
    }

    public StringProperty phoneProperty() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone.set(phone);
    }
}
