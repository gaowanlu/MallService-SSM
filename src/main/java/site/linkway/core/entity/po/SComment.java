package site.linkway.core.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SComment {
    public String sCommentId;// varchar(32) PK
    public String fCommentId;// varchar(32)
    public String content;// varchar(20)
    public Date time;// datetime
    public String userId;// varchar(32)
}
