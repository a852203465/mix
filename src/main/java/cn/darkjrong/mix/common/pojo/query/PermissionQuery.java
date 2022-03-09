package cn.darkjrong.mix.common.pojo.query;

import cn.darkjrong.mix.common.pojo.dto.PermissionFilterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 权限查询
 *
 * @author Rong.Jia
 * @date 2022/02/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionQuery extends PermissionFilterDTO implements Serializable {

    private static final long serialVersionUID = 1L;



}
