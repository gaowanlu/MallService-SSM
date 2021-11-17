package site.linkway.core.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//商品展示图
public class GoodImg {
    public String goodImgId;// varchar(32) PK
    public String imgId;// varchar(32)
    public String goodId;// varchar(32)
}
