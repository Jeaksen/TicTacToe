package com.awsome.tictactoe.repository;

import com.awsome.tictactoe.model.User;

public interface IUsersRepository {

    void saveUser(User user);

    User findUser(String username);
}
