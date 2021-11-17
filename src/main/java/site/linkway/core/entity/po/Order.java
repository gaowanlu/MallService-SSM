package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    public String orderId;// varchar(32) PK
    public String userId;// varchar(32)
    public String status;// varchar(10)
    public String phone;// char(11)
    public String address;// varchar(30)
    public String name;// varchar(14)
    public String logisticsNumber;// varchar(30) 物流单号
    public String logisticsName;// varchar(20) 物流名称
}
