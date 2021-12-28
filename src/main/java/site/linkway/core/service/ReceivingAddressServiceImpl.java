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

    /**
     * 添加收货地址
     *
     * @param email   邮箱
     * @param phone   手机号
     * @param name    姓名
     * @param address 收货地址
     * @return 添加结果
     */
    @Override
    public boolean add(String email, String phone, String name, String address) {
        //根据用户email找到其userId
        User user=new User();user.setEmail(email);
        User targetUser=userMapper.select(user);
        Address insertAddress=new Address(UUIDUtils.getUUID(),targetUser.getUserId(),phone,address,name);
        return 1==addressMapper.insert(insertAddress);
    }

    /**
     * 删除收货地址
     *
     * @param email     邮箱
     * @param addressId 收货地址id
     * @return 删除结果
     */
    @Override
    public boolean del(String email, String addressId) {
        User user=new User();user.setEmail(email);
        User targetUser=userMapper.select(user);
        Address delAddress=new Address();
        delAddress.setUserId(targetUser.getUserId());
        delAddress.setAddressId(addressId);
        return 1==addressMapper.delete(delAddress);
    }

    /**
     * 获得用户所有收货地址 by email
     *
     * @param email 用户邮箱
     * @return 收货地址列表
     */
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

    /**
     * 收货地址更新
     *
     * @param address 收货地址
     * @return 更新结果
     */
    @Override
    public boolean update(Address address) {
        String userId=userMapper.selectIdByEmail(address.getUserId());//获得userId
        address.setUserId(userId);
        return 1==addressMapper.update(address);
    }

}
