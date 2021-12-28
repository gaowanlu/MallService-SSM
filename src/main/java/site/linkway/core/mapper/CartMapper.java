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
    /**
     * 插入购物车条项
     * @param cart 购物车
     * @return 插入行
     */
    int insert(Cart cart);


    /**
     * 更新购物车条项，物品数量
     * @param cartId 购物车条项id
     * @param num 物品数量
     * @param userId 用户id
     * @return 更新行
     */
    int update(@Param("cartId") String cartId,@Param("num") int num,@Param("userId") String userId);

    /**
     * 删除购物车条项 by cartId
     * @param cart 购物车
     * @return 删除行
     */
    int delete(Cart cart);


    /**
     * 根据userId检索
     * @param cart 购物车条项
     * @return 购物车条项列表
     */
    List<Cart> select(Cart cart);

    /**
     * 根据用户id检索购物车列表信息
     * @param email
     * @return
     */
    List<CartItem> selectCartsDetail(String email);//根据用户id检索其购物车

    /**
     * 根据userId删除其所有购物车条项
     * @param userId
     * @return 删除行
     */
    int deleteByUserId(String userId);
}

