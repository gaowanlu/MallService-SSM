package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImgUploadResult {
    boolean result=true;//操作结果
    List<String> imgId=new ArrayList<>();//图片id列表
    List<String> imgURL=new ArrayList<>();//图片URL列表
}
