package site.linkway.core.service;

import site.linkway.core.entity.po.Address;
import java.util.List;

public interface ReceivingAddressService {
    public boolean add(String email,String phone,String name,String address);
    public boolean del(String email,String addressId);
    public List<Address> getAll(String email);
}
