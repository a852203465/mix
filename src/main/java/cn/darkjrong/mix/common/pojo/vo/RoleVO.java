package cn.darkjrong.mix.common.pojo.vo;

import cn.darkjrong.mix.common.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 角色信息
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@ApiModel("角色信息")
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String name;

    /**
     * 角色标识
     */
    @ApiModelProperty("角色标识")
    private String mark;

    /**
     * 状态(0->禁用,1->启用)
     */
    @ApiModelProperty("状态(0->禁用,1->启用)")
    private Integer status;

    /**
     * 系统标识(1->供应链内部端,2->供应链外部端)
     */
    @ApiModelProperty("系统标识(1->供应链内部端,2->供应链外部端)")
    private Integer systemMark;

    /**
     *  权限资源列表
     */
    @ApiModelProperty("权限资源列表")
    private List<PermissionVO> permissions;


}
