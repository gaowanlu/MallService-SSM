package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderGood {
    int orderGoodId;//自增id 订单物品id
    String orderId;//订单id 订单id
    String goodId;//商品id 物品id
    int num;//商品数量 商品数量
}
