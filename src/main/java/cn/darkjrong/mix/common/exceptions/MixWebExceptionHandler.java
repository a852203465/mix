package cn.darkjrong.mix.common.exceptions;

import cn.darkjrong.mix.common.enums.ResponseEnum;
import cn.darkjrong.mix.common.pojo.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常控制处理器
 *
 * @author Rong.Jia
 * @date 2021/12/21
 */
@Slf4j
@SuppressWarnings("ALL")
@RestControllerAdvice
public class MixWebExceptionHandler {

    /**
     *  捕获自定义异常，并返回异常数据
     * @author Rong.Jia
     * @date 2019/4/3 8:46
     */
    @ExceptionHandler(value = MixWebException.class)
    public ResponseVO mixWebExceptionHandler(MixWebException e){

        log.error("mixWebExceptionHandler  {}", e.getMessage());

        return ResponseVO.error(e.getCode(), e.getMessage());

    }

    /**
     *  捕捉所有Shiro异常
     * @param e 异常
     * @date 2019/04/17 09:46:22
     * @return ResponseVO
     */
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public ResponseVO shiroHandle401(ShiroException e) {

        log.error("shiroHandle401 {}", e.getMessage());

        return ResponseVO.error(ResponseEnum.UNAUTHORIZED.getCode(), e.getMessage());

    }

    /**
     *  单独捕捉Shiro(AuthenticationException)异常
     * 该异常为访问身份验证异常
     * @param e 身份验证异常
     * @date 2019/04/17 09:46:22
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseVO authenticationHandle401(AuthenticationException e) {

        log.error("authenticationHandle401 {}", e.getMessage());

        return ResponseVO.error(ResponseEnum.AUTHENTICATION_FAILED);
    }

    /**
     *  单独捕捉Shiro(UnauthorizedException)异常
     * 该异常为访问有权限管控的请求而该用户没有所需权限所抛出的异常
     * @param e 未收授权异常
     * @date 2019/04/17 09:46:22
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ResponseVO unauthorizedHandle401(UnauthorizedException e) {

        log.error("unauthorizedHandle401 {}", e.getMessage());

        return ResponseVO.error(ResponseEnum.SUBJECT_UNAUTHORIZED);

    }

    /**
     *  单独捕捉Shiro(UnauthenticatedException)异常
     * 该异常为以游客身份访问有权限管控的请求无法对匿名主体进行授权，而授权失败所抛出的异常
     * @param e 未授权异常
     * @date 2019/04/17 09:46:22
     * @return ResponseVO 异常信息
     */
    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public ResponseVO unauthenticatedHandle401(UnauthenticatedException e) {

        log.error("unauthenticatedHandle401 {}", e.getMessage());

        return ResponseVO.error(ResponseEnum.ANONYMOUS_SUBJECT_UNAUTHORIZED);

    }




}
