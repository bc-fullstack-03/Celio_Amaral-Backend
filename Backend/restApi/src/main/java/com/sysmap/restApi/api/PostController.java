package com.sysmap.restApi.api;

import java.util.UUID;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.sysmap.restApi.entities.Post;
import com.sysmap.restApi.service.post.CreatePostRequest;
import com.sysmap.restApi.service.post.IPostService;
import com.sysmap.restApi.service.post.UpdatePostRequest;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/post")
public class PostController {
    @Autowired
    private IPostService _postService;

    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody CreatePostRequest request) {
        var response = _postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping()
    public ResponseEntity<Void> updatePost(@RequestBody UpdatePostRequest request) {
        _postService.updatePost(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        _postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = _postService.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
}
