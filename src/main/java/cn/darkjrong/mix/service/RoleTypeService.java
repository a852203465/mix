package cn.darkjrong.mix.service;

import cn.darkjrong.mix.common.pojo.dto.RoleTypeDTO;
import cn.darkjrong.mix.common.pojo.dto.RoleTypeFilterDTO;
import cn.darkjrong.mix.common.pojo.entity.RoleType;
import cn.darkjrong.mix.common.pojo.vo.RoleTypeVO;
import cn.darkjrong.mp.service.BaseService;
import cn.darkjrong.pager.vo.PageVO;

import java.util.List;

/**
 * <p>
 * 角色类型信息 服务类
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
public interface RoleTypeService extends BaseService<RoleType, RoleTypeVO, RoleType> {

    /**
     *  查询角色类型信息, 不含指定的条件信息
     * @param roleTypes 角色类型集合
     * @return  Page<RoleType> 角色类型信息
     */
    List<RoleType> findRoleTypesByRoleNot(List<String> roleTypes);

    /**
     * 添加角色类型
     *
     * @param roleTypeDTO 角色类型DTO
     */
    void saveRoleType(RoleTypeDTO roleTypeDTO);

    /**
     * 更新角色类型
     *
     * @param roleTypeDTO 角色类型DTO
     */
    void updateRoleType(RoleTypeDTO roleTypeDTO);

    /**
     * 删除角色类型
     *
     * @param id id
     */
    void deleteRoleType(Long id);

    /**
     * 查询角色类型
     *
     * @param filterDTO 过滤器DTO
     * @return {@link PageVO}<{@link RoleTypeVO}>
     */
    PageVO<RoleTypeVO> queryRoleType(RoleTypeFilterDTO filterDTO);






















}
