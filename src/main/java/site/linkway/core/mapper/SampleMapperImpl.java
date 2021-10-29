package site.linkway.core.mapper;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import site.linkway.core.entity.po.SamplePo;

import java.util.List;

public class SampleMapperImpl extends SqlSessionDaoSupport implements SampleMapper {
    @Override
    public List<SamplePo> selectAll() {
        SampleMapper goodsMapper=getSqlSessionTemplate().getMapper(SampleMapper.class);
        return goodsMapper.selectAll();
    }

    @Override
    public SamplePo selectById(SamplePo good) {
        SampleMapper goodsMapper=getSqlSessionTemplate().getMapper(SampleMapper.class);
        return goodsMapper.selectById(good);
    }

    @Override
    public long add(SamplePo good) {
        SampleMapper goodsMapper=getSqlSessionTemplate().getMapper(SampleMapper.class);
        return goodsMapper.add(good);
    }

    @Override
    public long updateById(SamplePo good) {
        SampleMapper goodsMapper=getSqlSessionTemplate().getMapper(SampleMapper.class);
        return goodsMapper.updateById(good);
    }

    @Override
    public long deleteById(SamplePo good)
    {
        SampleMapper goodsMapper=getSqlSessionTemplate().getMapper(SampleMapper.class);
        return goodsMapper.deleteById(good);
    }
}
