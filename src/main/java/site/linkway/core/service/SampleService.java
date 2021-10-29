package site.linkway.core.service;

import site.linkway.core.entity.po.SamplePo;

import java.util.List;

public interface SampleService {
    List<SamplePo> selectAll();
    SamplePo selectById(SamplePo good);
    long add(SamplePo good);
    long updateById(SamplePo good);
    long deleteById(SamplePo good);
}
