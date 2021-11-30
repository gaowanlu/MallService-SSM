package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderGood {
    int orderGoodId;//自增id
    String orderId;//订单id
    String goodId;//商品id
    int num;//商品数量
}
