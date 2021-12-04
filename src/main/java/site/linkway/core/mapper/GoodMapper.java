package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
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
}
