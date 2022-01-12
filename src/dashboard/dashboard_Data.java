package dashboard;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class dashboard_Data {



    private final StringProperty Student_id;
    private final StringProperty SLastName;
    private final StringProperty SFirstName;
    private final StringProperty Gender;
    private final StringProperty SClass;

    public dashboard_Data(String student_id, String SLastName, String SFirstName, String gender, String SClass) {
        Student_id = new SimpleStringProperty(student_id);
        this.SLastName = new SimpleStringProperty(SLastName);
        this.SFirstName = new SimpleStringProperty(SFirstName);
        Gender = new SimpleStringProperty(gender);
        this.SClass = new SimpleStringProperty(SClass);
    }

    public String getStudent_id() {
        return Student_id.get();
    }

    public StringProperty student_idProperty() {
        return Student_id;
    }

    public void setStudent_id(String student_id) {
        this.Student_id.set(student_id);
    }

    public String getSLastName() {
        return SLastName.get();
    }

    public StringProperty SLastNameProperty() {
        return SLastName;
    }

    public void setSLastName(String SLastName) {
        this.SLastName.set(SLastName);
    }

    public String getSFirstName() {
        return SFirstName.get();
    }

    public StringProperty SFirstNameProperty() {
        return SFirstName;
    }

    public void setSFirstName(String SFirstName) {
        this.SFirstName.set(SFirstName);
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

    public String getSClass() {
        return SClass.get();
    }

    public StringProperty SClassProperty() {
        return SClass;
    }

    public void setSClass(String SClass) {
        this.SClass.set(SClass);
    }


}
