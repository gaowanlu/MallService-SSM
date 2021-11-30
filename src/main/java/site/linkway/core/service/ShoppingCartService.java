package site.linkway.core.service;

import site.linkway.core.entity.po.CartItem;

import java.util.List;

public interface ShoppingCartService {
    /*新增条项*/
    boolean addCart(String goodId,String email,int num);
    /*删除条项*/
    boolean deleteCart(String cartId,String email);
    /*获得购物车条项*/
    List<CartItem> getCartsByEmail(String email);
}
