package site.linkway.core.service;

import site.linkway.core.entity.po.Commodity;
import site.linkway.core.entity.vo.CommodityTipList;

public interface CommodityBrowsingService {
    CommodityTipList randomSelectCommodity(int maxSize);
    Commodity selectCommodityByGoodId(String goodId);
}
