package cn.darkjrong.mix.common.security;

import cn.darkjrong.core.lang.constants.NumberConstant;
import cn.darkjrong.mix.common.config.WhiteListConfig;
import cn.hutool.core.map.MapUtil;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  Shiro配置
 * @author Rong.Jia
 * @date 2019/04/17 10:18:22
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private WhiteListConfig whiteListConfig;

    /**
     *  配置使用自定义Realm，关闭Shiro自带的session
     * @author Rong.Jia
     * @date 2019/04/17 10:18:22
     * @return org.apache.security.web.mgt.DefaultWebSecurityManager
     */
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean("securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(ShiroRealm shiroRealm,
                                                               SessionManager sessionManager,
                                                               CustomCacheManager customCacheManager) {

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // 使用自定义Realm
        defaultWebSecurityManager.setRealm(shiroRealm);

        // 关闭Shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();

        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultWebSecurityManager.setSubjectDAO(subjectDAO);

        defaultWebSecurityManager.setSessionManager(sessionManager);

        // 设置自定义Cache缓存
        defaultWebSecurityManager.setCacheManager(customCacheManager);

        return defaultWebSecurityManager;

    }

    @Bean
    public CustomCacheManager customCacheManager () {
        return new CustomCacheManager();
    }

    @Bean(name = "sessionManager")
    public DefaultSessionManager sessionManager() {
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
        return sessionManager;
    }

    /**
     * 添加自己的过滤器，自定义url规则
     * Shiro自带拦截器配置规则
     * rest：比如/admins/user/**=rest[user],根据请求的方法，相当于/admins/user/**=perms[user：method] ,其中method为post，get，delete等
     * port：比如/admins/user/**=port[8081],当请求的url的端口不是8081是跳转到schemal：//serverName：8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString是你访问的url里的？后面的参数
     * perms：比如/admins/user/**=perms[user：add：*],perms参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，比如/admins/user/**=perms["user：add：*,user：modify：*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法
     * roles：比如/admins/user/**=roles[admin],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，比如/admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。//要实现or的效果看http://zgzty.blog.163.com/blog/static/83831226201302983358670/
     * anon：比如/admins/**=anon 没有参数，表示可以匿名使用
     * authc：比如/admins/user/**=authc表示需要认证才能使用，没有参数
     * authcBasic：比如/admins/user/**=authcBasic没有参数表示httpBasic认证
     * ssl：比如/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
     * user：比如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查
     * @param securityManager 权限管理对象
     * @author Rong.Jia
     * @date 2019/04/17 10:18:22
     * @return ShiroFilterFactoryBean security 拦截对象
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager, JwtFilter jwtFilter) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器取名为jwt
        Map<String, Filter> filterMap = MapUtil.newHashMap();

        filterMap.put("jwt", jwtFilter);

        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);

        // 自定义url规则使用LinkedHashMap有序Map
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>(NumberConstant.SIXTEEN);
        whiteListConfig.getUrls().forEach(a -> filterChainDefinitionMap.put(a, "anon"));

        // 所有请求通过我们自己的JWTFilter
        filterChainDefinitionMap.put("/**", "jwt");

        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

    /**
     * jwt 过滤器
     * @return {@link JwtFilter}
     */
    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    /**
     * 身份校验核心类
     * @return MyShiroRealm
     * @author Rong.Jia
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroRealm shiroRealm() {

        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCachingEnabled(true);

        return shiroRealm;
    }

    /**
     * Shiro生命周期处理器 ---可以自定的来调用配置在 Spring IOC 容器中 security bean 的生命周期方法.
     *
     * @return
     */
    @Bean("lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启shiro注解 ----启用 IOC 容器中使用 security 的注解. 但必须在配置了 LifecycleBeanPostProcessor
     * 之后才可以使用
     *
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);

        return advisor;
    }

}
