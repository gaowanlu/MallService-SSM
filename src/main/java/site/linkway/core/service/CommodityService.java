package site.linkway.core.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import site.linkway.core.entity.bo.PostCommodity;
import site.linkway.core.entity.po.Commodity;
import site.linkway.core.entity.po.Good;
import site.linkway.core.entity.po.GoodType;
import site.linkway.core.entity.vo.CommodityTipList;

import java.io.IOException;
import java.util.List;

public interface CommodityService {
    /*随机推荐商品列表*/
    CommodityTipList randomSelectCommodity(int maxSize);
    /*根据商品Id获取商品详情*/
    Commodity selectCommodityByGoodId(String goodId);
    //获取商品列表
    List<GoodType> goodTypes();
    //新增商品
    String addNewCommodity(PostCommodity postCommodity) throws IOException;
    //更新商品属性(相关文字信息)
    boolean updateCommodityText(Good good);
    //为商品添加图像
    boolean addCommodityImg(String goodId, CommonsMultipartFile files[]) throws IOException;
    //删除商品的某张图像
    boolean deleteCommodityImg(String imgId);
    //删除商品
    boolean deleteCommodity(String goodId);
    //更新商品详情图片
    boolean updateDetailsImg(String goodId,CommonsMultipartFile detailsImg) throws IOException;
}
