package site.linkway.core.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.bo.PostOrderSearchUnfold;
import site.linkway.core.entity.po.Order;
import java.util.List;

//table name:order  entity:po.Order
@Mapper
public interface OrderMapper {
    int insert(Order order);
    Order select(Order order);//select by orderId
    Order selectByOrderIdAndUserId(@Param("orderId") String orderId,
                                   @Param("userId") String userId);
    //获取指定用户的所有订单
    List<Order> selectByUserId(String userId);
    //插入新订单
    int insertByParam(@Param("orderId") String orderId,
                      @Param("addressId") String addressId,
                      @Param("userId") String userId,
                      @Param("priceCount") double priceCount,
                      @Param("mark") String mark);
    //更新订单状态
    int statusUpdate(@Param("orderId") String orderId,
                     @Param("userId") String userId,
                     @Param("status") String status);

    //订单删除
    int delete(@Param("orderId") String orderId,@Param("userId") String userId);

    //获取所有服务要求的订单
    List<Order> orderSearch(PostOrderSearchUnfold postOrderSearchUnfold);
    //统计符合搜索要求订单的数量
    int orderSearchCount(PostOrderSearchUnfold postOrderSearchUnfold);
    /*订单更新 只更新 status 以及物流信息*/
    int update(Order order);
}
