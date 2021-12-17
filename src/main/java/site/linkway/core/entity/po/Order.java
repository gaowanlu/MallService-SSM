package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    public String orderId;// varchar(32) PK
    public String userId;// varchar(32)
    public String status;// varchar(10) '待付款','待发货','已发货','已签收'
    public String phone;// char(11)
    public Date time;//下单时间
    public String address;// varchar(30)
    public String name;// varchar(14)
    public String logisticsNumber;// varchar(30) 物流单号
    public String logisticsName;// varchar(20) 物流名称
    public double priceCount;//订单总金额
}
