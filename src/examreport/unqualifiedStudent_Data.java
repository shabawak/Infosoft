package examreport;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class unqualifiedStudent_Data {


    private final StringProperty student_id;



    public unqualifiedStudent_Data(String student_id) {
        this.student_id = new SimpleStringProperty(student_id) ;

    }

    public String getStudent_id() {
        return student_id.get();
    }

    public StringProperty student_idProperty() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id.set(student_id);
    }
}
