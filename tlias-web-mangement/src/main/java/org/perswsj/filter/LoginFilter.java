package org.perswsj.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.perswsj.utils.JwtUtil;

import java.io.IOException;

@Slf4j
//@Component
//@WebFilter(urlPatterns = "/*") // 拦截所有请求
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // 获取请求路径
        String requestURI = httpServletRequest.getRequestURI();
        // 检查是否是登录请求
        if (requestURI.equals("/login")) {
            // 放行
            log.info("登录请求，放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 获取Token
        String token = httpServletRequest.getHeader("token");
        // 检查Token是否为空
        if (token == null || token.isEmpty()) {
            // 返回 401 状态码
            log.info("用户未登录，返回 401 状态码");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getWriter().write("用户未登录，请先登录！");
            return;
        }
        // 解析Token
        try {
            JwtUtil.parseToken(token);
        } catch (Exception e) {
            // 返回 401 状态码
            log.info("令牌非法，返回 401 状态码");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getWriter().write("令牌非法，请重新登录！");
            return;
        }
        // 放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
