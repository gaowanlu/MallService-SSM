package site.linkway.core.service;

import site.linkway.core.entity.bo.PostOrder;
import site.linkway.core.entity.bo.PostOrderSearch;
import site.linkway.core.entity.vo.OrderItem;
import site.linkway.core.entity.vo.OrderList;

import java.util.List;

public interface OrderService {
    /**
     * 添加新订单
     * @param email 用户邮箱
     * @param postOrder 提交订单信息
     * @return orderId or null
     */
    String insert(String email,PostOrder postOrder);

    /**
     * 更新订单状态
     * @param email 用户邮箱
     * @param orderId 订单id
     * @param status 订单状态
     * @param admin 是否为管理员操作
     * @return “true” or 原因
     */
    String updateStatus(String email,String orderId,String status,boolean admin);

    /**
     * 得到指定用户的所有订单
     * @param email 用户邮箱
     * @return 订单项列表
     */
    List<OrderItem> selectByEmail(String email);

    /**
     * 获得订单详情
     * @param email 邮箱
     * @param orderId 订单id
     * @return 订单项
     */
    OrderItem selectByOrderId(String email,String orderId);

    /**
     * 删除已签收的订单
     * @param orderId 订单id
     * @param email 邮箱
     * @return “true” or 原因
     */
    String delete(String orderId,String email);

    /**
     * 根据订单相关属性、查询订单列表
     * @param postOrderSearch
     * @return 订单列表
     */
    OrderList orderSearch(PostOrderSearch postOrderSearch);

    /**
     * 发货处理
     * @param orderId
     * @param logisticsNumber
     * @param logisticsName
     * @return “true” or 原因
     */
    String orderShip(String orderId,String logisticsNumber,String logisticsName);

    /**
     * 退款处理
     * @param orderId 订单号
     * @return “true” or 原因
     */
    String orderRefund(String orderId);
}
