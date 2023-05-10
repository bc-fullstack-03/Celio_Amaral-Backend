package com.sysmap.restApi.service.comment;

import org.springframework.stereotype.Service;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import com.sysmap.restApi.data.CommentRepository;
import com.sysmap.restApi.entities.Comment;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository repository;

    @Override
    public String createComment(CreateCommentRequest request) {
        var comment = new Comment(request.date, request.contents);
        if (!repository.existsCommentBycontents(request.contents)) {
            throw new RuntimeException("Comment already exists");
        }
        repository.save(comment);
        return comment.getId().toString();
    }

    @Override
    public void updateComment(UpdateCommentRequest request) {
        var comment = repository.findCommentByContents(request.getContents());
        repository.save(comment);
    }

    public void deleteComment(UUID id) {
        var comment = repository.findById(id).orElse(null);

        if (comment != null) {
            repository.deleteById(id);
        }
    }
}
