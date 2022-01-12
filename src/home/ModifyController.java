package home;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import sample.SqliteConnection;
import users.UserData;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifyController implements Initializable {
    @FXML
    private TextField textField_id;

    @FXML
    private TextField textField_fullName;


    @FXML
    private TextField textField_userName;

    @FXML
    private TextField textField_passWord;

    @FXML
    private TextField textField_phone;

    @FXML
    private Pane btn_refreshTable;

    @FXML
    private Pane btn_clear;

    @FXML
    private Pane btn_Insertquerry;



    @FXML
    private Pane btn_updateAdmin;

    @FXML
    private Pane btn_deleteAdmin;

    @FXML
    private ComboBox<String> combo_gender;

    @FXML
    private TableView<UserData> tableView_table1;

    @FXML
    private TableColumn<UserData, String> table_coulumn_id;

    @FXML
    private TableColumn<UserData, String> table_coulumn_fullName;

    @FXML
    private TableColumn<UserData, String> table_coulumn_gender;

    @FXML
    private TableColumn<UserData, String> table_coulumn_userName;

    @FXML
    private TableColumn<UserData, String> table_coulumn_passWord;

    @FXML
    private TableColumn<UserData, String> table_coulumn_phone;

    Connection conn;
    PreparedStatement pr = null;
    ResultSet rs = null;

    ObservableList<UserData> tableData;
    ObservableList<String> comboItems = FXCollections.observableArrayList("MALE","FEMALE");

    HomeController homeController = new HomeController();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        combo_gender.setItems(comboItems);


        btn_Insertquerry.setOnMouseClicked(event -> {

            try {
                insertQuerry();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        btn_refreshTable.setOnMouseClicked(event -> {

            try {
                getAdminData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        btn_updateAdmin.setOnMouseClicked(event -> {

            try {
                updatetQuerry();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        btn_deleteAdmin.setOnMouseClicked(event -> {

            try {
                deleteAdmin(tableView_table1.getSelectionModel().getSelectedItem());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        tableView_table1.setOnMouseClicked(event -> {

            try {
                populateFields(tableView_table1.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btn_clear.setOnMouseClicked(event -> {

            clearFields();
        });

    }

    private void insertQuerry()throws SQLException{

        try {
            String name = textField_fullName.getText();
            String gender = combo_gender.getValue();
            String username = textField_userName.getText();
            String password = textField_passWord.getText();
            String phone = textField_phone.getText();

            String addDataQuerry = "INSERT INTO AdminTable(id,fullName,gender,username,password,phone) VALUES (?,?,?,?,?,?)";

            if (name.isEmpty() || gender.isEmpty() || username.isEmpty() || password.isEmpty() || phone.isEmpty()) {

                homeController.showAlert("Information", null, "All fields required");
            } else {
                try {
                    conn = SqliteConnection.Connector();
                    pr = conn.prepareStatement(addDataQuerry);
                    pr.setString(1, null);
                    pr.setString(2, name.toUpperCase());
                    pr.setString(3, gender.toUpperCase());
                    pr.setString(4, username);
                    pr.setString(5, password);
                    pr.setString(6, phone);


                    pr.execute();

                    homeController.showAlert("Successful", null, "Admin added successfully!");


                } catch (SQLException e) {
                    homeController.showAlert("Error", null, e.getLocalizedMessage());
                    e.printStackTrace();
                } finally {
                    pr.close();
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void getAdminData() throws SQLException {


        try {
            this.tableData = FXCollections.observableArrayList();
            String querryText = "SELECT * FROM AdminTable";

            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querryText);


            while (rs.next()) {
                this.tableData.addAll(new UserData(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (SQLException e) {
            homeController.showAlert("Error", null, e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            rs.close();
        }

        this.table_coulumn_id.setCellValueFactory(new PropertyValueFactory<UserData, String>("id"));
        this.table_coulumn_fullName.setCellValueFactory(new PropertyValueFactory<UserData, String>("UserFullname"));
        this.table_coulumn_gender.setCellValueFactory(new PropertyValueFactory<UserData, String>("Gender"));
        this.table_coulumn_userName.setCellValueFactory(new PropertyValueFactory<UserData, String>("UserName"));
        this.table_coulumn_passWord.setCellValueFactory(new PropertyValueFactory<UserData, String>("Password"));
        this.table_coulumn_phone.setCellValueFactory(new PropertyValueFactory<UserData, String>("Phone"));

        this.tableView_table1.setItems(null);
        this.tableView_table1.setItems(tableData);


    }


    private void updatetQuerry()throws SQLException{

        try {
            String id = textField_id.getText();
            String name = textField_fullName.getText();
            String gender = combo_gender.getValue();
            String username = textField_userName.getText();
            String password = textField_passWord.getText();
            String phone = textField_phone.getText();

            String updatData = "UPDATE  AdminTable SET fullName=?,gender=?,username=?,password=?,phone=? WHERE id= "+id+"";

            if (name.isEmpty() || gender.isEmpty() || username.isEmpty() || password.isEmpty() || phone.isEmpty()) {

                homeController.showAlert("Information", null, "All fields required");
            } else {
                try {
                    conn = SqliteConnection.Connector();
                    pr = conn.prepareStatement(updatData);

                    pr.setString(1, name.toUpperCase());
                    pr.setString(2, gender.toUpperCase());
                    pr.setString(3, username);
                    pr.setString(4, password);
                    pr.setString(5, phone);


                    pr.executeUpdate();

                    homeController.showAlert("Successful", null, "Admin added successfully!");


                } catch (SQLException e) {
                    homeController.showAlert("Error", null, e.getLocalizedMessage());
                    e.printStackTrace();
                } finally {
                    pr.close();
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteAdmin(UserData info)throws SQLException{
        String admin_id = info.getId();

        String deleteAdmin = "DELETE from AdminTable WHERE id = "+admin_id+"";

        try {

            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement(deleteAdmin);
            pr.executeUpdate();

            homeController.showAlert( "Successful", null, "Admin has been deleted!");
        } catch (SQLException e) {
            homeController.showAlert( "Error!", null, "Could not delete student!");
            e.printStackTrace();
        } finally {
            pr.close();
//                conn.close();
        }
    }

    private void populateFields(UserData info) {

        try {
            String id = info.getId();
            String name = info.getUserFullname();
            String gender = info.getGender();
            String username = info.getUserName();
            String password = info.getPassword();
            String phone = info.getPhone();

            this.textField_id.setText(id);
            this.textField_fullName.setText(name);
            this.combo_gender.setValue(gender);
            this.textField_userName.setText(username);
            this.textField_passWord.setText(password);
            this.textField_phone.setText(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clearFields(){

        this.textField_id.setText("");
        this.textField_fullName.setText("");
        this.combo_gender.setValue("");
        this.textField_userName.setText("");
        this.textField_passWord.setText("");
        this.textField_phone.setText("");
    }

}


