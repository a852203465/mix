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
 * 用户
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户信息")
public class UserInfoVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String mail;

    /**
     * 性别  男：0，女：1
     */
    @ApiModelProperty(" 性别  男：0，女：1")
    private Integer sex;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phone;

    /**
     * 账号状态, (0->已过期，1->启用，-1->禁用 )
     */
    @ApiModelProperty("账号状态, (0->已过期，1->启用，-1->禁用 )")
    private Integer status;

    /**
     * 有效期(-1:永久)
     */
    @ApiModelProperty("有效期(-1:永久)")
    private Long validity;

    /**
     * 数据来源类型(1->系统添加, 2->ldap同步,默认是1)
     */
    @ApiModelProperty("数据来源类型(1->系统添加, 2->ldap同步,默认是1)")
    private Integer sourceType;

    /**
     * 是否分配, (1->已分配，0->未分配)
     */
    @ApiModelProperty("是否分配, (1->已分配，0->未分配)")
    private Integer allocated;

    /**
     * 系统标识(1->供应链内部端,2->供应链外部端)
     */
    @ApiModelProperty("系统标识(1->供应链内部端,2->供应链外部端)")
    private Integer systemMark;

    /**
     * 用户 -- 角色 ： 1对多
     * 立即从数据库中进行加载数据;
     */
    @ApiModelProperty("角色")
    private List<RoleVO> roles;



}
