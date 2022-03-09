package cn.darkjrong.mix.common.pojo.vo;

import cn.darkjrong.mix.common.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 角色类型信息
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@ApiModel("角色类型信息")
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleTypeVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色类型名
     */
    @ApiModelProperty("角色类型名")
    private String name;

    /**
     * 角色前缀
     */
    @ApiModelProperty("角色前缀")
    private String role;

    /**
     * 系统标识(1->供应链内部端,2->供应链外部端)
     */
    @ApiModelProperty("系统标识(1->供应链内部端,2->供应链外部端)")
    private Integer systemMark;


}