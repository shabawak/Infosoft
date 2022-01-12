package examreport;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class promotionData {

    private CheckBox Select;
    private final StringProperty studentID;
    private final StringProperty studentClass;
    private final StringProperty term;
    private final StringProperty totalMarks;
    private final StringProperty Year;

    public promotionData(String studentID, String studentClass, String term, String totalMarks, String year) {
        this.Select = new CheckBox();
        this.studentID = new SimpleStringProperty(studentID);
        this.studentClass = new SimpleStringProperty(studentClass);
        this.term = new SimpleStringProperty(term);
        this.totalMarks = new SimpleStringProperty(totalMarks);
        this.Year = new SimpleStringProperty(year);
    }

    public String getStudentID() {
        return studentID.get();
    }

    public StringProperty studentIDProperty() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID.set(studentID);
    }


    public String getStudentClass() {
        return studentClass.get();
    }

    public StringProperty studentClassProperty() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass.set(studentClass);
    }

    public String getTerm() {
        return term.get();
    }

    public StringProperty termProperty() {
        return term;
    }

    public void setTerm(String term) {
        this.term.set(term);
    }

    public String getTotalMarks() {
        return totalMarks.get();
    }

    public StringProperty totalMarksProperty() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks.set(totalMarks);
    }

    public CheckBox getSelect() {
        return Select;
    }

    public void setSelect(CheckBox select) {
        Select = select;
    }

    public String getYear() {
        return Year.get();
    }

    public StringProperty yearProperty() {
        return Year;
    }

    public void setYear(String year) {
        this.Year.set(year);
    }
}
