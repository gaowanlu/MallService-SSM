package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//商品
public class Good {
    public String goodId;//物品id
    public double price;//价格
    public String name;//名称
    public String profile;//简介
    public int stock;//库存
    public int soldSum;//销量
    public int goodTypeId;//商品类型id
    public int onSale;//是否在售
    public String detail;//文字详情
    public String detailImgId;//详情图片Id
}
