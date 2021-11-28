package site.linkway.core.mapper;


import site.linkway.core.entity.po.Order;

//table name:order  entity:po.Order
public interface OrderMapper {
    int insert(Order order);
    int update(Order order);
    int delete(Order order);
    Order select(Order order);
}
