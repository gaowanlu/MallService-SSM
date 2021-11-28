package site.linkway.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.linkway.core.entity.po.Address;
import site.linkway.core.entity.po.User;
import site.linkway.core.mapper.AddressMapper;
import site.linkway.core.mapper.UserMapper;
import site.linkway.utils.UUIDUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceivingAddressServiceImpl implements ReceivingAddressService{
    //构造器注入
    @Autowired
    public ReceivingAddressServiceImpl(AddressMapper addressMapper, UserMapper userMapper) {
        this.addressMapper = addressMapper;
        this.userMapper=userMapper;
    }
    private AddressMapper addressMapper;
    private UserMapper userMapper;

    @Override
    public boolean add(String email, String phone, String name, String address) {
        //根据用户email找到其userId
        User user=new User();user.setEmail(email);
        User targetUser=userMapper.select(user);
        Address insertAddress=new Address(UUIDUtils.getUUID(),targetUser.getUserId(),phone,address,name);
        return 1==addressMapper.insert(insertAddress);
    }

    @Override
    public boolean del(String email, String addressId) {
        User user=new User();user.setEmail(email);
        User targetUser=userMapper.select(user);
        Address delAddress=new Address();
        delAddress.setUserId(targetUser.getUserId());
        delAddress.setAddressId(addressId);
        return 1==addressMapper.delete(delAddress);
    }

    @Override
    public List<Address> getAll(String email) {
        User user=new User();user.setEmail(email);
        User targetUser=userMapper.select(user);
        Address address=new Address();
        address.setUserId(targetUser.getUserId());
        List<Address> addresses=new ArrayList<>();
        addresses=addressMapper.select(address);
        if(addresses==null){
            addresses=new ArrayList<>();
        }
        return addresses;
    }
}
