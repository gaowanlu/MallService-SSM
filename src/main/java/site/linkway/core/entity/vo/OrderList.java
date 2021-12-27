package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderList {
    List<OrderItem> orderItems;//订单项列表
    int pageNow;//此时页码
    int pageSize;//分页大小
    int pageCount;//共有多少页
}
