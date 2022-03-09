package cn.darkjrong.mix.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 角色类型-权限关联信息
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@TableName("sys_role_type_permission")
public class RoleTypePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 角色类型ID
     */
    private Long roleTypeId;

    /**
     * 权限ID
     */
    private Long permissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(Long roleTypeId) {
        this.roleTypeId = roleTypeId;
    }
    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "RoleTypePermission{" +
            "id=" + id +
            ", roleTypeId=" + roleTypeId +
            ", permissionId=" + permissionId +
        "}";
    }
}
