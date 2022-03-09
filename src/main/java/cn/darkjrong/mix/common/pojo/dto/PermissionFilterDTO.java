package cn.darkjrong.mix.common.pojo.dto;

import cn.darkjrong.pager.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 权限信息过滤DTO
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("权限信息过滤参数")
public class PermissionFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String name;

    /**
     * 功能的级别('menu','button')
     */
    @ApiModelProperty(value = "功能的级别('menu','button')")
    private String resourceType;

    /**
     * 是否隐藏  1:是 0：否
     */
    @ApiModelProperty(value = "是否隐藏  1:是 0：否")
    private Integer hide;

    /**
     * 系统标识(1->供应链内部端,2->供应链外部端)
     */
    @ApiModelProperty("系统标识(1->供应链内部端,2->供应链外部端)")
    private Integer systemMark;


}
