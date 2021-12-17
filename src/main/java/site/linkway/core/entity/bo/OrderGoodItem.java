package site.linkway.core.entity.bo;

import lombok.Data;

@Data
public class OrderGoodItem {
    @Override
    public String toString() {
        return "OrderGoodItem{" +
                "goodId='" + goodId + '\'' +
                ", num=" + num +
                '}';
    }

    String goodId;
    int num;
}
