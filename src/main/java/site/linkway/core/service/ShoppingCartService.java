package site.linkway.core.service;

import site.linkway.core.entity.bo.PostCart;
import site.linkway.core.entity.po.CartItem;

import java.util.List;

public interface ShoppingCartService {
    boolean addCart(String goodId, String email, int num);

    boolean deleteCart(String cartId, String email);

    List<CartItem> getCartsByEmail(String email);

    boolean update(String email, PostCart postCart[]);
}
