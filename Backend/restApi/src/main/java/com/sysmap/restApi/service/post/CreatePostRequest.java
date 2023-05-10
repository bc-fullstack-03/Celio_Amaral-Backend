package com.sysmap.restApi.service.post;

import java.util.Date;

import com.sysmap.restApi.entities.Author;

import lombok.Data;

@Data
public class CreatePostRequest {
    public Date date;
    public String title;
    public String body;
    public Author author;
}
