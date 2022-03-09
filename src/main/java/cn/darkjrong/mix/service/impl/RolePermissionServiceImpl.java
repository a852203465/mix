package cn.darkjrong.mix.service.impl;

import cn.darkjrong.core.lang.constants.NumberConstant;
import cn.darkjrong.mix.common.pojo.entity.RolePermission;
import cn.darkjrong.mix.mapper.RolePermissionMapper;
import cn.darkjrong.mix.service.RolePermissionService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * <p>
 * 角色权限关联信息 服务实现类
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@Slf4j
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public int countPermissionByIdAndRoleId(Long permissionId, Long roleId) {
        return rolePermissionMapper.countPermissionByIdAndRoleId(permissionId, roleId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insertNew(Set<Long> permissionIds, Long roleId) {
        if (CollectionUtil.isNotEmpty(permissionIds)) {
            Optional.of(createRolePermissions(permissionIds, roleId)).ifPresent(this::saveBatch);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void modify(Set<Long> permissionIds, Long roleId) {
        if (CollectionUtil.isNotEmpty(permissionIds)) {
            Optional.ofNullable(this.findRolePermissionsByRoleId(roleId)).ifPresent(a -> a.forEach(this::removeById));
            Optional.of(createRolePermissions(permissionIds, roleId)).ifPresent(this::saveBatch);
        }
    }

    @Override
    public List<RolePermission> findRolePermissionsByRoleId(Long roleId) {

        return rolePermissionMapper.findRolePermissionsByRoleId(roleId);
    }

    /**
     * 创建角色权限
     *
     * @param permissionIds ids允许
     * @param roleId        角色id
     * @return {@link List}<{@link RolePermission}>
     */
    private List<RolePermission> createRolePermissions(Set<Long> permissionIds, Long roleId) {

        List<RolePermission> rolePermissions = new ArrayList<>();

        for (Long permissionId : permissionIds) {
            int permission = rolePermissionMapper.countPermissionByIdAndRoleId(permissionId, roleId);
            if (Validator.equal(NumberConstant.ZERO, permission)) {
                RolePermission rolePermission = new RolePermission(permissionId, roleId);
                rolePermissions.add(rolePermission);
            }
        }

        return rolePermissions;
    }


}
