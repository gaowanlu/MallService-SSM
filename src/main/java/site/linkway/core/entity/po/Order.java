package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    public String orderId;// 订单id
    public String userId;// 用户id
    public String status;// 订单状态 in '待付款','待发货','已发货','已签收'
    public String phone;// 手机号码
    public String time;//下单时间
    public String address;// 收货地址
    public String name;// 姓名
    public String logisticsNumber;//  物流单号
    public String logisticsName;//  物流名称
    public double priceCount;//订单总金额
    public String mark;//订单备注
}
