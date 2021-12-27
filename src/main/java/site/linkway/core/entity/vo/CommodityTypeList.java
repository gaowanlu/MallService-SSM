package site.linkway.core.entity.vo;

import lombok.Data;
import site.linkway.core.entity.po.GoodType;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommodityTypeList {
    List<GoodType> types=new ArrayList<>();//商品类型列表
}
