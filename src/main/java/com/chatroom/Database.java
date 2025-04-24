package com.chatroom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Return connection to the database
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_room", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Database connection error", e);
        }
    }
}
