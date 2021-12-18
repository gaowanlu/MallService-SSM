package site.linkway.core.service;

import site.linkway.core.entity.po.Commodity;
import site.linkway.core.entity.po.GoodType;
import site.linkway.core.entity.vo.CommodityTipList;
import java.util.List;

public interface CommodityBrowsingService {
    CommodityTipList randomSelectCommodity(int maxSize);
    Commodity selectCommodityByGoodId(String goodId);
    List<GoodType> goodTypes();//获取商品列表
}
