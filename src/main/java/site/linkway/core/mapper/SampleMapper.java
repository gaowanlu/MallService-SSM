package site.linkway.core.mapper;

import site.linkway.core.entity.po.SamplePo;

import  java.util.*;

public interface SampleMapper {
    List<SamplePo> selectAll();
    SamplePo selectById(SamplePo good);
    long add(SamplePo good);
    long updateById(SamplePo good);
    long deleteById(SamplePo good);
}
