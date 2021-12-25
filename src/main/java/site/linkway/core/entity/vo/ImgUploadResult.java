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
    boolean result=true;
    List<String> imgId=new ArrayList<>();
    List<String> imgURL=new ArrayList<>();
}
