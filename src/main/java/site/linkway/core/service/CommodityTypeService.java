package site.linkway.core.service;

import site.linkway.core.entity.bo.PostCommodityType;
/*商品类型*/
public interface CommodityTypeService {
    /**
     * 商品类型操作
     * @param postCommodityType 类型信息以及操作类型
     * @return 操作结果
     *      1 插入 成功返回 goodTypeId
     *      0 更新 成功返回 "true"
     *      失败返回 "false"
     */
    String operator(PostCommodityType postCommodityType);
}
