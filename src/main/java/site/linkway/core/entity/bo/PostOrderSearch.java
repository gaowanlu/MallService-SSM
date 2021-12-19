package site.linkway.core.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOrderSearch {
    String orderId;//订单号
    String email;//订单所属者email
    String status;//订单状态
    DateMinMax time;//订单产生时间区间
    int pageSize;//每页多少个 1~20
    int pageNow;//现在所在页码 1~
    @Override
    public String toString() {
        return "PostOrderSearch{" +
                "orderId='" + orderId + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", time=" + time +
                ", pageSize=" + pageSize +
                ", pageNow=" + pageNow +
                '}';
    }
}
