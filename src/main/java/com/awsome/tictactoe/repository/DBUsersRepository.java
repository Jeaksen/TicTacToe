package com.awsome.tictactoe.repository;

import com.awsome.tictactoe.model.User;
import com.awsome.tictactoe.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUsersRepository implements IUsersRepository {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    public DBUsersRepository() throws SQLException {
        connection = DBConnection.getConnection();
    }

    @Override
    public void saveUser(User user) throws SQLException {
        statement = connection.prepareStatement("INSERT INTO users(username, password) VALUE (?,?);");
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.execute();
    }

    @Override
    public User findUser(String username) throws SQLException {
        statement = connection.prepareStatement("SELECT password FROM users WHERE username=?;");
        statement.setString(1,username);
        result = statement.executeQuery();
        while (result.next()){
            return new User(username,result.getString("password"));
        }
        return null;
    }
}
