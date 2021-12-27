package site.linkway.core.service;

import site.linkway.core.entity.bo.PostOrder;
import site.linkway.core.entity.bo.PostOrderSearch;
import site.linkway.core.entity.vo.OrderItem;
import site.linkway.core.entity.vo.OrderList;

import java.util.List;

public interface OrderService {
    /*添加新订单*/
    String insert(String email,PostOrder postOrder);
    /*更新订单状态*/
    String updateStatus(String email,String OrderId,String status,boolean admin);
    /*得到指定用户的所有订单*/
    List<OrderItem> selectByEmail(String email);
    /*获得订单详情*/
    OrderItem selectByOrderId(String email,String OrderId);
    /*删除已签收的订单*/
    String delete(String orderId,String email);
    /*根据订单相关属性、查询订单列表*/
    OrderList orderSearch(PostOrderSearch postOrderSearch);
    /*发货处理*/
    String orderShip(String orderId,String logisticsNumber,String logisticsName);
    /*退款处理*/
    String orderRefund(String orderId);
}
