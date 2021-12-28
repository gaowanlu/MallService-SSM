package site.linkway.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.linkway.core.entity.bo.PostSearch;
import site.linkway.core.service.SearchService;

/*搜索
* */
@RequestMapping("/api/search")
@Controller
public class Search {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    SearchService searchService;

    /**
     * 商品搜索
     * @param postSearch 请求体
     * @return CommoditySearchResult
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/commodity",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String searchCommodity(@RequestBody PostSearch postSearch)
            throws JsonProcessingException {
        return mapper.writeValueAsString(searchService.commoditySearch(postSearch));
    }
}
