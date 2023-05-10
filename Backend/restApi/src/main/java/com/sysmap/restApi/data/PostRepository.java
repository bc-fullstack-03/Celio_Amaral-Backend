package com.sysmap.restApi.data;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.sysmap.restApi.entities.Post;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;

public interface PostRepository extends MongoRepository<Post, UUID> {
    Post findPostByBody(String body);

    boolean existsPostBytitle(String title);

    Post deleteById(String id);

    @Query("{ $and: [ { date: {$gte: ?1} },"
            + " { date: { $lte: ?2} } ,"
            + " { $or: [ { 'title': { $regex: ?0, $options: 'i' } },"
            + " { 'body': { $regex: ?0, $options: 'i' } },"
            + " { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
