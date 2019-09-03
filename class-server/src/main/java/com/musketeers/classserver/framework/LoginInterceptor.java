package com.musketeers.classserver.framework;

import com.musketeers.common.entity.system.vo.UserLoginCallBackVo;
import com.musketeers.common.enums.SysEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器 fanjiahao 20190414
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 在请求被处理之前调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查每个到来的请求对应的session域中是否有登录标识
        UserLoginCallBackVo userLoginCallBackVo = (UserLoginCallBackVo)request.getSession().getAttribute(SysEnum.USER_SESSION);
        if (null == userLoginCallBackVo || !(userLoginCallBackVo instanceof UserLoginCallBackVo)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"用户登录验证不正确");
            return false;
        }
        return true;
    }

    /**
     * 在请求被处理后，视图渲染之前调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束后调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}