package site.linkway.core.mapper;

import site.linkway.core.entity.po.FComment;

//table name:fComment  entity:po.FComment
public interface FCommentMapper {
    public int insert(FComment fComment);
    public int update(FComment fComment);
    public int delete(FComment fComment);
    public FComment select(FComment fComment);
}
