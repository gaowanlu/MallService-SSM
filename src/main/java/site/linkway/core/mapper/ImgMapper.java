package site.linkway.core.mapper;

import site.linkway.core.entity.po.Img;

//table name:img  entity:po.Img
public interface ImgMapper {
    int insert(Img img);
    int update(Img img);
    int delete(Img img);
    Img select(Img img);
}
