package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//商品父评论条项
public class FComment {
    public String fCommentId;//父评论id
    public String goodId;//物品id
    public String Content;//评论内容
    public Date time;//评论时间
    public String userId;//用户账号id
}
