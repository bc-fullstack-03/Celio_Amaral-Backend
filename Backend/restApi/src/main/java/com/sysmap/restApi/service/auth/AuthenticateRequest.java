package com.sysmap.restApi.service.auth;

import lombok.Data;

@Data // getters e setters
public class AuthenticateRequest {
    private String email;
    private String password;
}
