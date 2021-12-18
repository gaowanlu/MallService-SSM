package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.po.GoodType;

import java.util.List;

//table name:goodType  entity:po.GoodType
@Mapper
public interface GoodTypeMapper {
    List<GoodType> selectAll();
    int insert(String name);
    int update(@Param("goodTypeId") int goodTypeId,@Param("name") String name);
    GoodType selectByName(String name);
}
