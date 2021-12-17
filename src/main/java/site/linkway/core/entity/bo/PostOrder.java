package site.linkway.core.entity.bo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOrder {
    List<OrderGoodItem> goods;
    String addressId;

    @Override
    public String toString() {
        return "PostOrder{" +
                "goods=" + goods +
                ", addressId='" + addressId + '\'' +
                '}';
    }
}
