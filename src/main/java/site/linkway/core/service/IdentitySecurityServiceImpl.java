package site.linkway.core.service;

import jodd.mail.EmailAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.linkway.core.entity.po.User;
import site.linkway.core.mapper.UserMapper;
import site.linkway.utils.EmailUtils;
import site.linkway.utils.RandomString;
import site.linkway.utils.UUIDUtils;

@Service
public class IdentitySecurityServiceImpl implements IdentitySecurityService{
    @Autowired
    public IdentitySecurityServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    private UserMapper userMapper;

    @Override
    public boolean checkIdPassword(String email, String password) {
        User user=new User();
        user.email=email;
        user.password=password;
        user=userMapper.selectByEmailAndPassword(user);
        return user!=null;
    }

    @Override
    @Transactional
    public boolean changePassword(String email, String newPassword) {
        User user=new User();
        user.email=email;
        user=userMapper.select(user);
        user.password=newPassword;
        return 1==userMapper.update(user);
    }

    @Override
    public String sendEmailCode(String email) {
        String code= RandomString.CreateVerificationCode();
        EmailUtils.SendVerificationCode(email,"邮箱验证",code,"邮箱验证");
        return code;
    }

    @Override
    public boolean register(String email, String password) {
        User newUser=new User();
        newUser.password=password;
        newUser.userId= UUIDUtils.getUUID();
        newUser.email=email;
        newUser.headImgId="";
        newUser.money=0.0d;
        newUser.name="未知";
        newUser.sex="男";
        int line=userMapper.insert(newUser);
        return line==1;
    }
}
