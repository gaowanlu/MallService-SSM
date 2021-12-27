package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.linkway.core.entity.po.GoodImg;
import java.util.List;
//table name:goodImg  entity:po.GoodImg
@Mapper
public interface GoodImgMapper {
    /**
     * 插入新物品展示图片
     * @param goodImg 展示图
     * @return 更新行
     */
    int insert(GoodImg goodImg);

    /**
     * 根据物品id检索展示图
     * @param goodId 物品id
     * @return 展示图id
     */
    List<String> selectImgIdByGoodId(String goodId);

    /**
     * 删除物品展示图
     * @param imgId 图片id
     * @return 删除行
     */
    int deleteByImgId(String imgId);
}
