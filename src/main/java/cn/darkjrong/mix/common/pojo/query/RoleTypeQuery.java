package cn.darkjrong.mix.common.pojo.query;

import cn.darkjrong.mix.common.pojo.dto.RoleTypeFilterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色类型查询
 *
 * @author Rong.Jia
 * @date 2022/02/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleTypeQuery extends RoleTypeFilterDTO implements Serializable {
    private static final long serialVersionUID = -3853777026905333691L;
}
