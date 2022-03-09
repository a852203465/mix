package cn.darkjrong.mix.service;

import cn.darkjrong.mix.common.pojo.entity.Permission;
import cn.darkjrong.mix.common.pojo.query.PermissionQuery;
import cn.darkjrong.mix.common.pojo.vo.PermissionVO;
import cn.darkjrong.mp.service.BaseService;
import cn.darkjrong.pager.dto.PageDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
public interface PermissionService extends BaseService<Permission, PermissionVO, Permission> {

    /**
     *  根据角色id查询某角色拥有的权限
     * @param roleId 角色id
     * @author Rong.Jia
     * @date 2018/8/8 13:54
     * @return List<Permission> 获取的权限信息
     */
    List<Permission> findPermissionByRoleId(Long roleId);

    /**
     *  查询某用户拥有的权限。
     * @param account 用户账号
     * @author Rong.Jia
     * @date 2018/8/8 13:54
     * @return List<Permission> 获取的权限信息
     */
    List<Permission> findPermissionsByAccount(String account);

    /**
     *  根据权限名获取权限信息
     * @param permissionName 权限名
     * @author Rong.Jia
     * @date 2019/04/17 16:04
     * @return Permission 获取的权限信息
     */
    Permission findPermissionByName(String permissionName);

    /**
     *  查询权限资源信息
     * @param pageDTO  分页条件
     * @date 2019/13/06 08:47；22
     * @author Rong.Jia
     * @return List<Permission> 资源信息分页集合
     */
    PageInfo<Permission> findAll(PageDTO pageDTO);

    /**
     *  根据权限资源主键id 集合 查询权限信息
     * @param ids  主键id 集合
     * @date 2019/13/06 08:47；22
     * @author Rong.Jia
     * @return List<Permission> 资源信息集合
     */
    List<Permission> findPermissions(List<Long> ids);

    /**
     * 查询权限
     *
     * @param query 查询
     * @return {@link List}<{@link Permission}>
     */
    List<Permission> queryPermissions(PermissionQuery query);

    /**
     * 查询权限根据父id
     *
     * @param parentId 父id
     * @return {@link List}<{@link Permission}>
     */
    List<Permission> queryPermissionsByParentId(Long parentId);


}
