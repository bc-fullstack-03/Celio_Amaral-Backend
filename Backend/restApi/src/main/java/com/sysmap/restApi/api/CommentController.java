package com.sysmap.restApi.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sysmap.restApi.service.comment.CreateCommentRequest;
import com.sysmap.restApi.service.comment.UpdateCommentRequest;
import com.sysmap.restApi.service.comment.ICommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {

    @Autowired
    private ICommentService _commentService;

    @PostMapping("/create")
    public ResponseEntity<String> createComment(@RequestBody CreateCommentRequest request) {
        var response = _commentService.createComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping()
    public ResponseEntity<Void> updateComment(@RequestBody UpdateCommentRequest request) {
        _commentService.updateComment(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteComment(@PathVariable UUID id) {
        _commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
