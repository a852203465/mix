package cn.darkjrong.mix.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 账号延长有效性DTO
 *
 * @author Rong.Jia
 * @date 2022/03/02
 */
@Data
@ApiModel("延长账号使用时间参数")
public class ExtensionValidityDTO implements Serializable {

    private static final long serialVersionUID = -8750497244871824458L;

    /**
     * 账号
     */
    @NotBlank(message = "账号 不能为空")
    @ApiModelProperty(value = "账号", required = true)
    private String account;

    /**
     * 截止时间
     */
    @NotNull(message = "截止时间 不能为空")
    @ApiModelProperty(value = "截止时间", required = true)
    private Long time;



}
