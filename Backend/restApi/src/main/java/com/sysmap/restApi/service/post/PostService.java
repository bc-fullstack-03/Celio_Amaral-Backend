package com.sysmap.restApi.service.post;

import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.sysmap.restApi.data.PostRepository;
import com.sysmap.restApi.entities.Post;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository repository;


    public String createPost(CreatePostRequest request) {
        var post = new Post(request.date, request.title, request.body, request.author);
        if (!repository.existsPostBytitle(request.title)) {
            throw new RuntimeException("Post already exists");
        }
        repository.save(post);
        return post.getId().toString();
    }

    @Override
    public void updatePost(UpdatePostRequest request) {
        var post = repository.findPostByBody(request.getBody());
        repository.save(post);
    }

    public void deletePost(UUID id) {
        var post = repository.findById(id).orElse(null);

        if(post != null) {
            repository.deleteById(id);
        }
    }

	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		// converting date to millisecond
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repository.fullSearch(text, minDate, maxDate);
	}
}
