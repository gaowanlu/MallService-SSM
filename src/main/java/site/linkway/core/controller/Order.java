package site.linkway.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.linkway.core.entity.bo.PostOrder;
import site.linkway.core.entity.vo.ResultMessage;
import site.linkway.core.entity.vo.OrderItem;
import site.linkway.core.entity.vo.OrderList;
import site.linkway.core.service.OrderService;
import java.util.List;

/*用户订单模块*/
@Controller
@RequestMapping("/api/user")
public class Order {
    static Logger logger= Logger.getLogger(Comment.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    OrderService orderService;

    /**
     * 用户提交订单
     * @param email 用户邮箱
     * @param postOrder 请求体 json
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping(value="/order",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addOrder(@SessionAttribute("id") String email,
                           @RequestBody @NonNull PostOrder postOrder) throws JsonProcessingException {
        logger.info(postOrder);
        ResultMessage resultMessage =new ResultMessage();
        String result=orderService.insert(email,postOrder);
        if (result != null) {
            resultMessage.setResult(true);
            resultMessage.setMessage(result);
        }
        return mapper.writeValueAsString(resultMessage);
    }

    /**
     * 用户获得自己的所有订单包括详情
     * @param email 用户邮箱
     * @return  OrderList
     * @throws JsonProcessingException
     */
    @GetMapping(value="/order", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String orderList(@SessionAttribute("id") String email) throws JsonProcessingException {
        List<OrderItem> orderItemList=orderService.selectByEmail(email);
        OrderList orderList=new OrderList();
        orderList.setOrderItems(orderItemList);
        return mapper.writeValueAsString(orderList);
    }

    /**
     * 根据订单id返回订单详情
     * @param email 用户邮箱
     * @param orderId 订单id
     * @return OrderItem
     * @throws JsonProcessingException
     */
    @PostMapping(value="/order/detail", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String orderDetail(@SessionAttribute("id") String email,
                              @NonNull String orderId
                              ) throws JsonProcessingException {
        OrderItem orderItem=orderService.selectByOrderId(email,orderId);
        return mapper.writeValueAsString(orderItem);
    }

    /**
     *
     * @param email 用户邮箱
     * @param orderId 订单id
     * @param status 订单状态
     * @return ResultMessage
     * @throws JsonProcessingException
     */
    @PutMapping(value="/order/update",produces = "application/json;charset=utf-8")
    @ResponseBody
    /*更新订单状态*/
    public String orderStatus(@SessionAttribute("id") String email,
                              @NonNull String orderId,
                              @RequestParam("status") @NonNull String status
                              ) throws JsonProcessingException {
        ResultMessage resultMessage=new ResultMessage();
        String result=orderService.updateStatus(email,orderId,status,false);//false非管理员操作
        resultMessage.setResult(result.equals("true"));
        resultMessage.setMessage(result);
        return mapper.writeValueAsString(resultMessage);
    }

    /**
     * 删除已签收的订单
     * @param email 用户邮箱
     * @param orderId 订单id
     * @return ResultMessage
     * @throws JsonProcessingException
     */
    @DeleteMapping(value="/order",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String orderDelete(@SessionAttribute("id") String email,
            @RequestParam("orderId") @NonNull String orderId
    ) throws JsonProcessingException {
        ResultMessage resultMessage=new ResultMessage();
        String result=orderService.delete(orderId,email);
        resultMessage.setResult(result.equals("true"));
        resultMessage.setMessage(result);
        return mapper.writeValueAsString(resultMessage);
    }

}
