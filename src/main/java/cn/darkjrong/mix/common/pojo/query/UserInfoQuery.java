package cn.darkjrong.mix.common.pojo.query;

import cn.darkjrong.mix.common.pojo.dto.UserInfoFilterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Collection;

/**
 * 用户查询
 *
 * @author Rong.Jia
 * @date 2022/02/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoQuery extends UserInfoFilterDTO implements Serializable {

    private static final long serialVersionUID = -8137595114315611396L;

    /**
     * 账号
     */
    private String notAccount;

    /**
     * 添加人
     */
    private String createdUser;

    /**
     *  用户主键id集合
     */
    private Collection<Long> ids;


}
