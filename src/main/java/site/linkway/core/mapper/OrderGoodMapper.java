package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.linkway.core.entity.po.Order;
import site.linkway.core.entity.po.OrderGood;
import site.linkway.core.entity.vo.OrderItemGood;
import java.util.List;

@Mapper
public interface OrderGoodMapper {
    int insert(OrderGood orderGood);
    List<OrderItemGood> orderItemGoodByOrderId(String orderId);
}
