package cn.darkjrong.mix.common.security;

import cn.darkjrong.core.lang.constants.AuthConstant;
import cn.darkjrong.mix.common.enums.ResponseEnum;
import cn.darkjrong.mix.common.pojo.vo.PermissionVO;
import cn.darkjrong.mix.common.pojo.vo.RoleVO;
import cn.darkjrong.mix.common.pojo.vo.UserInfoVO;
import cn.darkjrong.mix.service.AuthService;
import cn.darkjrong.redis.RedisUtils;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义Realm
 * @author Rong.Jia
 * @date 2019/04/17 15:49
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AuthService authService;

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JwtToken;
    }

    /**
     *  认证信息.(身份验证) : Authentication 是用来验证用户身份
     * @author Rong.Jia
     * @date 2019/04/17 15:49
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        String account = jwtUtils.getClaim(principalCollection.toString(), AuthConstant.ACCOUNT);

        UserInfoVO userInfoVO = authService.findUserByAccount(account);

        List<RoleVO> roles = userInfoVO.getRoles();
        simpleAuthorizationInfo.addRoles(roles.stream().map(RoleVO::getMark).collect(Collectors.toList()));

        for (RoleVO role : roles) {
            List<PermissionVO> rolePermissions = role.getPermissions();
            for(PermissionVO permissionVO : rolePermissions){
                simpleAuthorizationInfo.addStringPermission(permissionVO.getPermission());
            }
        }

        return simpleAuthorizationInfo;
    }

    /**
     *  此方法调用 hasRole,hasPermission的时候才会进行回调.
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。 （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例， 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     *
     * @param authenticationToken
     * @author Rong.Jia
     * @date 2019/04/17 15:49
     * @return AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String token = (String) authenticationToken.getCredentials();

        // 解密获得account，用于和数据库进行对比
        String account = jwtUtils.getClaim(token, AuthConstant.ACCOUNT);

        // 帐号为空
        if (StrUtil.isBlank(account)) {
            log.error("Account is empty");
            throw new UnknownAccountException(ResponseEnum.UNAUTHORIZED.getMessage());
        }

        // 查询用户是否存在
        UserInfoVO userInfoVO = authService.findUserByAccount(account);

        if (ObjectUtil.isNull(userInfoVO)) {
            log.error("User does not exist");
            throw new AuthenticationException(ResponseEnum.THE_USER_DOES_NOT_EXIST_REPLACE_THE_USER.getMessage());
        }

        // 开始认证，要AccessToken认证通过，且Redis中存在RefreshToken，且两个Token时间戳一致
        if (jwtUtils.verify(token) && redisUtils.hasKey(AuthConstant.PREFIX_SHIRO_REFRESH_TOKEN + token)) {

            // 获取RefreshToken的时间戳
            String currentTimeMillisRedis = redisUtils.get(AuthConstant.PREFIX_SHIRO_REFRESH_TOKEN + token).toString();

            // 获取AccessToken时间戳，与RefreshToken的时间戳对比
            if (jwtUtils.getClaim(token, AuthConstant.CURRENT_TIME_MILLIS).equals(currentTimeMillisRedis)) {
                return new SimpleAuthenticationInfo(token, token, "shiroRealm");
            }
        }

        log.error("Authorization expires");

        throw new AuthenticationException(ResponseEnum.AUTHORIZATION_EXPIRES.getMessage());
    }


}
