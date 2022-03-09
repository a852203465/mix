package cn.darkjrong.mix.mapper;

import cn.darkjrong.mix.common.pojo.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色关联信息 Mapper 接口
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     *  查询用户是否已经授权角色
     * @param userId 权限id
     * @param roleId 角色id
     * @return int 0：未授权， 1：已授权
     */
    int countRoleByUserInfoId(@Param("roleId") Long roleId,@Param("userId") Long userId);

    /**
     *  根据角色id 获取 获取当前角色是否有关联用户
     * @param roleId 角色id
     * @return int 0：未关联， 1：已关联
     */
    int countUserInfoByRoleId(@Param("roleId")Long roleId);

    /**
     *  根据用户id 获取关联信息
     * @param userId 用户id
     * @return UserRole  关联信息
     */
    List<UserRole> findUserRoleKeyByUserId(@Param("userId")Long userId);

    /**
     * 更新用户角色
     *
     * @param id     id
     * @param userId 用户id
     * @param roleId 角色id
     */
    void updateUserRole(@Param("id") Long id,@Param("userId") Long userId,@Param("roleId") Long roleId);

    /**
     * 查询用户角色根据用户id和角色id
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return {@link UserRole}
     */
    UserRole findUserRoleByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);



}
