package site.linkway.core.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSearch {
    String keyword;//名称关键此
    String searchType;//搜索物品类别
    String goodId;//物品id
    MinMax price;//价格区间
    int pageNow;//此时页码
    int pageSize;//分页大小
}
