package com.sysmap.restApi.entities;

import java.util.UUID;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document
@Data
public class User {
    // attributes
    @Id // determine the primary key
    private UUID id;
    private String name;
    private String email;
    private String photo;
    private String password;
    // user-post relationship
    private List<Post> posts;

    // encapsulation
    public User(String name, String email, String photo) {
        this.name = name;
        this.email = email;
        this.photo = photo;
    }

    protected void setId(String id) {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
