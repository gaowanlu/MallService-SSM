package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    int updateMoney(@Param("email") String email,@Param("money") double money);
}
