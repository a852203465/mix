package cn.darkjrong.mix.common.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import cn.darkjrong.mix.common.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

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
@ApiModel("权限信息")
public class PermissionVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    private String name;

    /**
     * 父权限ID
     */
    @ApiModelProperty("父权限ID")
    private Long parentId;

    /**
     * 权限字符串
     */
    @ApiModelProperty("权限字符串")
    private String permission;

    /**
     * 功能的级别('menu','button')
     */
    @ApiModelProperty("功能的级别('menu','button')")
    private String resourceType;

    /**
     * url地址
     */
    @ApiModelProperty("url地址")
    private String route;

    /**
     * 是否隐藏  1:是 0：否
     */
    @ApiModelProperty("是否隐藏  1:是 0：否")
    private Integer hide;

    /**
     * 请求方式
     */
    @ApiModelProperty("请求方式")
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

    /**
     * 子级
     */
    @ApiModelProperty("子级")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<PermissionVO> children;


}
