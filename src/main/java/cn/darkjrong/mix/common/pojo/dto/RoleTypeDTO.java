package cn.darkjrong.mix.common.pojo.dto;

import cn.darkjrong.mix.common.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 角色类型信息参数
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@ApiModel("角色类型信息参数")
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleTypeDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色类型名
     */
    @NotBlank(message = "角色类型名 不能为空")
    @ApiModelProperty(value = "角色类型名", required = true)
    private String name;

    /**
     * 系统标识(1->供应链内部端,2->供应链外部端)
     */
    @ApiModelProperty("系统标识(1->供应链内部端,2->供应链外部端)")
    private Integer systemMark;

    /**
     * 权限菜单主键ID集合
     */
    @NotNull(message = "权限菜单主键ID集合 不能为空")
    @ApiModelProperty(value = "权限菜单主键ID集合", required = true)
    private Set<Long> permissionIds;





}