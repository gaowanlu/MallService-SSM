package site.linkway.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.linkway.core.controller.Order;
import site.linkway.core.entity.bo.OrderGoodItem;
import site.linkway.core.entity.bo.PostOrder;
import site.linkway.core.entity.po.User;
import site.linkway.core.entity.vo.OrderItem;
import site.linkway.core.mapper.OrderMapper;
import site.linkway.core.mapper.UserMapper;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;

    /*添加新订单*/
    @Override
    @Transactional
    public String insert(String email, PostOrder postOrder) {
        User user=new User();user.setEmail(email);
        user=userMapper.select(user);/*根据邮箱获得用户*/
        double userMoney=user.getMoney();/*用户余额*/
        List<OrderGoodItem> orderItemList=postOrder.getGoods();/*获取所购买商品的id与数量*/
        String addressId=postOrder.getAddressId();/*获得收货地址id*/
        /*首先将商品对应的商品详情检索出来、进行总价与余额的比对*/

        /*对新订单与新订单内的商品条项进行注入*/


        return "添加失败";
    }

    /*用户申请取消订单*/
    @Override
    public String cancel(String email,String OrderId) {
        /*根据email与orderId检索出订单详情*/

        /* 将订单的状态改为'申请退款中' */

        return "取消订单失败";
    }

    /*获得指定用户的订单*/
    @Override
    public List<OrderItem> selectByEmail(String email) {
        /*检索出此用户的所有订单以及订单的所有详情*/
        return null;
    }

    /*根据OrderId获得订单详情*/
    @Override
    public OrderItem selectByOrderId(String email,String OrderId) {
        /*根据用户指定订单id检索出订单详情*/

        return null;
    }
}
