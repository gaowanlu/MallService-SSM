package site.linkway.core.mapper;

import site.linkway.core.entity.po.User;

//table name:user  entity:po.User
public interface UserMapper {
    public int insert(User user);
    public int update(User user);
    public int delete(User user);
    public User select(User user);
}
