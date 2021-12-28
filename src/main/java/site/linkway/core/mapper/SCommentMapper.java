package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.po.Comment;
import site.linkway.core.entity.po.SComment;

import java.util.List;

//table name:sComment  entity:po.SComment
@Mapper
public interface SCommentMapper {
    /**
     * 插入子评论
     * @param sCommentId 子评论id
     * @param content 评论内容
     * @param fCommentId 父评论id
     * @param email 用户邮箱
     * @return
     */
    int insert(@Param("sCommentId") String sCommentId,
               @Param("content") String content,
               @Param("fCommentId") String fCommentId,
               @Param("email") String email);
    /**
     * 删除子评论
     * @param sCommentId 子评论id
     * @param email 邮箱
     * @return
     */
    int delete(@Param("sCommentId") String sCommentId,
               @Param("email") String email);

    /**
     * 获取某父评论的子评论列表
     * @param fCommentId 父评论id
     * @param num 起点
     * @param size 大小
     * @return 子评论列表
     */
    List<Comment> commentListByFCommentId(@Param("fCommentId") String fCommentId,
                                          @Param("num") int num,
                                          @Param("size") int size);

    /**
     * 统计某条父评论的子评论个数
     * @param fCommentId 父评论id
     * @return
     */
    long countByfCommentId(String fCommentId);
}
