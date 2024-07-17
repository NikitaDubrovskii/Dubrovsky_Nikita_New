package dev.dubrovsky.dao.user;

import dev.dubrovsky.dao.ICommonDao;
import dev.dubrovsky.model.user.User;

public interface IUserDao extends ICommonDao<User> {

    User findByUsername(String username);

    User findByEmail(String email);

}
