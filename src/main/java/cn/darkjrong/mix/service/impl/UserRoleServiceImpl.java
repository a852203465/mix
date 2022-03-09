package cn.darkjrong.mix.service.impl;

import cn.darkjrong.mix.common.enums.ResponseEnum;
import cn.darkjrong.mix.common.pojo.entity.UserRole;
import cn.darkjrong.mix.mapper.UserRoleMapper;
import cn.darkjrong.mix.service.UserRoleService;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户角色关联信息 服务实现类
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@Slf4j
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> findUserRoleByUserId(Long userId) {
        Assert.notNull(userId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        return userRoleMapper.findUserRoleKeyByUserId(userId);
    }

    @Override
    public UserRole findUserRoleByUserIdAndRoleId(Long userId, Long roleId) {
        return userRoleMapper.findUserRoleByUserIdAndRoleId(userId, roleId);
    }

    @Override
    public int countUserInfoByRoleId(Long roleId) {
        return userRoleMapper.countUserInfoByRoleId(roleId);
    }

    @Override
    public int countRoleByUserInfoId(Long roleId, Long userId) {
        return userRoleMapper.countRoleByUserInfoId(roleId, userId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insertNew(Long userId, Long roleId) {
        userRoleMapper.insert(new UserRole(roleId, userId));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateUserRole(Long id, Long userId, Long roleId) {
        Assert.notNull(id, ResponseEnum.THE_NAME_CANNOT_BE_EMPTY.getMessage());
        userRoleMapper.updateUserRole(id, userId, roleId);
    }

}
