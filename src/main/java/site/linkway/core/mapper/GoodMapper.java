package site.linkway.core.mapper;

import site.linkway.core.entity.po.Good;

//table name:good  entity:po.Good
public interface GoodMapper {
    int insert(Good good);
    int update(Good good);
    int delete(Good good);
    Good select(Good good);
}
