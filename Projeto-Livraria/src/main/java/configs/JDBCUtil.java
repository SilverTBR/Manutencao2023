/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configs;

import java.io.File;
import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class JDBCUtil {
    
    private static String url;
    private static String username;
    private static String password;

    public static void init(File fileName)
            throws IOException, ClassNotFoundException {

        Properties props = new Properties();
        FileInputStream in = new FileInputStream(fileName);
        props.load(in);
        String driver = props.getProperty("jdbc.driver");
        url = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        if (username == null) {
            username = "";
        }
        password = props.getProperty("jdbc.password");
        if (password == null) {
            password = "";
        }
        if (driver != null) {
            Class.forName(driver);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
