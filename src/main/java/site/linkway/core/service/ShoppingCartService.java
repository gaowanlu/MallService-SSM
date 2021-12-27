package site.linkway.core.service;

import site.linkway.core.entity.bo.PostCart;
import site.linkway.core.entity.po.CartItem;

import java.util.List;

public interface ShoppingCartService {
    /**
     * 向购物车中添加商品
     *
     * @param goodId 商品 id
     * @param email  用户邮箱
     * @param num    商品数量
     * @return 是否添加成功
     */
    boolean addCart(String goodId, String email, int num);

    /**
     * 删除购物车中的商品
     *
     * @param cartId 购物车 id
     * @param email  用户邮箱
     * @return 是否删除成功
     */
    boolean deleteCart(String cartId, String email);

    /**
     * 获取指定用户的购物车
     *
     * @param email 用户邮箱
     * @return 购物车列表
     */
    List<CartItem> getCartsByEmail(String email);

    /**
     * 提交购物车
     * 覆盖式更新
     *
     * @param email    用户邮箱
     * @param postCart 购物车
     * @return 是否提交成功
     */
    boolean update(String email, PostCart postCart[]);
}
