package site.linkway.core.entity.bo;

import java.util.List;
import lombok.Data;

@Data
public class PostOrder {
    List<OrderGoodItem> goods;
    String addressId;
}
