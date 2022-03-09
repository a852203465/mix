package cn.darkjrong.mix.mapper;

import cn.darkjrong.mix.common.pojo.entity.RoleType;
import cn.darkjrong.mix.common.pojo.query.RoleTypeQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色类型信息 Mapper 接口
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
public interface RoleTypeMapper extends BaseMapper<RoleType> {

    /**
     * 查询不含指定参数的角色类型信息
     *
     * @param roleList 角色类型名集合
     * @return {@link List}<{@link RoleType}>
     */
    List<RoleType> findRoleTypesByRoleNot(@Param("roleList") List<String> roleList);

    /**
     * 查询角色类型
     *
     * @param query 查询
     * @return {@link List}<{@link RoleType}>
     */
    List<RoleType> queryRoleTypes(RoleTypeQuery query);

    /**
     * 查询角色类型根据的名字
     *
     * @param name 的名字
     * @return {@link RoleType}
     */
    RoleType queryRoleTypeByName(@Param("name") String name);




}
