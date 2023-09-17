package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * the Connection Class will be utlized to create an active connection to our database. this Class utilizes the
 * by a.shazally
 */

public class ConnectionUtil {
    static String url = "jdbc:mysql://localhost:3306/liberary";
    static String username = "root";
    static String password = "";

    private static Connection connection = null;

    /**
     * @return active connection to the database
     */

    public static Connection getConnection(){
        if (connection == null){
            try {
                connection = DriverManager.getConnection(url,username,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }


}
