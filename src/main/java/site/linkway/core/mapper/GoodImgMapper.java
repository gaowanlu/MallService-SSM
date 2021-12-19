package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.linkway.core.entity.po.GoodImg;
import java.util.List;
//table name:goodImg  entity:po.GoodImg
@Mapper
public interface GoodImgMapper {
    int insert(GoodImg goodImg);
    List<String> selectImgIdByGoodId(String goodId);
    int deleteByImgId(String imgId);
}
