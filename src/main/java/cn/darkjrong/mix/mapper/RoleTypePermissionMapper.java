package cn.darkjrong.mix.mapper;

import cn.darkjrong.mix.common.pojo.entity.RoleTypePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色类型-权限关联信息 Mapper 接口
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
public interface RoleTypePermissionMapper extends BaseMapper<RoleTypePermission> {

    /**
     * 根据角色类型ID查询 角色类型-权限信息
     *
     * @param roleTypeId 角色类型id
     * @return {@link List}<{@link RoleTypePermission}>
     */
    List<RoleTypePermission> findRoleTypePermissionByRoleTypeId(@Param("roleTypeId") Long roleTypeId);

    /**
     * 查询角色类型许可根据权限菜单id
     *
     * @param permissionId 权限id
     * @return {@link List}<{@link RoleTypePermission}>
     */
    List<RoleTypePermission> queryRoleTypePermissionByPermissionId(@Param("permissionId") Long permissionId);

}
