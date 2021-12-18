package site.linkway.core.service;

public interface IdentitySecurityService {
    /*检查账号密码是否匹配*/
    boolean checkIdPassword(String email,String password);
    /*修改密码*/
    boolean changePassword(String email,String newPassword);

    /**
     * 检查是否是管理员
     * @param userId 用户 id
     * @return 是否是管理员
     */
    boolean checkIsAdmin(String userId);
    /*发送邮箱验证码*/
    String sendEmailCode(String email);
    /*注册账号*/
    boolean register(String email,String password);
}
