package site.linkway.core.mapper;

import site.linkway.core.entity.po.Admin;

//table name:admin  entity:po.Admin
public interface AdminMapper {
    int insert(Admin admin);
    int update(Admin admin);
    int delete(Admin admin);
    Admin select(Admin admin);
}
