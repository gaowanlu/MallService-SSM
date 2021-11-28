package site.linkway.core.service;

import site.linkway.core.entity.po.User;

import java.io.InputStream;

public interface UserDataService {
    /*根据邮箱获得用户信息*/
    User getUserByEmail(String email);

    /*更新昵称 性别*/
    boolean updateUserData(String email, String name, String sex);

    /*更新头像*/
    boolean updateHeadImg(String email, InputStream inputStream, int fileSize, String fileType);
}
