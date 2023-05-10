package com.sysmap.restApi.entities;

import org.springframework.data.annotation.Id;
import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document
@Data
public class Author {

    @Id
    private UUID id;
    private String name;

    public Author(String name) {
        this.name = name;
    }

    protected void setId(String id) {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }
}
