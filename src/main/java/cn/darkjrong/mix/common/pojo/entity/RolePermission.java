package cn.darkjrong.mix.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 角色权限关联信息
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色编号
     */
    private Long roleId;

    /**
     * 权限编号
     */
    private Long permissionId;

    public RolePermission(Long permissionId, Long roleId) {
        this.permissionId = permissionId;
        this.roleId = roleId;
    }


}

