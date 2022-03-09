package cn.darkjrong.mix.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 *  角色 权限数据 dto对象
 * @author Rong.Jia
 * @date 2019/04/17 14:50
 */
@Data
@ApiModel("角色 权限数据 参数对照对象")
public class RolePermissionAuthDTO implements Serializable {

    private static final long serialVersionUID = 1013134901136312507L;

    /**
     *  角色主键
     */
    @ApiModelProperty(value = "角色主键 ", required = true)
    @NotNull(message = "角色id 不能为空")
    private Long roleId;

    /**
     *  权限资源id集合
     */
    @ApiModelProperty(value = "权限资源id集合", required = true)
    @NotNull(message = "权限资源id集合 不能为空")
    private Set<Long> permissionIds;



}
