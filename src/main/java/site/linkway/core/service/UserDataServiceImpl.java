package site.linkway.core.service;

import site.linkway.core.entity.po.User;

import java.io.InputStream;

public class UserDataServiceImpl implements UserDataService{
    /*根据邮箱获得用户信息*/
    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    /*更新昵称 性别*/
    @Override
    public boolean updateUserData(String email, String name, String sex) {
        return false;
    }

    /*更新头像*/
    @Override
    public boolean updateHeadImg(InputStream inputStream, String fileSize, String fileType) {
        return false;
    }
}
