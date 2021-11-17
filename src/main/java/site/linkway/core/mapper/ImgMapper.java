package site.linkway.core.mapper;

import site.linkway.core.entity.po.Img;

//table name:img  entity:po.Img
public interface ImgMapper {
    public int insert(Img img);
    public int update(Img img);
    public int delete(Img img);
    public Img select(Img img);
}
