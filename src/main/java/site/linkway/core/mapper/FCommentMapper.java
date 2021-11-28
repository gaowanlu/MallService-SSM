package site.linkway.core.mapper;

import site.linkway.core.entity.po.FComment;

//table name:fComment  entity:po.FComment
public interface FCommentMapper {
    int insert(FComment fComment);
    int update(FComment fComment);
    int delete(FComment fComment);
    FComment select(FComment fComment);
}
