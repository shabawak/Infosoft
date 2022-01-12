package dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.SqliteConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {
    @FXML
    private Label blb_totalStudent;

    @FXML
    private Hyperlink hype_totalStudent;

    @FXML
    private Label lbl_activeStudent;

    @FXML
    private Hyperlink hype_activeStudent;

    @FXML
    private Label lbl_droppedOut;

    @FXML
    private Hyperlink hype_droppedOut;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label lbl_pieClassValue;

    @FXML
    private Label lbl_totalMale;

    @FXML
    private Hyperlink hype_Male;

    @FXML
    private Label lbl_totalFemale;

    @FXML
    private Hyperlink hype_Female;

    @FXML
    private Label lbl_reload;

    @FXML
    private Label lbl_totalUsers;


    private SqliteConnection dc;

    private int jhs3Total, jhs2Total, jhs1Total, p6Total, p5Total, p4Total, p3Total, p2Total, p1Total, kg2Total, kg1Total, nurseryTotal ;


    private Connection conn;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getTotalStudent();
            getTotalFemaleStudent();
            getTotalMaleStudent();
            getDroppedOutStudent();
            getActiveStudent();
            getTotalUsers();
            countJHS3Student();
            countJHS2Student();
            countJHS1Student();
            countP6Student();
            countP5Student();
            countP4Student();
            countP3Student();
            countP2Student();
            countP1Student();
            countKG2Student();
            countKG1Student();
            countNurseryStudent();
            ShowPieChartValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lbl_activeStudent.setOnMouseClicked(event -> {

            try {
                Stage dashboardTable = new Stage();
                FXMLLoader Loader = new FXMLLoader();
                Pane root = Loader.load(getClass().getResource("/dashboard/dashboard_table.fxml").openStream());

                DashboardTableController dashboardTableController = Loader.getController();
                try {
                    dashboardTableController.displayActiveStudents();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dashboardTableController.enablePrintActiveButton();
                dashboardTableController.setLabelText("ALL ACTIVE STUDENTS");


                Image image = new Image("file:app_icon.png");
                dashboardTable.getIcons().add(image);

                dashboardTable.setScene(new Scene(root));
                dashboardTable.setResizable(true);
                dashboardTable.initStyle(StageStyle.UNIFIED);
                dashboardTable.setTitle("Active Students");

                dashboardTable.initModality(Modality.APPLICATION_MODAL);
                dashboardTable.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

       /* hype_activeStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage dashboardTable = new Stage();
                    FXMLLoader Loader = new FXMLLoader();
                    Pane root = Loader.load(getClass().getResource("/dashboard/dashboard_table.fxml").openStream());

                    DashboardTableController dashboardTableController = Loader.getController();
                    try {
                        dashboardTableController.displayActiveStudents();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    dashboardTableController.enablePrintActiveButton();
                    dashboardTableController.setLabelText("ALL ACTIVE STUDENTS");


                    Image image = new Image("file:app_icon.png");
                    dashboardTable.getIcons().add(image);

                    dashboardTable.setScene(new Scene(root));
                    dashboardTable.setResizable(true);
                    dashboardTable.initStyle(StageStyle.UNIFIED);
                    dashboardTable.setTitle("Active Students");

                    dashboardTable.initModality(Modality.APPLICATION_MODAL);
                    dashboardTable.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });*/

       lbl_droppedOut.setOnMouseClicked(event -> {

           try {
               Stage dashboardTable = new Stage();
               FXMLLoader Loader = new FXMLLoader();
               Pane root = Loader.load(getClass().getResource("/dashboard/dashboard_table.fxml").openStream());

               DashboardTableController dashboardTableController = Loader.getController();
               try {
                   dashboardTableController.displayInactiveStudents();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
               dashboardTableController.enablePrintInactiveButton();
               dashboardTableController.setLabelText("ALL DROPPED OUT STUDENTS");


               Image image = new Image("file:app_icon.png");
               dashboardTable.getIcons().add(image);

               dashboardTable.setScene(new Scene(root));
               dashboardTable.setResizable(true);
               dashboardTable.initStyle(StageStyle.UNIFIED);
               dashboardTable.setTitle("Dropped out Students");

               dashboardTable.initModality(Modality.APPLICATION_MODAL);
               dashboardTable.show();
           } catch (IOException e) {
               e.printStackTrace();
           }

       });

        /*hype_droppedOut.setOnAction(event -> {
            try {
                Stage dashboardTable = new Stage();
                FXMLLoader Loader = new FXMLLoader();
                Pane root = Loader.load(getClass().getResource("/dashboard/dashboard_table.fxml").openStream());

                DashboardTableController dashboardTableController = Loader.getController();
                try {
                    dashboardTableController.displayInactiveStudents();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dashboardTableController.enablePrintInactiveButton();
                dashboardTableController.setLabelText("ALL DROPPED OUT STUDENTS");


                Image image = new Image("file:app_icon.png");
                dashboardTable.getIcons().add(image);

                dashboardTable.setScene(new Scene(root));
                dashboardTable.setResizable(true);
                dashboardTable.initStyle(StageStyle.UNIFIED);
                dashboardTable.setTitle("Dropped out Students");

                dashboardTable.initModality(Modality.APPLICATION_MODAL);
                dashboardTable.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/

        lbl_totalFemale.setOnMouseClicked(event -> {

            try {
                Stage dashboardTable = new Stage();
                FXMLLoader Loader = new FXMLLoader();
                Pane root = Loader.load(getClass().getResource("/dashboard/dashboard_table.fxml").openStream());

                DashboardTableController dashboardTableController = Loader.getController();
                try {
                    dashboardTableController.displayFemaleStudents();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dashboardTableController.enablePrintFemalButton();
                dashboardTableController.setLabelText("ALL FEMALE STUDENTS");


                Image image = new Image("file:app_icon.png");
                dashboardTable.getIcons().add(image);

                dashboardTable.setScene(new Scene(root));
                dashboardTable.setResizable(true);
                dashboardTable.initStyle(StageStyle.UNIFIED);
                dashboardTable.setTitle("Female Students");

                dashboardTable.initModality(Modality.APPLICATION_MODAL);
                dashboardTable.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



       /* hype_Female.setOnAction(event -> {
            try {
                Stage dashboardTable = new Stage();
                FXMLLoader Loader = new FXMLLoader();
                Pane root = Loader.load(getClass().getResource("/dashboard/dashboard_table.fxml").openStream());

                DashboardTableController dashboardTableController = Loader.getController();
                try {
                    dashboardTableController.displayFemaleStudents();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dashboardTableController.enablePrintFemalButton();
                dashboardTableController.setLabelText("ALL FEMALE STUDENTS");


                Image image = new Image("file:app_icon.png");
                dashboardTable.getIcons().add(image);

                dashboardTable.setScene(new Scene(root));
                dashboardTable.setResizable(true);
                dashboardTable.initStyle(StageStyle.UNIFIED);
                dashboardTable.setTitle("Female Students");

                dashboardTable.initModality(Modality.APPLICATION_MODAL);
                dashboardTable.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/

       lbl_totalMale.setOnMouseClicked(event -> {

           try {
               Stage dashboardTable = new Stage();
               FXMLLoader Loader = new FXMLLoader();
               Pane root = Loader.load(getClass().getResource("/dashboard/dashboard_table.fxml").openStream());

               DashboardTableController dashboardTableController = Loader.getController();
               try {
                   dashboardTableController.displayMaleStudents();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
               dashboardTableController.enablePrintMaleButton();
               dashboardTableController.setLabelText("ALL MALE STUDENTS");


               Image image = new Image("file:app_icon.png");
               dashboardTable.getIcons().add(image);

               dashboardTable.setScene(new Scene(root));
               dashboardTable.setResizable(true);
               dashboardTable.initStyle(StageStyle.UNIFIED);
               dashboardTable.setTitle("Male Students");

               dashboardTable.initModality(Modality.APPLICATION_MODAL);
               dashboardTable.show();
           } catch (IOException e) {
               e.printStackTrace();
           }

       });




        /*hype_Male.setOnAction(event -> {
            try {
                Stage dashboardTable = new Stage();
                FXMLLoader Loader = new FXMLLoader();
                Pane root = Loader.load(getClass().getResource("/dashboard/dashboard_table.fxml").openStream());

                DashboardTableController dashboardTableController = Loader.getController();
                try {
                    dashboardTableController.displayMaleStudents();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dashboardTableController.enablePrintMaleButton();
                dashboardTableController.setLabelText("ALL MALE STUDENTS");


                Image image = new Image("file:app_icon.png");
                dashboardTable.getIcons().add(image);

                dashboardTable.setScene(new Scene(root));
                dashboardTable.setResizable(true);
                dashboardTable.initStyle(StageStyle.UNIFIED);
                dashboardTable.setTitle("Male Students");

                dashboardTable.initModality(Modality.APPLICATION_MODAL);
                dashboardTable.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/

        blb_totalStudent.setOnMouseClicked(event -> {


            try {
                Stage dashboardTable = new Stage();
                FXMLLoader Loader = new FXMLLoader();
                Pane root = Loader.load(getClass().getResource("/dashboard/dashboard_table.fxml").openStream());

                DashboardTableController dashboardTableController = Loader.getController();
                try {
                    dashboardTableController.displayTotalStudents();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dashboardTableController.enablePrintTotalButton();
                dashboardTableController.setLabelText("ALL STUDENTS");


                Image image = new Image("file:app_icon.png");
                dashboardTable.getIcons().add(image);

                dashboardTable.setScene(new Scene(root));
                dashboardTable.setResizable(true);
                dashboardTable.initStyle(StageStyle.UNIFIED);
                dashboardTable.setTitle("All Students");

                dashboardTable.initModality(Modality.APPLICATION_MODAL);
                dashboardTable.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        /*hype_totalStudent.setOnAction(event -> {
            try {
                Stage dashboardTable = new Stage();
                FXMLLoader Loader = new FXMLLoader();
                Pane root = Loader.load(getClass().getResource("/dashboard/dashboard_table.fxml").openStream());

                DashboardTableController dashboardTableController = Loader.getController();
                try {
                    dashboardTableController.displayTotalStudents();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dashboardTableController.enablePrintTotalButton();
                dashboardTableController.setLabelText("ALL STUDENTS");


                Image image = new Image("file:app_icon.png");
                dashboardTable.getIcons().add(image);

                dashboardTable.setScene(new Scene(root));
                dashboardTable.setResizable(true);
                dashboardTable.initStyle(StageStyle.UNIFIED);
                dashboardTable.setTitle("All Students");

                dashboardTable.initModality(Modality.APPLICATION_MODAL);
                dashboardTable.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/

    }


    public void getTotalStudent() throws SQLException {
        try {
            String sql = "SELECT count(*) FROM StudentData";
            conn = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                String totalStudent = resultSet.getString(1);
                blb_totalStudent.setText(totalStudent);
            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }

    }

    public void getActiveStudent() throws SQLException {
        String condition = "ACTIVE";
        try {
            String sqlquery = "SELECT count(*) FROM StudentData WHERE status = '" + condition + "' ";
            conn = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement(sqlquery);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String ActiveStudent = resultSet.getString(1);
                lbl_activeStudent.setText(ActiveStudent);
            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }


    }

    public void getDroppedOutStudent() throws SQLException {
        String Condition = "INACTIVE";
        try {
            String sqlquery = "SELECT count(*) FROM StudentData WHERE status = '" + Condition + "' ";
            conn = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement(sqlquery);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String InactiveStudent = resultSet.getString(1);
                lbl_droppedOut.setText(InactiveStudent);
            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }


    }

    public void countJHS3Student() throws SQLException {
        String JHS3_Condition = "JHS3";


        try {

            conn = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement("SELECT count(*) FROM StudentData WHERE class = '" + JHS3_Condition + "' ");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                jhs3Total = Integer.parseInt(resultSet.getString(1));

            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }
    }

    public void countJHS2Student() throws SQLException {
        String JHS2_Condition = "JHS2";


        try {

            conn = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement("SELECT count(*) FROM StudentData WHERE class = '" + JHS2_Condition + "' ");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                jhs2Total = Integer.parseInt(resultSet.getString(1));

            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }
    }

    public void countJHS1Student() throws SQLException {
        String JHS1_Condition = "JHS1";


        try {

            conn = SqliteConnection.Connector();

            preparedStatement = conn.prepareStatement("SELECT count(*) FROM StudentData WHERE class = '" + JHS1_Condition + "' ");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                jhs1Total = Integer.parseInt(resultSet.getString(1));

            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }
    }

    public void countP6Student() throws SQLException {
        String P6_Condition = "P6";


        try {

            conn = SqliteConnection.Connector();

            preparedStatement = conn.prepareStatement("SELECT count(*) FROM StudentData WHERE class = '" + P6_Condition + "' ");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                p6Total = Integer.parseInt(resultSet.getString(1));

            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }
    }

    public void countP5Student() throws SQLException {
        String P5_Condition = "P5";


        try {

            conn = SqliteConnection.Connector();

            preparedStatement = conn.prepareStatement("SELECT count(*) FROM StudentData WHERE class = '" + P5_Condition + "' ");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                p5Total = Integer.parseInt(resultSet.getString(1));

            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }
    }

    public void countP4Student() throws SQLException {
        String P4_Condition = "P4";


        try {

            conn = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement("SELECT count(*) FROM StudentData WHERE class = '" + P4_Condition + "' ");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                p4Total = Integer.parseInt(resultSet.getString(1));

            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }
    }

    public void countP3Student() throws SQLException {
        String P3_Condition = "P3";


        try {

            conn = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement("SELECT count(*) FROM StudentData WHERE class = '" + P3_Condition + "' ");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                p3Total =Integer.parseInt(resultSet.getString(1));

            }

        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }
    }

    public void countP2Student() throws SQLException {
        String P2_Condition = "P2";


        try {

            conn = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement("SELECT count(*) FROM StudentData WHERE class = '" + P2_Condition + "' ");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                p2Total = Integer.parseInt(resultSet.getString(1));

            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }
    }

    public void countP1Student() throws SQLException {
        String P1_Condition = "P1";


        try {

            Connection dbConnection = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement("SELECT count(*) FROM StudentData WHERE class = '" + P1_Condition + "' ");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                p1Total = Integer.parseInt(resultSet.getString(1));

            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }
    }

    public void countKG2Student() throws SQLException {
        String KG2_Condition = "KG2";


        try {

            conn = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement("SELECT count(*) FROM StudentData WHERE class = '" + KG2_Condition + "' ");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                kg2Total = Integer.parseInt(resultSet.getString(1));

            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }
    }

    public void countKG1Student() throws SQLException {
        String KG1_Condition = "KG1";


        try {

            conn = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement("SELECT count(*) FROM StudentData WHERE class = '" + KG1_Condition + "' ");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                kg1Total = Integer.parseInt(resultSet.getString(1));

            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }
    }

    public void countNurseryStudent() throws SQLException {
        String Nursery_Condition = "NURSERY";

        try {

            conn = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement("SELECT count(*) FROM StudentData WHERE class = '" + Nursery_Condition + "' ");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nurseryTotal = Integer.parseInt(resultSet.getString(1));

            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }
    }


    public void ShowPieChartValue() {



        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(

                new PieChart.Data("JHS3", jhs3Total),
                new PieChart.Data("JHS2", jhs2Total),
                new PieChart.Data("JHS1", jhs1Total),
                new PieChart.Data("P6", p6Total),
                new PieChart.Data("P5", p5Total),
                new PieChart.Data("P4", p4Total),
                new PieChart.Data("P3", p3Total),
                new PieChart.Data("P2", p2Total),
                new PieChart.Data("P1", p1Total),
                new PieChart.Data("KG2", kg2Total),
                new PieChart.Data("KG1", kg1Total),
                new PieChart.Data("NURSERY", nurseryTotal)

        );
        pieChart.setData(pieChartData);


        lbl_pieClassValue.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                   /* lbl_pieClassValue.setTranslateX(event.getScreenX());
                    lbl_pieClassValue.setTranslateY(event.getScreenY());*/
                    lbl_pieClassValue.setText(String.valueOf(data.getPieValue()) + "%");
                }
            });
        }


    }

    public void getTotalMaleStudent() throws SQLException {
        String Condition = "MALE";
        try {
            String sqlquery = "SELECT count(*) FROM StudentData WHERE gender = '" + Condition + "' ";
            conn = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement(sqlquery);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String Male = resultSet.getString(1);
                lbl_totalMale.setText(Male);
            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }


    }

    public void getTotalFemaleStudent() throws SQLException {
        String Condition = "FEMALE";
        try {
            String sqlquery = "SELECT count(*) FROM StudentData WHERE gender = '" + Condition + "' ";
            conn = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement(sqlquery);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String Female = resultSet.getString(1);
                lbl_totalFemale.setText(Female);
            }


        } catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();

        }


    }

    public void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @FXML
    private void reloadOverView() {
        try {
            getTotalStudent();
            getTotalFemaleStudent();
            getTotalMaleStudent();
            getDroppedOutStudent();
            getActiveStudent();
            getTotalUsers();
            countJHS3Student();
            countJHS2Student();
            countJHS1Student();
            countP6Student();
            countP5Student();
            countP4Student();
            countP3Student();
            countP2Student();
            countP1Student();
            countKG2Student();
            countKG1Student();
            countNurseryStudent();
            ShowPieChartValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getTotalUsers() throws SQLException {

        try {
            String sqlquery = "SELECT count(*) FROM UsersTable ";
            conn = SqliteConnection.Connector();
            preparedStatement = conn.prepareStatement(sqlquery);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String totalUsers = resultSet.getString(1);
                lbl_totalUsers.setText(totalUsers);
            }


        }
        catch (SQLException e) {
            showAlert("Error", "null", "" + e.getLocalizedMessage() + "");
            e.printStackTrace();
        }
        finally {
            resultSet.close();
            preparedStatement.close();
        }


    }

}


