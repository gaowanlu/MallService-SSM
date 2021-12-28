package site.linkway.core.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SComment {
    public String sCommentId;// 子评论id
    public String fCommentId;// 父评论id
    public String content;// 评论内容
    public Date time;// 评论时间
    public String userId;// 用户id
}
