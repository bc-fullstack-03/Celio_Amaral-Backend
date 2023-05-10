package com.sysmap.restApi.api;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
//import com.sysmap.restApi.service.security.JwtService;
import com.sysmap.restApi.service.user.CreateUserRequest;
import com.sysmap.restApi.service.user.IUserService;
import com.sysmap.restApi.service.user.UpdateUserRequest;
import com.sysmap.restApi.service.user.UserResponse;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private IUserService _userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request) {
        // if (!JwtService.isValidToken(getToken(), getUserId())) {
        // return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not
        // authenticate");
        // }
        var response = _userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Passwordless data query method
    @GetMapping()
    public ResponseEntity<UserResponse> getUser(String email) {
        var response = _userService.findUserByEmail(email);
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<Void> updateUser(@RequestBody UpdateUserRequest request) {
        _userService.updateUser(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        _userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // public String getToken() {
    // var jwt = ((ServletRequestAttributes)
    // RequestContextHolder.getRequestAttributes()).getRequest()
    // .getHeader("Authorization");
    // return jwt.substring(7);
    // }

    // public String getUserId() {
    // return ((ServletRequestAttributes)
    // RequestContextHolder.getRequestAttributes()).getRequest()
    // .getHeader("UserId");
    // }

    @PostMapping("/uploadPhoto")
    public ResponseEntity<Void> uploadPhotoProfile(@RequestParam("photo") MultipartFile photoFile) {
        try {
            _userService.uploadPhotoProfile(photoFile);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}