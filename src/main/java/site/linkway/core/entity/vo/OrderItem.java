package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.linkway.core.entity.po.CartItem;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    List<CartItem> orderDetail;
}
