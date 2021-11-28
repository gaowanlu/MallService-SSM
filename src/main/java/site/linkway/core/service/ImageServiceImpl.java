package site.linkway.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.linkway.core.entity.po.Img;
import site.linkway.core.mapper.ImgMapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    public ImageServiceImpl(ImgMapper imgMapper) {
        this.imgMapper = imgMapper;
    }
    private ImgMapper imgMapper;

    @Override
    public Map<String, Object> selectImgById(String imgId) {
        Map<String,Object> result=new HashMap<>();
        Img find=new Img();
        find.setImgId(imgId);
        Img fined=imgMapper.select(find);//根据imgId进行检索
        result.put("imgType",fined.getImgType());
        result.put("imgSize",fined.getImgSize());
        result.put("img",fined.getImg());
        return result;
    }
}
