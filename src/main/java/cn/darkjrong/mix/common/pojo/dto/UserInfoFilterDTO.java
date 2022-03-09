package cn.darkjrong.mix.common.pojo.dto;

import cn.darkjrong.pager.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 用户信息过滤DTO
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户信息过滤参数")
public class UserInfoFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String account;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 性别  男：0，女：1
     */
    @ApiModelProperty(value = " 性别  男：0，女：1")
    private Integer sex;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phone;

    /**
     * 系统标识(1->供应链内部端,2->供应链外部端)
     */
    @ApiModelProperty("系统标识(1->供应链内部端,2->供应链外部端)")
    private Integer systemMark;


}
