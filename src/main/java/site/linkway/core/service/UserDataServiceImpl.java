package site.linkway.core.service;

import jodd.buffer.FastBooleanBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.linkway.core.entity.po.Img;
import site.linkway.core.entity.po.User;
import site.linkway.core.mapper.ImgMapper;
import site.linkway.core.mapper.UserMapper;

import java.io.InputStream;
import java.util.UUID;

@Service
public class UserDataServiceImpl implements UserDataService {
    /*构造器注入 也可以使用 setter注入 为了看起来简介 使用构造器注入*/
    @Autowired
    public UserDataServiceImpl(UserMapper userMapper, ImgMapper imgMapper) {
        this.userMapper = userMapper;
        this.imgMapper = imgMapper;
    }
    private UserMapper userMapper;
    private ImgMapper imgMapper;

    /*根据邮箱获得用户信息*/
    @Override
    public User getUserByEmail(String email) {
        var user = new User();
        user.email = email;
        return userMapper.select(user);
    }

    /*更新昵称 性别*/
    @Override
    @Transactional
    public boolean updateUserData(String email, String name, String sex) {
        var user = getUserByEmail(email);
        if (user == null) return false;

        user.email = email;
        return 1 == userMapper.update(user);
    }

    /*更新头像*/
    @Override
    @Transactional
    public boolean updateHeadImg(String email, InputStream inputStream, int fileSize, String fileType) {
        var user = getUserByEmail(email);
        if (user == null) return false;

        var img = new Img();
        img.imgId = UUID.randomUUID().toString();
        img.imgType = fileType;
        img.imgSize = fileSize;
        img.img = inputStream;
        if (imgMapper.insert(img) != 1) return false;

        user.headImgId = img.imgId;
        return 1 == userMapper.update(user);
    }
}
