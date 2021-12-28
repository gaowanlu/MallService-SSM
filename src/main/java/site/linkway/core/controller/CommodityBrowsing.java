package site.linkway.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.linkway.core.entity.po.Commodity;
import site.linkway.core.entity.vo.CommodityTypeList;
import site.linkway.core.entity.vo.ResultMessage;
import site.linkway.core.service.CommodityService;
/**
 * 商品随机推荐
 * 商品相关信息模块
 * 据商品序列号或得商品详情
 */
@Controller
@RequestMapping("/api/commodity")
public class CommodityBrowsing {
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    CommodityService commodityService;

    /**
     * 随机推荐商品列表
     * @return CommodityTipList
     * @throws JsonProcessingException
     */
    @GetMapping(value="/recommendation",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String randomRecommendation() throws JsonProcessingException {
        return mapper.writeValueAsString(commodityService.randomSelectCommodity(20));//最多推荐20个
    }


    /**
     * 根据商品号获得商品详情
     * @param goodId 商品id
     * @return Commodity
     * @throws JsonProcessingException
     */
    @PostMapping(value="/detail",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String commodityDetail(@NonNull String goodId) throws JsonProcessingException {
        Commodity commodity= commodityService.selectCommodityByGoodId(goodId);
        if(commodity==null){
            ResultMessage resultMessage=new ResultMessage();
            resultMessage.setResult(false);
            resultMessage.setMessage("没有此商品");
            return mapper.writeValueAsString(resultMessage);
        }
        return mapper.writeValueAsString(commodity);
    }

    /**
     * 获取商品类型列表
     * @return CommodityTypeList
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/typeList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String commodityTypeList() throws JsonProcessingException {
        CommodityTypeList commodityTypeList=new CommodityTypeList();
        commodityTypeList.setTypes(commodityService.goodTypes());
        return mapper.writeValueAsString(commodityTypeList);
    }
}
