package cn.darkjrong.mix.service.impl;

import cn.darkjrong.core.lang.constants.AuthConstant;
import cn.darkjrong.mix.common.enums.ResponseEnum;
import cn.darkjrong.mix.common.pojo.dto.RoleFilterDTO;
import cn.darkjrong.mix.common.pojo.entity.Permission;
import cn.darkjrong.mix.common.pojo.entity.Role;
import cn.darkjrong.mix.common.pojo.entity.RolePermission;
import cn.darkjrong.mix.common.pojo.entity.UserInfo;
import cn.darkjrong.mix.common.pojo.query.RoleQuery;
import cn.darkjrong.mix.common.pojo.vo.RoleVO;
import cn.darkjrong.mix.mapper.RoleMapper;
import cn.darkjrong.mix.service.PermissionService;
import cn.darkjrong.mix.service.RolePermissionService;
import cn.darkjrong.mix.service.RoleService;
import cn.darkjrong.mix.service.UserInfoService;
import cn.darkjrong.mp.service.impl.BaseServiceImpl;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * <p>
 * 角色信息 服务实现类
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@Slf4j
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleVO, Role, RoleMapper> implements RoleService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public List<Role> queryRoleByAccount(String account){

        UserInfo userInfo = userInfoService.findUserInfoByAccount(account);
        Assert.notNull(userInfo, ResponseEnum.USER_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());
        return roleMapper.queryRoleByUserId(userInfo.getId());
    }

    @Override
    public List<Role> findRolesByPermissionId(Long permissionId){
        Assert.notNull(permissionId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        return roleMapper.findRolesByPermissionId(permissionId);
    }

    @Override
    public Role findRoleByMark(String role) {
        return roleMapper.findRoleByMark(role);
    }

    @Override
    public List<Role> queryRoles(RoleFilterDTO filterDTO) {
        RoleQuery query = new RoleQuery();
        BeanUtil.copyProperties(filterDTO, query);
        query.setNotRoles(CollectionUtil.newHashSet(AuthConstant.ADMINISTRATOR));
        return roleMapper.queryRoles(query);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleMapper.findRoleByName(name);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void modifyStatusById(Long id, Integer status) {
        roleMapper.modifyStatusById(id, status);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteById(Long roleId) {

        Assert.notNull(roleId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        List<RolePermission> rolePermissionList = rolePermissionService.findRolePermissionsByRoleId(roleId);
        Optional.ofNullable(rolePermissionList).ifPresent(a -> a.forEach(this::removeById));
        this.removeById(roleId);
    }

    @Override
    public List<RoleVO> queryRole(Set<Long> ids) {
        Assert.notEmpty(ids, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        return this.objectConversion(roleMapper.queryRoleByIds(ids));
    }

    @Override
    public RoleVO objectConversion(Role role) {
        RoleVO roleVO = super.objectConversion(role);
        if (ObjectUtil.isNotNull(roleVO)) {
            List<Permission> permissionList = permissionService.findPermissionByRoleId(role.getId());
            if (CollectionUtil.isNotEmpty(permissionList)) {
                roleVO.setPermissions(permissionService.objectConversion(permissionList));
            }
        }

        return roleVO;
    }




}
