package site.linkway.core.service;

import org.springframework.stereotype.Service;
import site.linkway.core.entity.po.Cart;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    @Override
    public boolean addCart(String goodId, String email, int num) {
        return false;
    }

    @Override
    public boolean deleteCart(String cartId, String email) {
        return false;
    }

    @Override
    public List<Cart> getCartsByEmail(String email) {
        return new ArrayList<>();
    }

}
