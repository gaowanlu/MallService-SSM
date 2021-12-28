package site.linkway.core.entity.bo;

import lombok.Data;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Data
public class PostCommodity {
    double price;//商品价格
    String name;//名称
    String profile;//简介
    int stock;//库存
    int goodTypeId;//物品类别id
    int onSale;//是都为上架状态
    String detail;//详情文字
    String file[];//展示图片的Id
    CommonsMultipartFile detailImg;//详情图片

    public PostCommodity(double price,
                         String name,
                         String profile,
                         int stock,
                         int goodTypeId,
                         int onSale,
                         String detail,
                         String[] file,
                         CommonsMultipartFile detailImg) {
        this.price = price;
        this.name = name;
        this.profile = profile;
        this.stock = stock;
        this.goodTypeId = goodTypeId;
        this.onSale = onSale;
        this.detail = detail;
        this.file = file;
        this.detailImg=detailImg;
    }
    public PostCommodity(){

    }
}
