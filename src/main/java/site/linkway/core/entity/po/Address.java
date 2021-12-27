package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//用户收货地址
public class Address {
    public String addressId;//收货地址id
    public String userId;//用户id
    public String phone;//手机号
    public String address;//收货地址
    public String name;//收货人姓名
}
