package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Img {
    public String imgId;// 图片id
    public String imgType;// 图片类型
    public int imgSize;// 图片大小
    public InputStream img;// 图片输入流
}
