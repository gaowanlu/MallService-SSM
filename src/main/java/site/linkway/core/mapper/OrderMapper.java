package site.linkway.core.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.bo.PostOrderSearchUnfold;
import site.linkway.core.entity.po.Order;
import java.util.List;


@Mapper
public interface OrderMapper {
    /**
     * 插入订单
     * @param order 订单
     * @return 插入行
     */
    int insert(Order order);

    /**
     * 根据orderId检索订单
     * @param order 订单
     * @return 订单
     */
    Order select(Order order);//select by orderId

    /**
     * 检索订单
     * @param orderId 订单号
     * @param userId 用户id
     * @return
     */
    Order selectByOrderIdAndUserId(@Param("orderId") String orderId,
                                   @Param("userId") String userId);

    /**
     * 获取指定用户的所有订单
     * @param userId 用户id
     * @return 订单列表
     */
    List<Order> selectByUserId(String userId);


    /**
     * 插入新订单
     * @param orderId 订单id
     * @param addressId 收货地址id
     * @param userId 用户id
     * @param priceCount 总价格
     * @param mark 订单备注
     * @return 插入行
     */
    int insertByParam(@Param("orderId") String orderId,
                      @Param("addressId") String addressId,
                      @Param("userId") String userId,
                      @Param("priceCount") double priceCount,
                      @Param("mark") String mark);

    /**
     * 更新订单状态
     * @param orderId 订单id
     * @param userId 用户id
     * @param status 订单状态
     * @return 更新行
     */
    int statusUpdate(@Param("orderId") String orderId,
                     @Param("userId") String userId,
                     @Param("status") String status);

    /**
     * 订单删除
     * @param orderId 订单id
     * @param userId 用户id
     * @return 删除行
     */
    int delete(@Param("orderId") String orderId,@Param("userId") String userId);

    /**
     * 条件搜索订单，分页处理
     * @param postOrderSearchUnfold 搜索约束
     * @return 订单列表
     */
    List<Order> orderSearch(PostOrderSearchUnfold postOrderSearchUnfold);

    /**
     * 统计符合搜索要求订单的数量
     * @param postOrderSearchUnfold
     * @return 订单数量
     */
    int orderSearchCount(PostOrderSearchUnfold postOrderSearchUnfold);

    /**
     * 订单更新 , 只更新status以及物流信息
     * @param order 订单
     * @return 更新行
     */
    int update(Order order);
}
