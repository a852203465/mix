package cn.darkjrong.mix.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 角色信息
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标识
     */
    private String mark;

    /**
     * 状态(0->禁用,1->启用)
     */
    private Integer status;

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

    /**
     * 角色描述
     */
    private String description;


}
