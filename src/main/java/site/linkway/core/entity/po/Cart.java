package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//购物车条项
public class Cart {
    public String cartId;//购物车id
    public String goodId;//物品id
    public String userId;//用户账号id
    public int num;//物品数量
}
