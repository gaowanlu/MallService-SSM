package site.linkway.core.mapper;

import site.linkway.core.entity.po.GoodType;

//table name:goodType  entity:po.GoodType
public interface GoodTypeMapper {
    public int insert(GoodType goodType);
    public int update(GoodType goodType);
    public int delete(GoodType goodType);
    public GoodType select(GoodType goodType);
}
