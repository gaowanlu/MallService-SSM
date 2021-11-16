package site.linkway.core.service;

public class IdentitySecurityServiceImpl implements IdentitySecurityService{

    @Override
    public boolean checkIdPassword(String id, String password) {
        return false;
    }

    @Override
    public boolean changePassword(String id, String newPassword) {
        return false;
    }

    @Override
    public String sendEmailCode(String id) {
        return "1234";
    }

    @Override
    public boolean register(String email, String password) {
        return false;
    }
}
