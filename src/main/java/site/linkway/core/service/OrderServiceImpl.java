package site.linkway.core.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttribute;
import site.linkway.core.controller.ImageDistribution;
import site.linkway.core.entity.bo.OrderGoodItem;
import site.linkway.core.entity.bo.PostOrder;
import site.linkway.core.entity.bo.PostOrderSearch;
import site.linkway.core.entity.bo.PostOrderSearchUnfold;
import site.linkway.core.entity.po.Order;
import site.linkway.core.entity.po.OrderGood;
import site.linkway.core.entity.po.User;
import site.linkway.core.entity.vo.OrderItem;
import site.linkway.core.entity.vo.OrderItemGood;
import site.linkway.core.entity.vo.OrderList;
import site.linkway.core.mapper.*;
import site.linkway.utils.UUIDUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GoodMapper goodMapper;
    @Autowired
    OrderGoodMapper orderGoodMapper;
    @Autowired
    GoodImgMapper goodImgMapper;

    /**
     * 添加新订单
     *
     * @param email     用户邮箱
     * @param postOrder 提交订单信息
     * @return orderId or null
     */
    @Override
    @Transactional
    public String insert(String email, PostOrder postOrder) {
        User user=new User();user.setEmail(email);
        user=userMapper.select(user);/*根据邮箱获得用户*/
        List<OrderGoodItem> orderItemList=postOrder.getGoods();/*获取所购买商品的id与数量*/
        String addressId=postOrder.getAddressId();/*获得收货地址id*/
        String mark=postOrder.getMark();//订单备注
        /*计算订单总价格*/
        double goodPriceCount=0f;
        for(OrderGoodItem orderGoodItem:orderItemList){
            goodPriceCount+=goodMapper.selectPriceByGoodId(orderGoodItem.getGoodId())*orderGoodItem.getNum();
        }
        //插入新的订单
        String orderId= UUIDUtils.getUUID();//新订单号
        int orderInsert=orderMapper.insertByParam(orderId,addressId,user.userId,goodPriceCount,mark);
        //新订单插入成功 插入新的订单项
        if(1==orderInsert) {
            for (OrderGoodItem orderGoodItem : orderItemList) {
                OrderGood orderGood = new OrderGood();
                orderGood.setGoodId(orderGoodItem.getGoodId());
                orderGood.setOrderId(orderId);
                orderGood.setNum(orderGoodItem.getNum());
                orderGoodMapper.insert(orderGood);
            }
        } else {
            return null;
        }
        return orderId;//添加订单成功
    }

    /**
     * 更新订单状态
     *
     * @param email   用户邮箱
     * @param orderId 订单id
     * @param status  订单状态
     * @param admin   是否为管理员操作
     * @return “true” or 原因
     */
    @Override
    @Transactional
    public String updateStatus(String email, String orderId, String status, boolean admin) {
        String result="true";
        User user=new User();user.setEmail(email);
        user=userMapper.select(user);/*根据邮箱获得用户*/
        Order order=new Order();
        order.setOrderId(orderId);
        order=orderMapper.select(order);
        //需要进行逻辑判断 即 订单状态的状态机
        if(admin){//管理员

        }else{//普通用户
            //付款结算
            /*待付款=>待发货*/
            if(status.equals("待发货")&&order.status.equals("待付款")){
                double userMoney=user.getMoney();/*用户余额*/
                double money=userMoney-order.getPriceCount();//剩余金额
                System.out.println("剩余金额:"+money);
                if(money<0d||1!=userMapper.updateMoney(email,money)){
                    return "您的余额: "+userMoney+"本次消费金额需: "+order.getPriceCount();
                }
                //进行状态更新
                int line=orderMapper.statusUpdate(orderId,user.getUserId(),status);
                if(1!=line){
                    result="订单状态更新失败";
                }
                return result;
            }
            //退款申请
            /*待发货=>退款中||已发货=>退款中*/
            if(status.equals("退款中")&&(order.status.equals("待发货")||order.status.equals("已发货"))){
                int line=orderMapper.statusUpdate(orderId,user.getUserId(),status);
                if(1!=line){
                    result="订单状态更新失败";
                }
                return result;
            }
            result="不允许此操作";
        }
        return result;
    }

    /**
     * 得到指定用户的所有订单
     *
     * @param email 用户邮箱
     * @return 订单项列表
     */
    @Override
    public List<OrderItem> selectByEmail(String email) {
        /*检索出此用户的所有订单以及订单的所有详情*/
        //取得userId
        String userId=userMapper.selectIdByEmail(email);
        //获取所有订单
        List<Order> orderList=orderMapper.selectByUserId(userId);
        //获取所有订单内的商品详情
        List<OrderItem> orderItemList=new ArrayList<>();
        //遍历所有订单
        for(Order order:orderList){
            orderItemList.add(getOrderItem(order));
        }
        return orderItemList;
    }

    /**
     * 获得订单详情
     *
     * @param email   邮箱
     * @param orderId 订单id
     * @return 订单项
     */
    @Override
    @Transactional
    public OrderItem selectByOrderId(String email, String orderId) {
        String userId = userMapper.selectIdByEmail(email);
        /*根据用户指定订单id检索出订单详情*/
        Order order = new Order();
        order.setOrderId(orderId);
        order = orderMapper.selectByOrderIdAndUserId(orderId, userId);
        OrderItem orderItem=getOrderItem(order);//根据订单获得其OrderItem
        return orderItem;
    }

    /**
     * 删除已签收的订单
     *
     * @param orderId 订单id
     * @param email   邮箱
     * @return “true” or 原因
     */
    @Override
    public String delete(String orderId, String email) {
        String userId=userMapper.selectIdByEmail(email);
        Order order=orderMapper.selectByOrderIdAndUserId(orderId,userId);
        //只有已签收或者待付款的订单可以删除操作
        if(order.status.equals("已签收")||order.status.equals("待付款")){
            //删除
            if(1==orderMapper.delete(orderId,userId)){
                return "true";
            }
        }
        return "删除失败";
    }

    /**
     * 根据订单相关属性、查询订单列表
     *
     * @param postOrderSearch
     * @return 订单列表
     */
    @Override
    public OrderList orderSearch(PostOrderSearch postOrderSearch) {
        /*将postOrderSearch属性展开供mapper使用*/
        PostOrderSearchUnfold postOrderSearchUnfold=new PostOrderSearchUnfold(postOrderSearch);
        /*如果提供了email则将其userId找到、在PostOrderSearchUnfold构造函数内进行的校验*/
        if(null!=postOrderSearchUnfold.getUserId()){
            postOrderSearchUnfold.setOrderId(userMapper.selectIdByEmail(postOrderSearch.getEmail()));
        }
        /*获取所有符合条件的订单*/
        List<Order> orders=orderMapper.orderSearch(postOrderSearchUnfold);
        OrderList orderList=new OrderList();
        /*装配orderList*/
        //获取所有订单内的商品详情
        List<OrderItem> orderItemList=new ArrayList<>();
        //遍历所有订单
        for(Order order:orders){
            orderItemList.add(getOrderItem(order));
        }
        orderList.setOrderItems(orderItemList);
        orderList.setPageNow(postOrderSearchUnfold.getPageNow());
        orderList.setPageSize(postOrderSearchUnfold.getPageSize());
        int pageCount=orderMapper.orderSearchCount(postOrderSearchUnfold)/ postOrderSearch.getPageSize();
        if(pageCount==0){pageCount++;}
        orderList.setPageCount(pageCount);
        return orderList;
    }

    /**
     * 发货处理
     *
     * @param orderId
     * @param logisticsNumber
     * @param logisticsName
     * @return “true” or 原因
     */
    @Override
    @Transactional
    public String orderShip(String orderId, String logisticsNumber, String logisticsName) {
        /*根据提供的orderId寻找订单*/
        Order order=new Order();order.setOrderId(orderId);
        order=orderMapper.select(order);
        if(null==order){
            return "无此订单";
        }
        /*判断订单状态是否可发货*/
        if(!(order.getStatus().equals("待发货")||order.getStatus().equals("已发货"))){
            return "订单状态为不可发货状态";
        }
        /*检查物流号与物流名是否为空*/
        if(logisticsNumber.replace(" ","").equals("")){
            return "物流号不能为空";
        }
        if(logisticsName.replace(" ","").equals("")){
            return "物流名称不能为空";
        }
        /*修改订单状态*/
        order.setLogisticsName(logisticsName);
        order.setLogisticsNumber(logisticsNumber);
        order.setStatus("已发货");
        return 1==orderMapper.update(order)?"true":"更新订单失败 未知错误";
    }

    /**
     * 退款处理
     *
     * @param orderId 订单号
     * @return “true” or 原因
     */
    @Override
    @Transactional
    public String orderRefund(String orderId) {
        /*根据提供的orderId寻找订单*/
        Order order=new Order();order.setOrderId(orderId);
        order=orderMapper.select(order);
        if(null==order){
            return "无此订单";
        }
        /*检查订单状态*/
        if(!order.status.equals("退款中")){
            return "订单不可退款";
        }
        /*订单状态更新，并将订单的金额返回给用户*/
        order.setStatus("已退款");
        int line=orderMapper.update(order);
        if(1!=line){
            return "退款失败 未知错误";
        }
        /*将金额返回至用户*/
        double priceCount=order.getPriceCount();
        String userId=order.getUserId();
        User user=userMapper.selectByUserId(userId);
        if(null==user){
            return "没有此订单的用户";
        }
        line=userMapper.updateMoney(user.getEmail(),user.getMoney()+priceCount);
        if(1!=line){
            return "返回金额失败";
        }
        return "true";
    }


    /**
     * 根据order获取其OrderItem
     * @param order 订单
     * @return 订单项
     */
    private OrderItem getOrderItem(Order order){
        OrderItem orderItem=new OrderItem();
        orderItem.setOrder(order);//将order装入orderItem
        /*获取此订单内的商品详情*/
        List<OrderItemGood> orderGoods = new ArrayList<>();
        orderGoods = orderGoodMapper.orderItemGoodByOrderId(order.getOrderId());
        /*获取订单中物品的相关图片地址*/
        for (OrderItemGood orderItemGood : orderGoods) {
            List<String> URLs = goodImgMapper.selectImgIdByGoodId(orderItemGood.goodId);
            //转换URL
            URLs = ImageDistribution.formatURLFromImgId(URLs);
            orderItemGood.setImgsURL(URLs);
        }
        orderItem.setOrderGoods(orderGoods);
        return orderItem;
    }
}
