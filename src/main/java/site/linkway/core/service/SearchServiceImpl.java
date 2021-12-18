package site.linkway.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.linkway.core.controller.ImageDistribution;
import site.linkway.core.entity.bo.PostSearch;
import site.linkway.core.entity.po.Commodity;
import site.linkway.core.entity.vo.CommoditySearchResult;
import site.linkway.core.mapper.GoodMapper;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{
    @Autowired
    GoodMapper goodMapper;

    @Override
    public CommoditySearchResult commoditySearch(PostSearch postSearch) {
        CommoditySearchResult commoditySearchResult=new CommoditySearchResult();
        /*限制分页大小与现在在那一页*/
        int pageNow=postSearch.getPageNow();
        pageNow=pageNow>=1?pageNow:1;
        int pageSize=postSearch.getPageSize();
        pageSize=pageSize>=1&&pageSize<=20?pageSize:20;
        /*将keyword与searchType两端加上通配符%*/
        String keyword=postSearch.getKeyword().replace(" ","");
        if(!keyword.equals("")){
            keyword="%"+keyword+"%";
        }else{
            keyword=null;
        }
        String searchType=postSearch.getSearchType().replace(" ","");
        if(!searchType.equals("")){
            searchType="%"+searchType+"%";
        }else{
            searchType=null;
        }
        /*最小价格与最大价格*/
        double minPrice=postSearch.getPrice().getMin();
        double maxPrice=postSearch.getPrice().getMax();
        /*商品Id*/
        String goodId=postSearch.getGoodId();
        goodId=goodId.equals("")?null:goodId;
        /*设置分页信息*/
        commoditySearchResult.setPageSize(pageSize);
        commoditySearchResult.setPageNow(pageNow);
        /*搜索*/
        List<Commodity> commodityList=goodMapper.search(keyword,searchType,minPrice,maxPrice,((pageNow-1)*pageSize),pageSize,goodId);
        /*转换商品图片URL*/
        for(Commodity commodity:commodityList){
            commodity.setImgsURL(ImageDistribution.formatURLFromImgId(commodity.getImgsURL()));
        }
        commoditySearchResult.setCommodities(commodityList);
        /*统计分页总页数*/
        int pageCount=goodMapper.searchPageCount(keyword,searchType,minPrice,maxPrice,goodId)/pageSize;
        if(pageCount==0){pageCount=1;}
        commoditySearchResult.setPageCount(pageCount);
        return commoditySearchResult;
    }

}
