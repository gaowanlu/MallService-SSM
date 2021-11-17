package site.linkway.core.mapper;


import site.linkway.core.entity.po.Order;

//table name:order  entity:po.Order
public interface OrderMapper {
    public int insert(Order order);
    public int update(Order order);
    public int delete(Order order);
    public Order select(Order order);
}
