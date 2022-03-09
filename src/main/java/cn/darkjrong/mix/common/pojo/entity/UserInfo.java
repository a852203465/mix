package cn.darkjrong.mix.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 性别  男：0，女：1
     */
    private Integer sex;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 账号状态, (0->已过期，1->启用，-1->禁用 )
     */
    private Integer status;

    /**
     * 有效期(-1:永久)
     */
    private Long validity;

    /**
     * 数据来源类型(1->系统添加, 2->ldap同步,默认是1)
     */
    private Integer sourceType;

    /**
     * 是否分配, (1->已分配，0->未分配)
     */
    private Integer allocated;

    /**
     * 系统标识(1->供应链内部端,2->供应链外部端)
     */
    private Integer systemMark;

    /**
     * 创建人
     */
    private String createdUser;

    /**
     * 创建时间
     */
    private Long createdTime;

    /**
     * 更新人
     */
    private String updatedUser;

    /**
     * 更新时间
     */
    private Long updatedTime;


}
