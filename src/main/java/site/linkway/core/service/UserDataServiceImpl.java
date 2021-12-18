package site.linkway.core.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.linkway.core.entity.po.Img;
import site.linkway.core.entity.po.User;
import site.linkway.core.mapper.ImgMapper;
import site.linkway.core.mapper.UserMapper;
import site.linkway.utils.UUIDUtils;

import java.io.InputStream;

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
        User user = new User();
        user.email = email;
        return userMapper.select(user);
    }

    /**
     * 通过邮箱获取用户 id
     *
     * @param email 邮箱
     * @return 用户 id
     */
    @Override
    public String getUserIdByEmail(String email) {
        val userId = userMapper.selectIdByEmail(email);
        return userId;
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

    /*更新头像 直接根据图片id进行对图片的二进制数据进行更新*/
    @Override
    @Transactional
    public boolean updateHeadImg(String email, InputStream inputStream, int fileSize, String fileType) {
        User user = getUserByEmail(email);
        if (user == null) return false;
        /*首先检索此用户是否已经存在自定义头像*/
        if(user.getUserId()==null||user.getHeadImgId().equals("")){
            //插入新的头像
            Img img=new Img(UUIDUtils.getUUID(),fileType,fileSize,inputStream);
            return 1==imgMapper.insert(img);
        }else{//根据头像id进行更新
            Img img=new Img(user.getHeadImgId(),fileType,fileSize,inputStream);
            return 1==imgMapper.update(img);
        }
    }

    /*充值 金额可正可负*/
    @Override
    @Transactional
    public boolean recharge(String email, double amount) {
        User user=new User();user.setEmail(email);
        user=userMapper.select(user);
        if(null==user){
            return false;
        }
        double balance=user.money;
        balance=balance+amount<0?0f:balance+amount;
        int line=userMapper.updateMoney(email,balance);
        return 1==line;
    }
}
