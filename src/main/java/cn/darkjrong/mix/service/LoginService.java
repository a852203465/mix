package cn.darkjrong.mix.service;

import cn.darkjrong.mix.common.pojo.dto.UserLoginDTO;
import cn.darkjrong.mix.common.pojo.vo.UserInfoVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录业务层接口
 *
 * @author Rong.Jia
 * @date 2022/02/09
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param userLoginDTO 登录信息
     * @param request request
     * @param response response
     * @return UserPermVO 用户信息
     */
    UserInfoVO login(UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response);

    /**
     * 登出
     *
     * @param request request
     * @param response response
     */
    void logout(HttpServletRequest request, HttpServletResponse response);



}

