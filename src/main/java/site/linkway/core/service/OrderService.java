package site.linkway.core.service;

import site.linkway.core.controller.Order;
import site.linkway.core.entity.bo.PostOrder;
import site.linkway.core.entity.vo.OrderItem;

import java.util.List;

public interface OrderService {
    /*添加新订单*/
    public String insert(String email,PostOrder postOrder);
    /*更新订单状态*/
    public String cancel(String email,String OrderId);
    /*得到指定用户的所有订单*/
    public List<OrderItem> selectByEmail(String email);
    /*获得订单详情*/
    public OrderItem selectByOrderId(String email,String OrderId);
}
