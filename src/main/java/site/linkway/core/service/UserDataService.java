package site.linkway.core.service;

import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.po.User;

import java.io.InputStream;

public interface UserDataService {
    /*根据邮箱获得用户信息*/
    User getUserByEmail(String email);

    /**
     * 通过邮箱获取用户 id
     * @param email 邮箱
     * @return 用户 id
     */
    String getUserIdByEmail(String email);

    /*更新昵称 性别*/
    boolean updateUserData(String email, String name, String sex);

    /*更新头像*/
    boolean updateHeadImg(String email, InputStream inputStream, int fileSize, String fileType);

    /*充值 金额可正可负*/
    boolean recharge(@Param("email") String email,@Param("amount") double amount);
}
