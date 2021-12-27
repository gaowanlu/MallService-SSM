package site.linkway.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.linkway.core.controller.ImageDistribution;
import site.linkway.core.entity.bo.PostCart;
import site.linkway.core.entity.po.Cart;
import site.linkway.core.entity.po.GoodImg;
import site.linkway.core.entity.po.User;
import site.linkway.core.entity.po.CartItem;
import site.linkway.core.mapper.CartMapper;
import site.linkway.core.mapper.GoodImgMapper;
import site.linkway.core.mapper.GoodMapper;
import site.linkway.core.mapper.UserMapper;
import site.linkway.utils.UUIDUtils;


import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodMapper goodMapper;
    @Autowired
    GoodImgMapper goodImgMapper;

    /**
     * 向购物车中添加商品
     *
     * @param goodId 商品 id
     * @param email  用户邮箱
     * @param num    商品数量
     * @return 是否添加成功
     */
    @Override
    public boolean addCart(String goodId, String email, int num) {
        String userId = userMapper.selectIdByEmail(email);
        Cart inertCart = new Cart(UUIDUtils.getUUID(), goodId, userId, num);
        return 1 == cartMapper.insert(inertCart);
    }

    /**
     * 删除购物车中的商品
     *
     * @param cartId 购物车 id
     * @param email  用户邮箱
     * @return 是否删除成功
     */
    @Override
    public boolean deleteCart(String cartId, String email) {
        // 根据邮箱用户
        User user = new User();
        user.setEmail(email);
        User targetUser = userMapper.select(user);
        Cart delCart = new Cart();
        delCart.setCartId(cartId);
        delCart.setUserId(targetUser.getUserId());
        return 1 == cartMapper.delete(delCart);
    }

    /**
     * 获取指定用户的购物车
     *
     * @param email 用户邮箱
     * @return 购物车列表
     */
    @Override
    public List<CartItem> getCartsByEmail(String email) {
        // 根据邮箱寻找用户
        User user = new User();
        user.setEmail(email);
        User targetUser = userMapper.select(user);
        Cart cart = new Cart();
        cart.setUserId(targetUser.getUserId());
        // 根据用户 id 查询购物车
        List<CartItem> cartItemList = cartMapper.selectCartsDetail(email);
        // 为购物车当中的商品添加图片
        for (CartItem item : cartItemList) {
            // 根据 goodId 检索出 imgId
            List<String> goodIds = goodImgMapper.selectImgIdByGoodId(item.getGoodId());
            for (int i = 0; i < goodIds.size(); i++) {
                String URL = goodIds.get(i);
                URL = ImageDistribution.formatURLFromImgId(URL);
                goodIds.set(i, URL);
            }
            // 将 imgsURL 添加到 item 中
            item.setImgsURL(goodIds);
        }
        return cartItemList;
    }

    /**
     * 提交购物车
     * 覆盖式更新
     *
     * @param email    用户邮箱
     * @param postCart 购物车
     * @return 是否提交成功
     */
    @Override
    @Transactional // 事务注解，提交失败则回滚
    public boolean update(String email, PostCart[] postCart) {
        String userId = userMapper.selectIdByEmail(email);
        // 删除原有购物车
        cartMapper.deleteByUserId(userId);
        // 插入新的购物车
        for (PostCart item : postCart) {
            if (false == addCart(item.getGoodId(), email, item.getNum())) {
                return false;
            }
        }
        return true;
    }


}
