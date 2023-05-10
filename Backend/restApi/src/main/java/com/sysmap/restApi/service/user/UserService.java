package com.sysmap.restApi.service.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.sysmap.restApi.data.UserRepository;
import com.sysmap.restApi.entities.User;
//import com.sysmap.restApi.service.event.IEventService;
import com.sysmap.restApi.service.fileUpload.IFileUploadService;

@Service
public class UserService implements IUserService {

    // @Autowired
    // private IEventService _eventService;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    @Autowired
    private IFileUploadService _fileUploadService;

    @Autowired
    private UserRepository repository;

    public String createUser(CreateUserRequest request) {
        var user = new User(request.name, request.email, request.photo);
        // In the repository, searches for user through email and returns true or false
        if (!repository.existsUserByEmail(request.email)) {
            throw new RuntimeException("User already exists");
        }
        // The password becomes an encrypted hash
        var hash = _passwordEncoder.encode(request.password);
        user.setPassword(hash);

        repository.save(user);
        // _eventService.send(user.getId().toString());
        return user.getId().toString();
    }

    public UserResponse findUserByEmail(String email) {
        var user = repository.findUserByEmail(email);
        var response = new UserResponse(user.getId(), user.getName(), user.getPhoto(), user.getEmail());
        return response;
    }

    public void updateUser(UpdateUserRequest request) {
        var user = repository.findUserByName(request.getName());
        repository.save(user);
    }

    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }

    public User getUser(String email) {
        return repository.findUserByEmail(email);
    }

    public User getUserById(UUID id) {
        return repository.findUserById(id);
    }

    // Potential null pointer access: The method getOriginalFilename() may return
    // null
    public void uploadPhotoProfile(MultipartFile photoFile) throws Exception {
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (!photoFile.isEmpty()) {

            try {
                var fileName = user.getId() + "."
                        + photoFile.getOriginalFilename()
                                .substring(photoFile.getOriginalFilename().lastIndexOf(".") + 1);
                user.setPhoto(_fileUploadService.upload(photoFile, fileName));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
            user.setPhoto("");
            repository.save(user);
        }
    }
}
