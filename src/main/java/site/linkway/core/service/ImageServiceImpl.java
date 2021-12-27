package site.linkway.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import site.linkway.core.entity.po.Img;
import site.linkway.core.mapper.ImgMapper;
import site.linkway.utils.UUIDUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService{
    //构造器注入
    @Autowired
    public ImageServiceImpl(ImgMapper imgMapper) {
        this.imgMapper = imgMapper;
    }
    private ImgMapper imgMapper;

    /**
     * 获得图片 by imgId
     *
     * @param imgId 图片id
     * @return <key,Object>
     */
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

    /**
     * 图片存储
     *
     * @param files 文件列表
     * @return UUID imgId 列表
     * @throws IOException
     */
    @Override
    public List<String> storeImg(CommonsMultipartFile[] files) throws IOException {
        List<String> result=new ArrayList<>();
        //遍历所有文件,存储图片并把每个图片的Id存储起来
        for(CommonsMultipartFile file:files){
            InputStream is = file.getInputStream(); //文件输入流
            String fileType=file.getContentType();//文件类型
            int fileSize= (int)file.getSize();//获得文件大小
            String imgId=UUIDUtils.getUUID();//图片ID
            Img img=new Img(imgId,fileType,fileSize,is);
            if(1!=imgMapper.insert(img)){
                return result;
            }
            result.add(imgId);
        }
        return result;
    }
}
