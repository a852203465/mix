package cn.darkjrong.mix.mapper;

import cn.darkjrong.mix.common.pojo.entity.Permission;
import cn.darkjrong.mix.common.pojo.query.PermissionQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 获取角色对应的权限资源集合
     *
     * @param roleId 角色id
     * @return 权限资源集合
     */
    List<Permission> findPermissionsByRoleId(@Param("roleId") Long roleId);

    /**
     * 获取账号对应的权限资源集合
     *
     * @param account 账号
     * @return 权限资源集合
     */
    List<Permission> findPermissionsByAccount(@Param("account") String account);

    /**
     * 根据权限资源名获取权限资源
     *
     * @param name 权限资源名
     * @return 权限资源
     * @author Rong.Jia
     */
    Permission findPermissionByName(@Param("name") String name);

    /**
     * 根据资源id集合 查询资源信息
     *
     * @param ids 资源id集合
     * @return 权限资源集合
     */
    List<Permission> findPermissions(@Param("ids") List<Long> ids);

    /**
     * 查询权限
     *
     * @param query 查询
     * @return {@link List}<{@link Permission}>
     */
    List<Permission> queryPermissions(PermissionQuery query);

    /**
     * 查询权限根据父id
     *
     * @param parentId 父id
     * @return {@link List}<{@link Permission}>
     */
    List<Permission> queryPermissionsByParentId(@Param("parentId") Long parentId);



}
