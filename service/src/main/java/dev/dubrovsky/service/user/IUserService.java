package dev.dubrovsky.service.user;

import dev.dubrovsky.dto.request.user.NewUserRequest;
import dev.dubrovsky.dto.request.user.UpdateUserRequest;
import dev.dubrovsky.dto.request.user.UserLoginRequest;
import dev.dubrovsky.dto.request.user.UserResetPasswordRequest;
import dev.dubrovsky.dto.response.user.UserResponse;
import dev.dubrovsky.service.ICommonService;

public interface IUserService extends ICommonService<UserResponse, NewUserRequest, UpdateUserRequest> {

    String loginUser(UserLoginRequest request);

    String recoverPassword(String email);

    void resetPassword(UserResetPasswordRequest request);

}
