package site.linkway.core.mapper;

import site.linkway.core.entity.po.Good;

//table name:good  entity:po.Good
public interface GoodMapper {
    public int insert(Good good);
    public int update(Good good);
    public int delete(Good good);
    public Good select(Good good);
}
