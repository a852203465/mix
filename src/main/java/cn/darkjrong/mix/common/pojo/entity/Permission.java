package cn.darkjrong.mix.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@TableName("sys_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 父权限ID
     */
    private Long parentId;

    /**
     * 权限字符串
     */
    private String permission;

    /**
     * 功能的级别('menu','button')
     */
    private String resourceType;

    /**
     * url地址
     */
    private String route;

    /**
     * 是否隐藏  1:是 0：否
     */
    private Integer hide;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 图标
     */
    private String icon;

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
     * 说明
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
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
    public Integer getHide() {
        return hide;
    }

    public void setHide(Integer hide) {
        this.hide = hide;
    }
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
        return "Permission{" +
            "id=" + id +
            ", name=" + name +
            ", parentId=" + parentId +
            ", permission=" + permission +
            ", resourceType=" + resourceType +
            ", route=" + route +
            ", hide=" + hide +
            ", method=" + method +
            ", icon=" + icon +
            ", systemMark=" + systemMark +
            ", createdUser=" + createdUser +
            ", createdTime=" + createdTime +
            ", updatedUser=" + updatedUser +
            ", updatedTime=" + updatedTime +
            ", description=" + description +
        "}";
    }
}
