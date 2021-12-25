package site.linkway.core.entity.bo;

import lombok.Data;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Data
public class PostCommodity {
    double price;
    String name;
    String profile;
    int stock;
    int goodTypeId;
    int onSale;
    String detail;
    String file[];//图片的Id
    CommonsMultipartFile detailImg;
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
