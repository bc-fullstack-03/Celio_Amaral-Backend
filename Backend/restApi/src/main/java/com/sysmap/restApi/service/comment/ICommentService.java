package com.sysmap.restApi.service.comment;

import java.util.UUID;

public interface ICommentService {
    String createComment(CreateCommentRequest request);
    void updateComment(UpdateCommentRequest request);
    void deleteComment(UUID id);
}
