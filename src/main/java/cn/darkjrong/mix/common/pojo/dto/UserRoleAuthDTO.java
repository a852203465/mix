package cn.darkjrong.mix.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *  用户授权角色 dto 对象
 * @author Rong.Jia
 * @date 2019/04/17 14:50
 */
@Data
@ApiModel("用户授权角色 参数对照对象")
public class UserRoleAuthDTO implements Serializable {

    private static final long serialVersionUID = -7151835983773644794L;

    /**
     *  用户主键
     */
    @NotNull(message = "用户主键 不为空")
    @ApiModelProperty(value = "用户主键", required = true)
    private Long userId;

    /**
     * 角色主键
     */
    @NotNull(message = "角色主键 不为空")
    @ApiModelProperty(value = "角色id", required = true)
    private Long roleId;

}
