package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.linkway.core.entity.po.Cart;
import java.util.List;

//table name:cart  entity:po.Cart
@Mapper
public interface CartMapper {
    int insert(Cart cart);
    int update(Cart cart);//只负责更新数量
    int delete(Cart cart);
    List<Cart> select(Cart cart);
}

