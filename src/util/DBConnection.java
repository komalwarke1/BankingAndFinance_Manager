package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/banking_db";
    private static final String USER = "bankuser";
    private static final String PASSWORD = "bank123";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
