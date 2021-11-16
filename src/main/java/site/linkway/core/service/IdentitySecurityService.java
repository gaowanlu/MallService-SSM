package site.linkway.core.service;

public interface IdentitySecurityService {
    /*检查账号密码是否匹配*/
    boolean checkIdPassword(String id,String password);
    /*修改密码*/
    boolean changePassword(String id,String newPassword);
    /*发送邮箱验证码*/
    String sendEmailCode(String id);
    /*注册账号*/
    boolean register(String email,String password);
}
