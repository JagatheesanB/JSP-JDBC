package com.jag.dao;

import com.jag.db.DbConnection;
import com.jag.model.User;

import java.sql.*;


public class UserDao {


    private static Connection connection;

    public UserDao() {

        DbConnection.createData();
        connection = DbConnection.getConnection();
    }

    private String selectSQL = "SELECT id, username, password FROM auth WHERE username=? and password=?";

    private static String regSQL = "INSERT INTO auth(username,password) VALUES(?,?)";

    public User loginUser(String username, String password) throws SQLException {
        User user = null;

        PreparedStatement statement = connection.prepareStatement(selectSQL);
        try {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(Integer.parseInt(rs.getString("id")));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void register(String username, String password, String c_password) {
        try {
            PreparedStatement ps = connection.prepareStatement(regSQL);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, c_password);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
