package update;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import home.HomeController;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import sample.SqliteConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParentsController implements Initializable {


    @FXML
    private JFXButton btn_RefreshTable;

    @FXML
    private JFXButton btn_addParent;

    @FXML
    private JFXButton btn_UpdateParent;

    @FXML
    private JFXButton btn_deleteParent;

    @FXML
    private JFXTextField searchField;

    @FXML
    private TableView<parentData> Table_View;

    @FXML
    private TableColumn<parentData, String> Column_ID;

    @FXML
    private TableColumn<parentData, String> Column_F_LastName;

    @FXML
    private TableColumn<parentData, String> Column_F_FirstName;

    @FXML
    private TableColumn<parentData, String> Column_F_PHONE;

    @FXML
    private TableColumn<parentData, String> Column_F_EMAIL;

    @FXML
    private TableColumn<parentData, String> Column_M_LASTNAME;

    @FXML
    private TableColumn<parentData, String> Column_M_FIRSTNAME;

    @FXML
    private TableColumn<parentData, String> Column_M_PHONE;

    @FXML
    private TableColumn<parentData, String> Colun_M_EMAIL;



    private Connection conn;
    private PreparedStatement pr = null;
    private ResultSet rs = null;

    ObservableList<parentData>data;

    HomeController homeController = new HomeController();



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            getParentData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Table_View.setOnMouseClicked(event -> {

            btn_UpdateParent.disableProperty().bind(Bindings.isEmpty(Table_View.getSelectionModel().getSelectedItems()));
            btn_deleteParent.disableProperty().bind(Bindings.isEmpty(Table_View.getSelectionModel().getSelectedItems()));

        });

        this.Table_View.setRowFactory(new Callback<TableView<parentData>, TableRow<parentData>>() {
            @Override
            public TableRow<parentData> call(TableView<parentData> param) {
                final TableRow<parentData> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem addChild = new MenuItem("Add Ward");
                final MenuItem view_wards = new MenuItem("Wards");


                addChild.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Stage formstage = new Stage();
                            FXMLLoader Loader = new FXMLLoader();
                            Pane root = Loader.load(getClass().getResource("/update/form.fxml").openStream());

                            DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                            DateFormat year = new SimpleDateFormat("yyyy");
                            Date d = new Date();

                            FormController formController = Loader.getController();
                            formController.getParentId(Table_View.getSelectionModel().getSelectedItem());
                            formController.disableUpdateRecordButton();
                            formController.getTime(""+dateFormatter.format(d)+"");
                            formController.getYear(""+year.format(d)+"");



                            formstage.setTitle("Student Form");

                            Image image = new Image("file:app_icon.png");
                            formstage.getIcons().add(image);

                            formstage.setScene(new Scene(root));
                            formstage.setResizable(false);
                            formstage.initStyle(StageStyle.UTILITY);

                            formstage.initModality(Modality.APPLICATION_MODAL);
                            formstage.show();
                        } catch (IOException e) {
                            homeController.showAlert("Error",null,e.getLocalizedMessage());
                            e.printStackTrace();
                        }

                    }
                });

                view_wards.setOnAction(event -> {

                    try {
                        Stage formstage = new Stage();
                        FXMLLoader Loader = new FXMLLoader();
                        Pane root = Loader.load(getClass().getResource("/update/parent_wardCount.fxml").openStream());

                        parentWardCountCotroller wardCountCotroller = Loader.getController();
                        try {
                            wardCountCotroller.getNumberOfWards(Table_View.getSelectionModel().getSelectedItem());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            wardCountCotroller.showParentWard(Table_View.getSelectionModel().getSelectedItem());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        formstage.setTitle("Student Form");

                        Image image = new Image("file:app_icon.png");
                        formstage.getIcons().add(image);

                        formstage.setScene(new Scene(root));
                        formstage.setResizable(false);
                        formstage.initStyle(StageStyle.UTILITY);

                        formstage.initModality(Modality.APPLICATION_MODAL);
                        formstage.show();
                    } catch (IOException e) {
                        homeController.showAlert("Error",null,e.getLocalizedMessage());
                        e.printStackTrace();
                    }


                });



                contextMenu.getItems().add(addChild);
                contextMenu.getItems().add(view_wards);


                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu) null)
                                .otherwise(contextMenu)
                );

                return row;
            }
        });


        FilteredList<parentData> filteredData = new FilteredList<>(data, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super parentData>) parentInfo -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (parentInfo.getId().contains(newValue)) {
                        return true;
                    } else if (parentInfo.getFatherFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (parentInfo.getFatherLastName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }else if (parentInfo.getMotherFirstName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if (parentInfo.getMotherLastName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<parentData> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(Table_View.comparatorProperty());
            Table_View.setItems(sortedData);

        });

        btn_RefreshTable.setOnAction(event -> {

            Table_View.setItems(null);
            try {
                getParentData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        btn_addParent.setOnAction(event -> {

            addParent();
        });

        btn_deleteParent.setOnAction(event -> {

            try {
                deleteParent(Table_View.getSelectionModel().getSelectedItem());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });



        btn_UpdateParent.setOnAction(event -> {

            updateButtonActionPerform();
        });



    }

    private void getParentData()throws SQLException{

        this.data = FXCollections.observableArrayList();
        String querry = "SELECT * FROM Parent";

        try {
            conn = SqliteConnection.Connector();
            rs = conn.createStatement().executeQuery(querry);

            while (rs.next()){
                this.data.addAll(new parentData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
        }

        this.Column_ID.setCellValueFactory(new PropertyValueFactory<parentData, String>("id"));
        this.Column_F_LastName.setCellValueFactory(new PropertyValueFactory<parentData, String>("fatherLastName"));
        this.Column_F_FirstName.setCellValueFactory(new PropertyValueFactory<parentData, String>("fatherFirstName"));
        this.Column_M_LASTNAME.setCellValueFactory(new PropertyValueFactory<parentData, String>("motherLastName"));
        this.Column_M_FIRSTNAME.setCellValueFactory(new PropertyValueFactory<parentData, String>("motherFirstName"));
        this.Column_F_PHONE.setCellValueFactory(new PropertyValueFactory<parentData, String>("fatherPhone"));
        this.Column_M_PHONE.setCellValueFactory(new PropertyValueFactory<parentData, String>("motherphone"));
        this.Column_F_EMAIL.setCellValueFactory(new PropertyValueFactory<parentData, String>("fatherEmail"));
        this.Colun_M_EMAIL.setCellValueFactory(new PropertyValueFactory<parentData, String>("motherEmail"));

        this.Table_View.setItems(null);
        this.Table_View.setItems(data);
    }

    private void addParent(){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/update/parent_form.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(ParentsController.class.getName()).log(Level.SEVERE, null, e);
        }

        parentFormController parentForm = loader.getController();
        parentForm.btn_update.setVisible(false);

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Parent Form");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNIFIED);
        stage.setResizable(false);

        stage.setScene(new Scene(parent));

        Image image = new Image("file:app_icon.png");
        stage.getIcons().add(image);

        stage.show();
    }

    private void deleteParent(parentData info)throws SQLException{
        String id = info.getId();

        String deleteQuerry = "DELETE FROM Parent WHERE parent_id = "+id+"";
        try {
            conn = SqliteConnection.Connector();
            pr = conn.prepareStatement(deleteQuerry);

            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pr.close();
        }

    }

    private void updateButtonActionPerform(){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/update/parent_form.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(ParentsController.class.getName()).log(Level.SEVERE,null,e);
        }

        parentFormController parentForm = loader.getController();
        parentForm.populateFields(Table_View.getSelectionModel().getSelectedItem());

        Pane root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Parent Form");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNIFIED);
        stage.setResizable(false);

        stage.setScene(new Scene(root));

        Image image = new Image("file:app_icon.png");
        stage.getIcons().add(image);

        stage.show();
    }
}
