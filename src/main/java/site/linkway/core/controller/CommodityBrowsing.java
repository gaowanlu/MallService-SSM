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
import site.linkway.core.entity.vo.ResultMessage;
import site.linkway.core.service.CommodityBrowsingService;

/*商品相关信息模块
## `商品详情浏览`
#### 商品推荐列表(暂为随机检索出20个)

`GET /commodity/recommendation`

* 返回格式  `CommodityTipList` ([数据类型](#数据类型))

#### 浏览商品详情
`POST /commodity/detail`

* 参数`{goodId:string}`
* 返回格式 `Commodity` ([数据类型](#数据类型))

---
* */
@Controller
@RequestMapping("/commodity")
public class CommodityBrowsing {
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    CommodityBrowsingService commodityBrowsingService;

    /*随机推荐商品*/
    @GetMapping(value="/recommendation",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String randomRecommendation() throws JsonProcessingException {
        return mapper.writeValueAsString(commodityBrowsingService.randomSelectCommodity(20));//最多推荐20个
    }

    /*根据商品号获得商品详情*/
    @PostMapping(value="/detail",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String commodityDetail(@NonNull String goodId) throws JsonProcessingException {
        Commodity commodity=commodityBrowsingService.selectCommodityByGoodId(goodId);
        if(commodity==null){
            ResultMessage resultMessage=new ResultMessage();
            resultMessage.setResult(false);
            resultMessage.setMessage("没有此商品");
            return mapper.writeValueAsString(resultMessage);
        }
        return mapper.writeValueAsString(commodity);
    }
}
