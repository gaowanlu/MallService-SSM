package site.linkway.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.linkway.core.entity.vo.CommentList;
import site.linkway.core.entity.vo.ResultMessage;
import site.linkway.core.service.CommentService;

/**
 * 评论模块 Controller
 */
@Controller
@RequestMapping("/api/comment")
public class Comment {

    private static int MAX_PAGE_SIZE = 20;

    static Logger logger = Logger.getLogger(Comment.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    CommentService commentService;

    /**
     * 获取评论
     *
     * @param goodId   商品 id
     * @param pageSize 分页大小
     * @param pageNow  当前页
     * @return 评论列表
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/fList", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String fList(@NonNull String goodId,
                        @NonNull int pageSize,
                        @NonNull int pageNow) throws JsonProcessingException {

        pageSize = getLimitSplitPageSize(pageSize);
        CommentList commentList = commentService.fCommentListByGoodId(goodId, pageSize, pageNow);
        return mapper.writeValueAsString(commentList);
    }


    /**
     * 获取子评论列表
     *
     * @param fCommentId 父评论 id
     * @param pageSize   分页大小
     * @param pageNow    当前页
     * @return 子评论列表
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/sList", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sList(@NonNull String fCommentId,
                        @NonNull int pageSize,
                        @NonNull int pageNow) throws JsonProcessingException {

        pageSize = getLimitSplitPageSize(pageSize);
        CommentList commentList = commentService.sCommentServiceByFCommentId(fCommentId, pageSize, pageNow);
        return mapper.writeValueAsString(commentList);
    }


    /**
     * 添加评价
     * @param content 内容
     * @param email 用户邮箱
     * @param goodId 商品 id
     * @param rate 评分
     * @return 操作结果
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/add/f", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addFComment(@NonNull String content,
                              @NonNull @SessionAttribute("id") String email,
                              @NonNull String goodId,
                              @NonNull int rate) throws JsonProcessingException {

        ResultMessage resultMessage = new ResultMessage();
        String UUID = commentService.insertFComment(content, goodId, email, rate);
        resultMessage.setResult(null != UUID);
        resultMessage.setMessage(UUID);
        return mapper.writeValueAsString(resultMessage);
    }


    /**
     * 添加子评论
     * @param content 内容
     * @param fCommentId 父评论 id
     * @param email 用户邮箱
     * @return 操作结果
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/add/s", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addSComment(@NonNull String content,
                              @NonNull String fCommentId,
                              @NonNull @SessionAttribute("id") String email) throws JsonProcessingException {

        ResultMessage resultMessage = new ResultMessage();
        String UUID = commentService.insertSComment(content, fCommentId, email);
        resultMessage.setResult(null != UUID);
        resultMessage.setMessage(UUID);
        return mapper.writeValueAsString(resultMessage);
    }


    /**
     * 删除评价
     * @param fCommentId 父评论 id
     * @param email 用户邮箱
     * @return 操作结果
     * @throws JsonProcessingException
     */
    @DeleteMapping(value = "/delete/f", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteFComment(@NonNull String fCommentId,
                                 @NonNull @SessionAttribute("id") String email) throws JsonProcessingException {

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setResult(commentService.deleteFComment(fCommentId, email));
        return mapper.writeValueAsString(resultMessage);
    }


    /**
     * 删除子评论
     * @param sCommentId 子评论 id
     * @param email 用户邮箱
     * @return 操作结果
     * @throws JsonProcessingException
     */
    @DeleteMapping(value = "/delete/s", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteSComment(@NonNull String sCommentId,
                                 @NonNull @SessionAttribute("id") String email) throws JsonProcessingException {

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setResult(commentService.deleteSComment(sCommentId, email));
        return mapper.writeValueAsString(resultMessage);
    }

    /**
     * 获取分页大小，不能超过 MAX_PAGE_SIZE
     *
     * @param size 分页大小
     * @return 分页大小
     */
    private static int getLimitSplitPageSize(int size) {
        return size > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : size;
    }
}
