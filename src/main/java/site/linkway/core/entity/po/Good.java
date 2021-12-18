package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//商品
public class Good {
    public String goodId;
    public double price;
    public String name;
    public String profile;
    public int stock;//库存
    public int soldSum;//销量
    public int goodTypeId;//商品类型id
    public int onSale;
}
