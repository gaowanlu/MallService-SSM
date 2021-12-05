package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.po.Comment;
import site.linkway.core.entity.po.SComment;

import java.util.List;

//table name:sComment  entity:po.SComment
@Mapper
public interface SCommentMapper {
    int insert(SComment sComment);
    int update(SComment sComment);
    int delete(SComment sComment);
    SComment select(SComment sComment);
    List<Comment> commentListByFCommentId(@Param("fCommentId") String fCommentId, @Param("num") int num, @Param("size") int size);
    long countByfCommentId(String fCommentId);
}
