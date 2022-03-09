package cn.darkjrong.mix.mapper;

import cn.darkjrong.mix.common.pojo.entity.Role;
import cn.darkjrong.mix.common.pojo.query.RoleQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色信息 Mapper 接口
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询角色信息
     *
     * @param userId 用户id
     * @return {@link List}<{@link Role}>
     */
    List<Role> queryRoleByUserId(@Param("userId") Long userId);

    /**
     *  获取拥有指定权限的角色
     * @param permissionId 权限id
     * @return 角色信息集合
     */
    List<Role> findRolesByPermissionId(@Param("permissionId") Long permissionId);

    /**
     *  根据角色标识查询角色信息
     * @param mark 角色标识
     * @return 角色信息
     */
    Role findRoleByMark(@Param("mark") String mark);

    /**
     *  根据角色名查询角色信息
     * @param name 角色名
     * @return 角色信息
     */
    Role findRoleByName(@Param("name") String name);

    /**
     *  根据角色主键修改角色状态
     * @param id 角色主键
     * @param status  角色状态
     * @return ：失败。1：成功
     */
    int modifyStatusById(@Param("id") Long id, @Param("status")  Integer status);

    /**
     * 查询角色根据id
     *
     * @param ids id
     * @return {@link List}<{@link Role}>
     */
    List<Role> queryRoleByIds(@Param("ids") Set<Long> ids);

    /**
     * 查询角色
     *
     * @param query 查询
     * @return {@link List}<{@link Role}>
     */
    List<Role> queryRoles(RoleQuery query);






}
