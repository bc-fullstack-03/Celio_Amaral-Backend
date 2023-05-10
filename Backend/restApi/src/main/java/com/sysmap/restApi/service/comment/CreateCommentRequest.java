package com.sysmap.restApi.service.comment;

import java.util.Date;
import lombok.Data;

@Data
public class CreateCommentRequest {
    public Date date;
    public String contents;
}