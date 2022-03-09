package cn.darkjrong.mix.controller;

import cn.darkjrong.mix.common.pojo.dto.UserLoginDTO;
import cn.darkjrong.mix.common.pojo.vo.ResponseVO;
import cn.darkjrong.mix.common.pojo.vo.UserInfoVO;
import cn.darkjrong.mix.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录/登出管理 Controller层
 *
 * @author Rong.Jia
 * @date 2019/04/17 16:01
 */
@Api(value = "认证管理", tags = "认证模块")
@Slf4j
@Validated
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "登录")
    @PostMapping(value = "basicLogin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<UserInfoVO> basicLogin(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response) {

        log.info("basicLogin {}", userLoginDTO.toString());
        return ResponseVO.success(loginService.login(userLoginDTO, request, response));
    }

    @ApiOperation("退出登录")
    @PostMapping(value = "logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO logout(HttpServletRequest request, HttpServletResponse response) {

        log.debug("logout {}", System.currentTimeMillis());

        loginService.logout(request, response);

        return ResponseVO.success();

    }




}
