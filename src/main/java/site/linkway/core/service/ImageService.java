package site.linkway.core.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.List;

public interface ImageService {
    /**
     * 获得图片 by imgId
     * @param imgId 图片id
     * @return <key,Object>
     */
    Map<String,Object> selectImgById(String imgId);

    /**
     * 图片存储
     * @param files 文件列表
     * @return UUID imgId 列表
     * @throws IOException
     */
    List<String> storeImg(CommonsMultipartFile files[]) throws IOException;
}
