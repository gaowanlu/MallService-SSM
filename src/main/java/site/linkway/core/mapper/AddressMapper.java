package site.linkway.core.mapper;

import site.linkway.core.entity.po.Address;
import java.util.List;

//table name:address  entity:po.Address
public interface AddressMapper {
    int insert(Address address);
    int delete(Address address);
    int update(Address address);
    /*By userId*/
    List<Address> select(Address address);
}
