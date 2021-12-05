package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.po.Comment;
import site.linkway.core.entity.po.FComment;
import java.util.List;


@Mapper
public interface FCommentMapper {
    int insert(FComment fComment);
    int update(FComment fComment);
    int delete(FComment fComment);
    FComment select(FComment fComment);
    List<Comment> commentListByGoodId(@Param("goodId") String goodId,@Param("num") int num, @Param("size") int size);
    long countByGoodId(String goodId);
}
