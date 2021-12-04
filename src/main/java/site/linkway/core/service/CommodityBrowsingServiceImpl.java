package site.linkway.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.linkway.core.controller.ImageDistribution;
import site.linkway.core.entity.po.Commodity;
import site.linkway.core.entity.vo.CommodityTipList;
import site.linkway.core.mapper.GoodMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityBrowsingServiceImpl implements CommodityBrowsingService {
    @Autowired
    GoodMapper goodMapper;



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

    @Override
    public Commodity selectCommodityByGoodId(String goodId) {
        Commodity commodity=goodMapper.commodityByGoodId(goodId);
        if(commodity!=null)
            formatURIForCommodity(commodity);
        return commodity;
    }

    private void formatURIForCommodity(Commodity commodity){
        List<String> imgs=commodity.getImgsURL();
        for(int i=0;i<imgs.size();i++){
            imgs.set(i,ImageDistribution.formatURLFromImgId(imgs.get(i)));
        }
    }

    private void formatURIForCommodity(List<Commodity> commodities){
        for(Commodity commodity:commodities){
            formatURIForCommodity(commodity);
        }
    }

}
