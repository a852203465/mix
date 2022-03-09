package cn.darkjrong.mix.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 身份验证配置
 *
 * @author Rong.Jia
 * @date 2022/02/09
 */
@Data
@Component
@ConfigurationProperties(prefix = "dark.auth")
public class AuthConfig {

    private String encryptAESKey;

    private String encryptJWTKey;

    private Long accessTokenExpireTime;

    private Long refreshTokenExpireTime;

    private Long shiroCacheExpireTime;

}
