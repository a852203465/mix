package cn.darkjrong.mix.service.impl;

import cn.darkjrong.mix.common.enums.ResponseEnum;
import cn.darkjrong.mix.common.pojo.entity.RoleTypePermission;
import cn.darkjrong.mix.mapper.RoleTypePermissionMapper;
import cn.darkjrong.mix.service.RoleTypePermissionService;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色类型-权限关联信息 服务实现类
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@Slf4j
@Service
public class RoleTypePermissionServiceImpl extends ServiceImpl<RoleTypePermissionMapper, RoleTypePermission> implements RoleTypePermissionService {

    @Autowired
    private RoleTypePermissionMapper roleTypePermissionMapper;

    @Override
    public List<RoleTypePermission> findRoleTypePermissionByRoleTypeId(Long roleTypeId) {
        Assert.notNull(roleTypeId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        return roleTypePermissionMapper.findRoleTypePermissionByRoleTypeId(roleTypeId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void syncRoleTypePermission(Long roleTypeId, Set<Long> permissionIds) {

        List<RoleTypePermission> roleTypePermissionList = permissionIds.stream().map(a -> {
            RoleTypePermission roleTypePermission = new RoleTypePermission();
            roleTypePermission.setPermissionId(a);
            roleTypePermission.setRoleTypeId(roleTypeId);
            return roleTypePermission;
        }).collect(Collectors.toList());

        Optional.ofNullable(this.findRoleTypePermissionByRoleTypeId(roleTypeId))
                .ifPresent(a -> this.removeByIds(a.stream().map(RoleTypePermission::getId).collect(Collectors.toSet())));
        this.saveOrUpdateBatch(roleTypePermissionList);
    }

    @Override
    public List<RoleTypePermission> queryRoleTypePermissionByPermissionId(Long permissionId) {
        Assert.notNull(permissionId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        return roleTypePermissionMapper.queryRoleTypePermissionByPermissionId(permissionId);
    }


}
