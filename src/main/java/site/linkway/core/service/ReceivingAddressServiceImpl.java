package site.linkway.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.linkway.core.entity.po.Address;
import site.linkway.core.mapper.AddressMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceivingAddressServiceImpl implements ReceivingAddressService{
    @Autowired
    public ReceivingAddressServiceImpl(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    private AddressMapper addressMapper;

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
