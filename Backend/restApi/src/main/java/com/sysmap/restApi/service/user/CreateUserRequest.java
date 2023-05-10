package com.sysmap.restApi.service.user;

import java.util.UUID;
import lombok.Data;

@Data
public class CreateUserRequest {
    public String name;
    public String email;
    public String photo;
    public String password;
    public UUID createTo;
}
