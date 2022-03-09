package cn.darkjrong.mix.common.utils;

import cn.darkjrong.core.lang.constants.AuthConstant;
import cn.darkjrong.mix.common.enums.ResponseEnum;
import cn.darkjrong.mix.common.exceptions.MixWebException;
import cn.darkjrong.mix.common.security.JwtUtils;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 *  权限相关工具类
 * @author Rong.Jia
 * @date 2019/08/15 08:53
 */
@Slf4j
public class AuthUtils {

    private static final JwtUtils jwtUtils = SpringUtil.getBean(JwtUtils.class);

    /**
     *  获取当前登录用户
     * @date 2019/08/15 08:55:22
     * @author Rong.Jia
     * @return  String 登录用户
     */
    public static String getCurrentUser() {
        // 获取token，账号
        String token = ServletUtils.getHttpRequest().getHeader(AuthConstant.AUTHORIZATION_HEADER);

        if (StrUtil.isBlank(token)) {
            log.error("Not logged in");
            throw new MixWebException(ResponseEnum.NOT_LOGGED_IN);
        }

        return jwtUtils.getClaim(token, AuthConstant.ACCOUNT);
    }





}
