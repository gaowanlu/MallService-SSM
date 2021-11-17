package site.linkway.core.mapper;

import site.linkway.core.entity.po.SComment;

//table name:sComment  entity:po.SComment
public interface SCommentMapper {
    public int insert(SComment sComment);
    public int update(SComment sComment);
    public int delete(SComment sComment);
    public SComment select(SComment sComment);
}
