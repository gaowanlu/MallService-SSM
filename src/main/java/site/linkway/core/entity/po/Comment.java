package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    String commentId;//评论id
    String content;//评论文字内容
    String userId;//用户id
    String userName;//用户昵称
    String avatarURL;//用户头像URL
    String time;//评论发布时间
    int rate;//(注：子评论没有rate，但此字段返回值前端)
    long childCount;//此条评论子评论数量
}
