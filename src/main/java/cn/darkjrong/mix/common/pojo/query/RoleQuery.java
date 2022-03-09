package cn.darkjrong.mix.common.pojo.query;

import cn.darkjrong.mix.common.pojo.dto.RoleFilterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * 查询角色
 *
 * @author Rong.Jia
 * @date 2022/02/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleQuery extends RoleFilterDTO implements Serializable {

    private static final long serialVersionUID = 6442048545327321738L;

    /**
     * 不含的角色
     */
    private Set<String> notRoles;

}
