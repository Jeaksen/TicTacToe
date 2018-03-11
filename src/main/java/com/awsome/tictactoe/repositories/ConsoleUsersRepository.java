package com.awsome.tictactoe.repositories;

import com.awsome.tictactoe.model.User;

public class ConsoleUsersRepository implements IUsersRepository {

    @Override
    public void saveUser(User user) {
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
    }

    @Override
    public User findUser(String username) {
        User user = new User(username, "*");
        return user;
    }
}
