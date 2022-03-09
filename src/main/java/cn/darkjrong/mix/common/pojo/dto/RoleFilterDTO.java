package cn.darkjrong.mix.common.pojo.dto;

import cn.darkjrong.pager.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 角色信息过滤DTO
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@ApiModel("角色信息过滤参数")
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 状态(0->禁用,1->启用)
     */
    @ApiModelProperty(value = "状态(0->禁用,1->启用)")
    private Integer status;

    /**
     * 系统标识(1->供应链内部端,2->供应链外部端)
     */
    @ApiModelProperty("系统标识(1->供应链内部端,2->供应链外部端)")
    private Integer systemMark;


}
