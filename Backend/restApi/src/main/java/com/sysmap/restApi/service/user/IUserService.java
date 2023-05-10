package com.sysmap.restApi.service.user;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import com.sysmap.restApi.entities.User;

// Interface UserService
public interface IUserService {
    String createUser(CreateUserRequest request);

    UserResponse findUserByEmail(String email);

    void updateUser(UpdateUserRequest request);

    void deleteUser(UUID id);

    User getUser(String email);

    User getUserById(UUID id);

    void uploadPhotoProfile(MultipartFile photoFile) throws Exception;
}
