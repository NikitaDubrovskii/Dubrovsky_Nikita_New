package dev.dubrovsky.dao.user;

import dev.dubrovsky.model.user.User;

public interface IUserDao {

    User findByUsername(String username);

    User findByEmail(String email);

    User findByUsernameOrEmail(String usernameOrEmail);

}
