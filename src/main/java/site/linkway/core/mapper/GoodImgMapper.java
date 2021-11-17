package site.linkway.core.mapper;

import site.linkway.core.entity.po.GoodImg;

//table name:goodImg  entity:po.GoodImg
public interface GoodImgMapper {
    public int insert(GoodImg goodImg);
    public int update(GoodImg goodImg);
    public int delete(GoodImg goodImg);
    public GoodImg select(GoodImg goodImg);
}
