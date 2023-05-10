package com.sysmap.restApi.entities;

import org.springframework.data.annotation.Id;
import java.util.UUID;
import java.util.Date;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document
@Data
public class Comment {

    @Id
    private UUID id;
    private String contents;
    private Date date;
    private Author author;

    public Comment(String contents, Date date, Author author) {
        this.contents = contents;
        this.date = date;
        this.author = author;
    }

    protected void setId(String id) {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }
}
