package settings;

import javafx.beans.property.StringProperty;

public class SettingsData {



    private StringProperty ID;
    private StringProperty Marks;
    private StringProperty Level;

    public SettingsData(StringProperty ID, StringProperty marks, StringProperty level) {
        this.ID = ID;
        Marks = marks;
        Level = level;
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

    public String getMarks() {
        return Marks.get();
    }

    public StringProperty marksProperty() {
        return Marks;
    }

    public void setMarks(String marks) {
        this.Marks.set(marks);
    }

    public String getLevel() {
        return Level.get();
    }

    public StringProperty levelProperty() {
        return Level;
    }

    public void setLevel(String level) {
        this.Level.set(level);
    }

}
