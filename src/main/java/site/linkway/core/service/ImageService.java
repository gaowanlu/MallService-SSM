package site.linkway.core.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.List;

public interface ImageService {
    Map<String,Object> selectImgById(String imgId);
    /*存储图片*/
    List<String> storeImg(CommonsMultipartFile files[]) throws IOException;
}
