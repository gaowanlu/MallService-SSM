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

    /**
     * 检查账号密码是否匹配
     *
     * @param email    邮箱
     * @param password 密码
     * @return 匹配结果
     */
    @Override
    public boolean checkIdPassword(String email, String password) {
        User user = new User();
        user.email = email;
        user.password = password;
        user = userMapper.selectByEmailAndPassword(user);
        return user != null;
    }

    /**
     * 修改密码
     *
     * @param email       邮箱
     * @param newPassword 新密码
     * @return 修改结果
     */
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
     * @param userId 用户 id
     * @return 是否是管理员
     */
    @Override
    public boolean checkIsAdmin(String userId) {
        Admin admin = adminMapper.selectByUserId(userId);
        return null!=admin;
    }

    /**
     * 发送验证码
     *
     * @param email 邮箱
     * @return 验证码
     */
    @Override
    public String sendEmailCode(String email) {
        String code = RandomString.CreateVerificationCode();
        EmailUtils.SendVerificationCode(email, "邮箱验证", code, "邮箱验证");
        return code;
    }

    /**
     * 注册账号
     *
     * @param email    邮箱
     * @param password 密码
     * @return 注册结果
     */
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
