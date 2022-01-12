package sample;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnection {


    public static Connection Connector() {


        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            Connection conn = DriverManager.getConnection("jdbc:sqlite:StudentManagementDb.sqlite");
            return conn;

        } catch (Exception e) {
            System.out.println(e);
            return null;

        }
    }

   /* public static Connection Connector(){

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:StudentManagementDb.sqlite");
            return conn;
        }catch (Exception e){
            System.out.println(e);
            return null;

        }
    }*/
}
