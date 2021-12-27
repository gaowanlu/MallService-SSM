package site.linkway.core.entity.bo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOrder {
    List<OrderGoodItem> goods;//物品列表
    String addressId;//收货地址
    String mark;//订单备注
    @Override
    public String toString() {
        return "PostOrder{" +
                "goods=" + goods +
                ", addressId='" + addressId + '\'' +
                '}';
    }
}
