package com.sysmap.restApi.data;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.sysmap.restApi.entities.Comment;

public interface CommentRepository extends MongoRepository<Comment, UUID> {
    boolean existsCommentBycontents(String contents);
    Comment findCommentByContents(String contents);
    Comment deleteById(String id);
}
