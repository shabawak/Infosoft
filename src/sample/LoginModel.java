package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection connection;

    public LoginModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) {
            System.out.println("Connection not successfull");
            System.exit(1);
        }
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean isUserLogin(String user, String pass) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from UsersTable where username = ? and password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;

        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public boolean isAdminLogin(String user, String pass) throws SQLException {
        PreparedStatement AdminpreparedStatement = null;
        ResultSet AdminresultSet = null;
        String query = "select * from AdminTable where username = ? and password = ?";
        try {
            AdminpreparedStatement = connection.prepareStatement(query);
            AdminpreparedStatement.setString(1, user);
            AdminpreparedStatement.setString(2, pass);

            AdminresultSet = AdminpreparedStatement.executeQuery();
            if (AdminresultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;

        } finally {
            AdminpreparedStatement.close();
            AdminresultSet.close();
        }
    }

    public boolean checkCredentials(String pass) throws SQLException {
        PreparedStatement AdminpreparedStatement = null;
        ResultSet AdminresultSet = null;
        String query = "SELECT * FROM UsersTable WHERE password = ?";
        try {
            AdminpreparedStatement = connection.prepareStatement(query);
            AdminpreparedStatement.setString(1, pass);

            AdminresultSet = AdminpreparedStatement.executeQuery();
            if (AdminresultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;

        } finally {
            AdminpreparedStatement.close();
            AdminresultSet.close();
        }
    }

    public boolean isReset(String pass) throws SQLException {
        PreparedStatement AdminpreparedStatement = null;
        ResultSet AdminresultSet = null;
        String query = "select * from RC where keyCode = ?";
        try {
            AdminpreparedStatement = connection.prepareStatement(query);
            AdminpreparedStatement.setString(1, pass);

            AdminresultSet = AdminpreparedStatement.executeQuery();
            if (AdminresultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;

        } finally {
            AdminpreparedStatement.close();
            AdminresultSet.close();
        }
    }

    public boolean administrator(String pass) throws SQLException {
        PreparedStatement AdminpreparedStatement = null;
        ResultSet AdminresultSet = null;
        String query = "SELECT * FROM AdminTable WHERE password = ?";
        try {
            AdminpreparedStatement = connection.prepareStatement(query);
            AdminpreparedStatement.setString(1, pass);

            AdminresultSet = AdminpreparedStatement.executeQuery();
            if (AdminresultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;

        } finally {
            AdminpreparedStatement.close();
            AdminresultSet.close();
        }
    }

    public boolean snrAdmin(String user, String pass) throws SQLException {
        PreparedStatement AdminpreparedStatement = null;
        ResultSet AdminresultSet = null;
        String query = "select * from developer where developer_username =? and developer_password =?";
        try {
            AdminpreparedStatement = connection.prepareStatement(query);
            AdminpreparedStatement.setString(1, user);
            AdminpreparedStatement.setString(2, pass);

            AdminresultSet = AdminpreparedStatement.executeQuery();
            if (AdminresultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;

        } finally {
            AdminpreparedStatement.close();
            AdminresultSet.close();
        }
    }


}
