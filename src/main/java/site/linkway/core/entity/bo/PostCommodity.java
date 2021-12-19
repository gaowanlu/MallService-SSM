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
    CommonsMultipartFile file[];
    public PostCommodity(double price, String name, String profile, int stock, int goodTypeId, int onSale, CommonsMultipartFile[] file) {
        this.price = price;
        this.name = name;
        this.profile = profile;
        this.stock = stock;
        this.goodTypeId = goodTypeId;
        this.onSale = onSale;
        this.file = file;
    }
    public PostCommodity(){

    }
}
