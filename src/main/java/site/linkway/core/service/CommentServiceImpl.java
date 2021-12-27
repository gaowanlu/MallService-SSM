package site.linkway.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.linkway.core.controller.ImageDistribution;
import site.linkway.core.entity.po.Comment;
import site.linkway.core.entity.vo.CommentList;
import site.linkway.core.mapper.FCommentMapper;
import site.linkway.core.mapper.SCommentMapper;
import site.linkway.utils.UUIDUtils;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    FCommentMapper fCommentMapper;
    @Autowired
    SCommentMapper sCommentMapper;

    /**
     * 获取评论列表
     *
     * @param goodId   商品 id
     * @param pageSize 每页大小
     * @param pageNow  当前页
     * @return 评论列表
     */
    @Override
    public CommentList fCommentListByGoodId(String goodId, int pageSize, int pageNow) {
        // 获取评论列表
        List<Comment> commentList = fCommentMapper.commentListByGoodId(goodId, ((pageNow - 1) * pageSize), pageSize);
        // 设置头像
        for (Comment comment : commentList) {
            comment.setAvatarURL(ImageDistribution.formatURLFromImgId(comment.getAvatarURL()));
        }
        CommentList result = new CommentList();
        result.setComments(commentList);
        result.setPageNow(pageNow);
        result.setPageSize(pageSize);
        result.setPageCount(fCommentMapper.countByGoodId(goodId) / pageSize);
        return result;
    }

    /**
     * 根据父评论 id 获取子评论列表
     *
     * @param fCommentId 父评论 id
     * @param pageSize   每页大小
     * @param pageNow    当前页
     * @return 子评论列表
     */
    @Override
    public CommentList sCommentServiceByFCommentId(String fCommentId, int pageSize, int pageNow) {
        List<Comment> commentList = sCommentMapper.commentListByFCommentId(fCommentId, ((pageNow - 1) * pageSize), pageSize);
        CommentList result = new CommentList();
        result.setComments(commentList);
        result.setPageNow(pageNow);
        result.setPageSize(pageSize);
        result.setPageCount(sCommentMapper.countByfCommentId(fCommentId) / pageSize);
        return result;
    }

    /**
     * 添加新的父评论
     *
     * @param content 父评论内容
     * @param goodId  商品 id
     * @param email   用户 email
     * @param rate    商品评分
     * @return 是否添加成功
     */
    @Override
    public String insertFComment(String content, String goodId, String email, int rate) {
        String UUID = UUIDUtils.getUUID();
        if (1 == fCommentMapper.insert(UUID, content, goodId, email, rate)) {
            return UUID;
        } else {
            return null;
        }
    }

    /**
     * 添加新的子评论
     *
     * @param content    子评论内容
     * @param fCommentId 父评论 id
     * @param email      用户 email
     * @return 是否添加成功
     */
    @Override
    public String insertSComment(String content, String fCommentId, String email) {
        String UUID = UUIDUtils.getUUID();
        if (1 == sCommentMapper.insert(UUID, content, fCommentId, email)) {
            return UUID;
        } else {
            return null;
        }
    }

    /**
     * 删除父评论
     *
     * @param fCommentId 父评论 id
     * @param email      用户 email
     * @return 是否删除成功
     */
    @Override
    public boolean deleteFComment(String fCommentId, String email) {
        return 1 == fCommentMapper.delete(fCommentId, email);
    }

    /**
     * 删除子评论
     *
     * @param sCommentId 子评论 id
     * @param email      用户 email
     * @return 是否删除成功
     */
    @Override
    public boolean deleteSComment(String sCommentId, String email) {
        return 1 == sCommentMapper.delete(sCommentId, email);
    }
}
