package site.linkway.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.linkway.core.controller.ImageDistribution;
import site.linkway.core.entity.po.Comment;
import site.linkway.core.entity.vo.CommentList;
import site.linkway.core.mapper.FCommentMapper;
import site.linkway.core.mapper.SCommentMapper;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    FCommentMapper fCommentMapper;
    @Autowired
    SCommentMapper sCommentMapper;

    /*获得父评论列表*/
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

    /*获得子评论列表*/
    @Override
    public CommentList sCommentServiceByFCommentId(String fCommentId,int pageSize,int pageNow){
        List<Comment> commentList=sCommentMapper.commentListByFCommentId(fCommentId,((pageNow-1)*pageSize),pageSize);
        CommentList result=new CommentList();
        result.setComments(commentList);
        result.setPageNow(pageNow);
        result.setPageSize(pageSize);
        result.setPageCount(sCommentMapper.countByfCommentId(fCommentId)/pageSize);
        return result;
    }

    /*添加新的父评论*/
    @Override
    public boolean insertFComment(String content, String goodId, String email) {
        return false;
    }

    /*添加新的子评论*/
    @Override
    public boolean insertSComment(String content, String fCommentId, String email) {
        return false;
    }

    /*删除父评论*/
    @Override
    public boolean deleteFComment(String fCommentId, String email) {
        return false;
    }

    /*删除子评论*/
    @Override
    public boolean deleteSComment(String sCommentId, String email) {
        return false;
    }
}
