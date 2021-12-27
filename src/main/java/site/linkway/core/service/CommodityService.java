package site.linkway.core.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import site.linkway.core.entity.bo.PostCommodity;
import site.linkway.core.entity.po.*;
import site.linkway.core.entity.vo.CommodityTipList;
import site.linkway.utils.UUIDUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface CommodityService {
    /**
     * 随机推荐商品列表
     * @param maxSize 大小
     * @return 商品推荐列表
     */
    CommodityTipList randomSelectCommodity(int maxSize);

    /**
     * 根据商品Id获取商品详情
     * @param goodId 商品id
     * @return 商品
     */
    Commodity selectCommodityByGoodId(String goodId);

    /**
     * 获取商品类型列表
     * @return 商品类型列表
     */
    List<GoodType> goodTypes();

    /**
     * 新增商品
     * @param postCommodity 提交商品信息
     * @return UUID or "false"
     * @throws IOException
     */
    String addNewCommodity(PostCommodity postCommodity) throws IOException;

    /**
     * 更新商品属性 相关文字信息
     * @param good 商品
     * @return true or false
     */
    boolean updateCommodityText(Good good);

    /**
     * 为商品追加展示图
     * @param goodId 商品id
     * @param files 文件
     * @return true or false
     * @throws IOException
     */
    boolean addCommodityImg(String goodId, CommonsMultipartFile files[]) throws IOException;

    /**
     * 删除商品的某张图像
     * @param imgId 图片id
     * @return 删除结果
     */
    boolean deleteCommodityImg(String imgId);

    /**
     * 删除商品
     * @param goodId 物品id
     * @return 删除结果
     */
    boolean deleteCommodity(String goodId);

    /**
     * 更新商品的详情图片
     * @param goodId 物品id
     * @param detailsImg 详情图片
     * @return 更新结果
     * @throws IOException
     */
    boolean updateDetailsImg(String goodId,CommonsMultipartFile detailsImg) throws IOException;
}