package com.sysmap.restApi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sysmap.restApi.service.auth.AuthenticateRequest;
import com.sysmap.restApi.service.auth.AuthenticateResponse;
import com.sysmap.restApi.service.auth.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService _authenticationService;

    // Authenticate = Login
    @PostMapping()
    public ResponseEntity<AuthenticateResponse> authenticate(@Valid @RequestBody AuthenticateRequest request) {
        try {
            return ResponseEntity.ok().body(_authenticationService.authenticate(request));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
