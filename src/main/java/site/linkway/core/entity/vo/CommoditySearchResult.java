package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.linkway.core.entity.po.Commodity;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommoditySearchResult {
    List<Commodity> commodities;//商品列表
    int pageCount; //总页数
    int pageNow;//现在所在页号
    int pageSize;//每页得的大小
}
