package cn.darkjrong.mix.common.security;

import cn.darkjrong.core.lang.constants.AuthConstant;
import cn.darkjrong.mix.common.config.AuthConfig;
import cn.darkjrong.mix.common.enums.ResponseEnum;
import cn.darkjrong.mix.common.exceptions.MixWebException;
import cn.hutool.core.codec.Base64;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *  JWT工具类
 * @author Rong.Jia
 * @date 2019/04/17 11:15:49
 */
@Slf4j
@Component
public class JwtUtils {

    @Autowired
    private AuthConfig authConfig;

    /**
     *  校验token是否正确
     * @param token Token
     * @return boolean 是否正确
     * @author Rong.Jia
     * @date 2019/04/17 11:15:49
     */
    public boolean verify(String token) {

        try {
            // 帐号加JWT私钥解密
            String secret = getClaim(token, AuthConstant.ACCOUNT) + Base64.decodeStr(authConfig.getEncryptJWTKey());

            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();

            DecodedJWT jwt = verifier.verify(token);

            return true;

        } catch (JWTVerificationException e) {
            log.error("verify {}", e.getMessage());
            return false;
        }
    }

    /**
     *  获得Token中的信息无需secret解密也能获得
     * @param token token
     * @param claim
     * @author Wang926454
     * @author Rong.Jia
     * @date 2019/04/17 11:15:49
     * @return String
     */
    public String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);

            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();

        } catch (JWTDecodeException e) {
            log.error("getClaim {}", e.getMessage());
            throw new MixWebException(ResponseEnum.UNAUTHORIZED);
        }
    }

    /**
     *  生成签名
     * @param account 帐号
     * @author Rong.Jia
     * @date 2019/04/17 11:15:49
     * @return java.lang.String 返回加密的Token
     */
    public String sign(String account, String currentTimeMillis) {

        try {

            // 帐号加JWT私钥加密
            String secret = account + Base64.decodeStr(authConfig.getEncryptJWTKey());

            // 此处过期时间是以毫秒为单位，所以乘以1000
            Date date = new Date(System.currentTimeMillis() + authConfig.getAccessTokenExpireTime() * 1000);

            Algorithm algorithm = Algorithm.HMAC256(secret);

            // 附带account帐号信息
            return JWT.create()
                    .withClaim(AuthConstant.ACCOUNT, account)
                    .withClaim(AuthConstant.CURRENT_TIME_MILLIS, currentTimeMillis)
                    .withExpiresAt(date)
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            log.error("sign {}", e.getMessage());
            throw new MixWebException(ResponseEnum.UNAUTHORIZED);
        }
    }

    /**
     *  获取TOKEN失效时间
     * @param token TOKEN信息
     * @return 失效时间
     */
    public Date getExpirationToken(String token) {

        DecodedJWT jwt = JWT.decode(token);

        return jwt.getExpiresAt();

    }

    /**
     *   校验TOKEN是否过期
     * @param token TOKEN信息
     * @return true/fasle 是否过期
     */
    public Boolean isTokenExpired(String token) {

        Date expiration = getExpirationToken(token);

        // 报异常
        return expiration.before(new Date());

    }


}
