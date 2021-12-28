package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.po.GoodType;

import java.util.List;

//table name:goodType  entity:po.GoodType
@Mapper
public interface GoodTypeMapper {
    /**
     * 获取所有商品类型
     * @return 商品类型列表
     */
    List<GoodType> selectAll();

    /**
     * 插入新的商品类型
     * @param name 商品类型名称
     * @return
     */
    int insert(String name);

    /**
     * 更新商品类型项
     * @param goodTypeId 类型id
     * @param name 类型名称
     * @return 更新行
     */
    int update(@Param("goodTypeId") int goodTypeId,@Param("name") String name);

    /**
     * 根据类型名称检索商品类型
     * @param name 商品类型名称
     * @return 商品类型
     */
    GoodType selectByName(String name);
}
