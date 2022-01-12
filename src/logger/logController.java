package logger;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import sample.SqliteConnection;
import studentInfo.PrintReport;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class logController implements Initializable {

    @FXML
    private TableView<LogData> logTable;
    @FXML
    private TableColumn<LogData, String> column_id;
    @FXML
    private TableColumn<LogData, String> column_Username;
    @FXML
    private TableColumn<LogData, String> column_name;
    @FXML
    private TableColumn<LogData, String> column_loggedIn;
    @FXML
    private TableColumn<LogData, String> column_loggedOut;
    @FXML
    private JFXButton btn_refreshLog;
    @FXML
    private JFXButton btn_printAll;

    private ObservableList<LogData> log;

    Connection conn;
    PreparedStatement stmt = null;
    ResultSet resultSet = null;

    Date date;

    int generatedKey;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.conn = SqliteConnection.Connector();
        this.date = new Date();

        try {
            retrieveData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.btn_refreshLog.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logTable.setItems(null);
                try {
                    retrieveData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_printAll.setOnAction(event -> {
            PrintReport printReport = new PrintReport();
            try {
                printReport.showLogReport();
            } catch (JRException e) {
                e.printStackTrace();
            }

        });

    }

    private void retrieveData() throws SQLException {
        String querry = "SELECT* FROM LogTable";

        try {
            this.log = FXCollections.observableArrayList();
            resultSet = this.conn.createStatement().executeQuery(querry);

            while (resultSet.next()) {
                this.log.add(new LogData(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
            }
        } catch (SQLException var3) {
            this.showAlert("Error", "Could not retrieve data from Login history", "" + var3.getLocalizedMessage() + "");
            var3.printStackTrace();
        } finally {
            resultSet.close();
        }

        this.column_id.setCellValueFactory(new PropertyValueFactory("logID"));
        this.column_Username.setCellValueFactory(new PropertyValueFactory("userName"));
        this.column_name.setCellValueFactory(new PropertyValueFactory("name"));
        this.column_loggedIn.setCellValueFactory(new PropertyValueFactory("timeIn"));
        this.column_loggedOut.setCellValueFactory(new PropertyValueFactory("timeOut"));
        this.logTable.setItems((ObservableList) null);
        this.logTable.setItems(this.log);
    }


    public void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
