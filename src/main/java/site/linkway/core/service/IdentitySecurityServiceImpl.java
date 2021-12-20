package site.linkway.core.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.linkway.core.entity.po.Admin;
import site.linkway.core.entity.po.User;
import site.linkway.core.mapper.AdminMapper;
import site.linkway.core.mapper.UserMapper;
import site.linkway.utils.EmailUtils;
import site.linkway.utils.RandomString;
import site.linkway.utils.UUIDUtils;

@Service
public class IdentitySecurityServiceImpl implements IdentitySecurityService {
    //set 注入
    @Autowired
    public IdentitySecurityServiceImpl(UserMapper userMapper, AdminMapper adminMapper) {
        this.userMapper = userMapper;
        this.adminMapper = adminMapper;
    }

    private UserMapper userMapper;
    private AdminMapper adminMapper;

    /*根据邮箱与密码检查身份是否匹配*/
    @Override
    public boolean checkIdPassword(String email, String password) {
        User user = new User();
        user.email = email;
        user.password = password;
        user = userMapper.selectByEmailAndPassword(user);
        return user != null;
    }

    /*
     *更新用户密码
     *开启事务管理*/
    @Override
    @Transactional
    public boolean changePassword(String email, String newPassword) {
        User user = new User();
        user.email = email;
        user = userMapper.select(user);
        user.password = newPassword;
        return 1 == userMapper.update(user);
    }


    /**
     * 检查是否是管理员
     *
     * @param userId
     * @return
     * @author: fronz
     */
    @Override
    public boolean checkIsAdmin(String userId) {
//        System.out.println("userId:" + userId);
        Admin admin = adminMapper.selectByUserId(userId);
        return null!=admin;
    }

    /*参数为邮箱地址
     * 返回发送过去的验证码字符串*/
    @Override
    public String sendEmailCode(String email) {
        String code = RandomString.CreateVerificationCode();
        EmailUtils.SendVerificationCode(email, "邮箱验证", code, "邮箱验证");
        return code;
    }

    /*提供邮箱与密码添加新用户*/
    @Override
    public boolean register(String email, String password) {
        User newUser = new User();
        newUser.password = password;
        newUser.userId = UUIDUtils.getUUID();
        newUser.email = email;
        newUser.headImgId = "";
        //头像默认设为空字符串
        //如果用户请求此用户的头像 则返回事先准备好的平台默认头像
        newUser.money = 0.0d;
        newUser.name = "未知";
        newUser.sex = "男";
        int line = userMapper.insert(newUser);
        return line == 1;
    }
}
