package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.linkway.core.entity.po.Address;
import java.util.List;

//table name:address  entity:po.Address
@Mapper
public interface AddressMapper {
    int insert(Address address);
    int delete(Address address);
    //update by Id
    int update(Address address);
    /*By userId*/
    List<Address> select(Address address);
}
