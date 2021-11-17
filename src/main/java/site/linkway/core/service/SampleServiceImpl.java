package site.linkway.core.service;

import site.linkway.core.entity.po.SamplePo;
import site.linkway.core.mapper.SampleMapper;

import java.util.List;

public class SampleServiceImpl implements SampleService {
    private SampleMapper goodsMapper;

    public void setGoodsMapper(SampleMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Override
    public List<SamplePo> selectAll() {
        return goodsMapper.selectAll();
    }

    @Override
    public SamplePo selectById(SamplePo good) {
        return goodsMapper.selectById(good);
    }

    @Override
    public long add(SamplePo good) {
        return goodsMapper.add(good);
    }

    @Override
    public long updateById(SamplePo good) {
        return goodsMapper.updateById(good);
    }

    @Override
    public long deleteById(SamplePo good) {
        return goodsMapper.deleteById(good);
    }
}
