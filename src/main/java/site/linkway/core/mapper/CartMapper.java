package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.linkway.core.entity.po.Cart;
import site.linkway.core.entity.po.CartItem;

import java.util.List;

//table name:cart  entity:po.Cart
@Mapper
public interface CartMapper {
    int insert(Cart cart);
    int update(Cart cart);//只负责更新数量
    int delete(Cart cart);
    List<Cart> select(Cart cart);
    List<CartItem> selectCartsDetail(String email);//根据用户id检索其购物车
}

