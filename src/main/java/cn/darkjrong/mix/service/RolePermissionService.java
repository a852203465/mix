package cn.darkjrong.mix.service;

import cn.darkjrong.mix.common.pojo.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色权限关联信息 服务类
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
public interface RolePermissionService extends IService<RolePermission> {

    /**
     *  查询某角色拥有的权限
     * @param permissionId 权限id
     * @param roleId 角色id
     * @return int 数量
     */
    int countPermissionByIdAndRoleId(Long permissionId, Long roleId);

    /**
     *  新增角色权限资源
     * @param permissionIds 权限资源集合
     * @param roleId 角色id
     */
    void insertNew(Set<Long> permissionIds, Long roleId);

    /**
     *  修改角色权限资源
     * @param permissionIds 权限资源集合
     * @param roleId 角色id
     */
    void modify(Set<Long> permissionIds, Long roleId);

    /**
     *   根据角色id 查询关联信息
     * @param roleId 角色id
     * @return List<RolePermissionKey> 关联信息集合
     */
    List<RolePermission> findRolePermissionsByRoleId(Long roleId);



}
