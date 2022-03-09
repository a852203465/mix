package cn.darkjrong.mix.service.impl;

import cn.darkjrong.core.lang.constants.AuthConstant;
import cn.darkjrong.core.lang.constants.NumberConstant;
import cn.darkjrong.mix.common.enums.ResponseEnum;
import cn.darkjrong.mix.common.pojo.dto.UserInfoFilterDTO;
import cn.darkjrong.mix.common.pojo.entity.UserInfo;
import cn.darkjrong.mix.common.pojo.entity.UserRole;
import cn.darkjrong.mix.common.pojo.query.UserInfoQuery;
import cn.darkjrong.mix.common.pojo.vo.UserInfoVO;
import cn.darkjrong.mix.mapper.UserInfoMapper;
import cn.darkjrong.mix.service.RoleService;
import cn.darkjrong.mix.service.UserInfoService;
import cn.darkjrong.mix.service.UserRoleService;
import cn.darkjrong.mp.service.impl.BaseServiceImpl;
import cn.darkjrong.mp.utils.PageableUtils;
import cn.darkjrong.pager.dto.PageDTO;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo, UserInfoVO, UserInfo, UserInfoMapper> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserInfo findUserInfoByAccount(String account) {
        return userInfoMapper.findUserInfoByAccount(account);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void modifyPwdById(String newPwd, Long userId) {
        Assert.notNull(userId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        userInfoMapper.modifyPasswordById(userId, newPwd);
    }

    @Override
    public PageInfo<UserInfo> findUserInfosByAccountNot(List<String> accountList, PageDTO pageDTO) {
        PageableUtils.basicPage(pageDTO);
        List<UserInfo> userInfoList = this.findUserInfosByAccountNot(accountList);
        return new PageInfo<>(userInfoList);
    }

    @Override
    public List<UserInfo> findUserInfosByAccountNot(List<String> accountList) {
        return userInfoMapper.findUserInfosByAccountNot(NumberConstant.ZERO, accountList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void modifyStatusById(Integer status, Long userId) {
        Assert.notNull(userId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        userInfoMapper.modifyStatusById(userId, status);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void modifyValidityById(Long userId, Long validity, Integer status) {
        Assert.notNull(userId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        userInfoMapper.modifyValidityById(userId, validity, status);
    }

    @Override
    public PageInfo<UserInfo> findUserInfos(UserInfoFilterDTO userInfoFilterDTO) {
        PageableUtils.basicPage(userInfoFilterDTO);
        List<UserInfo> userInfoList = this.findUserInfosByFilter(userInfoFilterDTO);
        return new PageInfo<>(userInfoList);
    }

    @Override
    public List<UserInfo> findUserInfosByFilter(UserInfoFilterDTO userInfoFilterDTO) {

        UserInfoQuery userInfoQuery = new UserInfoQuery();
        BeanUtils.copyProperties(userInfoFilterDTO, userInfoQuery);
        userInfoQuery.setNotAccount(AuthConstant.ADMINISTRATOR);

        return userInfoMapper.findUserInfos(userInfoQuery);
    }

    @Override
    public List<UserInfo> findUserInfos(UserInfoQuery userInfoQuery) {

        if (StrUtil.isBlank(userInfoQuery.getAccount())) {
            userInfoQuery.setNotAccount(AuthConstant.ADMINISTRATOR);
        }

        return userInfoMapper.findUserInfos(userInfoQuery);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteById(Long userId) {
        Assert.notNull(userId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        List<UserRole> userRoles = userRoleService.findUserRoleByUserId(userId);
        Optional.ofNullable(userRoles).ifPresent(a -> a.forEach(b -> userRoleService.removeById(b)));
        this.removeById(userId);
    }

    @Override
    public List<UserInfo> queryUserInfoByRoleId(Long roleId) {
        return userInfoMapper.queryUserInfoByRoleId(roleId);
    }

    @Override
    public List<UserInfo> findUserInfosByNameLike(String name) {
        return userInfoMapper.findUserInfosByNameLike(name);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void modifyAllocatedById(Long userId, Integer allocated, Long validity) {
        Assert.notNull(userId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        userInfoMapper.modifyAllocatedById(userId, allocated, validity);
    }

    @Override
    public UserInfoVO objectConversion(UserInfo userInfo) {
        UserInfoVO userInfoVO = super.objectConversion(userInfo);
        if(ObjectUtil.isNotNull(userInfoVO)) {
            Optional.ofNullable(userRoleService.findUserRoleByUserId(userInfo.getId()))
                    .ifPresent(a -> userInfoVO.setRoles(roleService.queryRole(a.stream().map(UserRole::getRoleId).collect(Collectors.toSet()))));
        }

        return userInfoVO;
    }





}
