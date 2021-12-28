package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.linkway.core.entity.po.Address;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressList {
    boolean result=true;//操作结果
    public List<Address> addresses=new ArrayList<>();//地址列表
}
