package com.sysmap.restApi.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sysmap.restApi.service.security.JwtService;
import com.sysmap.restApi.service.user.IUserService;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IUserService _userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    @Override
    public AuthenticateResponse authenticate(AuthenticateRequest request) {
        var user = _userService.getUser(request.getEmail());
        if (user == null) {
            return null;
        }

        // Validate Password (Database)
        if (!_passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect username or password.");
        }

        var token = jwtService.generateToken(user.getId());
        var response = new AuthenticateResponse();

        response.setUserId(user.getId());
        response.setToken(token);

        return response;
    }
}
