package site.linkway.core.mapper;

import site.linkway.core.entity.po.User;

//table name:user  entity:po.User
public interface UserMapper {
    int insert(User user);
    int update(User user);
    int delete(User user);
    User select(User user);//by email
    User selectByEmailAndPassword(User user);
}
