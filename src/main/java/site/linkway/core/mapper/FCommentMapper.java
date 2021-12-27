package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.po.Comment;
import site.linkway.core.entity.po.FComment;
import java.util.List;


@Mapper
public interface FCommentMapper {
    /**
     * 插入父评论
     * @param fCommentId 父评论id
     * @param content 评论内容
     * @param goodId 物品id
     * @param email 邮箱
     * @param rate 评星等级
     * @return
     */
    int insert(@Param("fCommentId") String fCommentId,
               @Param("content") String content,
               @Param("goodId") String goodId,
               @Param("email") String email,
               @Param("rate") int rate);


    /**
     * 删除父评论
     * @param fCommentId 父评论id
     * @param email 邮箱
     * @return
     */
    int delete(@Param("fCommentId") String fCommentId,
               @Param("email") String email);

    /**
     * 获得物品评论列表
     * @param goodId 物品id
     * @param num 分页起点
     * @param size 分页大小
     * @return
     */
    List<Comment> commentListByGoodId(@Param("goodId") String goodId,
                                      @Param("num") int num,
                                      @Param("size") int size);

    /**
     * 统计物品评论总数量
     * @param goodId 物品id
     * @return 评论总数
     */
    long countByGoodId(String goodId);
}
