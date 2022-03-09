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
 * 用户
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户信息参数")
public class UserInfoDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @NotBlank(message = "账号 不能为空")
    @ApiModelProperty(value = "账号", required = true)
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码(修改时非必填)")
    private String password;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名 不能为空")
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String mail;

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

    /**
     *  原角色主键
     */
    @ApiModelProperty(value = "原角色主键, 可与‘newRoleId’相同", required = true)
    @NotNull(message = "原角色主键 不能为空")
    private Long oldRoleId;

    /**
     *  新角色主键
     */
    @ApiModelProperty(value = "新角色主键", required = true)
    @NotNull(message = "新角色主键 不能为空")
    private Long newRoleId;



}
