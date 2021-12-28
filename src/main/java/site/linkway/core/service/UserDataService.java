package site.linkway.core.service;

import org.apache.ibatis.annotations.Param;
import site.linkway.core.entity.po.User;

import java.io.InputStream;

public interface UserDataService {

    /**
     * 根据邮箱获取用户信息
     * @param email 邮箱
     * @return 用户
     */
    User getUserByEmail(String email);

    /**
     * 通过邮箱获取用户 id
     * @param email 邮箱
     * @return 用户 id
     */
    String getUserIdByEmail(String email);

    /**
     * 更新性别 昵称
     * @param email 邮箱
     * @param name 昵称
     * @param sex 性别
     * @return 更新结果
     */
    boolean updateUserData(String email, String name, String sex);

    /**
     * 更新头像
     * @param email 邮箱
     * @param inputStream 文件输入流
     * @param fileSize 文件大小
     * @param fileType 文件类型
     * @return 更新结果
     */
    boolean updateHeadImg(String email, InputStream inputStream, int fileSize, String fileType);

    /**
     * 充值 金额可正可负
     * @param email 邮箱
     * @param amount 充值金额
     * @return 充值结果
     */
    boolean recharge(@Param("email") String email,@Param("amount") double amount);
}
