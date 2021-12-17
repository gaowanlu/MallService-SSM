package site.linkway.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jodd.madvoc.meta.method.POST;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.linkway.core.entity.vo.CommentList;
import site.linkway.core.entity.vo.ResultMessage;
import site.linkway.core.service.CommentService;

/*评论系统*/
@Controller
@RequestMapping("/api/comment")
public class CommentSystem {
    static Logger logger= Logger.getLogger(CommentSystem.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    CommentService commentService;


    /*根据商品id获得评论列表*/
    @PostMapping(value="/fList", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String fList(@NonNull String goodId,
                        @NonNull int pageSize,
                        @NonNull int pageNow) throws JsonProcessingException {

        pageSize=limitSplitPageSize(pageSize);//限制分页大小
        CommentList commentList=commentService.fCommentListByGoodId(goodId,pageSize,pageNow);
        return mapper.writeValueAsString(commentList);
    }


    /*根据父评论id请求子评论列表*/
    @PostMapping(value="/sList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sList(@NonNull String fCommentId,
                        @NonNull int pageSize,
                        @NonNull int pageNow) throws JsonProcessingException {

        pageSize=limitSplitPageSize(pageSize);//限制分页大小
        CommentList commentList=commentService.sCommentServiceByFCommentId(fCommentId,pageSize,pageNow);
        return mapper.writeValueAsString(commentList);
    }


    /*根据商品id添加父评论*/
    @PostMapping(value="/add/f",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addFComment(@NonNull String content,
                              @NonNull @SessionAttribute("id") String email,
                              @NonNull String goodId,
                              @NonNull int rate) throws JsonProcessingException {

        ResultMessage resultMessage=new ResultMessage();
        String UUID=commentService.insertFComment(content,goodId,email,rate);
        resultMessage.setResult(null!=UUID);
        resultMessage.setMessage(UUID);
        return mapper.writeValueAsString(resultMessage);
    }


    /*根据父评论id增加子评论*/
    @PostMapping(value="/add/s",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addSComment(@NonNull String content,
                              @NonNull String fCommentId,
                              @NonNull @SessionAttribute("id") String email) throws JsonProcessingException {

        ResultMessage resultMessage=new ResultMessage();
        String UUID=commentService.insertSComment(content,fCommentId,email);
        resultMessage.setResult(null!=UUID);
        resultMessage.setMessage(UUID);
        return mapper.writeValueAsString(resultMessage);
    }


    /*删除父评论*/
    @DeleteMapping(value="/delete/f",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteFComment(@NonNull String fCommentId,
                                 @NonNull @SessionAttribute("id") String email) throws JsonProcessingException {

        ResultMessage resultMessage=new ResultMessage();
        resultMessage.setResult(commentService.deleteFComment(fCommentId,email));
        return mapper.writeValueAsString(resultMessage);
    }


    /*删除子评论*/
    @DeleteMapping(value="/delete/s",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteSComment(@NonNull String sCommentId,
                                 @NonNull @SessionAttribute("id") String email) throws JsonProcessingException {

        ResultMessage resultMessage=new ResultMessage();
        resultMessage.setResult(commentService.deleteSComment(sCommentId,email));
        return mapper.writeValueAsString(resultMessage);
    }


    /*限制分页大小*/
    private static int limitSplitPageSize(int size){
        return size>20?20:size;
    }
}
