package cn.darkjrong.mix.service;

import cn.darkjrong.mix.common.pojo.dto.UserInfoFilterDTO;
import cn.darkjrong.mix.common.pojo.entity.UserInfo;
import cn.darkjrong.mix.common.pojo.query.UserInfoQuery;
import cn.darkjrong.mix.common.pojo.vo.UserInfoVO;
import cn.darkjrong.mp.service.BaseService;
import cn.darkjrong.pager.dto.PageDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
public interface UserInfoService extends BaseService<UserInfo, UserInfoVO, UserInfo> {

    /**
     *  根据用户账号 获取该用户信息
     * @param account 用户 账号
     * @return UserInfo 用户信息
     */
    UserInfo findUserInfoByAccount(String account);

    /**
     *  根据用户id 修改密码
     * @param newPwd 密码
     * @param userId 用户id
     * @date 2019/04/18 14:33
     * @author Rong.Jia
     */
    void modifyPwdById(String newPwd, Long userId);

    /**
     *  条件查询不包含指定条件的用户信息
     * @param accountList 用户账号 集合
     * @param pageDTO 分页信息
     * @return  PageInfo<UserInfo> 用户信息
     */
    PageInfo<UserInfo> findUserInfosByAccountNot(List<String> accountList, PageDTO pageDTO);

    /**
     *  条件查询不包含指定条件的用户信息
     * @param accountList 用户账号 集合
     * @return  List<UserInfo> 用户信息
     */
    List<UserInfo> findUserInfosByAccountNot(List<String> accountList);

    /**
     *  根据用户id 修改状态
     * @param status 状态
     * @param userId 用户id
     */
    void modifyStatusById(Integer status, Long userId);

    /**
     *  根据用户id 修改有效期
     * @param validity 有效期
     * @param status 状态
     * @param userId 用户id
     */
    void modifyValidityById(Long userId, Long validity, Integer status);

    /**
     *  根据条件过滤查询用户信息
     * @param userInfoFilterDTO 过滤条件
     * @return  PageInfo<UserInfo> 用户信息分页集合
     */
    PageInfo<UserInfo> findUserInfos(UserInfoFilterDTO userInfoFilterDTO);

    /**
     *  根据条件过滤查询用户信息
     * @param userInfoFilterDTO 过滤条件
     * @return  List<UserInfo> 用户信息集合
     */
    List<UserInfo> findUserInfosByFilter(UserInfoFilterDTO userInfoFilterDTO);

    /**
     * 根据条件过滤查询用户信息
     * @param userInfoQuery 过滤条件
     * @return  List<UserInfo> 用户信息集合
     */
    List<UserInfo> findUserInfos(UserInfoQuery userInfoQuery);

    /**
     *  根据用户名模糊查询用户信息
     * @param name 用户名
     * @return  List<UserInfo> 用户信息集合
     */
    List<UserInfo> findUserInfosByNameLike(String name);

    /**
     * 修改用户分配状态
     * @param userId 用户id
     * @param allocated 分配状态
     * @param validity 有效期
     */
    void modifyAllocatedById(Long userId, Integer allocated, Long validity);

    /**
     *  根据主键id 删除用户信息
     * @param userId 用户信息主键
     */
    void deleteById(Long userId);

    /**
     * 查询用户信息根据角色id
     *
     * @param roleId 角色id
     * @return {@link List}<{@link UserInfo}>
     */
    List<UserInfo> queryUserInfoByRoleId(Long roleId);


























}
