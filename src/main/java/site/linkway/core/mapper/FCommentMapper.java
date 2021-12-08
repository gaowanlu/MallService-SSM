package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.po.Comment;
import site.linkway.core.entity.po.FComment;
import java.util.List;


@Mapper
public interface FCommentMapper {
    /*插入新父评论*/
    int insert(@Param("fCommentId") String fCommentId,
               @Param("content") String content,
               @Param("goodId") String goodId,
               @Param("email") String email,
               @Param("rate") int rate);

    /*删除父评论*/
    int delete(@Param("fCommentId") String fCommentId,
               @Param("email") String email);

    /*获得物品的评论列表*/
    List<Comment> commentListByGoodId(@Param("goodId") String goodId,
                                      @Param("num") int num,
                                      @Param("size") int size);
    /*统计物品父评论个数*/
    long countByGoodId(String goodId);
}
