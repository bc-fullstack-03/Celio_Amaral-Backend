package com.sysmap.restApi.service.user;

import lombok.Data;

@Data
public class UpdateUserRequest {
    public String name;
    public String email;
    public String photo;
    public String password;
}
