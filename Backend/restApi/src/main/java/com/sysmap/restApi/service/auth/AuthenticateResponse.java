package com.sysmap.restApi.service.auth;

import java.util.UUID;
import lombok.Data;

@Data
public class AuthenticateResponse {
    private UUID userId;
    private String token;
}
