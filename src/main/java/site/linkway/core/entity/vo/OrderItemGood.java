package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemGood {
    public int num;//订单物品数量
    public String goodId;//物品id
    public double price;//价格
    public String name;//物品名称
    public String profile;//简介
    public List<String> imgsURL=new ArrayList<>();//展示图id
}
