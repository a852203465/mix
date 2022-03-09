package cn.darkjrong.mix.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 白名单配置 无需认证接口
 * @date 2021/05/19 16:45
 * @author Rong.Jia
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "dark.whitelist")
public class WhiteListConfig {

    private List<String> urls;

}
