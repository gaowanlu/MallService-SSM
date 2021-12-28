package site.linkway.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.linkway.core.entity.bo.PostCommodityType;
import site.linkway.core.entity.po.GoodType;
import site.linkway.core.mapper.GoodTypeMapper;

/*商品类型*/
@Service
public class CommodityTypeServiceImpl implements CommodityTypeService{
    @Autowired
    GoodTypeMapper goodTypeMapper;

    /**
     * 商品类型操作
     * @param postCommodityType 类型信息以及操作类型
     * @return 操作结果
     *      1 插入 成功返回 goodTypeId
     *      0 更新 成功返回 "true"
     *      失败返回 "false"
     */
    @Override
    public String operator(PostCommodityType postCommodityType) {
        String result = null;
        //插入
        if (1 == postCommodityType.getOperator()) {
            int line = goodTypeMapper.insert(postCommodityType.getName());
            if (1 == line) {
                GoodType goodType = goodTypeMapper.selectByName(postCommodityType.getName());
                result = Integer.toString(goodType.getGoodTypeId());
            }
            //更新
        }else if (0 == postCommodityType.getOperator()) {
            int line = goodTypeMapper.update(postCommodityType.getGoodTypeId(), postCommodityType.getName());
            if (1 == line) {
                result = "true";
            }
        }
        if (result == null) {
            result = "false";
        }
        return result;
    }

}
