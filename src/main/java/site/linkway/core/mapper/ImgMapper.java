package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.linkway.core.entity.po.Img;


@Mapper
public interface ImgMapper {
    /**
     * 插入新的图片
     * @param img 图片
     * @return 插入行
     */
    int insert(Img img);

    /**
     * 更新图片二进制数据
     * @param img 图片
     * @return 更新行
     */
    int update(Img img);

    /**
     * 删除图片 by imgId
     * @param img 图片
     * @return 删除行
     */
    int delete(Img img);

    /**
     * 检索图片 by imgId
     * @param img 图片
     * @return 图片
     */
    Img select(Img img);
}
