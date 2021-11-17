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
    public String fCommentId;
    public String goodId;
    public String Content;
    public Date time;
    public String userId;
}
