package site.linkway.core.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.po.Order;
import java.util.List;

//table name:order  entity:po.Order
@Mapper
public interface OrderMapper {
    int insert(Order order);
    Order select(Order order);//select by orderId
    Order selectByOrderIdAndUserId(@Param("orderId") String orderId,@Param("userId") String userId);
    List<Order> selectByUserId(String userId);
    //插入新订单
    int insertByAddressIdAndEmail(@Param("orderId") String orderId,
                                  @Param("addressId") String addressId,
                                  @Param("userId") String userId,
                                  @Param("priceCount") double priceCount);
    //更新订单状态
    int statusUpdate(@Param("orderId") String orderId,
                     @Param("userId") String userId,
                     @Param("status") String status);
    //订单删除
    int delete(@Param("orderId") String orderId,@Param("userId") String userId);
}
