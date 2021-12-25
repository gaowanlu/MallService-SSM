package site.linkway.core.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import site.linkway.core.entity.bo.PostCommodity;
import site.linkway.core.entity.bo.PostCommodityType;
import site.linkway.core.entity.bo.PostOrderSearch;
import site.linkway.core.entity.po.Good;
import site.linkway.core.entity.po.Img;
import site.linkway.core.entity.vo.ImgUploadResult;
import site.linkway.core.entity.vo.OrderList;
import site.linkway.core.entity.vo.ResultMessage;
import site.linkway.core.entity.vo.StatusResult;
import site.linkway.core.service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*管理端*/
@Controller
@RequestMapping(value = "/api/admin")
public class Admin {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    CommodityTypeService commodityTypeService;
    @Autowired
    UserDataService userDataService;
    @Autowired
    OrderService orderService;
    @Autowired
    CommodityService commodityService;
    @Autowired
    ImageService imageService;

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

    /*订单查询*/
    @PostMapping(value="/order/search",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String orderSearch(@RequestBody PostOrderSearch postOrderSearch) throws JsonProcessingException {
        OrderList orderList=orderService.orderSearch(postOrderSearch);
        return  mapper.writeValueAsString(orderList);
    }

    /*新商品发布*/
    @PostMapping (value = "/commodity/add",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addNewCommodity(@RequestParam("price") double price,
                                @RequestParam("name") String name,
                                @RequestParam("profile") String profile,
                                @RequestParam("stock") int stock,
                                @RequestParam("goodTypeId") int goodTypeId,
                                  @RequestParam("onSale") int onSale,
                                  @RequestParam("detail") String detail,
                                @RequestParam(name = "file",required = false) String file[],
                                  @RequestParam(name="detailsImg",required = false) CommonsMultipartFile detailImg//商品详情图片
    ) throws IOException {
        PostCommodity postCommodity=new PostCommodity(price,name,profile,stock,goodTypeId,onSale,detail,file,detailImg);
        StatusResult statusResult=new StatusResult();
        statusResult.setResult(!commodityService.addNewCommodity(postCommodity).equals("false"));
        return mapper.writeValueAsString(statusResult);
    }

    /*商品信息更新*/
    @PostMapping (value = "/commodity/update",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String commodityTextUpdate(@RequestParam("price") double price,
                                      @RequestParam("name") String name,
                                      @RequestParam("profile") String profile,
                                      @RequestParam("stock") int stock,
                                      @RequestParam("goodTypeId") int goodTypeId,
                                      @RequestParam("onSale") int onSale,
                                      @RequestParam("detail") String detail,
                                      @RequestParam("goodId") String goodId) throws JsonProcessingException {
        Good good=new Good(goodId,price,name,profile,stock,0,goodTypeId,onSale, detail,"");
        //0为soldSum 不会更新soldSum的
        //deailImgId "" 不更新
        StatusResult statusResult=new StatusResult();
        statusResult.setResult(commodityService.updateCommodityText(good));
        return mapper.writeValueAsString(statusResult);
    }


    /*商品详细图片更新*/
    @PostMapping(value="/commodity/detailsImg",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String commodityDetailsUpdate(@RequestParam("detailsImg") CommonsMultipartFile file,
                                         @RequestParam("goodId") String goodId) throws IOException {
        StatusResult statusResult=new StatusResult();
        statusResult.setResult(commodityService.updateDetailsImg(goodId,file));
        return mapper.writeValueAsString(statusResult);
    }



    /**
     * 删除商品
      * @param goodId 商品 id
     * @return 操作结果
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/commodity/delete",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteCommodity(@RequestParam("goodId") String goodId) throws JsonProcessingException {
        StatusResult statusResult=new StatusResult();
        statusResult.setResult(commodityService.deleteCommodity(goodId));
        return mapper.writeValueAsString(statusResult);
    }

    /*商品图片增加*/
    @PostMapping(value = "/commodity/img/add",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String commodityImgPush(@RequestParam("goodId") String goodId,
                                     @RequestParam("file") CommonsMultipartFile file[]) throws IOException {
        StatusResult statusResult=new StatusResult();
        statusResult.setResult(commodityService.addCommodityImg(goodId,file));
        return mapper.writeValueAsString(statusResult);
    }

    /*商品图片删除*/
    @PostMapping(value = "/commodity/img/delete",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String commodityImgPush(@RequestParam("imgId") String imgId) throws JsonProcessingException {
        StatusResult statusResult=new StatusResult();
        statusResult.setResult(commodityService.deleteCommodityImg(imgId));
        return mapper.writeValueAsString(statusResult);
    }

    /*订单发货处理*/
    @PostMapping(value="/order/ship",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String orderShip(@RequestParam("orderId") String orderId,
                            @RequestParam("logisticsNumber") String logisticsNumber,
                            @RequestParam("logisticsName") String logisticsName) throws JsonProcessingException {
        ResultMessage resultMessage=new ResultMessage();
        String result=orderService.orderShip(orderId,logisticsNumber,logisticsName);
        resultMessage.setResult(result.equals("true"));
        resultMessage.setMessage(result);
        return mapper.writeValueAsString(resultMessage);
    }

    /*订单申请退款处理*/
    @PostMapping(value="/order/refund",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String orderRefund(@RequestParam("orderId") String orderId) throws JsonProcessingException {
        ResultMessage resultMessage=new ResultMessage();
        String result=orderService.orderRefund(orderId);
        resultMessage.setResult(result.equals("true"));
        resultMessage.setMessage(result);
        return mapper.writeValueAsString(resultMessage);
    }

    /*管理员 图片上传、返回imgId*/
    @PostMapping(value="/img/upload",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String imgUpload(@RequestParam(name="file",required = false) CommonsMultipartFile files[]) throws IOException {
        if(null==files){
            files=new CommonsMultipartFile[0];
        }
        ImgUploadResult imgUploadResult=new ImgUploadResult();
        imgUploadResult.setImgId(imageService.storeImg(files));
        List<String> imgURL=new ArrayList<>();
        List<String> imgId=imgUploadResult.getImgId();
        for(int i=0;i<imgId.size();i++){
            imgURL.add(ImageDistribution.formatURLFromImgId(imgId.get(i)));
        }
        imgUploadResult.setImgURL(imgURL);
        return mapper.writeValueAsString(imgUploadResult);
    }
}

