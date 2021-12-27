package site.linkway.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.linkway.core.entity.po.Admin;

//table name:admin  entity:po.Admin
@Mapper
public interface AdminMapper {
    /**
     * 新增管理员
     * @param admin
     * @return 插入行
     */
    int insert(Admin admin);

    /**
     * 更新管理员信息
     * @param admin
     * @return 更新行
     */
    int update(Admin admin);

    /**
     * 删除管理员
     * @param admin
     * @return 删除行
     */
    int delete(Admin admin);

    /**
     * 根据adminId检索
     * @param admin
     * @return 管理员信息
     */
    Admin select(Admin admin);

    /**
     * 根据用户id检索管理员
     * @param userId
     * @return 管理员
     */
    Admin selectByUserId(String userId);
}
