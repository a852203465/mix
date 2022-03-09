package cn.darkjrong.mix.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 角色类型信息
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@TableName("sys_role_type")
public class RoleType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 信息主键
     */
    private Long id;

    /**
     * 角色类型名
     */
    private String name;

    /**
     * 角色前缀
     */
    private String role;

    /**
     * 系统标识(1->供应链内部端,2->供应链外部端)
     */
    private Integer systemMark;

    /**
     * 添加人
     */
    private String createdUser;

    /**
     * 添加时间
     */
    private Long createdTime;

    /**
     * 修改人
     */
    private String updatedUser;

    /**
     * 修改时间
     */
    private Long updatedTime;

    /**
     * 描述
     */
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public Integer getSystemMark() {
        return systemMark;
    }

    public void setSystemMark(Integer systemMark) {
        this.systemMark = systemMark;
    }
    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }
    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoleType{" +
            "id=" + id +
            ", name=" + name +
            ", role=" + role +
            ", systemMark=" + systemMark +
            ", createdUser=" + createdUser +
            ", createdTime=" + createdTime +
            ", updatedUser=" + updatedUser +
            ", updatedTime=" + updatedTime +
            ", description=" + description +
        "}";
    }
}
