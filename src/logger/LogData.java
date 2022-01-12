package logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LogData {

    private final StringProperty logID;
    private final StringProperty userName;
    private final StringProperty name;
    private final StringProperty timeIn;
    private final StringProperty timeOut;



    public LogData(String logID, String userName, String name, String timeIn, String timeOut) {
        this.logID = new SimpleStringProperty(logID);
        this.userName = new SimpleStringProperty(userName);
        this.name = new SimpleStringProperty(name);
        this.timeIn = new SimpleStringProperty(timeIn);
        this.timeOut = new SimpleStringProperty(timeOut);
    }

    public String getLogID() {
        return logID.get();
    }

    public StringProperty logIDProperty() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID.set(logID);
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getTimeIn() {
        return timeIn.get();
    }

    public StringProperty timeInProperty() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn.set(timeIn);
    }

    public String getTimeOut() {
        return timeOut.get();
    }

    public StringProperty timeOutProperty() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut.set(timeOut);
    }
}
