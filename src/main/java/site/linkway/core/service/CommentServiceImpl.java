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
public class CommentServiceImpl implements CommentService{
    @Autowired
    FCommentMapper fCommentMapper;
    @Autowired
    SCommentMapper sCommentMapper;


    /**
     * 检索父评论列表
     *
     * @param goodId   物品id
     * @param pageSize 分页大小
     * @param pageNow  此时页码
     * @return 评论列表
     */
    @Override
    public CommentList fCommentListByGoodId(String goodId, int pageSize, int pageNow) {
        /*根据商品号获得评论列表*/
        List<Comment> commentList=fCommentMapper.commentListByGoodId(goodId,((pageNow-1)*pageSize),pageSize);
        /*变换头像URL*/
        for(Comment comment:commentList){
            comment.setAvatarURL(ImageDistribution.formatURLFromImgId(comment.getAvatarURL()));
        }
        CommentList result=new CommentList();
        result.setComments(commentList);
        result.setPageNow(pageNow);
        result.setPageSize(pageSize);
        result.setPageCount(fCommentMapper.countByGoodId(goodId)/pageSize);
        return result;
    }

    /**
     * 检索子评论列表
     *
     * @param fCommentId 父评论id
     * @param pageSize   页大小
     * @param pageNow    此时页码
     * @return 评论列表
     */
    @Override
    public CommentList sCommentServiceByFCommentId(String fCommentId, int pageSize, int pageNow) {
        List<Comment> commentList=sCommentMapper.commentListByFCommentId(fCommentId,((pageNow-1)*pageSize),pageSize);
        CommentList result=new CommentList();
        result.setComments(commentList);
        result.setPageNow(pageNow);
        result.setPageSize(pageSize);
        result.setPageCount(sCommentMapper.countByfCommentId(fCommentId)/pageSize);
        return result;
    }

    /**
     * 添加父评论
     *
     * @param content 评论内容
     * @param goodId  物品id
     * @param email   邮箱
     * @param rate    评星
     * @return UUID or null
     */
    @Override
    public String insertFComment(String content, String goodId, String email, int rate) {
        String UUID= UUIDUtils.getUUID();
        if(1==fCommentMapper.insert(UUID,content,goodId,email,rate)){
            return UUID;
        }else{
            return null;
        }
    }

    /**
     * 添加子评论
     *
     * @param content    评论内容
     * @param fCommentId 父评论id
     * @param email      邮箱
     * @return UUID or null
     */
    @Override
    public String insertSComment(String content, String fCommentId, String email) {
        String UUID=UUIDUtils.getUUID();
        if(1==sCommentMapper.insert(UUID,content,fCommentId,email)){
            return UUID;
        }else{
            return null;
        }
    }

    /**
     * 删除父评论
     *
     * @param fCommentId 父评论id
     * @param email      邮箱
     * @return true or false
     */
    @Override
    public boolean deleteFComment(String fCommentId, String email) {
        return 1==fCommentMapper.delete(fCommentId,email);
    }

    /**
     * 删除子评论
     *
     * @param sCommentId 子评论id
     * @param email      邮箱
     * @return true or false
     */
    @Override
    public boolean deleteSComment(String sCommentId, String email) {
        return 1==sCommentMapper.delete(sCommentId,email);
    }

}
