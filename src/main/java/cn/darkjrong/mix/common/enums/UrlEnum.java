package cn.darkjrong.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * url 枚举
 *
 * @author Rong.Jia
 * @date 2022/02/09
 */
@Getter
@AllArgsConstructor
public enum UrlEnum {

    // Oauth2
    OAUTH_TOKEN("/oauth/token"),





    ;

    private final String value;





}
