package site.linkway.core.mapper;

import site.linkway.core.entity.po.Address;

//table name:address  entity:po.Address
public interface AddressMapper {
    public int insert(Address address);
    public int delete(Address address);
    public int update(Address address);
    public Address select(Address address);
}
