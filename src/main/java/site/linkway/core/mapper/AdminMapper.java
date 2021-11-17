package site.linkway.core.mapper;

import site.linkway.core.entity.po.Admin;

//table name:admin  entity:po.Admin
public interface AdminMapper {
    public int insert(Admin admin);
    public int update(Admin admin);
    public int delete(Admin admin);
    public Admin select(Admin admin);
}
