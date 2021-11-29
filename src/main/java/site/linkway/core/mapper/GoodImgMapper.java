package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.linkway.core.entity.po.GoodImg;

//table name:goodImg  entity:po.GoodImg
@Mapper
public interface GoodImgMapper {
    int insert(GoodImg goodImg);
    int update(GoodImg goodImg);
    int delete(GoodImg goodImg);
    GoodImg select(GoodImg goodImg);
}
