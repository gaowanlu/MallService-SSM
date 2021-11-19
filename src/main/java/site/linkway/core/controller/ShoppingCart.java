package site.linkway.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.linkway.core.entity.vo.CartList;
import site.linkway.core.entity.vo.StatusResult;
import site.linkway.core.service.ShoppingCartService;
import site.linkway.core.service.UserDataService;

import javax.servlet.http.HttpSession;

/*购物车模块
* 添加购物车条项
* /api/addCart?goodId={}&num={}
* 删除购物车条项
* /api/deleteCart?cartId={}
* 获得购物车全部条项
* /api/getMyCarts
* */
@Controller
@RequestMapping("/api")
public class ShoppingCart {
    static Logger logger= Logger.getLogger(ShoppingCart.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    @Qualifier("ShoppingCartServiceImpl")
    private ShoppingCartService shoppingCartService;

    /*新增购物车条项*/
    @PostMapping(value = "/cart",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addCart(@NonNull String goodId,
                          @NonNull int num,
                          @NonNull HttpSession httpSession) throws JsonProcessingException {
        StatusResult statusResult=new StatusResult();
        String email=(String)httpSession.getAttribute("id");
        //添加购物车条项
        statusResult.setResult(shoppingCartService.addCart(goodId,email,num));
        return mapper.writeValueAsString(statusResult);
    }

    /*删除购物车条项 同时返回现有条项*/
    @DeleteMapping(value = "/cart/{cartId}",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addCart(@NonNull String cartId,
                          @NonNull HttpSession httpSession) throws JsonProcessingException {
        CartList cartList=new CartList();
        String email=(String)httpSession.getAttribute("id");
        //删除购物车条项
        cartList.result=shoppingCartService.deleteCart(cartId,email);
        //获取购物车条项
        cartList.setCarts(shoppingCartService.getCartsByEmail(email));
        return mapper.writeValueAsString(cartList);
    }

    /*获取购物车全部条项*/
    @GetMapping(value = "/cart",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addCart(@NonNull HttpSession httpSession) throws JsonProcessingException {
        CartList cartList=new CartList();
        String email=(String)httpSession.getAttribute("id");
        //获取购物车条项
        cartList.setCarts(shoppingCartService.getCartsByEmail(email));
        return mapper.writeValueAsString(cartList);
    }

}
