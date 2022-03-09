package cn.darkjrong.mix.service.impl;

import cn.darkjrong.core.lang.constants.AuthConstant;
import cn.darkjrong.core.lang.constants.NumberConstant;
import cn.darkjrong.mix.common.enums.ResponseEnum;
import cn.darkjrong.mix.common.exceptions.MixWebException;
import cn.darkjrong.mix.common.pojo.dto.*;
import cn.darkjrong.mix.common.pojo.entity.*;
import cn.darkjrong.mix.common.pojo.query.PermissionQuery;
import cn.darkjrong.mix.common.pojo.query.UserInfoQuery;
import cn.darkjrong.mix.common.pojo.vo.PermissionVO;
import cn.darkjrong.mix.common.pojo.vo.RoleVO;
import cn.darkjrong.mix.common.pojo.vo.UserInfoVO;
import cn.darkjrong.mix.common.utils.AuthUtils;
import cn.darkjrong.mix.service.*;
import cn.darkjrong.mp.utils.PageableUtils;
import cn.darkjrong.mp.utils.PropertyUtils;
import cn.darkjrong.pager.vo.PageVO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 权限管理 service层 实现类
 *
 * @author Rong.Jia
 * @date 2019/04/17 14:08
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleTypePermissionService roleTypePermissionService;

    @Override
    public PageVO<UserInfoVO> findUserInfos(UserInfoFilterDTO userInfoFilterDTO) {

        if (userInfoFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(userInfoFilterDTO);
        }

        List<UserInfo> userInfos = userInfoService.findUserInfosByFilter(userInfoFilterDTO);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfos);
        return PropertyUtils.copyProperties(pageInfo, userInfoService.objectConversion(pageInfo.getList()));

