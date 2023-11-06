package com.jag.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String connectionUrl = "jdbc:mysql://localhost:3306/demo";
    private static final String username = "root";
    private static final String password = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionUrl, username, password);
            System.out.println("Connection " + !connection.isClosed());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;


    }

    public static void createData() {
        try {
            Connection con = getConnection();
            con.createStatement().executeUpdate(" " +
                    " " +
                    "CREATE TABLE IF NOT EXISTS auth (" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "username VARCHAR(255)," +
                    "password VARCHAR(255)," +
                    "PRIMARY KEY(id)" +
                    ")");
            con.createStatement().executeUpdate(" " +
                    "INSERT INTO auth (username, password) VALUES" +
                    "('a','a');" +
                    " ");
            con.createStatement().executeUpdate(" " +
                    "INSERT INTO auth (username, password) VALUES" +
                    "('b','b');" +
                    " ");
            con.createStatement().executeUpdate(" " +
                    "INSERT INTO auth (username, password) VALUES" +
                    "('ab','ab');" +
                    " ");
            con.createStatement().executeUpdate(" " +
                    "CREATE TABLE IF NOT EXISTS todo (" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "todo VARCHAR(255)," +
                    "userId INT," +
                    "PRIMARY KEY(id)," +
                    "FOREIGN KEY (userId) REFERENCES auth(id)" +
                    ")");
            con.createStatement().executeUpdate(" " +
                    "INSERT INTO todo (todo, userId) VALUES" +
                    "('ab',1);" +
                    " ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
