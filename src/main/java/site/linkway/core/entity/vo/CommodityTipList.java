package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.linkway.core.entity.po.Commodity;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityTipList {
    boolean result=true;//操作结果
    List<Commodity> commodities=new ArrayList<>();//商品列表
}
