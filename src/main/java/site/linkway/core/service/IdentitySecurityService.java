package site.linkway.core.service;

public interface IdentitySecurityService {
    /**
     * 检查账号密码是否匹配
     * @param email 邮箱
     * @param password 密码
     * @return 匹配结果
     */
    boolean checkIdPassword(String email,String password);

    /**
     * 修改密码
     * @param email 邮箱
     * @param newPassword 新密码
     * @return 修改结果
     */
    boolean changePassword(String email,String newPassword);

    /**
     * 检查是否是管理员
     * @param userId 用户 id
     * @return 是否是管理员
     */
    boolean checkIsAdmin(String userId);

    /**
     * 发送验证码
     * @param email 邮箱
     * @return 验证码
     */
    String sendEmailCode(String email);

    /**
     * 注册账号
     * @param email 邮箱
     * @param password 密码
     * @return 注册结果
     */
    boolean register(String email,String password);
}
