package cn.darkjrong.mix.service;

import cn.darkjrong.mix.common.pojo.entity.RoleTypePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色类型-权限关联信息 服务类
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
public interface RoleTypePermissionService extends IService<RoleTypePermission> {

    /**
     * 根据角色类型ID查询 角色类型-权限信息
     *
     * @param roleTypeId 角色类型id
     * @return {@link List}<{@link RoleTypePermission}>
     */
    List<RoleTypePermission> findRoleTypePermissionByRoleTypeId(Long roleTypeId);

    /**
     * 同步角色类型，权限ID
     *
     * @param roleTypeId    角色类型id
     * @param permissionIds 权限ID
     */
    void syncRoleTypePermission(Long roleTypeId, Set<Long> permissionIds);

    /**
     * 查询角色类型许可根据权限菜单id
     *
     * @param permissionId 权限id
     * @return {@link List}<{@link RoleTypePermission}>
     */
    List<RoleTypePermission> queryRoleTypePermissionByPermissionId(Long permissionId);



}
