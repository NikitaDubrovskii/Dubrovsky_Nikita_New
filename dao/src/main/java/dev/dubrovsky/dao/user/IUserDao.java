package dev.dubrovsky.dao.user;

import dev.dubrovsky.model.user.User;

public interface IUserDao {

    //void registerUser(User user);

    //void loginUser(User user);

    //String getPassword(User user);

    User findByUsername(String username);

    User findByEmail(String email);

}
