package site.linkway.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import site.linkway.core.controller.ImageDistribution;
import site.linkway.core.entity.bo.PostCommodity;
import site.linkway.core.entity.po.*;
import site.linkway.core.entity.vo.CommodityTipList;
import site.linkway.core.mapper.GoodImgMapper;
import site.linkway.core.mapper.GoodMapper;
import site.linkway.core.mapper.GoodTypeMapper;
import site.linkway.core.mapper.ImgMapper;
import site.linkway.utils.UUIDUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    GoodMapper goodMapper;
    @Autowired
    GoodTypeMapper goodTypeMapper;
    @Autowired
    ImgMapper imgMapper;
    @Autowired
    GoodImgMapper goodImgMapper;

    /*随机推荐商品列表*/
    @Override
    public CommodityTipList randomSelectCommodity(int maxSize) {
        CommodityTipList result=new CommodityTipList();
        List<Commodity> commodityList=goodMapper.randomCommodities(maxSize);
        if(commodityList!=null){
            formatURIForCommodity(commodityList);
            result.setCommodities(commodityList);
        }
        return result;
    }

    /*根据商品Id获取商品详情*/
    @Override
    public Commodity selectCommodityByGoodId(String goodId) {
        Commodity commodity=goodMapper.commodityByGoodId(goodId);
        if(commodity!=null)
            formatURIForCommodity(commodity);
        return commodity;
    }

    //获取商品列表
    @Override
    public List<GoodType> goodTypes() {
        return goodTypeMapper.selectAll();
    }


    //新增商品
    @Override
    @Transactional
    public String addNewCommodity(PostCommodity postCommodity) throws IOException {
        //装配
        Good good=new Good();
        String UUID= UUIDUtils.getUUID();
        good.setName(postCommodity.getName());//商品名称
        good.setGoodId(UUID);//商品id
        good.setPrice(postCommodity.getPrice());//商品价格
        good.setProfile(postCommodity.getProfile());//商品简介
        good.setStock(postCommodity.getStock());//商品库存量
        good.setSoldSum(0);//初始销量为0
        good.setGoodTypeId(postCommodity.getGoodTypeId());//商品类型id
        good.setOnSale(postCommodity.getOnSale());//商品是否上架
        good.setDetail(postCommodity.getDetail());
        //插入商品
        int line=goodMapper.insert(good);
        if(1==line){//新增商品成功
            //插入商品展示图片
            String files[]=postCommodity.getFile();//获得管理员已经上传到平台图片的Id
            if(null==files){//file可以先不提交,为null则空数组，不添加图片
                files=new String[0];
            }
            for(String file:files){
                //将图片添加到goodImg
                GoodImg goodImg=new GoodImg(UUIDUtils.getUUID(),file,UUID);
                if(1!=goodImgMapper.insert(goodImg)){
                    return "false";
                }
            }
            //插入商品详情图片
            CommonsMultipartFile detailImg=postCommodity.getDetailImg();
            if(null!=detailImg){
                //插入商品详情图片
                InputStream is = detailImg.getInputStream(); //文件输入流
                String fileType=detailImg.getContentType();//文件类型
                int fileSize= (int)detailImg.getSize();//获得文件大小
                String imgId=UUIDUtils.getUUID();//图片ID
                Img img=new Img(imgId,fileType,fileSize,is);
                if(1!=imgMapper.insert(img)){
                    return "false";
                }
                is.close();//更新good的detailImgId
                good.setDetailImgId(imgId);
                if(1!=goodMapper.update(good)){
                    return "false";
                }
            }
        }else{//商品项插入失败
            return "false";
        }
        return UUID;//如果成功则返回物品id
    }



    //商品相关文字信息更新
    @Override
    public boolean updateCommodityText(Good good) {
        return 1==goodMapper.update(good);
    }

    //为商品追加商品图片
    @Override
    public boolean addCommodityImg(String goodId,CommonsMultipartFile files[]) throws IOException {
        for(CommonsMultipartFile file:files){
            //获得文件相关属性
            InputStream is = file.getInputStream(); //文件输入流
            String fileType=file.getContentType();
            int fileSize= (int)file.getSize();
            String imgId=UUIDUtils.getUUID();//图片ID
            Img img=new Img(imgId,fileType,fileSize,is);
            if(1!=imgMapper.insert(img)){
                return false;
            }
            is.close();
            //将图片添加到goodImg
            GoodImg goodImg=new GoodImg(UUIDUtils.getUUID(),imgId,goodId);
            if(1!=goodImgMapper.insert(goodImg)){
                return false;
            }
        }
        return true;
    }

    //删除商品的图片
    @Override
    public boolean deleteCommodityImg(String imgId) {
        //先从goodImg中抹除
        int line=goodImgMapper.deleteByImgId(imgId);
        //再从img表中抹除
        if(1==line){
            Img img=new Img();img.setImgId(imgId);
            if(1==imgMapper.delete(img)){
                return true;
            }
        }
        return false;
    }

    //删除商品
    @Override
    public boolean deleteCommodity(String goodId) {
        return goodMapper.delete(goodId) == 1;
    }

    //更新商品详情图片
    @Override
    @Transactional
    public boolean updateDetailsImg(String goodId, CommonsMultipartFile detailsImg) throws IOException {
        Good good=new Good();good.setGoodId(goodId);
        good=goodMapper.select(good);
        InputStream is = detailsImg.getInputStream(); //文件输入流
        String fileType=detailsImg.getContentType();
        int fileSize= (int)detailsImg.getSize();
        if(good.getDetailImgId().equals("defaultDetail")){
            //插入
            String imgId=UUIDUtils.getUUID();//图片ID
            Img img=new Img(imgId,fileType,fileSize,is);
            if(1!=imgMapper.insert(img)){
                return false;
            }
            good.setDetailImgId(imgId);
            return 1==goodMapper.update(good);
        }else {
            //更新
            Img img = new Img(good.getDetailImgId(), fileType, fileSize, is);
            return 1 == imgMapper.update(img);
        }
    }


    //转换商品图片地址 由imgId到URL的转换
    private void formatURIForCommodity(Commodity commodity){
        //展示图
        List<String> imgs=commodity.getImgsURL();
        for(int i=0;i<imgs.size();i++){
            imgs.set(i,ImageDistribution.formatURLFromImgId(imgs.get(i)));
        }
        //详情图id=>URL
        commodity.setDetailsImg(ImageDistribution.formatURLFromImgId(commodity.getDetailsImg()));
    }

    private void formatURIForCommodity(List<Commodity> commodities){
        for(Commodity commodity:commodities){
            formatURIForCommodity(commodity);
        }
    }

}
