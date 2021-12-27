package site.linkway.core.service;

import site.linkway.core.entity.vo.CommentList;

public interface CommentService {
    CommentList fCommentListByGoodId(String goodId, int pageSize, int pageNow);

    CommentList sCommentServiceByFCommentId(String fCommentId, int pageSize, int pageNow);

    String insertFComment(String content, String goodId, String email, int rate);

    String insertSComment(String content, String fCommentId, String email);

    boolean deleteFComment(String fCommentId, String email);

    boolean deleteSComment(String sCommentId, String email);
}
