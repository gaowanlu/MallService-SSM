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

    int goodTypeId;
    String name;
    int operator;//0 更新操作 1 插入操作
}
