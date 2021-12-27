package site.linkway.core.controller;

/*用户个人信息模块*/
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import site.linkway.core.entity.po.User;
import site.linkway.core.entity.vo.PersonalData;
import site.linkway.core.entity.vo.StatusResult;
import site.linkway.core.service.UserDataService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

/*用户个人信息*/
@Controller
@RequestMapping("/api/user")
public class UserData {
    static Logger logger= Logger.getLogger(UserData.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    HttpServletResponse httpServletResponse;
    @Autowired
    HttpSession httpSession;

    @Autowired
    public void setUserDataService(UserDataService userDataService) {
        this.userDataService = userDataService;
    }
    private UserDataService userDataService;


    /**
     * 已经登陆的用户获得自己的个人信息
     * @param email 用户邮箱
     * @return PersonalData
     * @throws JsonProcessingException
     */
    @GetMapping(value = "/myData",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getSelfData(@NonNull @SessionAttribute(name="id") String email)
            throws JsonProcessingException {

        User user=userDataService.getUserByEmail(email);
        //封装返回数据
        PersonalData personalData=new PersonalData(
                user.userId,
                user.name,
                user.sex,
                ImageDistribution.formatURLFromImgId(user.headImgId),
                user.money,
                user.email
        );
        return mapper.writeValueAsString(personalData);
    }


    /**
     * 已经登录的用户更新个人信息
     * @param name 姓名
     * @param sex 性别
     * @param email 用户邮箱
     * @return
     * @throws JsonProcessingException
     */
    @PutMapping(value = "/myData",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateMyData(@NonNull String name,
                               @NonNull String sex,
                               @NonNull @SessionAttribute(name="id") String email)
            throws JsonProcessingException {

        StatusResult statusResult=new StatusResult();
        /*昵称或者性别可能为空 需要处理*/
        boolean result=userDataService.updateUserData(email,name,sex);
        statusResult.setResult(result);
        return mapper.writeValueAsString(statusResult);
    }


    /**
     * 用户头像更新
     * @param email 用户邮箱
     * @param file 头像图片文件
     * @return StatusResult
     * @throws IOException
     */
    @PostMapping (value = "/avatar",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateHeadImg(@NonNull @SessionAttribute(name="id") String email,
                                @RequestParam(name = "file") CommonsMultipartFile file
                                ) throws IOException {

        StatusResult statusResult=new StatusResult();
        //获得上传文件的文件名
        String uploadFileName = file.getOriginalFilename();
        System.out.println(uploadFileName);
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
