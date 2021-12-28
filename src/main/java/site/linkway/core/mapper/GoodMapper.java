package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.po.Good;
import site.linkway.core.entity.po.Commodity;
import java.util.List;

//table name:good  entity:po.Good
@Mapper
public interface GoodMapper {
    /**
     * 插入物品
     * @param good
     * @return
     */
    int insert(Good good);

    /**
     * 更新物品信息 by goodId
     * @param good
     * @return
     */
    int update(Good good);

    /**
     * 删除物品 by goodId
     * @param good 商品id
     * @return
     */
    int delete(String good);

    /**
     * 检索商品By goodId
     * @param good
     * @return
     */
    Good select(Good good);

    /**
     * 随机检索商品
     * @param maxSize 最大数量
     * @return
     */
    List<Commodity> randomCommodities(int maxSize);

    /**
     * 检索商品 by goodId
     * @param goodId 商品id
     * @return
     */
    Commodity commodityByGoodId(String goodId);

    /**
     * 获得物品商品展示图id
     * @param goodId 物品id
     * @return
     */
    List<String> imgIdByGoodId(String goodId);
    double selectPriceByGoodId(String goodId);

    /**
     *
     * @param keyword 名称关键词
     * @param searchType 物品类型
     * @param minPrice 最小价格
     * @param maxPrice 最大价格
     * @param num 起点
     * @param size 大小
     * @param goodId 物品id
     * @return
     */
    List<Commodity> search(@Param("keyword") String keyword,
                           @Param("searchType") String searchType,
                           @Param("minPrice") double minPrice,
                           @Param("maxPrice") double maxPrice,
                           @Param("num") int num,
                           @Param("size") int size,
                           @Param("goodId") String goodId);

    /**
     * 获得分页总页数
     * @param keyword 名称关键词
     * @param searchType 物品类型
     * @param minPrice 最小价格
     * @param maxPrice 最大价格
     * @param goodId 物品id
     * @return
     */
    int searchPageCount(@Param("keyword") String keyword,
                        @Param("searchType") String searchType,
                        @Param("minPrice") double minPrice,
                        @Param("maxPrice") double maxPrice,
                        @Param("goodId") String goodId);
}
