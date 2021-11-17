package site.linkway.core.service;

import site.linkway.core.entity.po.Address;

import java.util.ArrayList;
import java.util.List;

public class ReceivingAddressServiceImpl implements ReceivingAddressService{
    @Override
    public boolean add(String email, String phone, String name, String address) {
        return false;
    }

    @Override
    public boolean del(String email, String addressId) {
        return false;
    }

    @Override
    public List<Address> getAll(String email) {
        return new ArrayList<>();
    }
}
