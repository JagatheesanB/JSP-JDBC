package com.jag.dao;

import com.jag.db.DbConnection;
import com.jag.model.Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegDao {

    private final Connection connection;

    public RegDao() {
        connection = DbConnection.getConnection();
    }

    private String SelectSQL = "SELECT id, username, password,c_password FROM register WHERE username=? and password=? and c_password=?";

    private String INSERTSQL = "INSERT INTO register(username,password,c_password) VALUES (?,?,?) ";

    public Register regUser(String username, String password, String c_password) throws SQLException {
        Register user = null;

        PreparedStatement statement = connection.prepareStatement(INSERTSQL);
        try {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, c_password);
            statement.executeUpdate();
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new Register();
                user.setId(Integer.parseInt(rs.getString("id")));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setC_password(rs.getString("c_password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
