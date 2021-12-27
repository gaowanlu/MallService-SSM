package site.linkway.core.service;

import site.linkway.core.controller.ImageDistribution;
import site.linkway.core.entity.po.Comment;
import site.linkway.core.entity.vo.CommentList;
import site.linkway.utils.UUIDUtils;

import java.util.List;

public interface CommentService {
    /**
     * 检索父评论列表
     * @param goodId 物品id
     * @param pageSize 分页大小
     * @param pageNow 此时页码
     * @return 评论列表
     */
    CommentList fCommentListByGoodId(String goodId,int pageSize,int pageNow);

    /**
     * 检索子评论列表
     * @param fCommentId 父评论id
     * @param pageSize 页大小
     * @param pageNow 此时页码
     * @return 评论列表
     */
    CommentList sCommentServiceByFCommentId(String fCommentId,int pageSize,int pageNow);

    /**
     * 添加父评论
     * @param content 评论内容
     * @param goodId 物品id
     * @param email 邮箱
     * @param rate 评星
     * @return UUID or null
     */
    String insertFComment(String content,String goodId,String email,int rate);

    /**
     * 添加子评论
     * @param content 评论内容
     * @param fCommentId 父评论id
     * @param email 邮箱
     * @return UUID or null
     */
    String insertSComment(String content,String fCommentId,String email);

    /**
     * 删除父评论
     * @param fCommentId 父评论id
     * @param email 邮箱
     * @return true or false
     */
    boolean deleteFComment(String fCommentId,String email);

    /**
     * 删除子评论
     * @param sCommentId 子评论id
     * @param email 邮箱
     * @return true or false
     */
    boolean deleteSComment(String sCommentId,String email);
}
