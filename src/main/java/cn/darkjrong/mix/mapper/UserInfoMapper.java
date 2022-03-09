package cn.darkjrong.mix.mapper;

import cn.darkjrong.mix.common.pojo.entity.UserInfo;
import cn.darkjrong.mix.common.pojo.query.UserInfoQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 根据账号查询信息
     *
     * @param account 账号
     * @return {@link UserInfo}
     */
    UserInfo findUserInfoByAccount(@Param("account") String account);

    /**
     *  根据主键修改密码
     * @param id 主键
     * @param password  密码
     * @return 0：失败，1：成功
     */
    int modifyPasswordById(@Param("id") Long id, @Param("password") String password);

    /**
     *  根据分配状态，账号集合查询用户信息
     * @param allocated 分配状态
     * @param accountList 账号集合
     * @return  用户信息
     */
    List<UserInfo> findUserInfosByAccountNot(@Param("allocated") Integer allocated, @Param("accountList") List<String> accountList);

    /**
     *  根据主键修改状态
     * @param userId 主键
     * @param status 状态
     * @return 0：失败，1：成功
     */
    int modifyStatusById(@Param("userId") Long userId, @Param("status") Integer status);

    /**
     *  根据主键修改状态，有效期
     * @param userId 主键
     * @param status 状态
     * @param validity 有效期
     * @return 0：失败，1：成功
     */
    int modifyValidityById(@Param("userId") Long userId, @Param("validity") Long validity,
                           @Param("status") Integer status);

    /**
     *  过滤查询用户信息
     * @param query 条件
     * @return  用户信息
     */
    List<UserInfo> findUserInfos(UserInfoQuery query);

    /**
     *  根据名称模糊查询
     * @param name 名称
     * @return  用户信息
     */
    List<UserInfo> findUserInfosByNameLike(@Param("name") String name);

    /**
     * 修改用户分配状态
     * @param userId 用户id
     * @param allocated 分配状态
     * @param validity 有效期
     * @return 0：未成功 1：已成功
     */
    int modifyAllocatedById(@Param("userId") Long userId, @Param("allocated") Integer allocated,
                            @Param("validity") Long validity);

    /**
     * 查询用户信息根据角色id
     *
     * @param roleId 角色id
     * @return {@link List}<{@link UserInfo}>
     */
    List<UserInfo> queryUserInfoByRoleId(@Param("roleId") Long roleId);
















}
