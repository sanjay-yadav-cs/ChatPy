/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Sanja
 */
public class DBConnection {

    private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=ExampleDB";
    private static final String JDBC_USER = "yourUsername";
    private static final String JDBC_PASSWORD = "yourPassword";
    private static volatile Connection conn;
    
    private DBConnection(){}
    
    public static Connection getConnection() {
        try {
            // Load JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            if (conn == null) {
                synchronized (Connection.class) {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
