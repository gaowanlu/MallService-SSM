package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PutMapping;
import site.linkway.core.entity.po.Cart;
import site.linkway.core.entity.po.CartItem;

import java.util.List;

//table name:cart  entity:po.Cart
@Mapper
public interface CartMapper {
    int insert(Cart cart);
    //更新购物车条项数量
    int update(@Param("cartId") String cartId,@Param("num") int num,@Param("userId") String userId);
    int delete(Cart cart);
    List<Cart> select(Cart cart);
    List<CartItem> selectCartsDetail(String email);//根据用户id检索其购物车
    int deleteByUserId(String userId);
}

