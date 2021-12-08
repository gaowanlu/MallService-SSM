package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.po.Comment;
import site.linkway.core.entity.po.SComment;

import java.util.List;

//table name:sComment  entity:po.SComment
@Mapper
public interface SCommentMapper {
    /*插入新子评论*/
    int insert(@Param("sCommentId") String sCommentId,
               @Param("content") String content,
               @Param("fCommentId") String fCommentId,
               @Param("email") String email);
    /*删除子评论*/
    int delete(@Param("sCommentId") String sCommentId,
               @Param("email") String email);
    /*获得子评论列表*/
    List<Comment> commentListByFCommentId(@Param("fCommentId") String fCommentId,
                                          @Param("num") int num,
                                          @Param("size") int size);
    /*统计某条父评论的子评论个数*/
    long countByfCommentId(String fCommentId);
}
