package com.sysmap.restApi.service.auth;

public interface IAuthenticationService {
    AuthenticateResponse authenticate(AuthenticateRequest request);
}
