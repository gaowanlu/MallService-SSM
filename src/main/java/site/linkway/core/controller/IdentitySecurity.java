package site.linkway.core.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.linkway.core.entity.bo.TestRequestBody;
import site.linkway.core.entity.vo.ErrorResult;
import site.linkway.core.entity.vo.StatusResult;
import site.linkway.core.service.IdentitySecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*身份安全模块*/
/* 登录
 * /identitySecurity/login?id={email}&password={password}
 *  注册账号
 * /identitySecurity/register?password={}&emailCode={}
 *  发送验证码
 * /identitySecurity/sendEmailCode?email={}
 *  修改密码
 * /identitySecurity/changePassword?newPassword={}&emailCode={}
 * */
@Controller
@RequestMapping(value = "/identitySecurity")
public class IdentitySecurity {
    static Logger logger = Logger.getLogger(IdentitySecurity.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    @Qualifier("IdentitySecurityServiceImpl")
    private IdentitySecurityService identitySecurityService;

    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    HttpServletResponse httpServletResponse;
    @Autowired
    HttpSession httpSession;

    /*登录*/
    @PostMapping(value = "/login", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(@NonNull String id,
                        @NonNull String password,
                        @SessionAttribute(name = "id",required = false) String sessionAttrId
    ) throws JsonProcessingException {
        System.out.println(id+password);
        /*seesion判断 是否存在id 判断是否为登录状态*/
        StatusResult statusResult = new StatusResult();
        if (sessionAttrId!=null&&!sessionAttrId.equals("")) {
            //直接返回StatusResult
            return mapper.writeValueAsString(statusResult);
        }
        //进行登录相关操作
        if (identitySecurityService.checkIdPassword(id, password)) {
            //身份验证成功
            httpSession.setAttribute("id", id);
            return mapper.writeValueAsString(statusResult);
        }
        //身份验证失败
        httpServletResponse.setStatus(403);
        return mapper.writeValueAsString(new ErrorResult("用户名或密码错误"));
    }

    /*注册账号*/
    @PostMapping(value = "/register", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String register(@NonNull String password,
                           @NonNull String emailCode,
                           @NonNull @SessionAttribute(name="email") String sessionAttributeEmail,
                           @NonNull @SessionAttribute(name="emailCode") String sessionAttributeEmailCode
    ) throws JsonProcessingException {
        StatusResult statusResult = new StatusResult();
        /*邮箱验证码校验*/
        if (!emailCode.equals(sessionAttributeEmailCode)) {
            statusResult.setResult(false);
            return mapper.writeValueAsString(statusResult);
        }
        //使用邮箱与邮箱验证码进行账号的初步注册
        statusResult.setResult(identitySecurityService.register(sessionAttributeEmail, password));
        /*使验证码失效*/
        httpSession.removeAttribute("emailCode");
        return mapper.writeValueAsString(statusResult);
    }

    /*邮箱验证码身份验证::根据邮箱发送验证码*/
    @PostMapping(value = "/sendEmailCode", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sendEmailCode(@NonNull String email) throws JsonProcessingException {
        StatusResult statusResult = new StatusResult();
        /*发送验证码 并将邮箱与验证码存进session*/
        String emailCode = identitySecurityService.sendEmailCode(email);
        httpSession.setAttribute("emailCode", emailCode);
        httpSession.setAttribute("email", email);
        return mapper.writeValueAsString(statusResult);
    }

    /*修改密码*/
    @PostMapping(value = "/changePassword", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String changePassword(@NonNull String emailCode,
                                 @NonNull String newPassword,
                                 @NonNull @SessionAttribute(name="email") String sessionAttributeEmail,
                                 @NonNull @SessionAttribute(name="emailCode") String sessionAttributeEmailCode
    ) throws JsonProcessingException {

        StatusResult statusResult = new StatusResult();
        /*邮箱验证码校验*/
        if (!emailCode.equals(sessionAttributeEmailCode)) {
            statusResult.setResult(false);
            return mapper.writeValueAsString(statusResult);
        }
        //使用邮箱与邮箱验证码进行账号的初步注册
        statusResult.setResult(identitySecurityService.changePassword(sessionAttributeEmail, newPassword));
        /*将验证码失效*/
        httpSession.removeAttribute("emailCode");
        return mapper.writeValueAsString(statusResult);
    }


    @RequestMapping(value="/testRequestBody",
            method = {RequestMethod. POST },
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String testRequestBody(@RequestBody TestRequestBody testRequestBody) throws JsonProcessingException {
        System.out.println(testRequestBody);
        return mapper.writeValueAsString(testRequestBody);
    }
}