package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.linkway.core.entity.po.Order;
import site.linkway.core.entity.po.OrderGood;
import site.linkway.core.entity.vo.OrderItemGood;
import java.util.List;

@Mapper
public interface OrderGoodMapper {
    /**
     * 插入订单物品
     * @param orderGood 订单物品
     * @return 插入行
     */
    int insert(OrderGood orderGood);

    /**
     * 获得订单项物品列表
     * @param orderId 订单id
     * @return 订单项物品列表
     */
    List<OrderItemGood> orderItemGoodByOrderId(String orderId);
}
