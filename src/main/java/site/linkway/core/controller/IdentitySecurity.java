package site.linkway.core.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.linkway.core.entity.vo.StatusResult;
import site.linkway.core.service.IdentitySecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*身份安全模块*/
/* 登录
* /IdentitySecurity/login?id={email}&password={password}
*  注册账号
* /IdentitySecurity/register?password={}&emailCode={}
*  发送验证码
* /IdentitySecurity/sendEmailCode?email={}
*  修改密码
* /IdentitySecurity/changePassword?newPassword={}&emailCode={}
* */
@Controller
@RequestMapping(value = "/IdentitySecurity")
public class IdentitySecurity {
    static Logger logger= Logger.getLogger(IdentitySecurity.class);
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    @Qualifier("IdentitySecurityServiceImpl")
    private IdentitySecurityService identitySecurityService;


    /*登录*/
    @RequestMapping(value = "/login",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(@NonNull String id, @NonNull String password,
                        HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse) throws JsonProcessingException {
        /*seesion判断 是否存在id 判断是否为登录状态*/
        HttpSession httpSession=httpServletRequest.getSession();
        String sessionAttrId=(String)httpSession.getAttribute("id");
        StatusResult statusResult=new StatusResult();
        if(sessionAttrId!=null && !sessionAttrId.equals("")){
            //直接返回StatusResult
            return mapper.writeValueAsString(statusResult);
        }else{
            //进行登录相关操作
            if(identitySecurityService.checkIdPassword(id,password)){
                //身份验证成功
                httpSession.setAttribute("id",id);
            }else{
                //身份验证失败
                statusResult.setResult(false);
            }
        }
        return mapper.writeValueAsString(statusResult);
    }

    /*注册账号*/
    @RequestMapping(value = "/register",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String register(@NonNull String password,
                            @NonNull String emailCode,
                            @NonNull HttpSession httpSession) throws JsonProcessingException {
        String sessionAttributeEmail=(String)httpSession.getAttribute("email");
        String sessionAttributeEmailCode=(String)httpSession.getAttribute("emailCode");
        StatusResult statusResult=new StatusResult();
        /*邮箱验证码校验*/
        if(sessionAttributeEmail==null||sessionAttributeEmailCode==null||!emailCode.equals(sessionAttributeEmailCode)){
            statusResult.setResult(false);
            return mapper.writeValueAsString(statusResult);
        }else{
            //使用邮箱与邮箱验证码进行账号的初步注册
            statusResult.setResult(identitySecurityService.register(sessionAttributeEmail,password));
            /*使验证码失效*/
            httpSession.removeAttribute("emailCode");
        }
        return mapper.writeValueAsString(statusResult);
    }

    /*邮箱验证码身份验证::根据邮箱发送验证码*/
    @RequestMapping(value = "/sendEmailCode",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sendEmailCode(@NonNull String email,
                                 @NonNull HttpSession httpSession) throws JsonProcessingException {
        StatusResult statusResult=new StatusResult();
        /*发送验证码 并将邮箱与验证码存进session*/
        String emailCode=identitySecurityService.sendEmailCode(email);
        httpSession.setAttribute("emailCode",emailCode);
        httpSession.setAttribute("email",email);
        return mapper.writeValueAsString(statusResult);
    }

    /*修改密码*/
    @RequestMapping(value = "/changePassword",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String changePassword(@NonNull String emailCode,
                                  @NonNull String newPassword,
                                  @NonNull HttpSession httpSession) throws JsonProcessingException {
        String sessionAttributeEmail=(String)httpSession.getAttribute("email");
        String sessionAttributeEmailCode=(String)httpSession.getAttribute("emailCode");
        StatusResult statusResult=new StatusResult();
        /*邮箱验证码校验*/
        if(sessionAttributeEmail==null||sessionAttributeEmailCode==null||!emailCode.equals(sessionAttributeEmailCode)){
            statusResult.setResult(false);
            return mapper.writeValueAsString(statusResult);
        }else{
            //使用邮箱与邮箱验证码进行账号的初步注册
            statusResult.setResult(identitySecurityService.changePassword(sessionAttributeEmail,newPassword));
            /*将验证码失效*/
            httpSession.removeAttribute("emailCode");
        }
        return mapper.writeValueAsString(statusResult);
    }
}