//        // 登录用户
//        String currentUser = AuthUtils.getCurrentUser();
//
//        if (userInfoFilterDTO.getCurrentPage() < NumberConstant.ZERO) {
//
//            List<UserInfoVO> voList = null;
//
//            // 当前登录用户不是管理员
//            if (!StrUtil.equals(AuthConstant.ADMINISTRATOR, currentUser)) {
//                voList = CollUtil.newArrayList(differentiateUserInfos(currentUser, userInfoFilterDTO));
//            } else {
//                List<UserInfo> userInfoList = userInfoService.findUserInfosByFilter(userInfoFilterDTO);
//                voList = CollUtil.newArrayList(userInfoService.objectConversion(userInfoList));
//            }
//
//            Optional.of(voList).ifPresent(a -> pageVO.setTotal(a.size()));
//            pageVO.setRecords(voList);
//
//        } else if (!StrUtil.equals(AuthConstant.ADMINISTRATOR, currentUser)) {
//            List<UserInfoVO> userInfoVoList = differentiateUserInfos(currentUser, userInfoFilterDTO);
//            Optional.of(userInfoVoList).ifPresent(a -> pageVO.setTotal(a.size()));
//            pageVO.setRecords(userInfoVoList);
//
//        } else {
//            PageInfo<UserInfo> page = userInfoService.findUserInfos(userInfoFilterDTO);
//            PropertyUtils.copyProperties(page, pageVO, userInfoService.objectConversion(page.getList()));
//        }
//
//        return pageVO;
    }

    @Override
    public List<UserInfoVO> queryUnassignedUser() {
        List<UserInfo> userInfoList = userInfoService.findUserInfosByAccountNot(Collections.singletonList(AuthConstant.ADMINISTRATOR));
        return userInfoService.objectConversion(userInfoList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveUserInfo(UserInfoDTO userInfoDTO) {

        Assert.notBlank(userInfoDTO.getPassword(), ResponseEnum.THE_PASSWORD_CANNOT_BE_EMPTY.getMessage());
        Assert.notNull(roleService.getById(userInfoDTO.getNewRoleId()), ResponseEnum.THE_ROLE_ASSOCIATED_WITH_THE_USER_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        UserInfo userInfo = userInfoService.findUserInfoByAccount(userInfoDTO.getAccount());
        Assert.isNull(userInfo, ResponseEnum.THE_USER_INFORMATION_ALREADY_EXISTS.getMessage());
        userInfo = new UserInfo();

        BeanUtil.copyProperties(userInfoDTO, userInfo);
        userInfo.setCreatedTime(DateUtil.current());
        userInfo.setStatus(NumberConstant.ONE);
        userInfo.setAllocated(NumberConstant.ZERO);

        userInfo.setPassword(DigestUtil.bcrypt(userInfoDTO.getPassword()));

        userInfoService.save(userInfo);
        userRoleService.insertNew(userInfo.getId(), userInfoDTO.getNewRoleId());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteUserInfoById(Long userId) {

        Assert.notNull(userId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        UserInfo userInfo = userInfoService.getById(userId);
        Assert.notNull(userInfo, ResponseEnum.USER_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        // 判断是否管理员
        if (AuthConstant.ADMINISTRATOR.equals(userInfo.getAccount())) {
            log.error("deleteUserInfoById()  System administrator cannot delete");
            throw new MixWebException(ResponseEnum.SYSTEM_ADMINISTRATOR_CANNOT_DELETE);
        }

        String currentUser = AuthUtils.getCurrentUser();

        //  解析token, 获取账号, 并获取该账号信息  判断删除用户是否是当前登录用户
        if (userId.equals(userInfoService.findUserInfoByAccount(currentUser).getId())) {
            log.error("deleteUserInfoById() The current logged-in user cannot be deleted");
            throw new MixWebException(ResponseEnum.THE_CURRENT_LOGIN_USER_CANNOT_BE_DELETED);
        }

        userInfoService.deleteById(userId);
    }

    @Override
    public UserInfoVO findUserByAccount(String userAccount) {
        Assert.notBlank(userAccount, ResponseEnum.THE_ACCOUNT_CANNOT_BE_EMPTY.getMessage());
        return userInfoService.objectConversion(userInfoService.findUserInfoByAccount(userAccount));
    }

    @Override
    public UserInfoVO findUserInfoByUserId(Long userId) {
        Assert.notNull(userId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        return userInfoService.objectConversion(userInfoService.getById(userId));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateUserInfo(UserInfoDTO userInfoDTO) {

        UserInfo userInfo = userInfoService.getById(userInfoDTO.getId());
        Assert.notNull(userInfo, ResponseEnum.USER_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());
        Assert.isFalse(ObjectUtil.isNull(roleService.getById(userInfoDTO.getOldRoleId())) || ObjectUtil.isNull(roleService.getById(userInfoDTO.getNewRoleId())),
                ResponseEnum.THE_ROLE_ASSOCIATED_WITH_THE_USER_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        if (!StrUtil.equals(userInfoDTO.getAccount(), userInfo.getAccount())) {
            log.error("updateUserInfo() Account cannot be changed");
            throw new MixWebException(ResponseEnum.THE_ACCOUNT_NAME_CANNOT_BE_CHANGED);
        }

        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(Boolean.TRUE);
        BeanUtil.copyProperties(userInfoDTO, userInfo, copyOptions);
        userInfo.setUpdatedTime(DateUtil.current());

        userInfoService.updateById(userInfo);
        if (!Validator.equal(userInfoDTO.getNewRoleId(), userInfoDTO.getOldRoleId())) {
            UserRole userRole = userRoleService.findUserRoleByUserIdAndRoleId(userInfo.getId(), userInfoDTO.getOldRoleId());
            userRoleService.updateUserRole(userRole.getId(), userInfo.getId(), userInfoDTO.getNewRoleId());
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void savePermission(PermissionDTO permissionDTO) {

        Permission permission = permissionService.findPermissionByName(permissionDTO.getName());
        Assert.isNull(permission, ResponseEnum.PERMISSION_MENU_ALREADY_EXISTS.getMessage());
        permission = new Permission();

        if (!Validator.equal(NumberConstant.ZERO, permissionDTO.getParentId())) {
            Assert.notNull(permissionService.getById(permissionDTO.getParentId()),
                    ResponseEnum.THE_PARENT_PERMISSION_MENU_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());
        }

        BeanUtil.copyProperties(permissionDTO, permission);
        permission.setCreatedTime(DateUtil.current());

        permissionService.save(permission);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updatePermission(PermissionDTO permissionDTO) {

        Permission permission = permissionService.getById(permissionDTO.getId());
        Assert.notNull(permission, ResponseEnum.THE_PERMISSION_MENU_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        if (!Validator.equal(NumberConstant.ZERO, permissionDTO.getParentId())) {
            Assert.notNull(permissionService.getById(permissionDTO.getParentId()),
                    ResponseEnum.THE_PARENT_PERMISSION_MENU_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());
        }

        if (!StrUtil.equals(permission.getName(), permissionDTO.getName())) {
            Assert.isNull(permissionService.findPermissionByName(permissionDTO.getName()), ResponseEnum.PERMISSION_MENU_ALREADY_EXISTS.getMessage());
        }

        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(Boolean.TRUE);
        BeanUtil.copyProperties(permissionDTO, permission, copyOptions);
        permission.setUpdatedTime(DateUtil.current());

        permissionService.updateById(permission);
    }

    @Override
    public PermissionVO findPermissionById(Long permissionId) {
        Assert.notNull(permissionId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        return permissionService.objectConversion(permissionService.getById(permissionId));
    }

    @Override
    public PermissionVO findPermissionByName(String permissionName) {
        Assert.notBlank(permissionName, ResponseEnum.THE_NAME_CANNOT_BE_EMPTY.getMessage());
        Permission permission = permissionService.findPermissionByName(permissionName);
        return permissionService.objectConversion(permission);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deletePermissionById(Long permissionId) {

        List<Role> roles = roleService.findRolesByPermissionId(permissionId);
        List<RoleTypePermission> roleTypePermissionList = roleTypePermissionService.queryRoleTypePermissionByPermissionId(permissionId);

        if (CollectionUtil.isNotEmpty(roles) || CollUtil.isNotEmpty(roleTypePermissionList)) {
            log.error("deletePermissionById() The permission menu has an associated role that cannot be deleted");
            throw new MixWebException(ResponseEnum.DATA_QUOTE);
        }

        permissionService.removeById(permissionId);
    }

    @Override
    public PageVO<PermissionVO> findPermissions(PermissionFilterDTO filterDTO) {

        if (filterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(filterDTO);
        }

        PermissionQuery query = new PermissionQuery();
        BeanUtil.copyProperties(filterDTO, query);

        List<Permission> permissions = permissionService.queryPermissions(query);
        PageInfo<Permission> pageInfo = new PageInfo<>(permissions);
        return PropertyUtils.copyProperties(pageInfo, permissionService.objectConversion(pageInfo.getList()));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveRole(RoleDTO roleDTO) {

        Role role = roleService.findRoleByName(roleDTO.getName());
        Assert.isNull(role, ResponseEnum.ROLE_INFORMATION_ALREADY_EXISTS.getMessage());
        role = new Role();
        BeanUtil.copyProperties(roleDTO, role);
        role.setStatus(NumberConstant.ONE);
        role.setMark(IdUtil.getSnowflakeNextIdStr() + DateUtil.current());
        role.setCreatedTime(DateUtil.current());

        roleService.save(role);
        rolePermissionService.insertNew(roleDTO.getPermissionIds(), role.getId());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateRole(RoleDTO roleDTO) {

        Role role = roleService.getById(roleDTO.getId());
        Assert.notNull(role, ResponseEnum.ROLE_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        if (!StrUtil.equals(role.getName(), roleDTO.getName())) {
            Assert.isNull(roleService.findRoleByName(roleDTO.getName()), ResponseEnum.ROLE_INFORMATION_ALREADY_EXISTS.getMessage());
        }

        Assert.isFalse(userRoleService.countUserInfoByRoleId(roleDTO.getId()) > NumberConstant.ZERO,
                ResponseEnum.IF_A_ROLE_HAS_ASSOCIATED_USERS_IT_CANNOT_BE_DELETED.getMessage());

        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(Boolean.TRUE);
        BeanUtil.copyProperties(roleDTO, role, copyOptions);
        role.setUpdatedTime(DateUtil.current());

        roleService.updateById(role);
        rolePermissionService.modify(roleDTO.getPermissionIds(), role.getId());
    }

    @Override
    public RoleVO findRoleById(Long roleId) {
        Assert.notNull(roleId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        return roleService.objectConversion(roleService.getById(roleId));
    }

    @Override
    public List<RoleVO> findRoleByUserInfoAccount(String account) {

        Assert.notBlank(account, ResponseEnum.THE_ACCOUNT_CANNOT_BE_EMPTY.getMessage());
        List<Role> roleList = roleService.queryRoleByAccount(account);
        return roleService.objectConversion(roleList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteRoleById(Long roleId) {

        Role sysRole = roleService.getById(roleId);
        Assert.notNull(sysRole, ResponseEnum.ROLE_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());
        Assert.isFalse(userRoleService.countUserInfoByRoleId(roleId) > NumberConstant.ZERO,
                ResponseEnum.IF_A_ROLE_HAS_ASSOCIATED_USERS_IT_CANNOT_BE_DELETED.getMessage());

        roleService.deleteById(roleId);
    }

    @Override
    public PageVO<RoleVO> findRoles(RoleFilterDTO filterDTO) {

        if (filterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(filterDTO);
        }

        List<Role> roleList = roleService.queryRoles(filterDTO);
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        return PropertyUtils.copyProperties(pageInfo, roleService.objectConversion(pageInfo.getList()));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void userRoleAuthorization(UserRoleAuthDTO userRoleAuthDTO) {

        UserInfo userInfo = userInfoService.getById(userRoleAuthDTO.getUserId());
        Role role = roleService.getById(userRoleAuthDTO.getRoleId());

        Assert.isFalse(userRoleService.countRoleByUserInfoId(role.getId(), userInfo.getId()) > NumberConstant.ZERO,
                ResponseEnum.THE_USER_HAS_AUTHORIZED_THE_ROLE.getMessage());

        userRoleService.insertNew(userInfo.getId(), role.getId());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void rolePermissionAuth(RolePermissionAuthDTO rolePermissionAuthDTO) {

        Long roleId = rolePermissionAuthDTO.getRoleId();
        Assert.notNull(roleService.getById(roleId), ResponseEnum.ROLE_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        List<RolePermission> rolePermissionList = rolePermissionService.findRolePermissionsByRoleId(roleId);
        Optional.ofNullable(rolePermissionList).ifPresent(a -> rolePermissionService.removeByIds(a.stream().map(RolePermission::getId).collect(Collectors.toSet())));
        rolePermissionService.insertNew(rolePermissionAuthDTO.getPermissionIds(), roleId);
    }

    @Override
    public void verifyPwd(PwdDTO pwdDTO) {

        UserInfo userInfo = userInfoService.findUserInfoByAccount(pwdDTO.getAccount());
        Assert.notNull(userInfo, ResponseEnum.USER_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        // 校验原密码
        Assert.isTrue(DigestUtil.bcryptCheck(pwdDTO.getOldPwd(), userInfo.getPassword()),
                ResponseEnum.THE_OLD_PASSWORD_IS_INCORRECT.getMessage());

        // 校验新密码与旧密码是否相同
        Assert.isFalse(DigestUtil.bcryptCheck(pwdDTO.getNewPwd(), userInfo.getPassword()),
                ResponseEnum.THE_NEW_PASSWORD_IS_THE_SAME_AS_THE_OLD_PASSWORD.getMessage());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void modifyPwd(PwdDTO pwdDTO) {
        verifyPwd(pwdDTO);
        UserInfo userInfo = userInfoService.findUserInfoByAccount(pwdDTO.getAccount());
        userInfoService.modifyPwdById(DigestUtil.bcrypt(pwdDTO.getNewPwd()), userInfo.getId());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void disableUserInfo(String account) {
        Assert.notBlank(account, ResponseEnum.THE_ACCOUNT_CANNOT_BE_EMPTY.getMessage());

        UserInfo userInfo = userInfoService.findUserInfoByAccount(account);
        Assert.notNull(userInfo, ResponseEnum.USER_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        // 判断是否管理员
        Assert.isFalse(StrUtil.equals(AuthConstant.ADMINISTRATOR, account), ResponseEnum.SYSTEM_ADMINISTRATOR_CANNOT_DISABLE.getMessage());

        // 判断禁用用户是否是当前登录用户
        Assert.isFalse(StrUtil.equals(AuthUtils.getCurrentUser(), userInfo.getAccount()), ResponseEnum.CURRENT_USER_CANNOT_DISABLE.getMessage());

        if (Validator.equal(NumberConstant.A_NEGATIVE, userInfo.getStatus())) {
            userInfoService.modifyStatusById(NumberConstant.ONE, userInfo.getId());
        } else if (Validator.equal(NumberConstant.ONE, userInfo.getStatus())) {
            userInfoService.modifyStatusById(NumberConstant.A_NEGATIVE, userInfo.getId());
        } else {
            log.error("disableUserInfo() Invalid specified state");
            throw new MixWebException(ResponseEnum.INVALID_SPECIFIED_STATE);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void extensionValidity(ExtensionValidityDTO extensionValidityDTO) {

        Assert.notBlank(extensionValidityDTO.getAccount(), ResponseEnum.THE_ACCOUNT_CANNOT_BE_EMPTY.getMessage());
        UserInfo userInfo = userInfoService.findUserInfoByAccount(extensionValidityDTO.getAccount());
        Assert.notNull(userInfo, ResponseEnum.USER_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        if (NumberUtil.compare(extensionValidityDTO.getTime(), DateUtil.current()) <= NumberConstant.ZERO) {
            log.error("extensionValidity() The extension time cannot be less than or equal to the current time");
            throw new MixWebException(ResponseEnum.THE_END_TIME_CANNOT_BE_LESS_THAN_OR_EQUAL_TO_THE_START_TIME);
        }

        userInfoService.modifyValidityById(userInfo.getId(), extensionValidityDTO.getTime(), NumberConstant.ONE);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void modifyStatusById(Integer status, Long userId) {
        Assert.notNull(userId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        userInfoService.modifyStatusById(status, userId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String resetPwd(String account) {

        UserInfo userInfo = userInfoService.findUserInfoByAccount(account);

        Assert.notNull(userInfo, ResponseEnum.USER_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());
        userInfoService.modifyPwdById(DigestUtil.bcrypt(AuthConstant.DEFAULT_PASSWORD), userInfo.getId());
        return AuthConstant.DEFAULT_PASSWORD;
    }

    @Override
    public List<UserInfoVO> findUserInfosByNameLike(String name) {
        Assert.notBlank(name, ResponseEnum.THE_NAME_CANNOT_BE_EMPTY.getMessage());
        return userInfoService.objectConversion(userInfoService.findUserInfosByNameLike(name));
    }

    @Override
    public void accountMonitoring() {

        List<UserInfo> userInfoList = userInfoService.list();

        if (CollectionUtil.isNotEmpty(userInfoList)) {
            for (UserInfo userInfo : userInfoList) {
                Long validity = userInfo.getValidity();
                if (Validator.equal(NumberConstant.ONE, userInfo.getStatus())
                        && Validator.equal(NumberConstant.ONE, userInfo.getAllocated())
                        && !Validator.equal(NumberConstant.A_NEGATIVE, validity)) {

                    Date startDate = DateUtil.parseDate(DateUtil.format(DateUtil.date(validity), DatePattern.NORM_DATE_PATTERN));
                    Date endDate = DateUtil.parseDate(DateUtil.format(DateUtil.date(DateUtil.current()), DatePattern.NORM_DATE_PATTERN));

                    int compare = DateUtil.compare(startDate, endDate);
                    if (compare <= NumberConstant.ZERO) {
                        this.modifyStatusById(NumberConstant.ZERO, userInfo.getId());
                    }
                }
            }
        }
    }

    @Override
    public void disableRole(Long roleId) {

        Assert.notNull(roleId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        Role role = roleService.getById(roleId);
        Assert.notNull(role, ResponseEnum.ROLE_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        // 判断是否管理员
        List<Role> administratorRoles = roleService.queryRoleByAccount(AuthConstant.ADMINISTRATOR);
        if (administratorRoles.stream().map(Role::getId).collect(Collectors.toSet()).contains(roleId)) {
            log.error("disableRole(0 The system administrator cannot disable it");
            throw new MixWebException(ResponseEnum.SYSTEM_ADMINISTRATOR_CANNOT_DISABLE);
        }

        //  判断禁用角色是否是当前登录用户的角色
        List<Role> currentRoles = roleService.queryRoleByAccount(AuthUtils.getCurrentUser());
        if (currentRoles.stream().map(Role::getId).collect(Collectors.toSet()).contains(roleId)) {
            log.error("disableRole() The current user cannot be disabled. The user is the currently logged in user");
            throw new MixWebException(ResponseEnum.CURRENT_USER_CANNOT_DISABLE);
        }

        if (Validator.equal(NumberConstant.A_NEGATIVE, role.getStatus())) {
            userInfoService.modifyStatusById(NumberConstant.ONE, role.getId());
        } else if (Validator.equal(NumberConstant.ONE, role.getStatus())) {
            userInfoService.modifyStatusById(NumberConstant.A_NEGATIVE, role.getId());
        } else {
            log.error("disableRole() Invalid specified state");
            throw new MixWebException(ResponseEnum.INVALID_SPECIFIED_STATE);
        }

    }

    @Override
    public List<PermissionVO> findPermissionsByRoleId(Long roleId) {

        Assert.notNull(roleId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        List<Permission> permissionList = permissionService.findPermissionByRoleId(roleId);
        return permissionService.objectConversion(permissionList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void modifyAllocatedById(Long userId, Integer allocated, Long validity) {

        Assert.notNull(userId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        UserInfo userInfo = userInfoService.getById(userId);
        Assert.notNull(userInfo, ResponseEnum.USER_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        userInfoService.modifyAllocatedById(userId, allocated, validity);
    }

    @Override
    public List<UserInfoVO> queryUserInfoByRoleId(Long roleId) {
        Assert.notNull(roleId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        return userInfoService.objectConversion(userInfoService.queryUserInfoByRoleId(roleId));
    }

    @Override
    public List<PermissionVO> queryPermissionTreeByParentId(Long parentId) {

        Assert.notNull(parentId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        List<PermissionVO> permissionList = CollectionUtil.newArrayList();

        if (Validator.equal(NumberConstant.ZERO, parentId)) {
            permissionList.addAll(permissionService.objectConversion(permissionService.list()));
        }else {
            List<PermissionVO> permissionVOList = permissionService.objectConversion(permissionService.queryPermissionsByParentId(parentId));
            while (CollectionUtil.isNotEmpty(permissionVOList)) {
                permissionList.addAll(permissionVOList);
                for (PermissionVO permissionVO : permissionVOList) {
                    permissionVOList = permissionService.objectConversion(permissionService.queryPermissionsByParentId(permissionVO.getId()));
                }
            }
        }
        return toTree(permissionList, parentId);
    }

    @Override
    public List<PermissionVO> queryPermissionTreeByRoleId(Long roleId) {
        Assert.notNull(roleId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        return toTree(this.findPermissionsByRoleId(roleId), NumberConstant.ZERO.longValue());
    }

    /**
     * 转树结构
     *
     * @param parentId      父id
     * @param permissionVOList 权限集合
     * @return {@link List}<{@link PermissionVO}>
     */
    private List<PermissionVO> toTree(List<PermissionVO> permissionVOList, Long parentId) {
        if (CollectionUtil.isNotEmpty(permissionVOList)) {
            List<PermissionVO> permissions = permissionVOList.stream().filter(a -> Validator.equal(a.getParentId(), parentId)).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(permissions)){
                for (PermissionVO companyVO : permissions) {
                    companyVO.setChildren(toTree(permissionVOList, companyVO.getId()));
                }
            }
            return permissions;
        }
        return Collections.emptyList();
    }

    /**
     * 区分用户信息
     *
     * @param currentUser       当前用户
     * @param userInfoFilterDTO 用户信息过滤器DTO
     * @return {@link List}<{@link UserInfoVO}>
     */
    private List<UserInfoVO> differentiateUserInfos(String currentUser, UserInfoFilterDTO userInfoFilterDTO) {

        UserInfoQuery userInfoQuery = new UserInfoQuery();
        BeanUtil.copyProperties(userInfoFilterDTO, userInfoQuery);

        List<UserInfoVO> voList = new ArrayList<>();
        UserInfo userInfo = userInfoService.findUserInfoByAccount(currentUser);
        voList.add(userInfoService.objectConversion(userInfo));

        return voList;
    }


}
