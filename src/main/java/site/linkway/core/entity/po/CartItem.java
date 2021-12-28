package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import site.linkway.core.entity.po.Good;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    public String cartId;//购物车条项id
    public String userId;//用户账号id
    public int num;//物品数量
    public String goodId;//物品id
    public double price;//价格
    public String name;//物品名称
    public String profile;//物品简介
    public int stock;//库存
    public int soldSum;//销量
    public String goodType;//商品类型
    public int goodTypeId;//商品类型id
    public List<String> imgsURL=new ArrayList<>();//展示图 id
}
