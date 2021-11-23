package site.linkway.core.controller;

/*用户个人信息模块*/
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import site.linkway.core.entity.po.User;
import site.linkway.core.entity.vo.PersonalData;
import site.linkway.core.entity.vo.StatusResult;
import site.linkway.core.service.UserDataService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

/*用户个人信息*/
@Controller
@RequestMapping("/api")
public class UserData {
    static Logger logger= Logger.getLogger(UserData.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public void setUserDataService(UserDataService userDataService) {
        this.userDataService = userDataService;
    }
    private UserDataService userDataService;


    /*已经登陆的用户获得自己的个人信息*/
    @GetMapping(value = "/myData",produces = "application/json;charset=utf-8")
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


    /*已经登录的用户更新个人信息*/
    @PutMapping(value = "/myData",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateMyData(@NonNull HttpSession httpSession,
                               String name,
                               String sex) throws JsonProcessingException {
        StatusResult statusResult=new StatusResult();
        String email=(String)httpSession.getAttribute("id");
        /*昵称或者性别可能为空 需要处理*/
        boolean result=userDataService.updateUserData(email,name,sex);
        statusResult.setResult(result);
        return mapper.writeValueAsString(statusResult);
    }

    /*更新头像*/
    @PutMapping(value = "/myHeadImg",produces = "application/json;charset=utf-8")
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
        int fileSize= (int)file.getSize();
        boolean updateResult=userDataService.updateHeadImg(email, is, fileSize, fileType);
        statusResult.setResult(updateResult);
        is.close();
        return mapper.writeValueAsString(statusResult);
    }

}
