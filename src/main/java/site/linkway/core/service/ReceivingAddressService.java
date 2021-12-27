package site.linkway.core.service;

import site.linkway.core.entity.po.Address;
import java.util.List;

public interface ReceivingAddressService {
    /**
     * 添加收货地址
     * @param email 邮箱
     * @param phone 手机号
     * @param name 姓名
     * @param address 收货地址
     * @return 添加结果
     */
    boolean add(String email,String phone,String name,String address);

    /**
     * 删除收货地址
     * @param email 邮箱
     * @param addressId 收货地址id
     * @return 删除结果
     */
    boolean del(String email,String addressId);

    /**
     * 获得用户所有收货地址 by email
     * @param email 用户邮箱
     * @return 收货地址列表
     */
    List<Address> getAll(String email);

    /**
     * 收货地址更新
     * @param address 收货地址
     * @return 更新结果
     */
    boolean update(Address address);
}
