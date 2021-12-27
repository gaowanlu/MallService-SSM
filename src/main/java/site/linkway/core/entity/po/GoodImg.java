package site.linkway.core.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//商品展示图
public class GoodImg {
    public String goodImgId;//展示图id
    public String imgId;// 图片id
    public String goodId;// 商品id
}
