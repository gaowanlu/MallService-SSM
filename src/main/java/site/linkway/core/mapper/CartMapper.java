package site.linkway.core.mapper;

import site.linkway.core.entity.po.Cart;

//table name:cart  entity:po.Cart
public interface CartMapper {
    public int insert(Cart cart);
    public int update(Cart cart);
    public int delete(Cart cart);
    public Cart select(Cart cart);
}

