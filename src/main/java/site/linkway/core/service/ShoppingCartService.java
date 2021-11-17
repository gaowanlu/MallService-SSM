package site.linkway.core.service;

import site.linkway.core.entity.po.Cart;
import java.util.List;

public interface ShoppingCartService {
    /*新增条项*/
    public boolean addCart(String goodId,String email,int num);
    /*删除条项*/
    public boolean deleteCart(String cartId,String email);
    /*获得购物车条项*/
    public List<Cart> getCartsByEmail(String email);
}
