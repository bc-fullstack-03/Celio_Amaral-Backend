package com.sysmap.restApi.service.post;

import java.util.Date;
import lombok.Data;

@Data
public class UpdatePostRequest {
    private Date date;
    private String title;
    private String body; 
}
