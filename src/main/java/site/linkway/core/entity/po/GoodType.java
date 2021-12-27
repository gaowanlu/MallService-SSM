package site.linkway.core.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//商品类型
public class GoodType {
    public int goodTypeId;//商品类型id
    public String name;//商品类型名称
}
