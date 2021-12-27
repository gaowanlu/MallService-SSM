package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.po.User;

//table name:user  entity:po.User
@Mapper
public interface UserMapper {
    /**
     * 插入用户
     * @param user 用户
     * @return 插入行
     */
    int insert(User user);

    /**
     * 用户信息更新
     * @param user 用户
     * @return 更新行
     */
    int update(User user);

    /**
     * 删除用户 by userId
     * @param user
     * @return
     */
    int delete(User user);

    /**
     * 检索用户by email
     * @param user 用户
     * @return 用户
     */
    User select(User user);//by email

    /**
     * 用户检索by userId
     * @param userId 用户userId
     * @return 用户
     */
    User selectByUserId(String userId);

    /**
     * 检索用户 by email and password
     * @param user
     * @return 用户
     */
    User selectByEmailAndPassword(User user);

    /**
     * 检索用户 by email
     * @param email 邮箱
     * @return 用户
     */
    String selectIdByEmail(String email);

    /**
     * 更新头像id
     * @param email 邮箱
     * @param headImg 图片id
     * @return 更新行
     */
    int updateHeadImg(@Param("email") String email,@Param("headImg") String headImg);

    /**
     * 更新用户余额
     * @param email 邮箱
     * @param money 余额
     * @return 更新行
     */
    int updateMoney(@Param("email") String email,@Param("money") double money);
}
