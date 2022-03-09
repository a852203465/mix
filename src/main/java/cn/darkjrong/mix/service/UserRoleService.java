package cn.darkjrong.mix.service;

import cn.darkjrong.mix.common.pojo.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色关联信息 服务类
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 根据用户id 查询 关联信息
     *
     * @param userId 用户id
     * @return {@link List}<{@link UserRole}>
     */
    List<UserRole> findUserRoleByUserId(Long userId);

    /**
     * 查询用户角色根据用户id和角色id
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return {@link UserRole}
     */
    UserRole findUserRoleByUserIdAndRoleId(Long userId, Long roleId);

    /**
     *  根据角色id 获取 获取当前角色是否有关联用户
     * @param roleId 角色id
     * @return int 0：未关联， 1：已关联
     */
    int countUserInfoByRoleId(Long roleId);

    /**
     *  查询用户是否已经授权角色
     * @param userId 权限id
     * @param roleId 角色id
     * @return int 0：未授权， 1：已授权
     */
    int countRoleByUserInfoId(Long roleId, Long userId);

    /**
     *  新增用户--角色关联
     * @param userId 用户id
     * @param roleId 角色id
     */
    void insertNew(Long userId, Long roleId);

    /**
     * 更新用户角色
     *
     * @param id     id
     * @param userId 用户id
     * @param roleId 角色id
     */
    void updateUserRole(Long id, Long userId, Long roleId);



}
