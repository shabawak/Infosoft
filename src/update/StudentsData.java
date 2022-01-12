package update;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentsData {

    private final StringProperty ID;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty claSS;
    private final StringProperty dateOfBirth;
    private final StringProperty Gender;
    private final StringProperty Residence;
    private final StringProperty landMark;
    private final StringProperty dateOfAdmission;
    private final StringProperty lastSchoolAttended;
    private final StringProperty Religion;
    private final byte[] Student_image;
    private final StringProperty Status;
    private final StringProperty currYear;
    private final StringProperty prevYear;


    public StudentsData(
            String id, String lastname, String firstname, String gender, String dateofBirth, String religion, String residence,
            String landmark, String claSS, String dateofAdmission, String lastschoolAttended,  byte[] studentImage, String status, String curryear, String prevyear){

        this.ID = new SimpleStringProperty(id);
        this.lastName = new SimpleStringProperty(lastname);
        this.firstName = new SimpleStringProperty(firstname);
        this.Gender = new SimpleStringProperty(gender);
        this.dateOfBirth = new SimpleStringProperty(dateofBirth);
        this.Religion = new SimpleStringProperty(religion);
        this.Residence = new SimpleStringProperty(residence);
        this.landMark = new SimpleStringProperty(landmark);
        this.claSS = new SimpleStringProperty(claSS);
        this.dateOfAdmission = new SimpleStringProperty(dateofAdmission);
        this.lastSchoolAttended = new SimpleStringProperty(lastschoolAttended);
        this.Student_image = studentImage;
        this.Status = new SimpleStringProperty(status);
        this.currYear = new SimpleStringProperty(curryear);
        this.prevYear = new SimpleStringProperty(prevyear);


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

    public String getResidence() {
        return Residence.get();
    }

    public StringProperty residenceProperty() {
        return Residence;
    }

    public void setResidence(String residence) {
        this.Residence.set(residence);
    }

    public String getLandMark() {
        return landMark.get();
    }

    public StringProperty landMarkProperty() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark.set(landMark);
    }

    public String getDateOfAdmission() {
        return dateOfAdmission.get();
    }

    public StringProperty dateOfAdmissionProperty() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(String dateOfAdmission) {
        this.dateOfAdmission.set(dateOfAdmission);
    }

    public String getLastSchoolAttended() {
        return lastSchoolAttended.get();
    }

    public StringProperty lastSchoolAttendedProperty() {
        return lastSchoolAttended;
    }

    public void setLastSchoolAttended(String lastSchoolAttended) {
        this.lastSchoolAttended.set(lastSchoolAttended);
    }

    public String getReligion() {
        return Religion.get();
    }

    public StringProperty religionProperty() {
        return Religion;
    }

    public void setReligion(String religion) {
        this.Religion.set(religion);
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

    public String getCurrYear() {
        return currYear.get();
    }

    public StringProperty currYearProperty() {
        return currYear;
    }

    public void setCurrYear(String currYear) {
        this.currYear.set(currYear);
    }

    public String getPrevYear() {
        return prevYear.get();
    }

    public StringProperty prevYearProperty() {
        return prevYear;
    }

    public void setPrevYear(String prevYear) {
        this.prevYear.set(prevYear);
    }



   /* public String getStudent_image() {
        return Student_image.get();
    }

    public StringProperty student_imageProperty() {
        return Student_image;
    }

    public void setStudent_image(String student_image) {
        this.Student_image.set(student_image);
    }*/

    public byte[] getStudent_image() {
        return Student_image;
    }

}
