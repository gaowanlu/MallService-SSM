package site.linkway.core.mapper;

import site.linkway.core.entity.po.GoodType;

//table name:goodType  entity:po.GoodType
public interface GoodTypeMapper {
    int insert(GoodType goodType);
    int update(GoodType goodType);
    int delete(GoodType goodType);
    GoodType select(GoodType goodType);
}
