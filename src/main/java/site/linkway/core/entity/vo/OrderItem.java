package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.linkway.core.entity.po.Order;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    List<OrderItemGood> orderGoods;//订单物品列表
    Order order;//订单详情
}
