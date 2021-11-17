package site.linkway.core.controller;

/*用户个人信息模块*/
/*
* 已登录用户获得自己的相关个人信息
* /apis/getMyData
*
*
* */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import site.linkway.core.entity.po.User;
import site.linkway.core.entity.vo.PersonalData;
import site.linkway.core.entity.vo.StatusResult;
import site.linkway.core.service.UserDataService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

/*用户个人信息
*
* 获得个人信息
* /api/getMyData
* 更新性别或者昵称
* /api/updateMyData?sex={}&name={}
* 更新头像
* /api/updateHeadImg    表单文件属性 "file":file
* */
@Controller
@RequestMapping("/api")
public class UserData {
    static Logger logger= Logger.getLogger(UserData.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    @Qualifier("UserDataServiceImpl")
    private UserDataService userDataService;

    /*已经登陆的用户获得自己的个人信息
    * id name sex headImgId money email
    * */
    @RequestMapping(value = "/getMyData",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getSelfData(@NonNull HttpSession httpSession)
            throws JsonProcessingException {
        String email=(String)httpSession.getAttribute("id");
        User user=userDataService.getUserByEmail(email);
        //封装返回数据
        PersonalData personalData=new PersonalData();
        personalData.email=user.email;
        personalData.userId=user.userId;
        personalData.headImgId=user.headImgId;
        personalData.sex=user.sex;
        personalData.name=user.name;
        personalData.money=user.money;
        return mapper.writeValueAsString(personalData);
    }

    /*用户更新信息
    * 已经登陆的用户更新name sex
    * */
    @RequestMapping(value = "/updateMyData",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateMyData(@NonNull HttpSession httpSession,
                               String name,
                               String sex) throws JsonProcessingException {
        StatusResult statusResult=new StatusResult();
        String email=(String)httpSession.getAttribute("id");
        boolean result=userDataService.updateUserData(email,name,sex);
        statusResult.setResult(result);
        return mapper.writeValueAsString(statusResult);
    }

    /*更新头像*/
    @RequestMapping(value = "/updateHeadImg",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateHeadImg(@NonNull HttpSession httpSession,
                                @NonNull @RequestParam("file") CommonsMultipartFile file
                                ) throws IOException {
        StatusResult statusResult=new StatusResult();
        //获得用户邮箱
        String email=(String)httpSession.getAttribute("id");
        //获得上传文件的文件名
        String uploadFileName = file.getOriginalFilename();
        //如果文件名为空,则直接返回
        if("".equals(uploadFileName)){
            return mapper.writeValueAsString(statusResult);
        }
        InputStream is = file.getInputStream(); //文件输入流
        //获得文件大小
        String fileType=file.getContentType();
        String fileSize=String.valueOf(file.getSize());
        boolean updateResult=userDataService.updateHeadImg(is,fileSize,fileType);
        statusResult.setResult(updateResult);
        is.close();
        return mapper.writeValueAsString(statusResult);
    }

}
