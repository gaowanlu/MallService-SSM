package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.linkway.core.entity.po.Address;
import java.util.List;

//table name:address  entity:po.Address
@Mapper
public interface AddressMapper {
    /**
     * 插入收货地址 根据 addressId
     * @param address
     * @return 插入行
     */
    int insert(Address address);

    /**
     * 删除收货地址
     * @param address
     * @return 删除行
     */
    int delete(Address address);
    /**
     * 更新收货地址 根据 addressId
     * @param address
     * @return
     */
    int update(Address address);

    /**
     * 根据用户id userId 检索收货地址
     * @param address
     * @return
     */
    List<Address> select(Address address);
}
