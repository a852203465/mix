package cn.darkjrong.mix.service.impl;

import cn.darkjrong.mix.common.enums.ResponseEnum;
import cn.darkjrong.mix.common.pojo.entity.Permission;
import cn.darkjrong.mix.common.pojo.query.PermissionQuery;
import cn.darkjrong.mix.common.pojo.vo.PermissionVO;
import cn.darkjrong.mix.mapper.PermissionMapper;
import cn.darkjrong.mix.service.PermissionService;
import cn.darkjrong.mp.service.impl.BaseServiceImpl;
import cn.darkjrong.mp.utils.PageableUtils;
import cn.darkjrong.pager.dto.PageDTO;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@Slf4j
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, PermissionVO, Permission, PermissionMapper> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findPermissionByRoleId(Long roleId){

        return permissionMapper.findPermissionsByRoleId(roleId);
    }

    @Override
    public List<Permission> findPermissionsByAccount(String account){
        return permissionMapper.findPermissionsByAccount(account);
    }

    @Override
    public Permission findPermissionByName(String permissionName) {
        Assert.notBlank(permissionName, ResponseEnum.THE_NAME_CANNOT_BE_EMPTY.getMessage());
        return permissionMapper.findPermissionByName(permissionName);
    }

    @Override
    public PageInfo<Permission> findAll(PageDTO pageDTO) {
        PageableUtils.basicPage(pageDTO);
        return new PageInfo<>(this.list());
    }

    @Override
    public List<Permission> findPermissions(List<Long> ids) {

        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }

        return permissionMapper.findPermissions(ids);
    }

    @Override
    public List<Permission> queryPermissions(PermissionQuery query) {
        return permissionMapper.queryPermissions(query);
    }

    @Override
    public List<Permission> queryPermissionsByParentId(Long parentId) {
        Assert.notNull(parentId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        return permissionMapper.queryPermissionsByParentId(parentId);
    }


}
