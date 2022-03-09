package cn.darkjrong.mix.mapper;

import cn.darkjrong.mix.common.pojo.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色权限关联信息 Mapper 接口
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     *  查询某角色是否拥有指定权限
     * @param permissionId 权限id
     * @param roleId 角色id
     * @return int 0：未授权， 1：已授权
     */
    int countPermissionByIdAndRoleId(@Param("permissionId") Long permissionId,@Param("roleId") Long roleId);

    /**
     *  查询某角色拥有的权限
     * @param roleId 角色id
     * @return List<RolePermission> 关联信息集合
     */
    List<RolePermission> findRolePermissionsByRoleId(@Param("roleId") Long roleId);


}
