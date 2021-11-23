package site.linkway.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.linkway.core.mapper.ImgMapper;

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
        return null;
    }
}
