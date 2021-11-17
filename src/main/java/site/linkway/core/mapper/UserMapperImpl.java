package site.linkway.core.mapper;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import site.linkway.core.entity.po.User;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper{

    @Override
    public int insert(User user) {
        return getSqlSessionTemplate().getMapper(UserMapper.class).insert(user);
    }

    @Override
    public int update(User user) {
        return getSqlSessionTemplate().getMapper(UserMapper.class).update(user);
    }

    @Override
    public int delete(User user) {
        return getSqlSessionTemplate().getMapper(UserMapper.class).insert(user);
    }

    @Override
    public User select(User user) {
        return getSqlSessionTemplate().getMapper(UserMapper.class).select(user);
    }

    @Override
    public User selectByEmailAndPassword(User user) {
        return getSqlSessionTemplate().getMapper(UserMapper.class).selectByEmailAndPassword(user);
    }
}
