package cn.darkjrong.mix.service.impl;

import cn.darkjrong.core.lang.constants.AuthConstant;
import cn.darkjrong.core.lang.constants.NumberConstant;
import cn.darkjrong.mix.common.config.AuthConfig;
import cn.darkjrong.mix.common.enums.ResponseEnum;
import cn.darkjrong.mix.common.exceptions.MixWebException;
import cn.darkjrong.mix.common.pojo.dto.UserLoginDTO;
import cn.darkjrong.mix.common.pojo.vo.UserInfoVO;
import cn.darkjrong.mix.common.security.JwtUtils;
import cn.darkjrong.mix.service.AuthService;
import cn.darkjrong.mix.service.LoginService;
import cn.darkjrong.redis.RedisUtils;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 登录业务层接口实现类
 *
 * @author Rong.Jia
 * @date 2022/02/09
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthConfig authConfig;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserInfoVO login(UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response) {

        // 账号
        String account = userLoginDTO.getAccount();
        UserInfoVO userInfoVO = authService.findUserByAccount(account);
        checkUser(userInfoVO);

        //  登录次数限制
        retryLimit(account, userLoginDTO.getPassword(), userInfoVO.getPassword());

        generateToken(response, account);
        userInfoVO.setPassword(null);
        return userInfoVO;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader(AuthConstant.AUTHORIZATION_HEADER);

        if (redisUtils.hasKey(AuthConstant.PREFIX_SHIRO_REFRESH_TOKEN + token)) {
            redisUtils.delete(AuthConstant.PREFIX_SHIRO_REFRESH_TOKEN + token);
        } else {
            throw new MixWebException(ResponseEnum.ACCOUNT_AUTOMATIC_LOGOUT);
        }

        response.setHeader(AuthConstant.AUTHORIZATION_HEADER, null);
    }

    /**
     *  token 产生并出入response 中
     * @param response
     * @param account 登录账号
     * @date 2019/07/26 13:55:33
     * @author Rong.Jia
     */
    private void generateToken(HttpServletResponse response, String account) {

        // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());

        // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
        String token = jwtUtils.sign(account, currentTimeMillis);

        // 清除可能存在的Shiro权限信息缓存
        if (redisUtils.hasKey(AuthConstant.PREFIX_SHIRO_CACHE + token)) {
            redisUtils.delete(AuthConstant.PREFIX_SHIRO_CACHE + token);
        }

        redisUtils.setEx(AuthConstant.PREFIX_SHIRO_REFRESH_TOKEN + token, currentTimeMillis,
                Convert.toLong(authConfig.getRefreshTokenExpireTime()), TimeUnit.SECONDS);

        response.setHeader(AuthConstant.AUTHORIZATION_HEADER, token);
        response.setHeader("Access-Control-Expose-Headers", AuthConstant.AUTHORIZATION_HEADER);

        //response.setHeader(AuthConstant.X_CA_TIMESTAMP,  String.valueOf(jwtUtils.getExpirationToken(token).getTime()));
        //response.setHeader("Access-Control-Expose-Headers", AuthConstant.X_CA_TIMESTAMP);
    }

    /**
     * 校验用户
     *
     * @param userInfoVO 用户信息
     */
    private void checkUser(UserInfoVO userInfoVO) {

        if (ObjectUtil.isNull(userInfoVO)) {
            log.error("checkUser {} ", "The account does not exist");
            throw new MixWebException(ResponseEnum.THE_ACCOUNT_DOES_NOT_EXIST_PLEASE_CHANGE_THE_ACCOUNT_TO_LOGIN);
        } else {

            if (StrUtil.isBlank(userInfoVO.getAccount())) {
                log.error("checkUser {} ", "The account does not exist");
                throw new MixWebException(ResponseEnum.THE_ACCOUNT_DOES_NOT_EXIST_PLEASE_CHANGE_THE_ACCOUNT_TO_LOGIN);
            }

            // 角色
            if (userInfoVO.getRoles().stream().allMatch(a -> Validator.equal(NumberConstant.ZERO, a.getStatus()))) {
                log.error("checkUser {}", "The account has been disabled");
                throw new MixWebException(ResponseEnum.ACCOUNT_LOGIN_IS_PROHIBITED);
            }

            if (!StrUtil.equals(AuthConstant.ADMINISTRATOR, userInfoVO.getAccount())) {

                // 账号未分配
                if (Validator.equal(NumberConstant.ZERO, userInfoVO.getAllocated())) {
                    log.error("checkUser {}", "Account not allocated");
                    throw new MixWebException(ResponseEnum.UNAUTHORIZED);
                }

                // 账号状态
                if (Validator.equal(userInfoVO.getStatus(), NumberConstant.A_NEGATIVE)) {
                    log.error("checkUser {}", "Account authorization expired");
                    throw new MixWebException(ResponseEnum.ACCOUNT_LOGIN_IS_PROHIBITED);
                } else if (Validator.equal(userInfoVO.getStatus(), NumberConstant.ZERO)) {
                    log.error("checkUser {}", "Account security is prohibited");
                    throw new MixWebException(ResponseEnum.ACCOUNT_AUTHORIZATION_EXPIRED);
                }
            }
        }
    }

    /**
     *  登录次数限制
     * @param account 登录账号
     * @param password 登录密码
     * @param correctPassword 账号正确密码
     * @date 2019/07/26 13:55:33
     * @author Rong.Jia
     */
    private void retryLimit(String account, String password, String correctPassword) {

        String shiroLoginCount = AuthConstant.PREFIX_SHIRO_LOGIN_COUNT + account;
        String shiroIsLock = AuthConstant.PREFIX_SHIRO_IS_LOCK + account;

        ValueOperations<String, Object> opsForValue = redisUtils.getRedisTemplate().opsForValue();

        if (!DigestUtil.bcryptCheck(password, correctPassword)) {

            log.error("Wrong account or password");

            //访问一次，计数一次
            if (Validator.equal(AuthConstant.USER_LOCK_UN.get(NumberConstant.ZERO), opsForValue.get(shiroIsLock))) {

                log.error("验证未通过,错误次数大于5次,账户已锁定  account：{}", account);
                throw new ExcessiveAttemptsException(ResponseEnum.USER_NAME_OR_PASSWORD_ERRORS_GREATER_THAN_5_TIMES.getMessage());

            }

            opsForValue.increment(shiroLoginCount, NumberConstant.ONE);

            //计数大于5时，设置用户被锁定一小时
            if(Convert.toInt(opsForValue.get(shiroLoginCount)) >= NumberConstant.FIVE){

                opsForValue.set(shiroIsLock, AuthConstant.USER_LOCK_UN.get(NumberConstant.ZERO));
                redisUtils.getRedisTemplate().expire(shiroIsLock, NumberConstant.TEN_POINT_ZERO.longValue(), TimeUnit.MINUTES);

            }

            throw new MixWebException(ResponseEnum.WRONG_ACCOUNT_OR_PASSWORD);

        }else {

            //清空登录计数
            opsForValue.set(shiroLoginCount, NumberConstant.ZERO);

            //设置未锁定状态
            opsForValue.set(shiroIsLock, AuthConstant.USER_LOCK_UN.get(NumberConstant.ONE));

        }
    }


}
