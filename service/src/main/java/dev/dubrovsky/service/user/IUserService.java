package dev.dubrovsky.service.user;

import dev.dubrovsky.dto.request.user.NewUserRequest;
import dev.dubrovsky.dto.request.user.UpdateUserRequest;
import dev.dubrovsky.service.ICommonService;
import dev.dubrovsky.model.user.User;

public interface IUserService extends ICommonService<User, NewUserRequest, UpdateUserRequest> {

    void loginUser(String usernameOrEmail, String password);

    void recoverPassword(String email);

    void resetPassword(String usernameOrEmail, String oldPassword, String newPassword);

}
