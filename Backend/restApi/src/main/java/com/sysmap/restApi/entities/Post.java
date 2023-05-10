package com.sysmap.restApi.entities;

import org.springframework.data.annotation.Id;
import java.util.UUID;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document
@Data
public class Post {

    @Id
    private UUID id;
    private Date date;
    private String title;
    private String body;
    private Author author;
    // user-comment relationship
    private List<Comment> comments;

    

    public Post(Date date, String title, String body, Author author) {
        this.date = date;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    protected void setId(String id) {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
