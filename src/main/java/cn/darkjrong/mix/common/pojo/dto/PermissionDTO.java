package cn.darkjrong.mix.common.pojo.dto;

import cn.darkjrong.mix.common.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("权限信息参数")
public class PermissionDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称 不能为空")
    @ApiModelProperty(value = "权限名称", required = true)
    private String name;

    /**
     * 父权限ID
     */
    @NotNull(message = "父权限ID 不能为空")
    @ApiModelProperty(value = "父权限ID, 顶级->0", required = true)
    private Long parentId;

    /**
     * 权限字符串
     */
    @NotBlank(message = "权限字符串 不能为空")
    @ApiModelProperty(value = "权限字符串", required = true)
    private String permission;

    /**
     * 功能的级别('menu','button')
     */
    @NotBlank(message = "功能的级别 不能为空")
    @ApiModelProperty(value = "功能的级别('menu','button')", required = true)
    private String resourceType;

    /**
     * url地址
     */
    @ApiModelProperty(value = "url地址")
    private String route;

    /**
     * 是否隐藏  1:是 0：否
     */
    @NotNull(message = "是否隐藏 不能为空")
    @ApiModelProperty(value = "是否隐藏  1:是 0：否", required = true)
    private Integer hide;

    /**
     * 请求方式
     */
    @NotBlank(message = "请求方式 不能为空")
    @ApiModelProperty(value = "请求方式", required = true)
    private String method;

    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String icon;

    /**
     * 系统标识(1->供应链内部端,2->供应链外部端)
     */
    @ApiModelProperty("系统标识(1->供应链内部端,2->供应链外部端)")
    private Integer systemMark;


}
