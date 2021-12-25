package site.linkway.core.service;

import site.linkway.core.entity.bo.PostCart;
import site.linkway.core.entity.po.CartItem;

import java.util.List;

public interface ShoppingCartService {
    /*新增条项*/
    boolean addCart(String goodId,String email,int num);
    /*删除条项*/
    boolean deleteCart(String cartId,String email);
    /*获得购物车条项*/
    List<CartItem> getCartsByEmail(String email);
    /*购物车条项更新*/
    boolean updateCart(String email,CartItem cartItem);
    /*购物车覆盖*/
    boolean cover(String email,PostCart postCart[]);
}
