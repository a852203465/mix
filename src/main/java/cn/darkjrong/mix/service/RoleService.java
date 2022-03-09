package cn.darkjrong.mix.service;

import cn.darkjrong.mix.common.pojo.dto.RoleFilterDTO;
import cn.darkjrong.mix.common.pojo.entity.Role;
import cn.darkjrong.mix.common.pojo.vo.RoleVO;
import cn.darkjrong.mp.service.BaseService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色信息 服务类
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
public interface RoleService extends BaseService<Role, RoleVO, Role> {

    /**
     *  查询某用户名称 的对应的角色。
     * @param account 用户名称
     * @return Role 获取的角色信息
     */
    List<Role> queryRoleByAccount(String account);

    /**
     *  根据权限id 查询拥有某权限的角色
     * @param permissionId 权限id
     * @return List<Role> 获取的角色信息
     */
    List<Role> findRolesByPermissionId(Long permissionId);

    /**
     *  根据角色标识查询角色信息
     * @param mark 角色标识
     * @return Role 角色信息
     */
    Role findRoleByMark(String mark);

    /**
     * 查询角色
     *
     * @param filterDTO 过滤器DTO
     * @return {@link List}<{@link Role}>
     */
    List<Role> queryRoles(RoleFilterDTO filterDTO);

    /**
     *  根据角色名获取角色信息
     * @param name 角色名
     * @return Role 角色信息
     */
    Role findRoleByName(String name);

    /**
     *  修改角色状态
     * @param id 角色主键
     * @param status 状态
     */
    void modifyStatusById(Long id, Integer status);

    /**
     *  根据id 删除角色信息
     * @param roleId 角色id
     */
    void deleteById(Long roleId);

    /**
     * 查询角色
     *
     * @param ids id
     * @return {@link List}<{@link RoleVO}>
     */
    List<RoleVO> queryRole(Set<Long>ids);












}
