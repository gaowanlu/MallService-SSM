package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.po.Good;
import site.linkway.core.entity.po.Commodity;
import java.util.List;

//table name:good  entity:po.Good
@Mapper
public interface GoodMapper {
    int insert(Good good);
    int update(Good good);
    int delete(Good good);
    Good select(Good good);
    List<Commodity> randomCommodities(int maxSize);
    Commodity commodityByGoodId(String goodId);
    List<String> imgIdByGoodId(String goodId);
    double selectPriceByGoodId(String goodId);
    /*搜索*/
    List<Commodity> search(@Param("keyword") String keyword,
                           @Param("searchType") String searchType,
                           @Param("minPrice") double minPrice,
                           @Param("maxPrice") double maxPrice,
                           @Param("num") int num,
                           @Param("size") int size,
                           @Param("goodId") String goodId);
    /*搜索分页统计页数*/
    int searchPageCount(@Param("keyword") String keyword,
                        @Param("searchType") String searchType,
                        @Param("minPrice") double minPrice,
                        @Param("maxPrice") double maxPrice,
                        @Param("goodId") String goodId);
}
