package com.sysmap.restApi.service.post;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.sysmap.restApi.entities.Post;

public interface IPostService {
    String createPost(CreatePostRequest request);
    void updatePost(UpdatePostRequest request);
    void deletePost(UUID id);
    List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
