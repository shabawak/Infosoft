package update;

import home.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.SqliteConnection;
import studentInfo.StudentInfo_Data;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class parentWardCountCotroller implements Initializable {

    @FXML
    private TableView<StudentInfo_Data> wardCountTable;

    @FXML
    private TableColumn<StudentInfo_Data, String> ward_Id;

    @FXML
    private TableColumn<StudentInfo_Data, String> lastName;

    @FXML
    private TableColumn<StudentInfo_Data, String> firstName;

    @FXML
    private TableColumn<StudentInfo_Data, String> gender;

    @FXML
    private TableColumn<StudentInfo_Data, String> dob;

    @FXML
    private TableColumn<StudentInfo_Data, String> wardClass;

    @FXML
    private Label lbl_count;


    private Connection conn;
    private ResultSet rs = null;
    private ObservableList<StudentInfo_Data>WardInfo;

    HomeController homeController = new HomeController();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    public void showParentWard(parentData info)throws SQLException{

        String parentID = info.getId();
        String querryText = "SELECT Student_id,lastName,firstName,gender,dob,class,status,parent_id FROM StudentData WHERE parent_id="+parentID+"";

        try {
            this.WardInfo = FXCollections.observableArrayList();

            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querryText);

            while (rs.next()){

                this.WardInfo.addAll(new StudentInfo_Data(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }
        } catch (SQLException e) {
            homeController.showAlert("Error",null,"Could not get ward info");
            e.printStackTrace();
        } finally {
            rs.close();
        }

        this.ward_Id.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("ID"));
        this.lastName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("lastName"));
        this.firstName.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("firstName"));
        this.gender.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("Gender"));
        this.dob.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("dateOfBirth"));
        this.wardClass.setCellValueFactory(new PropertyValueFactory<StudentInfo_Data, String>("claSS"));

        this.wardCountTable.setItems(null);
        this.wardCountTable.setItems(WardInfo);


    }

    public void getNumberOfWards(parentData info)throws SQLException{
        String parentID = info.getId();

        String total_wardQuerry = "SELECT Count(*) FROM StudentData WHERE parent_id = "+parentID+" ";

        try {
            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(total_wardQuerry);

            while (rs.next()){
                this.lbl_count.setText(rs.getString(1));
            }

        } catch (SQLException e) {
            homeController.showAlert("Error",null,"Could not get number of ward");
            e.printStackTrace();
        } finally {
            rs.close();
        }
    }
}
