package cn.darkjrong.mix.service.impl;

import cn.darkjrong.core.lang.constants.AuthConstant;
import cn.darkjrong.core.lang.constants.NumberConstant;
import cn.darkjrong.mix.common.enums.ResponseEnum;
import cn.darkjrong.mix.common.pojo.dto.RoleTypeDTO;
import cn.darkjrong.mix.common.pojo.dto.RoleTypeFilterDTO;
import cn.darkjrong.mix.common.pojo.entity.RoleType;
import cn.darkjrong.mix.common.pojo.query.RoleTypeQuery;
import cn.darkjrong.mix.common.pojo.vo.RoleTypeVO;
import cn.darkjrong.mix.mapper.RoleTypeMapper;
import cn.darkjrong.mix.service.RoleTypePermissionService;
import cn.darkjrong.mix.service.RoleTypeService;
import cn.darkjrong.mp.service.impl.BaseServiceImpl;
import cn.darkjrong.mp.utils.PageableUtils;
import cn.darkjrong.mp.utils.PropertyUtils;
import cn.darkjrong.pager.vo.PageVO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色类型信息 服务实现类
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@Slf4j
@Service
public class RoleTypeServiceImpl extends BaseServiceImpl<RoleType, RoleTypeVO, RoleType, RoleTypeMapper> implements RoleTypeService {

    @Autowired
    private RoleTypeMapper roleTypeMapper;

    @Autowired
    private RoleTypePermissionService roleTypePermissionService;

    @Override
    public List<RoleType> findRoleTypesByRoleNot(List<String> roleTypes) {

        if (CollectionUtil.isNotEmpty(roleTypes)) {
            roleTypes = new ArrayList<>();
        }

        if (!roleTypes.contains(AuthConstant.ADMINISTRATOR)) {
            roleTypes.add(AuthConstant.ADMINISTRATOR);
        }

        return roleTypeMapper.findRoleTypesByRoleNot(roleTypes);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveRoleType(RoleTypeDTO roleTypeDTO) {

        RoleType roleType = roleTypeMapper.queryRoleTypeByName(roleTypeDTO.getName());
        Assert.isNull(roleType, ResponseEnum.THE_ROLE_TYPE_ALREADY_EXISTS.getMessage());
        roleType = new RoleType();

        BeanUtil.copyProperties(roleTypeDTO, roleType);
        roleType.setCreatedTime(DateUtil.current());
        roleType.setRole(IdUtil.getSnowflakeNextIdStr());

        this.save(roleType);
        roleTypePermissionService.syncRoleTypePermission(roleType.getId(), roleTypeDTO.getPermissionIds());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateRoleType(RoleTypeDTO roleTypeDTO) {

        RoleType roleType = this.getById(roleTypeDTO.getId());
        Assert.notNull(roleType, ResponseEnum.THE_ROLE_TYPE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());
        if (!StrUtil.equals(roleType.getName(), roleTypeDTO.getName())) {
            Assert.isNull(roleTypeMapper.queryRoleTypeByName(roleTypeDTO.getName()), ResponseEnum.THE_ROLE_TYPE_ALREADY_EXISTS.getMessage());
        }

        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(Boolean.TRUE);
        BeanUtil.copyProperties(roleTypeDTO, roleType, copyOptions);
        roleType.setUpdatedTime(DateUtil.current());

        this.updateById(roleType);
        roleTypePermissionService.syncRoleTypePermission(roleType.getId(), roleTypeDTO.getPermissionIds());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteRoleType(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        Assert.notNull(this.getById(id), ResponseEnum.THE_ROLE_TYPE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        this.removeById(id);
    }

    @Override
    public PageVO<RoleTypeVO> queryRoleType(RoleTypeFilterDTO filterDTO) {

        if (filterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(filterDTO);
        }

        RoleTypeQuery query = new RoleTypeQuery();
        BeanUtil.copyProperties(filterDTO, query);

        List<RoleType> roleTypeList = roleTypeMapper.queryRoleTypes(query);
        PageInfo<RoleType> pageInfo = new PageInfo<>(roleTypeList);
        return PropertyUtils.copyProperties(pageInfo, this.objectConversion(pageInfo.getList()));
    }


}
