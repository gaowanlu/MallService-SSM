package site.linkway.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

/*
评论系统
1、读取商品的父评论
2、请求子评论
3、删除父评论
4、增加父评论
5、增加子评论
6、删除父评论
*/

public class CommentSystem {
    static Logger logger= Logger.getLogger(CommentSystem.class);
    private ObjectMapper mapper = new ObjectMapper();

    /*
fCommentId varchar(32) PK
goodId varchar(32)
content varchar(20)
time datetime
userId varchar(32)

sCommentId varchar(32) PK
fCommentId varchar(32)
content varchar(20)
time datetime
userId varchar(32)
    * */

    /*根据商品id获得评论列表*/

    /*根据父评论id请求子评论列表*/

    /*根据商品id添加父评论*/

    /*根据父评论id增加子评论*/

    /*删除父评论*/

    /*删除子评论*/

}
