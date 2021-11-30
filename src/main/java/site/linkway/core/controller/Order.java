package site.linkway.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
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
@RequestMapping("/api")
public class Order {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    OrderService orderService;
    @PostMapping("/order")
    @ResponseBody
    /*用户提交订单*/
    public String addOrder(@SessionAttribute("id") String email,
                           @RequestBody @NonNull PostOrder postOrder) throws JsonProcessingException {
        ResultMessage resultMessage =new ResultMessage();
        String result=orderService.insert(email,postOrder);
        resultMessage.setResult(result.equals("true"));
        resultMessage.setMessage(result);
        return mapper.writeValueAsString(resultMessage);
    }

    @GetMapping("/order")
    @ResponseBody
    /*用户获得自己的所有订单包括详情*/
    public String orderList(@SessionAttribute("id") String email) throws JsonProcessingException {
        List<OrderItem> orderItemList=orderService.selectByEmail(email);
        OrderList orderList=new OrderList();
        orderList.setOrders(orderItemList);
        return mapper.writeValueAsString(orderList);
    }

    @GetMapping("/order/detail")
    @ResponseBody
    /*根据订单id返回详情*/
    public String orderDetail(@SessionAttribute("id") String email,
                              @NonNull String orderId
                              ) throws JsonProcessingException {
        OrderItem orderItem=orderService.selectByOrderId(email,orderId);
        return mapper.writeValueAsString(orderItem);
    }

    @PostMapping("/order/cancel")
    @ResponseBody
    /*申请取消订单*/
    public String orderCancel(@SessionAttribute("id") String email,
                              @NonNull String orderId
                              ) throws JsonProcessingException {
        ResultMessage cancelResult=new ResultMessage();
        String result=orderService.cancel(email,orderId);
        cancelResult.setResult(result.equals("true"));
        cancelResult.setMessage(result);
        return mapper.writeValueAsString(cancelResult);
    }

}
