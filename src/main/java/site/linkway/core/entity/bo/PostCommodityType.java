package site.linkway.core.entity.bo;

import lombok.Data;

@Data
public class PostCommodityType {
    @Override
    public String toString() {
        return "PostCommodityType{" +
                "goodTypeId=" + goodTypeId +
                ", name='" + name + '\'' +
                ", operator=" + operator +
                '}';
    }

    int goodTypeId;//物品类型id
    String name;//物品名称
    int operator;//0 更新操作 1 插入操作
}
