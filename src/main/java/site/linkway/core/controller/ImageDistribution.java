package site.linkway.core.controller;

import lombok.NonNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import site.linkway.core.service.ImageService;
import site.linkway.utils.ResizeImg;


import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

/*图片分发*/
@Controller
public class ImageDistribution {
    static Logger logger= Logger.getLogger(ImageDistribution.class);

    @Autowired
    private ImageService imageService;

    /*获取图像*/
    @GetMapping(value = "/imgApi")
    public void img(@NonNull String imgId,
                    @NonNull HttpServletResponse httpServletResponse
                    ) throws Exception {
        Map<String,Object> result=imageService.selectImgById(imgId);
        InputStream in = new ByteArrayInputStream((byte[]) result.get("img"));
        long filesize=Long.valueOf((String)result.get("imgSize"));
        String filetype=(String) result.get("imgType");
        httpServletResponse.reset();
        /*
        *图像缩放并返回至客户端
        *在使用图像缩放时可能导致 图片过小 如果出现了 图片上传成功但是获取图片
        * 一直失败 就要注意了 可能是这里图片缩放的问题 可以将其放松要求
        * 再进行尝试
        * */
        ResizeImg.zoomImage(httpServletResponse,in,500,filesize,filetype);
    }

    /*格式化URI*/
    public static String formatURLFromImgId(String imgId){
        return "/imgApi?imgId="+imgId;
    }

}
