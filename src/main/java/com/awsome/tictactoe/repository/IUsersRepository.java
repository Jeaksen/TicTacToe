package com.awsome.tictactoe.repository;

import com.awsome.tictactoe.model.User;

public interface IUsersRepository {


    /**
     * This method saves the given User
     * @param user - User object with a username(String) and password(String)
     */
    void saveUser(User user) throws Exception;

    /**
     * This methods searches a User by his username
     * @param username name of a User that should be returned
     * @return User object or null if User associated with the username was not found
     */
    User findUser(String username) throws Exception;
}
