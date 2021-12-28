package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.linkway.core.entity.po.Comment;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentList {
    boolean result=true;//操作结果
    List<Comment> comments=new ArrayList<>();//评论列表
    int pageNow;//现在所在页号
    int pageSize;//每页得的大小
    long pageCount;//总共页数
}
