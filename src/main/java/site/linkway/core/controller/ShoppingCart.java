package site.linkway.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.linkway.core.entity.bo.PostCart;
import site.linkway.core.entity.vo.CartList;
import site.linkway.core.entity.vo.StatusResult;
import site.linkway.core.service.ShoppingCartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/user")
public class ShoppingCart {
    static Logger logger = Logger.getLogger(ShoppingCart.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    HttpServletResponse httpServletResponse;
    @Autowired
    HttpSession httpSession;

    /**
     * 向购物车添加物品
     *
     * @param email  HttpSession 中的用户邮箱
     * @param goodId 商品 id
     * @param num    商品数量
     * @return 添加结果
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/cart", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addCart(@SessionAttribute(value = "id") String email,
                          @NonNull String goodId,
                          @NonNull int num) throws JsonProcessingException {
        StatusResult statusResult = new StatusResult();
        // 添加购物车条项并写入结果
        statusResult.setResult(shoppingCartService.addCart(goodId, email, num));
        return mapper.writeValueAsString(statusResult);
    }

    /**
     * 删除购物车中的物品
     *
     * @param email  HttpSession 中的用户邮箱
     * @param cartId 购物车 id
     * @return 删除之后购物车的内容
     * @throws JsonProcessingException
     */
    @DeleteMapping(value = "/cart", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addCart(@NonNull String cartId,
                          @SessionAttribute(value = "id") String email
    ) throws JsonProcessingException {
        CartList cartList = new CartList();
        // 删除购物车条项并写入操作结果
        cartList.result = shoppingCartService.deleteCart(cartId, email);
        // 获取此用户的购物车条项
        cartList.setCarts(shoppingCartService.getCartsByEmail(email));
        // 结果返回
        return mapper.writeValueAsString(cartList);
    }

    /**
     * 获取购物车的全部内容
     *
     * @param email HttpSession 中的用户邮箱
     * @return 购物车的全部内容
     * @throws JsonProcessingException
     */
    @GetMapping(value = "/cart", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addCart(@SessionAttribute(value = "id") String email) throws JsonProcessingException {
        CartList cartList = new CartList();
        // 获取购物车条项
        cartList.setCarts(shoppingCartService.getCartsByEmail(email));
        return mapper.writeValueAsString(cartList);
    }

    /**
     * 提交购物车
     *
     * @param postCart 购物车信息
     * @return 提交结果
     * @throws JsonProcessingException
     */
    @PutMapping(value = "/cart", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateCart(@SessionAttribute("id") String email,
                             @RequestBody PostCart postCart[]) throws JsonProcessingException {
        // 使用提交的购物车条项更新旧的购物车数据
        boolean result = shoppingCartService.update(email, postCart);
        // 返回操作结果
        CartList cartList = new CartList();
        cartList.setResult(result);
        cartList.setCarts(shoppingCartService.getCartsByEmail(email));
        return mapper.writeValueAsString(cartList);
    }

}
