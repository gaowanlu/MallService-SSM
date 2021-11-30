package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.linkway.core.entity.po.User;

//table name:user  entity:po.User
@Mapper
public interface UserMapper {
    int insert(User user);
    int update(User user);
    int delete(User user);
    User select(User user);//by email
    User selectByEmailAndPassword(User user);
    String selectIdByEmail(String email);
}
