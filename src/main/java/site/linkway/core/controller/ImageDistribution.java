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
import site.linkway.core.service.ImageService;
import site.linkway.core.service.UserDataService;
import site.linkway.utils.ResizeImg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

/*图片分发模块
* 获得图片
* /imgapi?imgId={}
* */
@Controller
public class ImageDistribution {
    static Logger logger= Logger.getLogger(ImageDistribution.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    @Qualifier("ImageServiceImpl")
    private ImageService imageService;

    /*获取图像*/
    @RequestMapping(value = "/imgapi",produces = "application/json;charset=utf-8")
    public void img(@NonNull String imgId,
                    @NonNull HttpServletResponse httpServletResponse
                    ) throws Exception {
//        imgId varchar(32) PK
//        imgType varchar(20)
//        imgSize int un
//        img mediumblob
        Map<String,Object> result=imageService.selectImgById(imgId);
        InputStream in = new ByteArrayInputStream((byte[]) result.get("img"));
        long filesize=Long.valueOf((String)result.get("imgSize"));
        String filetype=(String) result.get("imgType");
        httpServletResponse.reset();
        //图像缩放并返回至客户端
        ResizeImg.zoomImage(httpServletResponse,in,500,filesize,filetype);
    }
}
