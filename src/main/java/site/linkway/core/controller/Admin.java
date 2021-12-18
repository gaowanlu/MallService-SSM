package site.linkway.core.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.linkway.core.entity.bo.PostCommodityType;
import site.linkway.core.entity.vo.ResultMessage;
import site.linkway.core.service.CommodityTypeService;
import site.linkway.core.service.UserDataService;

@Controller
@RequestMapping(value = "/api/admin")
public class Admin {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    CommodityTypeService commodityTypeService;
    @Autowired
    UserDataService userDataService;


    /*更新或插入 商品类型选项 一旦插入 母前:只能修改、不能删除、如删除需要检验是否有商品使用此类型*/
    @PostMapping(value = "/commodity/type",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String insertOrUpdateType(@RequestBody PostCommodityType postCommodityType) throws JsonProcessingException {
        ResultMessage resultMessage=new ResultMessage();
        String result=commodityTypeService.operator(postCommodityType);
        if(result.equals("false")){
            resultMessage.setResult(false);
            resultMessage.setMessage("操作错误 请检查");
        }else{
            resultMessage.setResult(true);
            resultMessage.setMessage(result);
        }
        return mapper.writeValueAsString(resultMessage);
    }



    /*为用户充值 : 为了模拟暂时只实现充值 但可以用负值 做到减余额的效果*/
    @PostMapping(value = "/recharge",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String recharge(@RequestParam("amount") double amount,@RequestParam("email") String email) throws JsonProcessingException {
        ResultMessage resultMessage=new ResultMessage();
        boolean result=userDataService.recharge(email,amount);
        resultMessage.setResult(result);
        resultMessage.setMessage(result?"充值成功":"充值失败");
        return  mapper.writeValueAsString(resultMessage);
    }

}

