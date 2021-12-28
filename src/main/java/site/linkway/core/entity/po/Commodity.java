package site.linkway.core.entity.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commodity {
    String goodType;//商品类型
    int goodTypeId;//商品类型id
    String name;//物品名称
    String profile;//物品简介
    double price;//价格
    String goodId;//物品id
    int stock;//库存量
    int soldSum;//销量
    int onSale;//是否在售
    List<String> imgsURL=new ArrayList<>();//展示图id
    String detail;//文字详情
    String detailsImg;//详细介绍图
}
