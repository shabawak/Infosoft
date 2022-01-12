package studentInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.SqliteConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CompletedStudent_Controller implements Initializable {
    @FXML
    private TableView<CompletedStudent_Data> tableView;

    @FXML
    private TableColumn<CompletedStudent_Data, String> column_sid;

    @FXML
    private TableColumn<CompletedStudent_Data, String> column_surname;

    @FXML
    private TableColumn<CompletedStudent_Data, String> column_firstname;

    @FXML
    private TableColumn<CompletedStudent_Data, String> column_gender;

    @FXML
    private TableColumn<CompletedStudent_Data, String> column_year;

    @FXML
    private TextField txt_year;

    @FXML
    private Button btn_generate;

    @FXML
    private Button btn_print;

    Connection conn;
    ResultSet rs = null;

    ObservableList<CompletedStudent_Data> studentList;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btn_generate.setOnAction(event -> {

            try {
                getCompletedStudent();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    public void getCompletedStudent() throws SQLException {
        try {
            String classConditon = "COMPLETED";
            String year = txt_year.getText();
            String Querry = "Select Student_id,lastName,firstName,gender,preYear from StudentData where class= '"+classConditon+"' and preYear='"+year+"' ";

            conn = SqliteConnection.Connector();
            this.studentList = FXCollections.observableArrayList();
            rs = conn.createStatement().executeQuery(Querry);
            while (rs.next()){
            this.studentList.add(new CompletedStudent_Data(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
            conn.close();
        }
        this.column_sid.setCellValueFactory(new PropertyValueFactory<CompletedStudent_Data,String>("ID"));
        this.column_surname.setCellValueFactory(new PropertyValueFactory<CompletedStudent_Data,String>("lastName"));
        this.column_firstname.setCellValueFactory(new PropertyValueFactory<CompletedStudent_Data,String>("firstName"));
        this.column_gender.setCellValueFactory(new PropertyValueFactory<CompletedStudent_Data,String>("Gender"));
        this.column_year.setCellValueFactory(new PropertyValueFactory<CompletedStudent_Data,String>("preYEAR"));

        this.tableView.setItems(null);
        this.tableView.setItems(studentList);



    }
}
