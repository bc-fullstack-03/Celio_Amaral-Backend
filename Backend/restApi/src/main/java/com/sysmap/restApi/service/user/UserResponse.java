package com.sysmap.restApi.service.user;

import lombok.Data;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private String photo;

    public UserResponse(UUID id, String name, String photo, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photo = photo;
    }
}
