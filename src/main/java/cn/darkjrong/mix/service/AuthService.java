package cn.darkjrong.mix.service;

import cn.darkjrong.mix.common.pojo.dto.*;
import cn.darkjrong.mix.common.pojo.vo.PermissionVO;
import cn.darkjrong.mix.common.pojo.vo.RoleVO;
import cn.darkjrong.mix.common.pojo.vo.UserInfoVO;
import cn.darkjrong.pager.vo.PageVO;

import java.util.List;

/**
 *  权限管理 service层
 * @author Rong.Jia
 * @date 2019/04/17 15:25
 */
public interface AuthService {

    /**
     *  获取用户信息 并分页
     * @param userInfoFilterDTO 过滤查询 分页条件
     * @return PageVO<UserInfoVO> 分页数据
     */
    PageVO<UserInfoVO> findUserInfos(UserInfoFilterDTO userInfoFilterDTO);

    /**
     *  获取未分配用户信息
     * @return List<UserInfoVO>  用户信息集合
     */
    List<UserInfoVO> queryUnassignedUser();

    /**
     *  添加用户信息
     * @param userInfoDTO 用户信息
     */
    void saveUserInfo(UserInfoDTO userInfoDTO);

    /**
     *  根据用户id 删除用户
     * @param userId 用户id
     */
    void deleteUserInfoById(Long userId);

    /**
     *  根据用户账号 获取用户信息
     * @param userAccount 用户账号
     *  @return  UserInfoVO 用户信息
     */
    UserInfoVO findUserByAccount(String userAccount);

    /**
     *  根据用户id 获取用户信息
     * @param userId 用户Id
     * @return  UserInfoVO 用户信息
     */
    UserInfoVO findUserInfoByUserId(Long userId);

    /**
     *  修改用户信息
     * @param userInfoDTO 用户信息
     */
    void updateUserInfo(UserInfoDTO userInfoDTO);

    /**
     *  添加权限
     * @param permissionDTO 权限信息
     */
    void savePermission(PermissionDTO permissionDTO);

    /**
     *  修改权限
     * @param permissionDTO 权限信息
     */
    void updatePermission(PermissionDTO permissionDTO);

    /**
     *  根据权限id获取权限
     * @param permissionId 权限id
     * @return  PermissionVO 权限信息
     */
    PermissionVO findPermissionById(Long permissionId);

    /**
     *  根据权限名获取权限
     * @param permissionName 权限名
     * @author Rong.Jia
     * @date 2019/04/17 15:25
     * @return PermissionVO 权限信息
     */
    PermissionVO findPermissionByName(String permissionName);

    /**
     *  根据权限id 删除权限
     * @param permissionsId 权限id
     */
    void deletePermissionById(Long permissionsId);

    /**
     *  根据分页条件查询权限信息
     * @param filterDTO 分页查询条件
     * @return PageVO<PermissionVO> 权限信息
     */
    PageVO<PermissionVO> findPermissions(PermissionFilterDTO filterDTO);

    /**
     *  添加角色信息
     * @param roleDTO 角色信息对象
     */
    void saveRole(RoleDTO roleDTO);

    /**
     *  修改角色信息
     * @param roleDTO 角色信息
     */
    void updateRole(RoleDTO roleDTO);

    /**
     *  根据角色Id 获取角色信息
     * @param roleId  角色id
     * @return RoleVO 角色信息
     */
    RoleVO findRoleById(Long roleId);

    /**
     *  查询某用户名称 的对应的角色。
     * @param account 用户名称
     * @return RoleVO 角色信息
     */
    List<RoleVO> findRoleByUserInfoAccount(String account);

    /**
     *  根据角色Id 删除角色
     * @param roleId  角色id
     */
    void deleteRoleById(Long roleId);

    /**
     *  角色用户查询
     * @param filterDTO 分页查询条件
     * @return PageVO<RoleVO> 角色信息
     */
    PageVO<RoleVO> findRoles(RoleFilterDTO filterDTO);

    /**
     *  用户授权角色
     * @param userRoleAuthDTO 用户授权对象
     */
    void userRoleAuthorization(UserRoleAuthDTO userRoleAuthDTO);

    /**
     *  角色授权权限
     * @param rolePermissionAuthDTO 角色授权对象
     */
    void rolePermissionAuth(RolePermissionAuthDTO rolePermissionAuthDTO);

    /**
     *  验证密码
     * @param pwdDTO 密码参数
     */
    void verifyPwd(PwdDTO pwdDTO);

    /**
     *  修改密码
     * @param pwdDTO 密码参数
     */
    void modifyPwd(PwdDTO pwdDTO);

    /**
     *  重置用户密码
     * @param account 用户账号
     * @return String 重置后密码
     */
    String resetPwd(String account);

    /**
     *  禁用/启用账号
     * @param account 账号
     */
    void disableUserInfo(String account);

    /**
     * 延长账号使用时间
     *
     * @param extensionValidityDTO 扩展有效性DTO
     */
    void extensionValidity(ExtensionValidityDTO extensionValidityDTO);

    /**
     *  根据用户id 修改状态
     * @param status 状态
     * @param userId 用户id
     */
    void modifyStatusById(Integer status, Long userId);

    /**
     *  根据姓名模糊查询用户信息
     * @param name 姓名
     * @return List<UserInfoVO> 用户信息集合
     */
    List<UserInfoVO> findUserInfosByNameLike(String name);

    /**
     *  监听账号过期状态
     */
    void accountMonitoring();

    /**
     *  禁用/启用角色
     * @param roleId 角色id
     */
    void disableRole(Long roleId);

    /**
     *  根据角色id查询某角色拥有的权限
     * @param roleId 角色id
     * @return List<PermissionVO> 获取的权限信息
     */
    List<PermissionVO> findPermissionsByRoleId(Long roleId);

    /**
     * 修改用户分配状态
     * @param userId 用户id
     * @param allocated 分配状态
     * @param validity 有效期
     */
    void modifyAllocatedById(Long userId, Integer allocated, Long validity);

    /**
     * 查询用户信息根据角色id
     *
     * @param roleId 角色id
     * @return {@link List}<{@link UserInfoVO}>
     */
    List<UserInfoVO> queryUserInfoByRoleId(Long roleId);

    /**
     * 查询权树根据父id
     *
     * @param parentId 父id
     * @return {@link List}<{@link PermissionVO}>
     */
    List<PermissionVO> queryPermissionTreeByParentId(Long parentId);

    /**
     * 查询权树根据角色ID
     *
     * @param roleId 角色ID
     * @return {@link List}<{@link PermissionVO}>
     */
    List<PermissionVO> queryPermissionTreeByRoleId(Long roleId);










}

