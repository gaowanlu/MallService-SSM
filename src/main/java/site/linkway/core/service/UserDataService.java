package site.linkway.core.service;

import site.linkway.core.entity.po.User;

import java.io.InputStream;

public interface UserDataService {
    /*获取账号个人信息*/
    public User getUserByEmail(String email);
    /*用户更新昵称或者性别*/
    public boolean updateUserData(String email,String name,String sex);
    /*更新头像*/
    public boolean updateHeadImg(InputStream inputStream,String fileSize,String fileType);
}
