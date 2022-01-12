package users;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.SqliteConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class UseresController implements Initializable {
    @FXML
    private JFXTextField txt_fullName;

    @FXML
    private JFXTextField txt_username;

    @FXML
    private JFXComboBox<String> cmBox_gender;

    @FXML
    private JFXTextField txt_id;

    @FXML
    private JFXPasswordField txt_password;

    @FXML
    private JFXTextField txt_phone;

    @FXML
    private JFXButton btn_Save;

    @FXML
    private JFXButton btn_loadTable;

    @FXML
    private JFXButton btn_Clear;

    @FXML
    private JFXButton btn_Add;

    @FXML
    private JFXButton btn_Update;

    @FXML
    private JFXButton btn_Delete;

    @FXML
    public JFXButton btn_querry;

    @FXML
    private TableView<UserData> tbl_table;

    @FXML
    private TableColumn<UserData, String> tbl_idColumn;

    @FXML
    private TableColumn<UserData, String> tbl_fullNameColumn;

    @FXML
    private TableColumn<UserData, String> tbl_genderColumn;

    @FXML
    private TableColumn<UserData, String> tbl_userNameColumn;

    @FXML
    private TableColumn<UserData, String> tbl_passwordColumn;

    @FXML
    private TableColumn<UserData, String> tbl_phoneColumn;

    private Connection conn;
    private PreparedStatement pr = null;
    private ResultSet rs = null;

    ObservableList<String> genderItems = FXCollections.observableArrayList("Male", "Female");

    ObservableList<UserData> userDataItems;

    private UserData user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.btn_Save.setDisable(true);


        this.cmBox_gender.setItems(genderItems);

        this.btn_Clear.setOnAction(event -> clearForm());

        try {
            loadUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btn_Update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                populateUserForm(tbl_table.getSelectionModel().getSelectedItem());

                btn_Add.setDisable(true);
                btn_Save.setDisable(false);
            }
        });

        btn_Add.setOnAction(event -> {

            try {
                addUser();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        btn_Save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateUser();
                    clearForm();
                    btn_Save.setDisable(true);
                    btn_Add.setDisable(false);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        btn_loadTable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    tbl_table.setItems(null);
                    loadUsers();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                btn_Save.setDisable(true);
            }
        });

        btn_Delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    deleteUserData(tbl_table.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void updateUser() throws SQLException {
        String gender = cmBox_gender.getValue();

        String updateQuerry = "UPDATE UsersTable SET fullName=?,gender=?,username=?,password=?,phone=? WHERE id ='" + txt_id.getText() + "'";

        try {
            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement(updateQuerry);

            pr.setString(1, txt_fullName.getText());
            pr.setString(2,gender);
            pr.setString(3, txt_username.getText());
            pr.setString(4, txt_password.getText());
            pr.setString(5, txt_phone.getText());

            pr.execute();


            showAlert(Alert.AlertType.INFORMATION, "Successful", "null", "User update successful!");

        } catch (SQLException e) {
            showAlert(Alert.AlertType.INFORMATION, "SQLException", "Could not update user", "Error :" + e.getMessage());
            e.printStackTrace();
        } finally {

            pr.close();

        }
    }

    private void deleteUserData(UserData selectedItem) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Confirm Delete operation.");
        Optional<ButtonType> bp = alert.showAndWait();
        if (bp.get() == ButtonType.OK) {

            String id = selectedItem.getId();
            String deleteQuerry = "DELETE FROM UsersTable WHERE id = '" + id + "'";

            try {
                conn = SqliteConnection.Connector();
                pr = conn.prepareStatement(deleteQuerry);
                pr.execute();


                showAlert(Alert.AlertType.INFORMATION, "Successful", null, "User has been deleted!");
            } catch (SQLException e) {
                showAlert(Alert.AlertType.INFORMATION, "SQLExeption", "User could not be deleted", "" + e.getLocalizedMessage() + "");
                e.printStackTrace();
            } finally {
                pr.close();
            }
        } else {
            alert.close();
        }


    }

    // Method to clear Uer form
    private void clearForm() {
        txt_id.setText("");
        txt_fullName.setText("");
        cmBox_gender.setValue("");
        txt_username.setText("");
        txt_password.setText("");
        txt_phone.setText("");

        btn_Add.setDisable(false);
        btn_Save.setDisable(true);
    }

    public void loadUsers() throws SQLException {
        String usersQuery = "SELECT * FROM UsersTable";

        try {
            conn = SqliteConnection.Connector();
            pr = null;
            rs = null;

            this.userDataItems = FXCollections.observableArrayList();

            rs = conn.createStatement().executeQuery(usersQuery);

            while (rs.next()) {
                this.userDataItems.addAll(new UserData(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.INFORMATION, "SQLException", "Load error", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            rs.close();
        }

        this.tbl_idColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("id"));
        this.tbl_fullNameColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("UserFullname"));
        this.tbl_genderColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("Gender"));
        this.tbl_userNameColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("UserName"));
        this.tbl_passwordColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("Password"));
        this.tbl_phoneColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("Phone"));

        this.tbl_table.setItems(null);
        this.tbl_table.setItems(userDataItems);

      /*  this.tbl_table.setRowFactory(new Callback<TableView<UserData>, TableRow<UserData>>() {
            @Override
            public TableRow<UserData> call(TableView<UserData> param) {
                final TableRow<UserData> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem updateItem = new MenuItem("Update");
                final MenuItem deleteItem = new MenuItem("Delete");

                updateItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                      *//* TableColumn<String, String> column1 = new TableColumn<>();
                        tbl_table.getSelectionModel().getSelectedItems();*//*
                        populateUserForm(tbl_table.getSelectionModel().getSelectedItem());
                    }
                });

                deleteItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        tbl_table.getItems().remove(row.getItem());
                    }
                });

                contextMenu.getItems().add(updateItem);
                contextMenu.getItems().add(deleteItem);

                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                        .then((ContextMenu)null)
                        .otherwise(contextMenu)
                );

                return row;
            }
        });*/

    }

    private void populateUserForm(UserData person) {
        user = person;
        this.txt_id.setText(user.getId());
        this.txt_fullName.setText(user.getUserFullname());
        this.cmBox_gender.setValue(user.getGender());
        this.txt_username.setText(user.getUserName());
        this.txt_password.setText(user.getPassword());
        this.txt_phone.setText(user.getPhone());

        btn_Add.setDisable(true);


    }

    @FXML
    private void hasndleMouseClicked(MouseEvent event) {
        TableColumn<String, String> column1 = new TableColumn<>();
        btn_Update.disableProperty().bind(Bindings.isEmpty(tbl_table.getSelectionModel().getSelectedItems()));
        btn_Delete.disableProperty().bind(Bindings.isEmpty(tbl_table.getSelectionModel().getSelectedItems()));
    }

    private void addUser()throws SQLException {
        String name = txt_fullName.getText();
        String gender = cmBox_gender.getValue();
        String username = txt_username.getText();
        String password = txt_password.getText();
        String phone = txt_phone.getText();


        String addDataQuerry = "INSERT INTO UsersTable(id,fullName,gender,username,password,phone) VALUES (?,?,?,?,?,?)";

        if (name.isEmpty() || gender.isEmpty() || username.isEmpty() || password.isEmpty() || phone.isEmpty()){

            showAlert(Alert.AlertType.INFORMATION,"Information",""+null+"","All fields required");
        }
        else {
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

                showAlert(Alert.AlertType.INFORMATION, "Successful", null, "User added successfully!");
                clearForm();
                loadUsers();

            } catch (SQLException e) {
                showAlert(Alert.AlertType.INFORMATION, "SQLException", "User could not be added", "" + e.getLocalizedMessage() + "");
                e.printStackTrace();

            } finally {

                    pr.close();
            }

        }




    }


    public void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {


        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
