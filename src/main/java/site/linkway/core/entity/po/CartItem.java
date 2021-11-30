package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.linkway.core.entity.po.Good;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    public String cartId;
    public String userId;
    public int num;
    public String goodId;
    public double price;
    public String name;
    public String profile;
    public int stock;//库存
    public int soldSum;//销量
    public String goodType;//商品类型
}
