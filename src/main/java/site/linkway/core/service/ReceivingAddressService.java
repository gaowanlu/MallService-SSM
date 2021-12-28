package site.linkway.core.service;

import site.linkway.core.entity.po.Address;
import java.util.List;

public interface ReceivingAddressService {
    boolean add(String email,String phone,String name,String address);
    boolean del(String email,String addressId);
    List<Address> getAll(String email);
    //收货地址更新
    boolean update(Address address);
}
