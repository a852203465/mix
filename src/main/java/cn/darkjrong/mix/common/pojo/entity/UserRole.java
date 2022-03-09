package cn.darkjrong.mix.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 用户角色关联信息
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_role")
public class UserRole implements Serializable {

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
     * 用户id
     */
    private Long userId;

    public UserRole(Long roleId, Long userId) {
        this.roleId = roleId;
        this.userId = userId;
    }
}
