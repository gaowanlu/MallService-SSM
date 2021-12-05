package site.linkway.core.service;

import site.linkway.core.entity.vo.CommentList;

public interface CommentService {
    /*检索父评论列表*/
    CommentList fCommentListByGoodId(String goodId,int pageSize,int pageNow);
    /*检索子评论列表*/
    CommentList sCommentServiceByFCommentId(String fCommentId,int pageSize,int pageNow);
    /*添加父评论*/
    boolean insertFComment(String content,String goodId,String email);
    /*添加子评论*/
    boolean insertSComment(String content,String fCommentId,String email);
    /*删除父评论*/
    boolean deleteFComment(String fCommentId,String email);
    /*删除子评论*/
    boolean deleteSComment(String sCommentId,String email);
}
