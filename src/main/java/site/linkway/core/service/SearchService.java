package site.linkway.core.service;


import site.linkway.core.entity.bo.PostSearch;
import site.linkway.core.entity.vo.CommoditySearchResult;

public interface SearchService {
    /**
     * 商品搜索
     * @param postSearch 搜索条件
     * @return 商品搜索结果
     */
    CommoditySearchResult commoditySearch(PostSearch postSearch);
}
