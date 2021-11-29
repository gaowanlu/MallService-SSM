package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.linkway.core.entity.po.FComment;

//table name:fComment  entity:po.FComment
@Mapper
public interface FCommentMapper {
    int insert(FComment fComment);
    int update(FComment fComment);
    int delete(FComment fComment);
    FComment select(FComment fComment);
}
