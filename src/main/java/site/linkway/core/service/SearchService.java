package site.linkway.core.service;


import site.linkway.core.entity.bo.PostSearch;
import site.linkway.core.entity.vo.CommoditySearchResult;

public interface SearchService {
    CommoditySearchResult commoditySearch(PostSearch postSearch);
}
